package com.geekbrains.java2.lesson8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String name;

    public static final long TIMEOUT = 120000;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        this.myServer = myServer;
        this.socket = socket;
        this.name = "";
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(()-> {
                try {
                    authenticate();
                    readMessages();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Client creation error");
        }
    }

    private void closeConnection() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        myServer.unsubscribe(this);
        myServer.broadcast("User " + name + " left", true);
    }

    private void readMessages() throws IOException {
        while (true) {
            if (dataInputStream.available()>0) {
                String message = dataInputStream.readUTF();
                System.out.println("From " + name + ":" + message);
                if (message.equals("/end")) {
                    closeConnection();
                    return;
                }
                if (message.startsWith("/w ")) {
                    String[] parts = message.split("\\s");
                    String realMessage = message.substring(message.indexOf(" ", message.indexOf(" ") + 1));
                    myServer.sendDirect(parts[1],name+ ": "+ realMessage);
                } else myServer.broadcast(name + ": " + message, true);
            }
        }
    }

    private void authenticate() throws IOException {
        long standbyTime = System.currentTimeMillis() + TIMEOUT;
        while (socket.isConnected()) {
            if (System.currentTimeMillis() > standbyTime) {
                System.out.println("Authorisation timeout");
                sendMsg("/end");
                closeConnection();
                return;
            } else {
                if (dataInputStream.available() > 0) {
                    String str = dataInputStream.readUTF();
                    if (str.startsWith("/auth")) {
                        String[] parts = str.split("\\s");
                        String nick = myServer.getAuthService().getNickByLoginAndPwd(parts[1], parts[2]);
                        if (nick != null) {
                            if (!myServer.isNickLogged(nick)) {
                                System.out.println(nick + " logged into chat");
                                name = nick;
                                sendMsg("/authOk " + nick);
                                myServer.broadcast(nick + " is in chat",true);
                                myServer.subscribe(this);
                                return;
                            } else {
                                System.out.println("Wrong login/password");
                                sendMsg("Incorrect login attempted");
                            }
                        }
                    }
                }
            }
        }
    }

    public void sendMsg(String s) {
        try {
            dataOutputStream.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

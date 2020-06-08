package com.geekbrains.java2.lesson6.homework;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
    private static int uniqueId;
    private ArrayList<ClientThread> clientThreads;
    private final SimpleDateFormat dateFormat;
    private final int port;
    private boolean keepGoing;
    private final String notificationMask = " *** ";

    public Server(int port) {
        this.port = port;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        clientThreads = new ArrayList<>();
    }

    public void start() {
        keepGoing = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(keepGoing) {
                display("Server waiting for Clients on port " + port + ".");
                Socket socket = serverSocket.accept();

                if(!keepGoing)
                    break;

                ClientThread t = new ClientThread(socket);
                clientThreads.add(t);
                t.start();
            }
            try {
                serverSocket.close();
                for (ClientThread tc : clientThreads) {
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {
                    }
                }
            } catch(Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        } catch (IOException e) {
            String msg = dateFormat.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    protected void stop() {
        keepGoing = false;
        try {
            new Socket("localhost", port);
        } catch(Exception e) {
        }
    }

    private void display(String msg) {
        String time = dateFormat.format(new Date()) + " " + msg;
        System.out.println(time);
    }

    private synchronized boolean broadcast(String message) {
        String time = dateFormat.format(new Date());
        String[] w = message.split(" ",3);
        boolean isPrivate = false;
        if(w[1].charAt(0) == '@')
            isPrivate = true;

        if(isPrivate) {
            String toCheck = w[1].substring(1);

            message = w[0] + w[2];
            String messageLf = time + " " + message + "\n";
            boolean found=false;

            for(int y = clientThreads.size(); --y >= 0;) {
                ClientThread ct1 = clientThreads.get(y);
                String check = ct1.getUsername();

                if(check.equals(toCheck)) {
                    if(!ct1.writeMsg(messageLf)) {
                        clientThreads.remove(y);
                        display("Disconnected Client " + ct1.username + " removed from list.");
                    }
                    found = true;
                    break;
                }
            }

            return found;
        } else {
            String messageLf = time + " " + message + "\n";
            System.out.print(messageLf);

            for(int i = clientThreads.size(); --i >= 0;) {
                ClientThread ct = clientThreads.get(i);

                if(!ct.writeMsg(messageLf)) {
                    clientThreads.remove(i);
                    display("Disconnected Client " + ct.username + " removed from list.");
                }
            }
        }
        return true;


    }

    synchronized void remove(int id) {
        String disconnectedClient = "";

        for(int i = 0; i < clientThreads.size(); ++i) {
            ClientThread ct = clientThreads.get(i);

            if(ct.id == id) {
                disconnectedClient = ct.getUsername();
                clientThreads.remove(i);
                break;
            }
        }
        broadcast(notificationMask + disconnectedClient + " has left the chat room." + notificationMask);
    }

    public static void main(String[] args) {
        int portNumber = 8099;

        switch(args.length) {
            case 1:
                try {
                    portNumber = Integer.parseInt(args[0]);
                } catch(Exception e) {
                    System.out.println("Invalid port number.");
                    System.out.println("Usage is: > java Server [portNumber]");
                    return;
                }
            case 0:
                break;
            default:
                System.out.println("Usage is: > java Server [portNumber]");
                return;
        }

        Server server = new Server(portNumber);
        server.start();
    }

    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        int id;
        String username;
        Message cm;
        String date;

        ClientThread(Socket socket) {
            id = ++uniqueId;
            this.socket = socket;
            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput  = new ObjectInputStream(socket.getInputStream());
                username = (String) sInput.readObject();
                broadcast(notificationMask + username + " has joined the chat room." + notificationMask);
            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            } catch (ClassNotFoundException e) {
                System.out.println("2");
            }
            date = new Date().toString() + "\n";
        }

        public String getUsername() { return username; }

        public void setUsername(String username) { this.username = username; }

        public void run() {
            boolean keepGoing = true;
            while(keepGoing) {
                try {
                    cm = (Message) sInput.readObject();
                } catch (IOException e) {
                    display(username + " Exception reading Streams: " + e);
                    break;
                } catch(ClassNotFoundException e2) {
                    break;
                }
                String message = cm.getMessage();

                switch (cm.getType()) {
                    case Message.MESSAGE -> {
                        boolean confirmation = broadcast(username + ": " + message);
                        if (!confirmation) {
                            String msg = notificationMask + "Sorry. No such user exists." + notificationMask;
                            writeMsg(msg);
                        }
                    }
                    case Message.LOGOUT -> {
                        display(username + " disconnected with a LOGOUT message.");
                        keepGoing = false;
                    }
                    case Message.ACTIVE -> {
                        writeMsg("List of the users connected at " + dateFormat.format(new Date()) + "\n");
                        for (int i = 0; i < clientThreads.size(); ++i) {
                            ClientThread ct = clientThreads.get(i);
                            writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
                        }
                    }
                }
            }
            remove(id);
            close();
        }

        private void close() {
            try {
                if(sOutput != null) sOutput.close();
            } catch(Exception e) {}
            try {
                if(sInput != null) sInput.close();
            } catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            } catch (Exception e) {}
        }

        private boolean writeMsg(String msg) {
            if(!socket.isConnected()) {
                close();
                return false;
            }
            try {
                sOutput.writeObject(msg);
            } catch(IOException e) {
                display(notificationMask + "Error sending message to " + username + notificationMask);
                display(e.toString());
            }
            return true;
        }
    }
}
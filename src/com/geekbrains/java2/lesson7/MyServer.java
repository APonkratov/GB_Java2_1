package com.geekbrains.java2.lesson7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new AuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Server awaits clients");
                Socket socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Server error");
        } finally {
            if(authService != null) {
                authService.stop();
            }
        }
    }


    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void broadcast(String s) {
        for(ClientHandler client: clients) {
            client.sendMsg(s);
        }
    }

    public synchronized boolean isNickLogged(String nick) {
        for(ClientHandler client: clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void sendDirectMessage(String userFrom, String userTo, String message) {
        if (isNickLogged(userTo)) {
            for (ClientHandler client: clients) {
                if (client.getName().equals(userTo)) {
                    client.sendMsg(userFrom + " direct to you: " + message);
                }
                if (client.getName().equals(userFrom)) {
                    client.sendMsg("You direct to " + userTo + ": " + message);
                }
            }
        } else {
            for (ClientHandler client: clients) {
                if (client.getName().equals(userFrom)) {
                    client.sendMsg("Server: " + userTo + " is offline now. Try later.");
                }
            }
        }
    }
}

package com.geekbrains.java2.lesson7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void setAuthorized(boolean authorized) {
        Client.authorized = authorized;
    }

    private static final Scanner scanner = new Scanner(System.in);
    static boolean authorized;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF("/auth login1 pass1");
            setAuthorized(false);
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        if (inputStream.available() > 0) {
                            String strFromServer = inputStream.readUTF();
                            if (strFromServer.startsWith("/authOk")) {
                                setAuthorized(true);
                                System.out.println("Authorized on server");
                                Client.runOutputThread(outputStream);
                                break;
                            }
                            System.out.println(strFromServer + "\n");
                        }
                    }
                    while (true) {
                        if (inputStream.available() > 0) {
                            String strFromServer = inputStream.readUTF();
                            if (strFromServer.equalsIgnoreCase("/end")) {
                                break;
                            }
                            System.out.println(strFromServer);
                            System.out.println("\n");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
            t.join();
            while (true) {
                String message = scanner.nextLine();
                outputStream.writeUTF(message);
                if (message.equals("/end")) {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    t.interrupt();
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runOutputThread(DataOutputStream out) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    try {
                        out.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (message.equals("/end")) {
                        break;
                    }
                }
            }
        });
        thread.start();
    }
}

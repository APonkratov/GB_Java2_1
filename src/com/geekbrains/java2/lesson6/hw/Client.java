package com.geekbrains.java2.lesson6.hw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        new Client().start("localhost", 8189);
    }

    private void start(String host, int port) throws IOException {
        Socket socket =null;
        Thread thread = null;
        try {
            socket = new Socket(host, port);
            System.out.println("Client started");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            thread = runInputThread(in);
            runOutputLoop(out);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket!=null) socket.close();
            if (thread!= null) socket.close();
        }
    }

    private void runOutputLoop(DataOutputStream out) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            out.writeUTF(message);
            if (message.equals("/end")) {
                break;
            }
        }
    }

    private Thread runInputThread(DataInputStream in) {
        Thread thread = new Thread(()-> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = in.readUTF();
                    System.out.println("From server: " + message);
                } catch (IOException e) {
                    System.out.println("Connection closed");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }
}

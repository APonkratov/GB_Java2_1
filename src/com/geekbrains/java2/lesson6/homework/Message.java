package com.geekbrains.java2.lesson6.homework;

import java.io.*;

public class Message implements Serializable {

    static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;
    private int type;
    private String message;

    Message(int type, String message) {
        this.type = type;
        this.message = message;
    }

    int getType() { return type; }

    String getMessage() { return message; }
}
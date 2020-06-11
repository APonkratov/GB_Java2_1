package com.geekbrains.java2.lesson2;

public class Throwing {
    public static void main(String[] args) {
        try {
            throw new NullPointerException("NPE Test");
        } catch (NullPointerException е) {
            System.out.println("Catch block");
        }
    }
}

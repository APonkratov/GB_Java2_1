package com.geekbrains.java2.lesson2.homework;

public class MyArrayDataException extends Exception {
    public MyArrayDataException () {
        super();
    }

    public MyArrayDataException(String message) {
        super(message);
    }

    static MyArrayDataException forItem(int indexI, int indexJ) {
        String message = String.format("Element at index [%d][%d] is not number", indexI, indexJ);
        return new MyArrayDataException(message);
    }
}

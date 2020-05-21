package com.geekbrains.java2.lesson2.homework;

public class MyArraySizeException extends Exception {
    public MyArraySizeException () {
        super();
    }

    public MyArraySizeException(String message) {
        super(message);
    }

    static MyArraySizeException forSize(int size) {
        String message = String.format("Array size is not [%d][%d]", size, size);
        return new MyArraySizeException(message);
    }
}

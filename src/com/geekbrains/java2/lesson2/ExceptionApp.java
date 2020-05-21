package com.geekbrains.java2.lesson2;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.zip.ZipFile;

public class ExceptionApp {
    public static void main(String[] args) {
//        try {
//            divide(0);
//        } catch (MyIOException e) {
//            e.printStackTrace();
//        }
//        Try with resources
        try(ZipFile f = new ZipFile("c:\\code\\test.txt")) {
            int а = 0;
            int b = 42 / а;
            f.size();
        } catch (ArithmeticException е) { // ОШИБКА : недостижимый код !
            System.out.println("Этот код достижим");
            System.out.println(42/0);
            System.out.println("Still in catch block");
        } catch (Exception е) {
            System.out.println("Exception");
        }
//        try {
//            int a = 10;
//            a -= 10;
////            int b = 42 / a;
//            int[] с = {1, 2, 3};
//            с[42] = 99;
//        } catch (ArithmeticException е) {
//            System.out.println("Дeлeниe на ноль: " + е);
//        } catch (ArrayIndexOutOfBoundsException е){
//            System.out.println("Ошибка индексации массива: " + е);
//        }
//        System.out.println("Пocлe блока операторов try/catch");
    }

    private static void divide(int a) throws MyIOException {
        try {
            int b = 10 / a;
        } catch (Throwable e) {
            System.out.println("Exception caught! Hooray");
            e.printStackTrace();
        }
        System.out.println("You will now see this in console!");
        try{
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyIOException();
        }
    }

    static class MyIOException extends RuntimeException {

        public MyIOException() {

        }
    }
}
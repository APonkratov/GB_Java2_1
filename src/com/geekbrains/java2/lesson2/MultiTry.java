package com.geekbrains.java2.lesson2;

public class MultiTry {
    public static void main(String args[]) {
        try {
            int a = 10;
            a -= 10;
            int b = 42 / a;
            int[] с = {1, 2, 3};
            с[42] = 99;
        } catch (ArithmeticException e) {
            System.out.println("Дeлeниe на ноль: " + e);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка индексации массива: " + e);
        }
        System.out.println("Пocлe блока операторов try/catch");
    }
}

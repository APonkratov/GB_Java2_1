package com.geekbrains.java2.lesson5.homework;

import java.util.Arrays;

public class ThreadArrayCalcTest {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        mainThread();
        multiThreads();
    }

    public static void mainThread() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Main thread time, millis");
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void multiThreads() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long startTime = System.currentTimeMillis();
        float[] arrPart1 = new float[HALF];
        float[] arrPart2 = new float[HALF];
        System.arraycopy(arr, 0, arrPart1, 0, HALF);
        System.arraycopy(arr, HALF, arrPart2, 0, HALF);

        ThreadArrayCalculate t1 = new ThreadArrayCalculate(arrPart1, 0);
        ThreadArrayCalculate t2 = new ThreadArrayCalculate(arrPart2, HALF);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arrPart1, 0, arr, 0, HALF);
        System.arraycopy(arrPart2, 0, arr, HALF, HALF);
        System.out.println("Multi-threads calculating, millis");
        System.out.println(System.currentTimeMillis() - startTime);
    }
}

package com.geekbrains.java2.lesson5.homework;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ThreadArrayCalculate extends Thread{

    private float[] array;
    private int offset;

    public ThreadArrayCalculate(float[] array, int offset) {
        this.array = array;
        this.offset = offset;
    }

    @Override
    public void run() {
        calc();
    }

    private void calc() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] *
                    Math.sin(0.2f + (i + offset) / 5) *
                    Math.cos(0.2f + (i + offset) / 5) *
                    Math.cos(0.4f + (i + offset) / 2));
        }
    }
}

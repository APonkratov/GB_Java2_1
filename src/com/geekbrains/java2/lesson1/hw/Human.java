package com.geekbrains.java2.lesson1.hw;

public class Human implements Competitor {
    static int MAX_HEIGHT = 2;
    static int MAX_LENGTH = 30000;

    @Override
    public void run() {
        System.out.println("Human runs");
    }

    @Override
    public void jump() {
        System.out.println("Human jumps");
    }

    @Override
    public boolean canPass(Obstacle o) {
        boolean canRun = (MAX_LENGTH >= o.getLENGTH());
        if (canRun) {
            System.out.println("Human passed the distance!");
        } else {
            System.out.println("Human failed the distance...");
        }
        boolean canJump = (MAX_HEIGHT >= o.getHEIGHT());
        if (canJump) {
            System.out.println("Human climbed the peak!");
        } else {
            System.out.println("Human failed the peak...");
        }
        return canRun && canJump;
    }
}

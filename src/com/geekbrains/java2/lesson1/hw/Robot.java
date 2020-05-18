package com.geekbrains.java2.lesson1.hw;

public class Robot implements Competitor {
    static int MAX_HEIGHT = 1;
    static int MAX_LENGTH = 50000;

    @Override
    public void run() {
        System.out.printf("Robot runs");
    }

    @Override
    public void jump() {
        System.out.printf("Robot jumps");
    }

    @Override
    public boolean canPass(Obstacle o) {
        boolean canRun = (MAX_LENGTH >= o.getLENGTH());
        if (canRun) {
            System.out.println("Robot passed the distance!");
        } else {
            System.out.println("Robot failed the distance...");
        }
        boolean canJump = (MAX_HEIGHT >= o.getHEIGHT());
        if (canJump) {
            System.out.println("Robot climbed the peak!");
        } else {
            System.out.println("Robot failed the peak...");
        }
        return canRun && canJump;
    }
}

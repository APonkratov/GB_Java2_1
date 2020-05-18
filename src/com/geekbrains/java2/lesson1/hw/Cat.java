package com.geekbrains.java2.lesson1.hw;

public class Cat implements Competitor {
    static int MAX_HEIGHT = 2;
    static int MAX_LENGTH = 300;

    @Override
    public void run() {
        System.out.println("Cat runs");
    }

    @Override
    public void jump() {
        System.out.printf("Cat jumps");
    }

    @Override
    public boolean canPass(Obstacle o) {
        boolean canRun = (MAX_LENGTH >= o.getLENGTH());
        if (canRun) {
            System.out.println("Cat passed the distance!");
        } else {
            System.out.println("Cat failed the distance...");
            return false;
        }
        boolean canJump = (MAX_HEIGHT >= o.getHEIGHT());
        if (canJump) {
            System.out.println("Cat climbed the peak!");
        } else {
            System.out.println("Cat failed the peak...");
        }
        return canRun && canJump;
    }
}

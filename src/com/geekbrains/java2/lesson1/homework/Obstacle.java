package com.geekbrains.java2.lesson1.homework;

public class Obstacle {
    private final Motion motion;
    private final double size;

    public Obstacle(Motion motion, double size) {
        this.motion = motion;
        this.size = size;
    }

    public Motion getMotion() {
        return motion;
    }

    public double getSize() {
        return size;
    }
}

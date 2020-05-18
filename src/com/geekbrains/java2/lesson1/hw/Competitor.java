package com.geekbrains.java2.lesson1.hw;

public interface Competitor {
    int MAX_HEIGHT = 0;
    int MAX_LENGTH = 0;

    void run();
    void jump();
    boolean canPass(Obstacle o);
}

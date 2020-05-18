package com.geekbrains.java2.lesson1.hw;

public class Competition {
    static Competitor[] competitors = {new Human(), new Cat(), new Robot()};
    static Obstacle[] obstacles = {new Wall(2),
            new Threadmill(100),
            new Threadmill(1000),
            new Wall(1)};

    public static void main(String[] args) {
        for(Competitor competitor: competitors) {
           for(Obstacle obstacle: obstacles) {
               boolean canPass = competitor.canPass(obstacle);
               if (!canPass) break;
           }
        }
    }
}

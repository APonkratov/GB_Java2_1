package com.geekbrains.java2.lesson1.homework;

public class TestHomework {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Participant human = new Human(10.5, 1.5);
        Participant cat = new Cat(5.0, 2.0);
        Participant robot = new Robot(100.0, 4.0);

        Participant[] participants = {human, cat, robot};

        for (Participant participant: participants) {
            participant.running();
            participant.jumping();
        }

        Obstacle wall = new Wall(2.0);
        Obstacle treadmill = new Treadmill(10.0);

        Obstacle[] obstacles = {wall, treadmill};
        for (Participant participant: participants) {
            for (Obstacle obstacle: obstacles) {
                if (participant.isParticipating()) {
                    tryPassObstacle(participant, obstacle);
                }
            }
        }
    }

    private static void tryPassObstacle(Participant participant, Obstacle obstacle) {
        if (!participant.pass(obstacle)) {
            participant.completeParticipating();
        }
    }
}

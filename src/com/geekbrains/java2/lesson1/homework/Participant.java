package com.geekbrains.java2.lesson1.homework;

public interface Participant {
    void running();
    void jumping();
    boolean run(double distance);
    boolean jump(double height);

    default boolean pass(Obstacle obstacle) {
        if (obstacle.getMotion() == Motion.RUN){
            return run(obstacle.getSize());
        } else if (obstacle.getMotion() == Motion.JUMP) {
            return jump(obstacle.getSize());
        } else {
            return false;
        }
    }

    boolean isParticipating();
    void completeParticipating();
}

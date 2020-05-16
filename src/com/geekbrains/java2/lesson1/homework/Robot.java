package com.geekbrains.java2.lesson1.homework;

public class Robot implements Participant {

    private final double maxRunDistance;
    private final double maxJumpHeight;
    private boolean isParticipating;

    public Robot(double maxRunDistance, double maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.isParticipating = true;
    }

    @Override
    public void running() {
        System.out.println("Robot is running");
    }

    @Override
    public void jumping() {
        System.out.println("Robot is jumping");
    }

    @Override
    public boolean run(double distance) {
        if (maxRunDistance >= distance) {
            System.out.printf("Robot successfully run %.1f kilometers%n", distance);
            return true;
        } else {
            System.out.printf("Robot cant run %.1f kilometers%n", distance);
            return false;
        }
    }

    @Override
    public boolean jump(double height) {
        if (maxJumpHeight >= height) {
            System.out.printf("Robot successfully jump on %.1f meters%n", height);
            return true;
        } else {
            System.out.printf("Robot cant jump on %.1f meters%n", height);
            return false;
        }
    }

    public void setParticipating(boolean participating) {
        isParticipating = participating;
    }

    @Override
    public boolean isParticipating() {
        return isParticipating;
    }

    @Override
    public void completeParticipating() {
        this.setParticipating(false);
    }
}

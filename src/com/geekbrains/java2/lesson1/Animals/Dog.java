package com.geekbrains.java2.lesson1.Animals;

public class Dog extends Animal implements Jumping, Soundly {
    public static void main(String[] args) {
        Animal dog = new Dog();
    }

    @Override
    public void jump() {
        System.out.println("Dog jumps lower than cat");
    }

    @Override
    public void giveSound() {
        System.out.println("Woof!");
    }
}

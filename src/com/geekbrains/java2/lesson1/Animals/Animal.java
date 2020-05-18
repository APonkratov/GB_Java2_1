package com.geekbrains.java2.lesson1.Animals;

public abstract class Animal {
    String name;

    public Animal() {
    }

    public void animalInfo() {
        System.out.println("Животное: " + name);
    }
}
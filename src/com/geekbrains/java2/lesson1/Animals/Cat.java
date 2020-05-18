package com.geekbrains.java2.lesson1.Animals;

public class Cat extends Animal implements Jumping, Soundly {
    Color color;
    static int specieId;

    {
        color = Color.RED;
    }

    static {
        specieId = 0;
    }

    public Cat() {
        this.name = "Default name";
        this.color = Color.BLACK;
        specieId++;
    }

    @Override
    public void jump() {
        System.out.println("Cat jumps!");
    }

    public static void staticJump() {
        System.out.println("Cat jumps statically!");
    }

    public Cat(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void catInfo() {
        System.out.println("Кот имя: " + name + " цвет: " + color.getIndex() + ", id" + specieId);
    }

    public static void main(String[] args) {
        Jumping cat = new Cat("Purr", Color.RED);
        cat.jump();
        Cat.staticJump();
    }

    @Override
    public void giveSound() {
        System.out.println("Meow!");
    }
//    SOLID
//I = ISP, Interface Segregation Principle
//D - DIP, Dependency Inversion Principle
//Dependency Injection
}


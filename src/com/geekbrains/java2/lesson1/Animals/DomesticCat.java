package com.geekbrains.java2.lesson1.Animals;

public class DomesticCat extends Cat{
    private String owner;

    public static void main(String[] args) {
        Cat pussy = new DomesticCat();
        if (pussy instanceof Cat) System.out.println("It's a Cat!");
        if (pussy instanceof DomesticCat) System.out.println("Also a domestic one!");
        ((DomesticCat)pussy).owner = "123";
    }

    @Override
    public void jump() {
        super.jump();
        System.out.println("That was a domestic jump!");
    }
}

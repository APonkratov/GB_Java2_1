package com.geekbrains.java2.lesson1.Animals;

public enum Color {
    RED("Рыжий",1),BLACK("Чёрный",10),GRAY("Серый",100),WHITE("Белый",1000),BROWN("Коричневый",10000);

    private String russianTitle;
    private int index;

    Color(String russianTitle, int index) {
        this.index = index;
        this.russianTitle = russianTitle;
    }

    @Override
    public String toString() {
        return "Color{" +
                "russianTitle='" + russianTitle + '\'' +
                ", index=" + index +
                '}';
    }

    public String getRussianTitle() {
        return russianTitle;
    }

    public int getIndex() {
        return index;
    }
}

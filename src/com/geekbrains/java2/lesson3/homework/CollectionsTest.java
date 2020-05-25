package com.geekbrains.java2.lesson3.homework;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsTest {
    public static void main(String[] args) {
        String[] testArray = {
                "Lada", "UAZ", "Kia", "Hyundai",
                "BMW", "Audi", "Mercedes", "Skoda",
                "Seat", "Kia", "Lada", "Audi"
        };

        HashSet<String> hashSet = new HashSet<>(Arrays.asList(testArray));
        HashMap<String, Integer> hashMap = new HashMap<>();

        System.out.println("Уникальные элементы");
        System.out.println(hashSet.toString());

        Integer number;

        for (String element: testArray) {
            number = hashMap.get(element);
            hashMap.put(element, number == null ? 1 : number + 1);
        }

        List doublesList = hashMap.entrySet().stream()
                .filter(pair -> pair.getValue() > 1)
                .collect(Collectors.toList());

        System.out.println("Дубликаты");
        System.out.println(doublesList.toString());

        System.out.println();
        System.out.println("Телефонный справочник");

        PhoneBook book1 = new PhoneBook();
        book1.add("Ivanov", "1234567890");
        book1.add("Ivanov", "2345678901");
        book1.add("Petrov", "3456789012");
        book1.add("Sidorov", "4567890123");

        System.out.println("Ivanov: " + book1.get("Ivanov").toString());
        System.out.println("Petrov: " + book1.get("Petrov").toString());
        System.out.println("Alekseev: " + book1.get("Alekseev").toString());
    }
}

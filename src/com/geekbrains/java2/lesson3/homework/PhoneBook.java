package com.geekbrains.java2.lesson3.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PhoneBook {
    private HashMap<String, HashSet<String>> phones;

    public PhoneBook() {
        this.phones = new HashMap<>();
    }

    public void add(String lastName, String phone) {
        HashSet<String> currentPhones = phones.getOrDefault(lastName, new HashSet<>());
        if (!currentPhones.contains(phone)) {
            currentPhones.add(phone);
            phones.put(lastName, currentPhones);
        }
    }

    public HashSet<String> get(String lastName) {
        return phones.getOrDefault(lastName, new HashSet<>());
    }
}

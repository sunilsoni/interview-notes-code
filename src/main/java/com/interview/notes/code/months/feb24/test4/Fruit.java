package com.interview.notes.code.months.feb24.test4;

import java.util.Optional;

public class Fruit {
    private String name;
    private String key;

    public Fruit(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() { return name; }
    public String getKey() { return key; }

    public static void main(String[] args) {
        Fruit fruitA = new Fruit("orange", "ORA");
        String str1 = Optional.ofNullable(fruitA).map(Fruit::getKey).orElse("UNKNOWN");

        Fruit fruitB = null;
        String str2 = Optional.ofNullable(fruitB).map(Fruit::getKey).orElse("UNKNOWN");
    }
}

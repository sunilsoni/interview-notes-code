package com.interview.notes.code.year.y2025.october.assessments.test1;

import java.util.HashMap;
import java.util.Map;

public class FruitStreamExample {
    public static void main(String[] args) {
        // Create and populate the HashMap
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "mango");
        map.put(2, "orange");
        map.put(3, "apple");
        map.put(4, "kiwi");

        // Using streams to filter and display fruits with length 5
        System.out.println("Fruits with length 5:");
        
        // Method 1: Using map values() and stream
        map.values()
           .stream()
           .filter(fruit -> fruit.length() == 5)
           .forEach(System.out::println);

        // Alternative Method 2: Using entrySet() and stream
        map.entrySet()
           .stream()
           .filter(entry -> entry.getValue().length() == 5)
           .forEach(entry -> System.out.println(entry.getValue()));
    }
}

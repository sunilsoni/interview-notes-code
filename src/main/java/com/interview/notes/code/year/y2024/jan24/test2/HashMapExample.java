package com.interview.notes.code.year.y2024.jan24.test2;

import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        // Insert a key-value pair with a null key and null value
        map.put(null, null);

        // Retrieving a null value
        String value = map.get(null);

        System.out.println("Value for null key: " + value); // Output: Value for null key: null
    }
}

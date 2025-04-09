package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageAge {
    public static void main(String[] args) {
        // Create and populate the list of maps
        Map<String, String> p1 = new HashMap<>();
        p1.put("name", "test");
        p1.put("age", "30");
        p1.put("salary", "30000");

        Map<String, String> p2 = new HashMap<>();
        p2.put("name", "test1");
        p2.put("age", "31");
        p2.put("salary", "40000");

        Map<String, String> p3 = new HashMap<>();
        p3.put("name", "test3");
        p3.put("age", "40");
        p3.put("salary", "50000");

        Map<String, String> p4 = new HashMap<>();
        p4.put("name", "test4");
        p4.put("age", "50");
        p4.put("salary", "10000");

        Map<String, String> p5 = new HashMap<>();
        p5.put("name", "test5");
        p5.put("age", "22");
        p5.put("salary", "15000");

        List<Map<String, String>> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        // Find average age of people above 30
        double averageAge = list.stream()
                .map(map -> Integer.parseInt(map.get("age"))) // Extract age
                .filter(age -> age > 30) // Filter ages above 30
                .mapToInt(age -> age) // Convert to IntStream
                .average() // Calculate average
                .orElse(0.0); // Default if no match

        System.out.println("Average age of people above 30: " + averageAge);
    }
}

package com.interview.notes.code.year.y2026.feb.Coderbyte.test1;

import java.net.URI;
import java.util.Arrays;
import java.util.Scanner;

public class AgeCensus {
    public static void main(String[] args) {
        try {
            // Production Execution
            var url = URI.create("https://coderbyte.com/api/challenges/json/age-counting").toURL();
            try (var scanner = new Scanner(url.openStream())) {
                var json = scanner.useDelimiter("\\A").next();
                var content = json.split("\"data\":\"")[1].split("\"")[0];
                System.out.println("Final Count: " + countAge(content));
            }

            // Test Suite
            System.out.println("\n--- Test Results ---");
            runTest("Example Case", "key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47", 2);
            runTest("Boundary 50", "key=test, age=50", 1);
            runTest("Below 50", "key=test, age=49", 0);
            runTest("Empty", "", 0);
            
            // Large Data Simulation
            var sb = new StringBuilder();
            for (int i = 0; i < 50000; i++) sb.append("key=L, age=" + (i % 100) + ", ");
            runTest("Large Dataset (50k items)", sb.toString(), 25000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long countAge(String data) {
        if (data == null || data.isEmpty()) return 0;
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .filter(s -> s.startsWith("age="))
                .mapToInt(s -> Integer.parseInt(s.substring(4)))
                .filter(age -> age >= 50)
                .count();
    }

    static void runTest(String name, String input, long expected) {
        long result = countAge(input);
        System.out.println(name + ": " + (result == expected ? "PASS" : "FAIL [Expected: " + expected + ", Got: " + result + "]"));
    }
}
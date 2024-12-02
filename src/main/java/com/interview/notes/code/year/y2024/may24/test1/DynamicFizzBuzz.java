package com.interview.notes.code.year.y2024.may24.test1;

import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicFizzBuzz {
    public static void main(String[] args) {
        // Define conditions and corresponding strings
        Map<Integer, String> conditions = new LinkedHashMap<>();
        conditions.put(3, "Fizz");
        conditions.put(5, "Buzz");
        conditions.put(7, "Bizz");
        conditions.put(9, "Fizz"); // Example to add another condition

        for (int i = 1; i <= 100; i++) {
            StringBuilder result = new StringBuilder();

            // Check each condition defined in the map
            for (Map.Entry<Integer, String> entry : conditions.entrySet()) {
                if (i % entry.getKey() == 0) {
                    result.append(entry.getValue());
                }
            }

            if (result.length() == 0) { // If no condition was met, append the number
                result.append(i);
            }

            System.out.println(result);
        }
    }
}

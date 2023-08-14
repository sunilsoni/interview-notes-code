package com.interview.notes.code.july23.test12;

import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicFizzBuzz {
    public static void main(String[] args) {
        // Define the rules with a LinkedHashMap to maintain insertion order
        Map<Integer, String> rules = new LinkedHashMap<>();
        rules.put(3, "Fizz");
        rules.put(5, "Buzz");
        rules.put(7, "Wizz");
        
        printFizzBuzz(rules, 100);
    }
    
    public static void printFizzBuzz(Map<Integer, String> rules, int limit) {
        // Loop through numbers from 1 to the specified limit
        for (int i = 1; i <= limit; i++) {
            StringBuilder result = new StringBuilder();

            // Apply each rule
            for (Integer divisor : rules.keySet()) {
                if (i % divisor == 0) {
                    result.append(rules.get(divisor));
                }
            }

            // If no rule was applied, append the number itself
            if (result.length() == 0) {
                result.append(i);
            }

            // Print the result, followed by a comma and space if not the last number
            System.out.print(result);
            if (i < limit) {
                System.out.print(", ");
            }
        }
    }
}

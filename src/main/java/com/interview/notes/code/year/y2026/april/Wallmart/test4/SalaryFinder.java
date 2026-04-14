package com.interview.notes.code.year.y2026.april.Wallmart.test4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryFinder { // Main class to encapsulate our logic

    public static void main(String[] args) { // Simple main method for testing as requested
        
        // --- TEST CASE 1: Provided Interview Input ---
        var map1 = new HashMap<String, Integer>(); // Create map using Java local variable type inference
        map1.put("e1", 1000);   // Add employee 1
        map1.put("e2", 10000);  // Add employee 2
        map1.put("e3", 100000); // Add employee 3
        map1.put("e4", 10000);  // Add employee 4
        map1.put("e5", 100000); // Add employee 5
        
        // Execute logic for Test 1
        var result1 = getThirdHighestSalary(map1); // Call our method
        // Check if the result contains exactly e1 with 1000
        boolean pass1 = result1.size() == 1 && result1.get(0).getKey().equals("e1"); // Validate result
        System.out.println("Test Case 1 (Standard): " + (pass1 ? "PASS" : "FAIL")); // Print Pass/Fail
        
        // --- TEST CASE 2: Multiple Employees with 3rd Highest ---
        var map2 = new HashMap<String, Integer>(); // Create second map
        map2.put("a", 500); // 3rd highest
        map2.put("b", 100); // 4th highest
        map2.put("c", 900); // 2nd highest
        map2.put("d", 500); // 3rd highest (Duplicate)
        map2.put("e", 1000); // 1st highest
        
        // Execute logic for Test 2
        var result2 = getThirdHighestSalary(map2); // Call method
        // Expecting employees 'a' and 'd'
        boolean pass2 = result2.size() == 2 && result2.get(0).getValue() == 500; // Validate result
        System.out.println("Test Case 2 (Ties): " + (pass2 ? "PASS" : "FAIL")); // Print Pass/Fail

        // --- TEST CASE 3: Not enough data (Edge Case) ---
        var map3 = new HashMap<String, Integer>(); // Create empty/small map
        map3.put("x", 100); // Add only one entry
        
        // Execute logic for Test 3
        var result3 = getThirdHighestSalary(map3); // Call method
        // Expecting empty list since there is no 3rd highest
        boolean pass3 = result3.isEmpty(); // Validate result
        System.out.println("Test Case 3 (Not enough salaries): " + (pass3 ? "PASS" : "FAIL")); // Print Pass/Fail
        
        // --- TEST CASE 4: Large Data Input ---
        var largeMap = new HashMap<String, Integer>(); // Create large map
        for (int i = 1; i <= 100000; i++) { // Loop 100,000 times
            largeMap.put("emp" + i, i % 10); // Salaries will be 0 through 9
        }
        
        long startTime = System.currentTimeMillis(); // Start timer
        var resultLarge = getThirdHighestSalary(largeMap); // Call method
        long endTime = System.currentTimeMillis(); // End timer
        
        // The highest is 9, 2nd is 8, 3rd highest is 7.
        boolean passLarge = resultLarge.get(0).getValue() == 7 && resultLarge.size() == 10000; // Validate 10,000 employees have salary 7
        System.out.println("Test Case 4 (100k records): " + (passLarge ? "PASS" : "FAIL") + " (Took " + (endTime - startTime) + "ms)"); // Print Pass/Fail
    }

    /**
     * Finds the employees with the 3rd highest salary.
     */
    public static List<Map.Entry<String, Integer>> getThirdHighestSalary(Map<String, Integer> map) { // Method declaration returning a List of Entries
        
        // Step 1: Find the target salary amount
        var targetSalary = map.values().stream() // Extract only the salary values into a stream
            .distinct() // Remove duplicate salaries to find absolute rankings
            .sorted(Comparator.reverseOrder()) // Sort salaries from highest to lowest
            .skip(2) // Skip the 1st and 2nd highest salaries
            .findFirst() // Grab the next one (which is the 3rd highest)
            .orElse(null); // If a 3rd highest doesn't exist, return null safely

        // Step 1.5: Handle edge cases where target salary isn't found
        if (targetSalary == null) { // Check if we failed to find a 3rd highest salary
            return List.of(); // Return an empty, immutable list (Java 9+ feature)
        }

        // Step 2: Find all employees matching that target salary
        return map.entrySet().stream() // Stream the original map's key-value pairs
            .filter(entry -> entry.getValue().equals(targetSalary)) // Keep only entries where the salary matches our target
            .toList(); // Collect the results directly into an immutable list (Java 16+ feature)
    }
}
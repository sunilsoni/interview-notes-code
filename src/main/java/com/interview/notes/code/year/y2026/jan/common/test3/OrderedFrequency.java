package com.interview.notes.code.year.y2026.jan.common.test3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderedFrequency {

    public static Map<Character, Long> getOrderedFrequency(String input) {
        // Validation: Return empty map for null to prevent crashes
        if (input == null) return Collections.emptyMap();

        // Stream Pipeline
        return input.chars() // 1. Stream the string characters
                .mapToObj(c -> (char) c) // 2. Convert int codes to Character objects
                .collect(Collectors.groupingBy( // 3. Group them
                        Function.identity(), // Classifier: Use the char itself as key
                        LinkedHashMap::new,  // Factory: Use LinkedHashMap to PRESERVE ORDER
                        Collectors.counting() // Downstream: Count occurrences
                ));
    }

    // Helper to check if two maps are equal AND preserve order (for the test)
    private static boolean verifyOrderAndContent(Map<Character, Long> result, List<Character> expectedOrder) {
        if (result.size() != expectedOrder.size()) return false;
        
        // Convert result keys to a list to check their sequence
        List<Character> resultKeys = new ArrayList<>(result.keySet());
        
        // strict check: Are the keys in the exact same order?
        return resultKeys.equals(expectedOrder);
    }

    public static void main(String[] args) {
        System.out.println("Running Ordered Tests...\n");

        // --- Test Case 1: "communication" (Check specific order) ---
        // Input string starts with 'c', so 'c' must be first in the map
        String input1 = "communication";
        
        // Execute Logic
        var result1 = getOrderedFrequency(input1); // 'var' is Java 10+ feature for less code words
        
        // Define exact expected order of keys: c, o, m, u, n, i, a, t
        var expectedOrder = List.of('c', 'o', 'm', 'u', 'n', 'i', 'a', 't');

        // Verify
        boolean pass1 = verifyOrderAndContent(result1, expectedOrder);
        System.out.println("Test 1 (Order Check): " + (pass1 ? "PASS" : "FAIL"));
        System.out.println("Output: " + result1); // Visually confirm 'c' is first

        // --- Test Case 2: "banana" (b -> a -> n) ---
        String input2 = "banana";
        var result2 = getOrderedFrequency(input2);
        // Expected order: b (1st), a (2nd), n (3rd)
        boolean pass2 = verifyOrderAndContent(result2, List.of('b', 'a', 'n'));
        System.out.println("\nTest 2 (banana): " + (pass2 ? "PASS" : "FAIL"));
        System.out.println("Output: " + result2);

        // --- Test Case 3: Large Data (Performance & Order) ---
        // Create 1 million chars: "abcabc..." repeated
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<333333; i++) sb.append("abc"); 
        String input3 = sb.toString();
        
        long start = System.currentTimeMillis();
        var result3 = getOrderedFrequency(input3);
        long end = System.currentTimeMillis();
        
        // Verify order is strictly a, b, c
        boolean pass3 = verifyOrderAndContent(result3, List.of('a', 'b', 'c'));
        
        System.out.println("\nTest 3 (Large Data): " + (pass3 ? "PASS" : "FAIL"));
        System.out.println("Time: " + (end - start) + "ms");
    }
}
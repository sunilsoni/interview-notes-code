package com.interview.notes.code.year.y2025.jan24.test25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamWordReverser {
    
    // Main solution method
    public static List<String> reverseWordsAndList(List<String> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        
        return input.stream()
                   .map(StreamWordReverser::reverseAndCapitalize)
                   .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            java.util.Collections.reverse(list);
                            return list;
                        }));
    }
    
    // Helper method to reverse and capitalize each word
    private static String reverseAndCapitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String reversed = new StringBuilder(str).reverse().toString();
        return reversed.substring(0, 1).toUpperCase() + 
               (reversed.length() > 1 ? reversed.substring(1).toLowerCase() : "");
    }

    // Test helper method
    public static void testCase(String testName, List<String> input, List<String> expected) {
        List<String> result = reverseWordsAndList(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        testCase("Normal Case", 
                Arrays.asList("hello", "world"),
                Arrays.asList("Dlrow", "Olleh"));

        // Test Case 2: Empty list
        testCase("Empty List", 
                new ArrayList<>(), 
                new ArrayList<>());

        // Test Case 3: Null input
        testCase("Null Input", 
                null, 
                new ArrayList<>());

        // Test Case 4: Single item
        testCase("Single Item", 
                Arrays.asList("test"),
                Arrays.asList("Tset"));

        // Test Case 5: Palindrome
        testCase("Palindrome", 
                Arrays.asList("radar", "level"),
                Arrays.asList("Level", "Radar"));

        // Test Case 6: Special characters
        testCase("Special Characters", 
                Arrays.asList("@hello!", "123world"),
                Arrays.asList("dlrow321", "!olleh@"));

        // Test Case 7: Mixed case
        testCase("Mixed Case", 
                Arrays.asList("HeLLo", "wOrLD"),
                Arrays.asList("Dlrow", "Olleh"));

        // Test Case 8: Single character
        testCase("Single Character", 
                Arrays.asList("a", "b"),
                Arrays.asList("B", "A"));

        // Test Case 9: Large words
        testCase("Large Words", 
                Arrays.asList("pneumonoultramicroscopicsilicovolcanoconiosis", "supercalifragilisticexpialidocious"),
                Arrays.asList("Suoicodilaipxecitsiligarfilacrepus", "Sisoinoconaclovociliscipocsorcimartluonomuenp"));

        // Test Case 10: Large dataset
        List<String> largeList = IntStream.range(0, 10000)
                                        .mapToObj(i -> "item" + i)
                                        .collect(Collectors.toList());
        
        List<String> expectedLarge = IntStream.range(0, 10000)
                                            .mapToObj(i -> "Meti" + (9999 - i))
                                            .collect(Collectors.toList());
        
        testCase("Large Data", largeList, expectedLarge);

        // Performance test
        System.out.println("\nPerformance Test with 1 million items:");
        List<String> hugeList = IntStream.range(0, 1_000_000)
                                       .mapToObj(i -> "test" + i)
                                       .collect(Collectors.toList());
        
        long startTime = System.currentTimeMillis();
        List<String> hugeResult = reverseWordsAndList(hugeList);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("First item: " + hugeResult.get(0));
        System.out.println("Last item: " + hugeResult.get(hugeResult.size() - 1));
    }
}

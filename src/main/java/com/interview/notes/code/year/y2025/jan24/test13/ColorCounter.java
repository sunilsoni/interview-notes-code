package com.interview.notes.code.year.y2025.jan24.test13;

import java.util.*;
import java.util.stream.Collectors;

public class ColorCounter {
    
    // Main method to solve the problem
    public static Map<String, Long> countColors(String[] colors) {
        return Arrays.stream(colors)
                    .collect(Collectors.groupingBy(
                        color -> color,
                        Collectors.counting()
                    ));
    }
    
    // Main method with tests
    public static void main(String[] args) {
        // Test 1: Basic case
        String[] test1 = {"Grean", "Blue", "White", "Grean", "Blue", "White"};
        runTest("Test 1 - Basic case", test1);
        
        // Test 2: Empty array
        String[] test2 = {};
        runTest("Test 2 - Empty array", test2);
        
        // Test 3: Single color
        String[] test3 = {"Blue"};
        runTest("Test 3 - Single color", test3);
        
        // Test 4: Large dataset
      //  String[] test4 = generateLargeDataset(1000000);
      //  runTest("Test 4 - Large dataset", test4);
    }
    
    // Helper method to run tests
    private static void runTest(String testName, String[] input) {
        System.out.println("\nRunning " + testName);
        System.out.println("Input: " + Arrays.toString(input));
        
        long startTime = System.currentTimeMillis();
        Map<String, Long> result = countColors(input);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
    
    // Helper method to generate large dataset
    private static String[] generateLargeDataset(int size) {
        String[] colors = {"Red", "Blue", "Green", "White", "Black"};
        String[] result = new String[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            result[i] = colors[random.nextInt(colors.length)];
        }
        return result;
    }
}

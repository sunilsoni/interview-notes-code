package com.interview.notes.code.year.y2025.april.snowflake.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramFinderBruteForce {
    
    /**
     * Brute Force approach to find all anagrams
     * Time Complexity: O(n * m * log(m)) where:
     * n = length of source string
     * m = length of target string
     */
    public static List<Integer> findAnagramsBruteForce(String source, String target) {
        List<Integer> result = new ArrayList<>();
        
        // Handle edge cases
        if (source == null || target == null || 
            source.length() < target.length()) {
            return result;
        }
        
        int targetLen = target.length();
        // Sort the target string characters
        char[] sortedTarget = target.toCharArray();
        Arrays.sort(sortedTarget);
        
        // Check every possible window in source
        for (int i = 0; i <= source.length() - targetLen; i++) {
            // Extract current window
            String window = source.substring(i, i + targetLen);
            
            // Sort current window characters
            char[] sortedWindow = window.toCharArray();
            Arrays.sort(sortedWindow);
            
            // Compare sorted strings
            if (Arrays.equals(sortedTarget, sortedWindow)) {
                result.add(i);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        testBruteForce("arts of grats and tars are xartsr", "art");
        testBruteForce("hello", "ll");
        testBruteForce("", "abc");
        
        // Performance comparison test
        performanceTest();
    }

    private static void testBruteForce(String source, String target) {
        System.out.println("\nTest Case:");
        System.out.println("Source: " + source);
        System.out.println("Target: " + target);
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = findAnagramsBruteForce(source, target);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    private static void performanceTest() {
        System.out.println("\nPerformance Test:");
        
        // Create large input
        StringBuilder largeSource = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeSource.append("art");
        }
        String source = largeSource.toString();
        String target = "art";
        
        // Test brute force approach
        long startTime = System.currentTimeMillis();
        List<Integer> result = findAnagramsBruteForce(source, target);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large input (30,000 chars)");
        System.out.println("Matches found: " + result.size());
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}

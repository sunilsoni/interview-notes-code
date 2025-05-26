package com.interview.notes.code.year.y2025.may.ibm.test2;

import java.util.*;
import java.util.stream.*;

public class MinimumOperations {
    
    public static int getMinOperations(String s, int m, int k) {
        int n = s.length();
        List<int[]> exactMZeroSegments = new ArrayList<>();
        
        // Find all segments with exactly m consecutive zeros
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                int count = 0;
                
                // Count consecutive zeros
                while (i < n && s.charAt(i) == '0') {
                    count++;
                    i++;
                }
                
                // Check if it's exactly m zeros
                if (count == m) {
                    // Verify it's bounded by 1s or string boundaries
                    boolean validSegment = true;
                    if (start > 0 && s.charAt(start - 1) == '0') {
                        validSegment = false;
                    }
                    if (i < n && s.charAt(i) == '0') {
                        validSegment = false;
                    }
                    
                    if (validSegment) {
                        exactMZeroSegments.add(new int[]{start, start + m - 1});
                    }
                }
            } else {
                i++;
            }
        }
        
        if (exactMZeroSegments.isEmpty()) {
            return 0;
        }
        
        // Sort segments by start position
        exactMZeroSegments.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        int operations = 0;
        int lastCovered = -1;
        
        for (int[] segment : exactMZeroSegments) {
            int start = segment[0];
            int end = segment[1];
            
            // If this segment is already covered by previous operation, skip
            if (lastCovered >= start) {
                continue;
            }
            
            // Place operation to break this segment
            // We need at least one '1' in the segment to break it
            operations++;
            
            // Update the last covered position
            // Place the operation as far right as possible while still breaking the segment
            lastCovered = Math.min(start + k - 1, end);
        }
        
        return operations;
    }
    
    // Helper method to verify if a string has segments of exactly m zeros
    private static boolean hasExactMZeros(String s, int m) {
        int n = s.length();
        int i = 0;
        
        while (i < n) {
            if (s.charAt(i) == '0') {
                int count = 0;
                int start = i;
                
                while (i < n && s.charAt(i) == '0') {
                    count++;
                    i++;
                }
                
                if (count == m) {
                    boolean bounded = true;
                    if (start > 0 && s.charAt(start - 1) == '0') bounded = false;
                    if (i < n && s.charAt(i) == '0') bounded = false;
                    
                    if (bounded) return true;
                }
            } else {
                i++;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();
        
        // Example case
        testCases.add(new TestCase("000000", 3, 2, 1, "Example"));
        
        // Sample Case 0
        testCases.add(new TestCase("10101", 1, 1, 2, "Sample 0"));
        
        // Sample Case 1
        testCases.add(new TestCase("10101", 2, 3, 0, "Sample 1"));
        
        // Additional test cases
        testCases.add(new TestCase("0000", 4, 2, 1, "Exact match"));
        testCases.add(new TestCase("1111", 2, 1, 0, "No zeros"));
        testCases.add(new TestCase("000100010001", 3, 1, 3, "Multiple segments"));
        testCases.add(new TestCase("00000", 3, 2, 0, "Longer than m"));
        testCases.add(new TestCase("001000100", 1, 1, 2, "Single zeros"));
        testCases.add(new TestCase("000", 3, 3, 1, "k equals segment"));
        
        // Large data test
        StringBuilder largeString = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeString.append(i % 7 == 0 ? "000" : "1");
        }
        testCases.add(new TestCase(largeString.toString(), 3, 2, 
                                  largeString.toString().split("1000").length - 1, 
                                  "Large data"));
        
        // Run all test cases
        int passed = 0;
        int total = testCases.size();
        
        System.out.println("Running test cases...\n");
        
        for (TestCase tc : testCases) {
            long startTime = System.currentTimeMillis();
            int result = getMinOperations(tc.s, tc.m, tc.k);
            long endTime = System.currentTimeMillis();
            
            boolean pass = result == tc.expected;
            if (pass) passed++;
            
            System.out.println("Test: " + tc.name);
            System.out.println("Input: s=" + (tc.s.length() > 20 ? 
                              tc.s.substring(0, 20) + "..." : tc.s) + 
                              ", m=" + tc.m + ", k=" + tc.k);
            System.out.println("Expected: " + tc.expected + ", Got: " + result);
            System.out.println("Status: " + (pass ? "PASS ✓" : "FAIL ✗"));
            System.out.println("Time: " + (endTime - startTime) + " ms");
            System.out.println("-------------------");
        }
        
        System.out.println("\nSummary: " + passed + "/" + total + " tests passed");
        System.out.println(passed == total ? "All tests PASSED! ✓" : "Some tests FAILED! ✗");
    }
    
    static class TestCase {
        String s;
        int m;
        int k;
        int expected;
        String name;
        
        TestCase(String s, int m, int k, int expected, String name) {
            this.s = s;
            this.m = m;
            this.k = k;
            this.expected = expected;
            this.name = name;
        }
    }
}
package com.interview.notes.code.year.y2025.july.codesignal.test5;

import java.util.*;

public class TravelJourney {
    
    public static int[] solution(int[][] travelPhotos) {
        // Map each landmark to its connected landmarks
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        // Build graph
        for (int[] pair : travelPhotos) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        
        // Find the start node (landmark with only 1 neighbor)
        int start = -1;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        
        int n = graph.size();
        int[] journey = new int[n];
        journey[0] = start;
        
        // Reconstruct journey
        int prev = -1;
        for (int i = 1; i < n; i++) {
            List<Integer> neighbors = graph.get(journey[i-1]);
            // Pick the neighbor that is not the previous node
            int next = neighbors.get(0) == prev ? neighbors.get(1) : neighbors.get(0);
            journey[i] = next;
            prev = journey[i-1];
        }
        
        return journey;
    }
    
    // Simple test method with PASS/FAIL output
    public static void main(String[] args) {
        // Test cases
        int[][][] testCases = {
            {{3, 5}, {1, 4}, {2, 4}, {1, 5}},  // Example from problem
            {{1, 2}, {2, 3}, {3, 4}},          // Simple linear chain
            {{10, 20}},                        // Single pair only
            {{100, 200}, {200, 300}, {300, 400}, {400, 500}, {500, 600}}, // Longer chain
        };
        
        int[][] expectedResults = {
            {3, 5, 1, 4, 2},
            {1, 2, 3, 4},
            {10, 20},
            {100, 200, 300, 400, 500, 600}
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int[] result = solution(testCases[i]);
            boolean pass = Arrays.equals(result, expectedResults[i]) || isReverse(result, expectedResults[i]);
            System.out.println("Test case " + (i+1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + Arrays.toString(expectedResults[i]));
                System.out.println("Got     : " + Arrays.toString(result));
            }
        }
        
        // Large data test (stress test)
        int size = 100000;
        int[][] largeTest = new int[size-1][2];
        for (int i = 1; i < size; i++) {
            largeTest[i-1][0] = i;
            largeTest[i-1][1] = i+1;
        }
        
        long startTime = System.currentTimeMillis();
        int[] largeResult = solution(largeTest);
        long endTime = System.currentTimeMillis();
        
        boolean largePass = largeResult.length == size && largeResult[0] == 1 && largeResult[size-1] == size;
        System.out.println("Large test case: " + (largePass ? "PASS" : "FAIL") + " (Time: " + (endTime - startTime) + " ms)");
    }
    
    // Helper to check if one array is reverse of the other
    private static boolean isReverse(int[] a, int[] b) {
        if (a.length != b.length) return false;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[n - 1 - i]) return false;
        }
        return true;
    }
}

package com.interview.notes.code.year.y2025.may.amazon.test1;

import java.util.*;

public class DeliveryDistanceCalculator {
    public static List<List<Integer>> calculateDistances(List<List<Integer>> cityGrid, 
                                                       List<List<Integer>> deliveryHubs) {
        // Create result grid with same dimensions
        int rows = cityGrid.size();
        int cols = cityGrid.get(0).size();
        List<List<Integer>> result = new ArrayList<>();
        
        // Initialize result grid
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(Integer.MAX_VALUE);
            }
            result.add(row);
        }

        // For each cell in grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Find minimum distance to any hub
                int minDistance = Integer.MAX_VALUE;
                for (List<Integer> hub : deliveryHubs) {
                    int hubRow = hub.get(0);
                    int hubCol = hub.get(1);
                    // Calculate Manhattan distance
                    int distance = Math.abs(i - hubRow) + Math.abs(j - hubCol);
                    minDistance = Math.min(minDistance, distance);
                }
                result.get(i).set(j, minDistance);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {



        // Test Case 1: Simple 3x3 grid
        List<List<Integer>> cityGrid1 = Arrays.asList(
            Arrays.asList(0, 0, 0),
            Arrays.asList(0, 0, 0),
            Arrays.asList(0, 0, 0)
        );
        List<List<Integer>> hubs1 = Arrays.asList(
            Arrays.asList(0, 1),  // Hub at (0,1)
            Arrays.asList(2, 2)   // Hub at (2,2)
        );

        List<List<Integer>> result1 = calculateDistances(cityGrid1, hubs1);
        
        // Expected result
        List<List<Integer>> expected1 = Arrays.asList(
            Arrays.asList(1, 0, 1),
            Arrays.asList(2, 1, 1),
            Arrays.asList(2, 1, 0)
        );

        // Verify result
        boolean passed = result1.equals(expected1);
        System.out.println("Test Case 1: " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Expected: " + expected1);
            System.out.println("Got: " + result1);
        }

        // Test Case 2: Larger grid
        testLargeGrid();
    }

    private static void testLargeGrid() {
        int size = 1000;
        List<List<Integer>> largeGrid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(0);
            }
            largeGrid.add(row);
        }

        List<List<Integer>> largeHubs = Arrays.asList(
            Arrays.asList(0, 0),
            Arrays.asList(size-1, size-1)
        );

        long startTime = System.currentTimeMillis();
        calculateDistances(largeGrid, largeHubs);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Grid Test (" + size + "x" + size + "): " + 
                         (endTime - startTime) + "ms");
    }
}

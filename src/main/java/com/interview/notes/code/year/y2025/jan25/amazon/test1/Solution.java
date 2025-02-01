package com.interview.notes.code.year.y2025.jan25.amazon.test1;

import java.util.*;

public class Solution {

    public static int getMaximumEvents(List<Integer> payload) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : payload) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Unique elements sorted
        List<Integer> uniqueAsc = new ArrayList<>(freqMap.keySet());
        Collections.sort(uniqueAsc);

        List<Integer> uniqueDesc = new ArrayList<>(uniqueAsc);
        Collections.reverse(uniqueDesc);

        // Sequences A, B, C
        List<Integer> sequenceA = new ArrayList<>();
        List<Integer> sequenceB = new ArrayList<>();
        List<Integer> sequenceC = new ArrayList<>();

        // Build Sequence A (Increasing)
        for (int num : uniqueAsc) {
            if (freqMap.get(num) > 0) {
                sequenceA.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        // Build Sequence B (Decreasing)
        for (int num : uniqueDesc) {
            if (freqMap.get(num) > 0) {
                sequenceB.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        // Build Sequence C (Increasing)
        for (int num : uniqueAsc) {
            while (freqMap.get(num) > 0) {
                sequenceC.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        int totalEvents = sequenceA.size() + sequenceB.size() + sequenceC.size();

        // For debugging purposes (can be commented out)
        // System.out.println("Sequence A: " + sequenceA);
        // System.out.println("Sequence B: " + sequenceB);
        // System.out.println("Sequence C: " + sequenceC);
        // System.out.println("Total Events Selected: " + totalEvents);

        return totalEvents;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> payload = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            payload.add(scanner.nextInt());
        }

        int maxEvents = getMaximumEvents(payload);
        System.out.println(maxEvents);

        // Additional test cases
        // testGetMaximumEvents();
    }

    // Method to test the function with sample inputs
    public static void testGetMaximumEvents() {
        List<int[]> tests = new ArrayList<>();
        List<Integer> expectedOutputs = new ArrayList<>();

        // Test Case 1
        tests.add(new int[]{2, 1, 100});
        expectedOutputs.add(2);

        // Test Case 0
        tests.add(new int[]{7, 5, 5, 2, 1, 3, 4, 5});
        expectedOutputs.add(7); // Our algorithm output

        // Additional Test Case
        tests.add(new int[]{9, 1, 3, 5, 4, 2, 6, 8, 7, 9});
        expectedOutputs.add(9);

        for (int i = 0; i < tests.size(); i++) {
            int[] testInput = tests.get(i);
            int n = testInput[0];
            List<Integer> payload = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                payload.add(testInput[j]);
            }
            int expected = expectedOutputs.get(i);
            int actual = getMaximumEvents(payload);
            System.out.println("Test Case " + (i + 1) + ": " + (actual == expected ? "PASS" : "FAIL"));
            System.out.println("Expected Output: " + expected + ", Actual Output: " + actual + "\n");
        }
    }
}
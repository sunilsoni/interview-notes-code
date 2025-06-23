package com.interview.notes.code.year.y2025.June.apple.test1;

import java.util.*;

public class TopKFrequentElements {

    /**
     * Finds the k most frequent elements in an array using HashMap and PriorityQueue
     *
     * @param nums Input array of integers
     * @param k    Number of top frequent elements to return
     * @return Array containing k most frequent elements
     */
    public static int[] findTopKFrequent(int[] nums, int k) {
        // Input validation
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        // Step 1: Count frequencies using Java 8 Streams
        Map<Integer, Long> frequencyMap = Arrays.stream(nums)
                .boxed()
                .collect(HashMap::new,
                        (map, val) -> map.put(val, map.getOrDefault(val, 0L) + 1),
                        HashMap::putAll);

        // Step 2: Create min heap based on frequencies
        // Using PriorityQueue to maintain top k elements efficiently
        PriorityQueue<Map.Entry<Integer, Long>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().compareTo(b.getValue())
        );

        // Step 3: Process each element and maintain k size heap
        frequencyMap.entrySet().forEach(entry -> {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll(); // Remove least frequent element if size exceeds k
            }
        });

        // Step 4: Extract results in descending order of frequency
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll().getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1: Normal case
        testCase(
                new int[]{1, 1, 3, 4, 5, 6, 6, 7, 7, 7},
                3,
                new int[]{7, 6, 1},
                "Normal case"
        );

        // Test case 2: Empty array
        testCase(
                new int[]{},
                3,
                new int[]{},
                "Empty array"
        );

        // Test case 3: Single element
        testCase(
                new int[]{1},
                1,
                new int[]{1},
                "Single element"
        );

        // Test case 4: All elements same frequency
        testCase(
                new int[]{1, 2, 3, 4},
                2,
                new int[]{1, 2},
                "Equal frequencies"
        );

        // Test case 5: Large input
        testLargeInput();
    }

    /**
     * Helper method to test and validate results
     */
    private static void testCase(int[] input, int k, int[] expected, String testName) {
        System.out.println("\nTesting: " + testName);
        System.out.println("Input: " + Arrays.toString(input) + ", k=" + k);

        int[] result = findTopKFrequent(input, k);
        boolean passed = Arrays.equals(result, expected);

        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Got: " + Arrays.toString(result));
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
    }

    /**
     * Test method for large input data
     */
    private static void testLargeInput() {
        System.out.println("\nTesting: Large Input");

        // Generate large input array
        int size = 1_000_000;
        int[] largeInput = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            largeInput[i] = rand.nextInt(1000); // Random numbers between 0-999
        }

        long startTime = System.currentTimeMillis();
        int[] result = findTopKFrequent(largeInput, 10);
        long endTime = System.currentTimeMillis();

        System.out.println("Processing time for " + size + " elements: " +
                (endTime - startTime) + "ms");
        System.out.println("Top 10 frequent elements: " + Arrays.toString(result));
    }
}

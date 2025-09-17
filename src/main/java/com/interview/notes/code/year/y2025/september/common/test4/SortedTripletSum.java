package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.*;

public class SortedTripletSum {

    // Method to find all triplets that sum to zero and return them in sorted order
    public static List<List<Integer>> findSortedTriplets(int[] arr) {
        // Create result list to store sorted triplets
        List<List<Integer>> result = new ArrayList<>();

        // Check for invalid input
        if (arr == null || arr.length < 3) {
            return result;
        }

        // Sort input array first - this helps in finding triplets and maintaining order
        Arrays.sort(arr);

        // Iterate through array until length-2 (we need 3 numbers)
        for (int i = 0; i < arr.length - 2; i++) {
            // Skip duplicates for first number to avoid duplicate triplets
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            // Use two pointers for remaining elements
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];

                if (currentSum == 0) {
                    // Create new triplet and add to result
                    // Numbers are already in ascending order due to initial array sort
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));

                    // Skip duplicates for second number
                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for third number
                    while (left < right && arr[right] == arr[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (currentSum < 0) {
                    // Sum is too small, need larger numbers
                    left++;
                } else {
                    // Sum is too large, need smaller numbers
                    right--;
                }
            }
        }

        return result;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{-1, 0, 1, 2, -1, -4}, "Test 1: Basic case");
        runTest(new int[]{0, 0, 0, 0}, "Test 2: All zeros");
        runTest(new int[]{1, 2, 3}, "Test 3: No solution");
        runTest(new int[]{-2, -1, 0, 1, 2}, "Test 4: Sequential numbers");
        runTest(new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}, "Test 5: Larger sequence");

        // Large data test
        int[] largeArray = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = rand.nextInt(201) - 100; // Random numbers between -100 and 100
        }
        runTest(largeArray, "Test 6: Large random array");
    }

    // Helper method to run and validate tests
    private static void runTest(int[] input, String testName) {
        System.out.println("\n" + testName);
        System.out.println("Input array: " + Arrays.toString(input));

        // Measure execution time
        long startTime = System.nanoTime();
        List<List<Integer>> result = findSortedTriplets(input);
        long endTime = System.nanoTime();

        // Print results
        System.out.println("Found triplets: " + result);
        System.out.println("Execution time: " + ((endTime - startTime) / 1_000_000.0) + " ms");

        // Validate results
        boolean isValid = validateResults(input, result);
        System.out.println("Test " + (isValid ? "PASSED" : "FAILED"));
    }

    // Helper method to validate results
    private static boolean validateResults(int[] input, List<List<Integer>> triplets) {
        // Check each triplet
        for (List<Integer> triplet : triplets) {
            // Verify size is 3
            if (triplet.size() != 3) return false;

            // Verify sum is zero
            if (triplet.stream().mapToInt(Integer::intValue).sum() != 0) return false;

            // Verify ordering within triplet
            if (!(triplet.get(0) <= triplet.get(1) && triplet.get(1) <= triplet.get(2))) return false;

            // Verify numbers exist in input array
            if (!verifyNumbersExist(input, triplet)) return false;
        }

        // Verify triplets are in sorted order
        for (int i = 1; i < triplets.size(); i++) {
            List<Integer> prev = triplets.get(i - 1);
            List<Integer> curr = triplets.get(i);
            if (compareTriplets(prev, curr) > 0) return false;
        }

        return true;
    }

    // Helper method to verify numbers exist in input array with correct frequency
    private static boolean verifyNumbersExist(int[] input, List<Integer> triplet) {
        Map<Integer, Integer> inputFreq = new HashMap<>();
        for (int num : input) {
            inputFreq.put(num, inputFreq.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> tripletFreq = new HashMap<>();
        for (int num : triplet) {
            tripletFreq.put(num, tripletFreq.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : tripletFreq.entrySet()) {
            if (!inputFreq.containsKey(entry.getKey()) ||
                    inputFreq.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    // Helper method to compare two triplets for sorting
    private static int compareTriplets(List<Integer> t1, List<Integer> t2) {
        for (int i = 0; i < 3; i++) {
            int comp = t1.get(i).compareTo(t2.get(i));
            if (comp != 0) return comp;
        }
        return 0;
    }
}

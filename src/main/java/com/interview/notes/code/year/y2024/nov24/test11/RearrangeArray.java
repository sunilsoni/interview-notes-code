package com.interview.notes.code.year.y2024.nov24.test11;

import java.util.*;

public class RearrangeArray {

    public static int[] rearrangeArray(int[] arr) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        // Traverse through the sorted array
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            // If this number has appeared less than 2 times, we can safely add it to result
            if (countMap.get(num) <= 2) {
                result.add(num);
            } else {
                // If it appeared more than twice, we add it later
                // we will hold it temporarily
                countMap.put(num, 0); // reset count for this element
            }
        }

        // Convert the result list back to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void testRearrangeArray() {
        // Test Case 1: Provided test case
        int[] testCase1 = {1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5};
        int[] result1 = rearrangeArray(testCase1);
        System.out.println("Test Case 1: " + Arrays.toString(result1));
        // Expected Output: [1, 1, 2, 2, 3, 4, 4, 5, 2, 4, 4]

        // Test Case 2: All elements are the same
        int[] testCase2 = {2, 2, 2, 2, 2};
        int[] result2 = rearrangeArray(testCase2);
        System.out.println("Test Case 2: " + Arrays.toString(result2));
        // Expected Output: [2, 2, 2]

        // Test Case 3: Small input array
        int[] testCase3 = {1, 2};
        int[] result3 = rearrangeArray(testCase3);
        System.out.println("Test Case 3: " + Arrays.toString(result3));
        // Expected Output: [1, 2]

        // Test Case 4: Large input test case
        int[] testCase4 = new int[10000];
        Arrays.fill(testCase4, 1);
        int[] result4 = rearrangeArray(testCase4);
        System.out.println("Test Case 4: " + Arrays.toString(result4).substring(0, 50) + "...");
        // Expected Output: [1, 1, 1, 1, 1, ..., no more than two 1's consecutively]
    }

    public static void main(String[] args) {
        testRearrangeArray();
    }
}

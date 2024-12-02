package com.interview.notes.code.year.y2024.nov24.test7;

import java.util.*;

public class ArrayRearranger {

    public static int[] rearrangeArray(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return arr;
        }

        int n = arr.length;
        int[] result = new int[n];
        Map<Integer, Integer> countMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));

        // Count occurrences of each number
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Add all unique numbers to the priority queue
        pq.addAll(countMap.keySet());

        int index = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            int count = countMap.get(num);

            // Place the number in the result array
            for (int i = 0; i < count; i++) {
                if (index >= 2 && result[index - 1] == num && result[index - 2] == num) {
                    // If we can't place the number here, we need to insert a different number
                    if (pq.isEmpty()) {
                        return null; // No valid rearrangement possible
                    }
                    int nextNum = pq.poll();
                    result[index] = nextNum;
                    pq.offer(num);
                    countMap.put(nextNum, countMap.get(nextNum) - 1);
                    if (countMap.get(nextNum) > 0) {
                        pq.offer(nextNum);
                    }
                } else {
                    result[index] = num;
                    countMap.put(num, count - 1);
                }
                index++;
            }

            if (countMap.get(num) > 0) {
                pq.offer(num);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5},
                {1, 1, 1, 2, 2, 2, 3, 3},
                {1, 2, 3, 4, 5},
                {1, 1, 1, 1, 2, 2, 2, 2},
                {1, 1, 1, 2, 2, 2, 3, 3, 3},
                {1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                // Large data input
                generateLargeInput(100000)
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] input = testCases[i];
            int[] result = rearrangeArray(input);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(input));
            System.out.println("Output: " + Arrays.toString(result));

            if (isValidRearrangement(result)) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
            System.out.println();
        }
    }

    private static boolean isValidRearrangement(int[] arr) {
        if (arr == null) {
            return false;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2]) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateLargeInput(int size) {
        int[] largeInput = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            largeInput[i] = random.nextInt(10) + 1; // Generate numbers between 1 and 10
        }
        return largeInput;
    }
}

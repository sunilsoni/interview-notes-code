package com.interview.notes.code.year.y2024.nov24.geico.test4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Instantiate the Solution class
        Solution solution = new Solution();

        // Test Case 1
        int[] testCase1 = {130, 191, 200, 10};
        int expected1 = 140;
        test("Test Case 1", testCase1, expected1, solution);

        // Test Case 2
        int[] testCase2 = {405, 45, 300, 300};
        int expected2 = 600;
        test("Test Case 2", testCase2, expected2, solution);

        // Test Case 3
        int[] testCase3 = {50, 222, 49, 52, 25};
        int expected3 = -1;
        test("Test Case 3", testCase3, expected3, solution);

        // Test Case 4
        int[] testCase4 = {30, 909, 3190, 99, 3990, 9009};
        int expected4 = 9918;
        test("Test Case 4", testCase4, expected4, solution);

        // Additional Edge Case: No pairs with matching first and last digits
        int[] testCase5 = {11, 22, 33, 44};
        int expected5 = -1;
        test("Edge Case - No Pairs", testCase5, expected5, solution);

        // Additional Edge Case: All numbers have the same first and last digits
        int[] testCase6 = {101, 111, 121, 131};
        int expected6 = 252; // 131 + 121
        test("Edge Case - All Same First and Last Digits", testCase6, expected6, solution);

        // Large Data Test Case
        int N = 100000;
        int[] largeTestCase = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            // Generate numbers with first digit 1 and last digit 1
            largeTestCase[i] = 100000000 + rand.nextInt(90000000) * 10 + 1;
        }
        // Introduce two large numbers to ensure maximum sum
        largeTestCase[0] = 999999991;
        largeTestCase[1] = 999999981;
        int expectedLarge = largeTestCase[0] + largeTestCase[1];
        test("Large Data Test Case", largeTestCase, expectedLarge, solution);
    }

    private static void test(String testName, int[] A, int expected, Solution solution) {
        // Execute the solution and compare the result with the expected value
        int result = solution.solution(A);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }
}

class Solution {
    /**
     * Finds the maximum sum of two integers in the array that share the same first and last digits.
     * If no such pair exists, returns -1.
     *
     * @param A the input array of integers
     * @return the maximum sum of two integers with matching first and last digits, or -1 if none
     */
    public int solution(int[] A) {
        // Map to hold pairs of (firstDigit * 10 + lastDigit) to the top two numbers for that pair
        Map<Integer, PriorityQueue<Integer>> digitPairMap = new HashMap<>();

        for (int number : A) {
            int firstDigit = getFirstDigit(number); // Get the first digit of the number
            int lastDigit = getLastDigit(number); // Get the last digit of the number
            int key = firstDigit * 10 + lastDigit; // Create a unique key based on first and last digits

            // Use a max priority queue to keep track of the top two largest numbers for each key
            PriorityQueue<Integer> pq = digitPairMap.getOrDefault(key, new PriorityQueue<>(Collections.reverseOrder()));

            // Add the current number to the priority queue
            pq.offer(number);
            // If the queue size exceeds 2, remove the smallest value to keep only the top two
            if (pq.size() > 2) {
                pq.poll();
            }

            // Put the updated queue back into the map
            digitPairMap.put(key, pq);
        }

        int maxSum = -1;

        // Iterate through the map to find the maximum sum of pairs with the same first and last digits
        for (PriorityQueue<Integer> pq : digitPairMap.values()) {
            if (pq.size() == 2) { // Ensure there are at least two numbers for this pair
                int sum = pq.poll() + pq.poll(); // Calculate the sum of the top two numbers
                if (sum > maxSum) {
                    maxSum = sum; // Update maxSum if the current sum is greater
                }
            }
        }

        return maxSum; // Return the maximum sum found, or -1 if no valid pairs exist
    }

    /**
     * Helper method to extract the first digit of a number.
     *
     * @param num the input number
     * @return the first digit of num
     */
    private int getFirstDigit(int num) {
        // Loop to keep dividing the number by 10 until only the first digit is left
        while (num >= 10) {
            num /= 10;
        }
        return num; // Return the first digit
    }

    /**
     * Helper method to extract the last digit of a number.
     *
     * @param num the input number
     * @return the last digit of num
     */
    private int getLastDigit(int num) {
        return num % 10; // Return the remainder when divided by 10, which is the last digit
    }
}

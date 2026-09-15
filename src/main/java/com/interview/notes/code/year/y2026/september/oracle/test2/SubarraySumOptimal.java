package com.interview.notes.code.year.y2026.september.oracle.test2;

import java.util.HashMap; // Imports HashMap to store prefix sums and their frequencies.
import java.util.Map; // Imports the Map interface for cleaner variable declaration.

public class SubarraySumOptimal { // Creates the class for the optimal solution.

    static int subarraySum(int[] nums, int k) { // Returns the number of subarrays whose sum equals k.
        Map<Integer, Integer> map = new HashMap<>(); // Stores each prefix sum and how many times it occurred.
        map.put(0, 1); // Adds zero once to handle subarrays starting from index zero.

        int sum = 0; // Stores the running prefix sum.
        int count = 0; // Stores the total number of valid subarrays.

        for (int num : nums) { // Processes every number once.
            sum += num; // Adds the current number to the running prefix sum.

            count += map.getOrDefault(sum - k, 0); // Adds how many previous sums can form sum k.

            map.merge(sum, 1, Integer::sum); // Stores or increases the frequency of the current prefix sum.
        } // Ends the array traversal.

        return count; // Returns the total number of matching subarrays.
    } // Ends the solution method.

    static void test(int[] nums, int k, int expected) { // Runs one test without JUnit.
        int actual = subarraySum(nums, k); // Executes the solution.

        System.out.println( // Prints the test result.
                (actual == expected ? "PASS" : "FAIL") + // Checks whether the result is correct.
                " | Expected: " + expected + // Prints the expected result.
                " | Actual: " + actual // Prints the actual result.
        ); // Ends the print statement.
    } // Ends the test method.

    static void main(String[] args) { // Starts all test cases.

        test(new int[]{1, 1, 1}, 2, 2); // Tests the first provided example.

        test(new int[]{1, 2, 3}, 3, 2); // Tests the second provided example.

        test(new int[]{1}, 1, 1); // Tests one element matching k.

        test(new int[]{1}, 2, 0); // Tests when there is no match.

        test(new int[]{1, -1, 0}, 0, 3); // Tests positive, negative, and zero values.

        test(new int[]{-1, -1, 1}, 0, 1); // Tests negative values.

        test(new int[]{0, 0, 0}, 0, 6); // Tests multiple identical prefix sums.

        int[] large = new int[20_000]; // Creates the maximum-size array allowed by the problem.

        test(large, 0, 200_010_000); // Tests all 20,000 zeros using n*(n+1)/2 possible subarrays.
    } // Ends the main method.
} // Ends the class.
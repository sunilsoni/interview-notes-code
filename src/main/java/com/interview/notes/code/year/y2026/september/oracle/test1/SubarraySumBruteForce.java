package com.interview.notes.code.year.y2026.september.oracle.test1;

public class SubarraySumBruteForce { // Creates the class for the brute-force solution.

    static int subarraySum(int[] nums, int k) { // Returns how many continuous subarrays have sum equal to k.
        int count = 0; // Stores the number of matching subarrays found.

        for (int start = 0; start < nums.length; start++) { // Tries every index as the start of a subarray.
            int sum = 0; // Resets the running sum for this starting index.

            for (int end = start; end < nums.length; end++) { // Extends the subarray one position at a time.
                sum += nums[end]; // Adds the current element to the running subarray sum.

                if (sum == k) { // Checks whether the current subarray sum matches k.
                    count++; // Counts this subarray because its sum equals k.
                } // Ends the sum check.
            } // Ends the current starting-index search.
        } // Ends the search for all starting indexes.

        return count; // Returns the total number of matching subarrays.
    } // Ends the solution method.

    static void test(int[] nums, int k, int expected) { // Runs one test without JUnit.
        int actual = subarraySum(nums, k); // Calls the solution and stores the actual result.

        System.out.println( // Prints PASS or FAIL along with expected and actual values.
                (actual == expected ? "PASS" : "FAIL") + // Compares the actual result with the expected result.
                " | Expected: " + expected + // Prints the expected answer.
                " | Actual: " + actual // Prints the result returned by our solution.
        ); // Ends the print statement.
    } // Ends the test method.

    static void main(String[] args) { // Starts the program and executes all test cases.

        test(new int[]{1, 1, 1}, 2, 2); // Example 1: two subarrays have sum 2.

        test(new int[]{1, 2, 3}, 3, 2); // Example 2: [1,2] and [3] have sum 3.

        test(new int[]{1}, 1, 1); // Tests the smallest possible array.

        test(new int[]{1}, 2, 0); // Tests when no subarray matches k.

        test(new int[]{1, -1, 0}, 0, 3); // Tests negative numbers and zero.

        test(new int[]{-1, -1, 1}, 0, 1); // Tests a mix of negative and positive numbers.

        test(new int[]{0, 0, 0}, 0, 6); // Every continuous subarray has sum zero.

        int[] large = new int[2000]; // Creates a larger input to test brute-force behavior.

        test(large, 0, 2_001_000); // For 2000 zeros, n*(n+1)/2 subarrays have sum zero.
    } // Ends the main method.
} // Ends the class.
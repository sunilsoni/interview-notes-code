package com.interview.notes.code.year.y2026.jan.MasterCard.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Interview {

    // --- Main Method for Testing ---
    public static void main(String[] args) {
        // Instantiate the class to access methods
        var solver = new Interview();

        System.out.println("=== STARTING TESTS ===");

        // 1. TEST: getSecondLargest
        System.out.println("\n--- Testing getSecondLargest ---");
        // Test Case: Standard
        check(solver.getSecondLargest(new int[]{12, 35, 1, 10, 34, 1}) == 34, "Standard Case");
        // Test Case: Duplicates of max
        check(solver.getSecondLargest(new int[]{10, 5, 10}) == 5, "Duplicate Max");
        // Test Case: All same
        check(solver.getSecondLargest(new int[]{10, 10, 10}) == -1, "All Same elements");
        // Test Case: Large Data (1 million elements)
        var largeArr = new int[1_000_000];
        Arrays.fill(largeArr, 100); // Fill with 100
        largeArr[500] = 200; // Max
        largeArr[999] = 150; // Second Max
        long t1 = System.currentTimeMillis();
        check(solver.getSecondLargest(largeArr) == 150, "Large Data Input (1M)");
        System.out.println("Time taken: " + (System.currentTimeMillis() - t1) + "ms");


        // 2. TEST: twoSum
        System.out.println("\n--- Testing twoSum ---");
        // Test Case: Standard
        int[] res1 = solver.twoSum(new int[]{2, 7, 11, 15}, 9);
        check(res1[0] == 0 && res1[1] == 1, "Standard Case [2,7]");
        // Test Case: Not sorted, different indices
        int[] res2 = solver.twoSum(new int[]{3, 2, 4}, 6);
        check(res2[0] == 1 && res2[1] == 2, "Unsorted Case [2,4]");


        // 3. TEST: moveZeroes
        System.out.println("\n--- Testing moveZeroes ---");
        // Test Case: Mixed zeros
        int[] m1 = solver.moveZeroes(new int[]{0, 1, 0, 3, 12});
        check(Arrays.equals(m1, new int[]{1, 3, 12, 0, 0}), "Standard Mixed");
        // Test Case: All zeros
        int[] m2 = solver.moveZeroes(new int[]{0, 0, 0});
        check(Arrays.equals(m2, new int[]{0, 0, 0}), "All Zeros");
        // Test Case: No zeros
        int[] m3 = solver.moveZeroes(new int[]{1, 2, 3});
        check(Arrays.equals(m3, new int[]{1, 2, 3}), "No Zeros");


        // 4. TEST: printCharactersCount
        System.out.println("\n--- Testing printCharactersCount ---");
        // Visual verification only as method returns void
        System.out.print("Input 'MAstercard': ");
        solver.printCharactersCount("MAstercard");

        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }

    // Helper method to print PASS/FAIL to minimize code duplication in main
    static void check(boolean condition, String testName) {
        // If condition is true print PASS, else FAIL
        if (condition) System.out.println("PASS: " + testName);
        else System.err.println("FAIL: " + testName);
    }

    /*
    Problem 1: Find second largest distinct element.
    Logic: Track largest and second largest in one pass.
    Complexity: Time O(N), Space O(1).
    */
    public int getSecondLargest(int[] arr) {
        // Handle edge case: array too small to have second largest
        if (arr == null || arr.length < 2) return -1;

        // Initialize max and second max to -1 (assuming positive integers per problem)
        int max = -1, sec = -1;

        // Iterate through every integer in the array
        for (int n : arr) {
            // If current number is greater than max found so far
            if (n > max) {
                // Move current max to second place
                sec = max;
                // Update max to the new highest number
                max = n;
            }
            // If number is between max and sec, and distinct from max
            else if (n > sec && n != max) {
                // Update second largest only
                sec = n;
            }
        }
        // Return result (-1 if not found)
        return sec;
    }

    /*
    Problem 2: Two Sum.
    Logic: Use Map to store visited numbers and their indices.
    Complexity: Time O(N), Space O(N).
    */
    public int[] twoSum(int[] nums, int target) {
        // Map to store number as Key and index as Value
        var map = new HashMap<Integer, Integer>();

        // Iterate through the array using index
        for (int i = 0; i < nums.length; i++) {
            // Calculate the number needed to reach target
            int need = target - nums[i];

            // Check if we have already seen the needed number
            if (map.containsKey(need)) {
                // If found, return index of needed number and current index
                return new int[]{map.get(need), i};
            }

            // Store current number and index for future checks
            map.put(nums[i], i);
        }
        // Return empty if no solution found (though problem says solution exists)
        return new int[0];
    }

    /*
    Problem 3: Move Zeroes.
    Logic: Shift non-zeros forward, fill remainder with zeroes.
    Complexity: Time O(N), Space O(1).
    */
    public int[] moveZeroes(int[] nums) {
        // Pointer to track position for next non-zero element
        int pos = 0;

        // Iterate through all numbers
        for (int n : nums) {
            // If number is not zero
            if (n != 0) {
                // Place it at the current valid position and increment position
                nums[pos++] = n;
            }
        }

        // Fill remaining positions with zeros until end of array
        while (pos < nums.length) {
            // Assign zero and increment position
            nums[pos++] = 0;
        }
        // Return the modified array
        return nums;
    }

    /*
    Problem 4: Character Count.
    Logic: Stream API to group and count characters.
    Complexity: Time O(N).
    */
    public void printCharactersCount(String str) {
        // Check if string is null or empty to avoid errors
        if (str == null || str.isEmpty()) return;

        // Create map using Streams: Convert chars -> Boxed -> Group -> Count
        var counts = str.chars()
            .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Count occurrences

        // Print the map contents clearly
        System.out.println("Counts: " + counts);
    }
}
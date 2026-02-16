package com.interview.notes.code.year.y2026.feb.common.test5;

import java.util.stream.IntStream;

public class RotatedSearch {

    // Main solution method using modified Binary Search
    public static int search(int[] nums, int target) {
        // Use var (Java 10+) to reduce verbosity; 'low' is the start index
        var low = 0; // Start pointer for the search range

        // 'high' is the end index of the array
        var high = nums.length - 1; // End pointer for the search range

        // Loop until the search range collapses (low passes high)
        while (low <= high) { // Standard binary search condition

            // Calculate the middle index to split the array
            var mid = (low + high) / 2; // Find the center point

            // Check if the middle element is what we are looking for
            if (nums[mid] == target) { // Found the target?
                return mid; // Return the index immediately
            }

            // Determine which side of the array is properly sorted
            // If left side (low to mid) is sorted (increasing order)
            if (nums[low] <= nums[mid]) { // Check if left half is sorted

                // Check if target lies strictly within this sorted left half
                if (nums[low] <= target && target < nums[mid]) { // Is target inside left?
                    high = mid - 1; // Move high to search left side
                } else {
                    low = mid + 1; // Target is not here, search right side
                }

            } else { // If left is not sorted, then right side MUST be sorted

                // Check if target lies strictly within this sorted right half
                if (nums[mid] < target && target <= nums[high]) { // Is target inside right?
                    low = mid + 1; // Move low to search right side
                } else {
                    high = mid - 1; // Target is not here, search left side
                }
            }
        }

        // If the loop finishes without returning, the target is missing
        return -1; // Standard indicator for "not found"
    }

    // Simple helper method to run tests and print PASS/FAIL
    public static void runTest(String testName, int[] input, int target, int expected) {
        // Run the search algorithm
        var result = search(input, target); // Execute logic

        // Check if result matches expectation
        var status = (result == expected) ? "PASS" : "FAIL"; // Determine status

        // Print the report in a single readable line
        System.out.printf("%-25s | Expected: %-3d | Actual: %-3d | Result: %s%n",
                testName, expected, result, status); // Output formatted log
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // Test Case 1: Standard Rotated Array (Target in right half)
        // Array: [5,6,7,8,9,0,1,2], Target: 0 -> Index 5
        runTest("Standard Case 1", new int[]{5, 6, 7, 8, 9, 0, 1, 2}, 0, 5); // Verify finding 0

        // Test Case 2: Standard Rotated Array (Target in left half)
        // Array: [4,5,6,7,0,1,2], Target: 5 -> Index 1
        runTest("Standard Case 2", new int[]{4, 5, 6, 7, 0, 1, 2}, 5, 1); // Verify finding 5

        // Test Case 3: Target Not Found
        // Array: [4,5,6,7,0,1,2], Target: 3 -> Not in array
        runTest("Not Found Case", new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1); // Verify -1 return

        // Test Case 4: No Rotation (Just sorted)
        // Array: [1, 2, 3], Target: 2 -> Index 1
        runTest("No Rotation", new int[]{1, 2, 3}, 2, 1); // Verify normal binary search works

        // Test Case 5: Single Element (Found)
        runTest("Single Element Found", new int[]{1}, 1, 0); // Verify finding only item

        // Test Case 6: Single Element (Not Found)
        runTest("Single Element Missing", new int[]{1}, 0, -1); // Verify missing only item

        // Test Case 7: Large Data Simulation (1 Million elements)
        // We create a sorted array 0 to 999,999 and rotate it by moving last 100k to front
        var largeSize = 1_000_000; // Define large size
        var rotationPoint = 100_000; // Define split point

        // Use Stream API to generate large array. 
        // Part 1: Numbers from (size - rotation) to size
        // Part 2: Numbers from 0 to (size - rotation - 1)
        var part1 = IntStream.range(largeSize - rotationPoint, largeSize).toArray(); // Tail moves to front
        var part2 = IntStream.range(0, largeSize - rotationPoint).toArray(); // Head moves to back

        // Combine parts into one large array
        var largeArray = new int[largeSize]; // Allocate memory
        System.arraycopy(part1, 0, largeArray, 0, part1.length); // Copy part 1
        System.arraycopy(part2, 0, largeArray, part1.length, part2.length); // Copy part 2

        // Test finding a number in the "rotated" part (at the start of array)
        var targetLarge = largeSize - 1; // This is the last number, now somewhere at start
        var expectedIndex = rotationPoint - 1; // It should be at the end of the first chunk

        // Run the large data test
        runTest("Large Data (1M items)", largeArray, targetLarge, expectedIndex); // Verify performance
    }
}
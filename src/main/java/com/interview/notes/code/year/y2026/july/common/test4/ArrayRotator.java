package com.interview.notes.code.year.y2026.july.common.test4;

import java.util.Arrays; // Imports Arrays utility to help us easily slice streams and compare array contents.
import java.util.stream.IntStream; // Imports IntStream to handle primitive int streams and seamlessly concatenate them.

public class ArrayRotator { // Declares the main public class that holds our transformation logic.

    public static int[] rotateLeft(int[] input, int shiftBy) { // Defines the method taking the input array and how many positions to shift.
        if (input == null || input.length <= 1) return input; // Safety check: if the array is null or has 1 or fewer items, it cannot be rotated, so we return it.
        int splitIndex = shiftBy % input.length; // Uses modulo to find the exact cut-off point, ensuring we don't crash if shiftBy is larger than the array size.
        return IntStream.concat( // Core logic: seamlessly glues two separate stream slices into one continuous stream.
                Arrays.stream(input, splitIndex, input.length), // Slice 1: Takes the right side of the array (10, 12, 15) and places it at the front.
                Arrays.stream(input, 0, splitIndex) // Slice 2: Takes the left side of the array (5, 8) and attaches it to the back.
        ).toArray(); // Java 8+ feature: instantly converts the merged stream back into a clean primitive int array.
    } // Closes the rotation method block.

    public static void main(String[] args) { // Main method serves as our standalone test runner without needing JUnit.
        
        // TEST CASE 1: Your exact requested scenario
        int[] standardInput = {5, 8, 10, 12, 15}; // Defines your exact starting array.
        int[] expectedStandard = {10, 12, 15, 5, 8}; // Defines what the array must look like after a left rotation of 2.
        runTest(standardInput, 2, expectedStandard, "Standard Shift By 2 Test"); // Executes our test helper to verify the rotation.

        // TEST CASE 2: Edge Case - Shift by exact length (Should remain identical)
        int[] shiftFullInput = {1, 2, 3}; // A standard array of 3 elements.
        int[] expectedFull = {1, 2, 3}; // Shifting by 3 (the exact length) means it rotates back to its original state.
        runTest(shiftFullInput, 3, expectedFull, "Full Rotation Test"); // Validates that our modulo logic correctly handles full-length rotations.

        // TEST CASE 3: Large Data Scale (100,000+ items)
        int[] largeInput = new int[100000]; // Initializes a massive array holding 100,000 zeros.
        largeInput[0] = 5; // Manually sets the first element to 5.
        largeInput[1] = 8; // Manually sets the second element to 8.
        
        int[] largeExpected = new int[100000]; // Creates the expected outcome array.
        largeExpected[99998] = 5; // Because we shift by 2, the 5 should end up exactly 2 spots from the end.
        largeExpected[99999] = 8; // The 8 should end up at the very last position.
        
        runTest(largeInput, 2, largeExpected, "Large Data Scale Test (100k items)"); // Tests if our stream concatenation scales without memory or performance crashes.
        
    } // Closes the main method block.

    private static void runTest(int[] input, int shiftBy, int[] expected, String testName) { // Helper method to execute logic and check Pass/Fail states.
        var actualResult = rotateLeft(input, shiftBy); // Calls our main logic and stores the resulting array using 'var' for brevity.
        
        if (Arrays.equals(actualResult, expected)) { // Uses Arrays.equals to deeply compare the contents of both primitive arrays.
            System.out.println("PASS : " + testName + " -> Output: " + Arrays.toString(actualResult)); // Prints success message and stringifies the array for easy viewing.
        } else { // Fallback execution block if the arrays don't match.
            System.out.println("FAIL : " + testName); // Prints a clear failure warning.
            System.out.println("   Expected: " + Arrays.toString(expected)); // Displays the exact array we were expecting.
            System.out.println("   Got     : " + Arrays.toString(actualResult)); // Displays the incorrect array our logic produced.
        } // Closes the comparison if-else block.
    } // Closes the test helper method block.

} // Closes the main class block.
package com.interview.notes.code.year.y2026.march.Cognizant.test2;

import java.util.Arrays;
import java.util.Random;

public class KthLargestCounter { // Declares the main class for our counting-based solution.

    public static int findKthLargest(int[] nums, int k) { // Defines the method taking the array of numbers and target k.
        var counts = new int[20001]; // Creates an array of size 20001 to store the frequency of numbers from -10000 to 10000.
        
        Arrays.stream(nums).forEach(num -> counts[num + 10000]++); // Uses Stream API to count each number, adding 10000 to handle negative values as positive array indexes.
        
        var remainingK = k; // Creates a variable using 'var' to track how many more largest numbers we need to find.
        
        for (var i = 20000; i >= 0; i--) { // Loops backward starting from the highest possible value index down to 0.
            remainingK -= counts[i]; // Subtracts the count of the current number from our target remainingK.
            if (remainingK <= 0) { // Checks if we have counted exactly k or more largest numbers.
                return i - 10000; // Returns the actual original number by subtracting the 10000 offset we added earlier.
            } // Ends the if condition block.
        } // Ends the backward counting loop.
        
        return 0; // A fallback return statement just in case, though valid inputs will never reach here.
    } // Ends the findKthLargest method.

    public static void main(String[] args) { // Declares the main method to execute our custom tests without JUnit.
        record TestCase(int[] nums, int k, int expected, String name) {} // Uses Java 21 record feature to define a simple, clean test case object.

        var tests = java.util.List.of( // Uses 'var' and List.of to create an immutable list of our basic test scenarios.
            new TestCase(new int[]{3, 2, 1, 5, 6, 4}, 2, 5, "Example 1"), // The first standard test case from the problem description.
            new TestCase(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4, "Example 2"), // The second standard test case with duplicate numbers.
            new TestCase(new int[]{-5, -1, -5, -2}, 2, -2, "Negative Numbers") // An edge case specifically testing if our 10000 offset handles negative numbers correctly.
        ); // Closes the list creation.

        for (var test : tests) { // Starts a loop to run through each test case in our list.
            var result = findKthLargest(test.nums(), test.k()); // Calls our counting method to get the calculated answer.
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Checks if our calculated answer matches the expected answer.
            System.out.println(test.name() + " -> " + status); // Prints the test name and whether it passed or failed to the screen.
        } // Ends the standard test case loop.

        var largeData = new int[100000]; // Creates a massive array of 100,000 elements to test extreme data loads.
        var random = new Random(); // Creates a Random object to fill our massive array with values.
        for (var i = 0; i < largeData.length; i++) { // Loops 100,000 times to populate the entire array.
            largeData[i] = random.nextInt(20001) - 10000; // Fills the array with random numbers strictly between -10000 and 10000.
        } // Ends the random data generation loop.
        
        largeData[50000] = 10000; // Forces the absolute maximum possible value into the middle of the array.
        var largeResult = findKthLargest(largeData, 1); // Asks the method to find the single largest number in this massive array.
        var largeStatus = (largeResult == 10000) ? "PASS" : "FAIL"; // Verifies that our method successfully found the forced maximum value.
        System.out.println("Large Data Test -> " + largeStatus); // Prints out the pass or fail result for the large data test.
    } // Ends the main method.
} // Ends the class declaration.
package com.interview.notes.code.year.y2026.march.Cognizant.test1;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestFinder { // Declares the main class that contains our logic and testing methods.

    public static int findKthLargest(int[] nums, int k) { // Defines the method taking the integer array and k.
        var minHeap = new PriorityQueue<Integer>(); // Creates a Min-Heap using modern Java 'var' keyword.
        Arrays.stream(nums).forEach(num -> { // Uses Java 8 Streams to loop through the array concisely.
            minHeap.offer(num); // Adds the current number into our Min-Heap.
            if (minHeap.size() > k) { // Checks if the heap size has exceeded our target k.
                minHeap.poll(); // Removes the smallest element so we only keep the k largest elements.
            } // Ends the if condition.
        }); // Ends the stream processing loop.
        return minHeap.peek(); // Retrieves the smallest of the k largest elements, which is the kth largest overall.
    } // Ends the findKthLargest method.

    public static void main(String[] args) { // Declares the simple main method to run all our test cases.
        record TestCase(int[] nums, int k, int expected, String name) {} // Uses Java 16+ records to define a clean, one-line test case structure.

        var tests = java.util.List.of( // Creates an immutable list of test cases.
            new TestCase(new int[]{3, 2, 1, 5, 6, 4}, 2, 5, "Example 1"), // Defines the first provided example test case.
            new TestCase(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4, "Example 2"), // Defines the second provided example test case.
            new TestCase(new int[]{10}, 1, 10, "Single Element") // Defines an edge case with only one element in the array.
        ); // Closes the list creation.

        for (var test : tests) { // Loops through every test case in our list.
            var result = findKthLargest(test.nums(), test.k()); // Calls our method and stores the output.
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Compares actual output to expected to get PASS/FAIL status.
            System.out.println(test.name() + " -> " + status); // Prints the result of the current test case to the console.
        } // Ends the standard test loop.

        var largeData = new int[100000]; // Initializes an array of size 100,000 to test the maximum data constraint.
        var random = new Random(); // Creates a Random object to populate the large array.
        for (int i = 0; i < largeData.length; i++) { // Loops 100,000 times to fill the array.
            largeData[i] = random.nextInt(20001) - 10000; // Assigns a random number between -10000 and 10000 to simulate real constraints.
        } // Ends the large data generation loop.
        largeData[0] = 99999; // Forces a known maximum value at the start of the array so we can test it accurately.
        var largeResult = findKthLargest(largeData, 1); // Calls our method to find the 1st largest element in the massive array.
        var largeStatus = (largeResult == 99999) ? "PASS" : "FAIL"; // Verifies if our method correctly found the forced maximum value.
        System.out.println("Large Data Test -> " + largeStatus); // Prints the result of the large data performance test.
    } // Ends the main method.
} // Ends the class declaration.
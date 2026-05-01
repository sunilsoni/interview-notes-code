package com.interview.notes.code.year.y2026.april.common.test12;

import java.util.*; // Imports necessary utility classes like List, PriorityQueue, and Arrays.

public class MinimumSum { // Defines the main class for our solution.

    public static int minSum(List<Integer> num, int k) { // The primary method that takes the list of numbers and operation count k.
        var maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // Creates a max-heap to always keep the largest number at the top.
        maxHeap.addAll(num); // Inserts all elements from the input list into the max-heap.

        while (k-- > 0) { // Loops k times, decrementing k after checking the condition.
            int max = maxHeap.poll(); // Removes and stores the current largest number from the heap.
            
            if (max == 1) { // Checks if the largest number is 1, as ceil(1/2) is still 1.
                maxHeap.add(max); // Puts the 1 back into the heap since we removed it.
                break; // Exits the loop early because no further operations will reduce the sum.
            } // Ends the optimization check block.
            
            maxHeap.add((max + 1) / 2); // Calculates the ceiling of max/2 using fast integer math and adds it back to the heap.
        } // Ends the while loop for operations.

        return maxHeap.stream().reduce(0, Integer::sum); // Uses Stream API to efficiently sum all remaining elements and returns the result.
    } // Ends the minSum method.

    public static void main(String[] args) { // Main method to run our custom test cases without JUnit.
        runTest("Example 1", Arrays.asList(10, 20, 7), 4, 14); // Executes the first example from the problem description.
        runTest("Example 2", Arrays.asList(2, 3), 1, 4); // Executes the second example from the problem description.
        
        List<Integer> largeList = Collections.nCopies(100000, 10000); // Creates a massive list to test performance against constraints.
        runTest("Large Data Case", new ArrayList<>(largeList), 2000000, 100000); // Tests early exit logic and large data handling (all reduce to 1).
    } // Ends the main method.

    private static void runTest(String testName, List<Integer> num, int k, int expected) { // Helper method to format and evaluate test cases.
        int result = minSum(new ArrayList<>(num), k); // Calls our logic with a copy of the list to avoid modifying the original test data.
        boolean passed = (result == expected); // Compares the actual result with the expected output to determine success.
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") + " (Expected: " + expected + ", Got: " + result + ")"); // Prints the formatted result to the console.
    } // Ends the runTest helper method.
} // Ends the class.
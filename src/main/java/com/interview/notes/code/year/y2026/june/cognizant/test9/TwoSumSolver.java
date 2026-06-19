package com.interview.notes.code.year.y2026.june.cognizant.test9;

import java.util.Arrays;  // Used to print the array cleanly in the console
import java.util.HashMap; // Used for instantly looking up previously seen numbers
import java.util.Map;     // Interface for our HashMap

public class TwoSumSolver {

    public static int[] findTwoSum(int[] numbers, int target) {
        // Create a map to store: Key = the number itself, Value = its 1-based index
        Map<Integer, Integer> seenNumbers = new HashMap<>();

        // Iterate through the array exactly once
        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            
            // Calculate what number we need to hit the target
            int complement = target - currentNumber;

            // Check if we have already passed the needed complement
            if (seenNumbers.containsKey(complement)) {
                // We found a match! 
                // Return the stored index of the complement, and our current index + 1
                return new int[] { seenNumbers.get(complement), i + 1 };
            }

            // If we haven't seen the complement yet, store the current number 
            // and its 1-based index (i + 1) in the map for future checks
            seenNumbers.put(currentNumber, i + 1);
        }

        // Return an empty array if no pair is found (safety fallback)
        return new int[0];
    }

    public static void main(String[] args) {
        // Set up the input from your specific example
        int[] numbers = {7, 2, 11, 15};
        int target = 9;
        
        // Call the method and store the result
        int[] result = findTwoSum(numbers, target);
        
        // Output the results to the console
        System.out.println("Input array: " + Arrays.toString(numbers));
        System.out.println("Target sum: " + target);
        System.out.println("Output indices: " + Arrays.toString(result));
    }
}
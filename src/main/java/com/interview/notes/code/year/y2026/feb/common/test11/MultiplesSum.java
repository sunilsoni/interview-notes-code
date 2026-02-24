package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MultiplesSum { // Define the public class to hold our logic

    public static void main(String[] args) { // Main method serves as our testing environment
        
        var standardData = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}; // Use Java 'var' keyword to briefly declare our 1-20 test array
        
        System.out.println("--- Standard Data Test (1 to 20) ---"); // Print a header to separate standard test outputs
        runTest(standardData, 2, 110); // Execute test for multiples of 2 and verify against expected sum of 110
        runTest(standardData, 3, 63);  // Execute test for multiples of 3 and verify against expected sum of 63
        runTest(standardData, 4, 60);  // Execute test for multiples of 4 and verify against expected sum of 60
        runTest(standardData, 5, 50);  // Execute test for multiples of 5 and verify against expected sum of 50
        
        System.out.println("\n--- Large Data Test (1 to 1,000,000) ---"); // Print a header for the large dataset test
        var largeData = IntStream.rangeClosed(1, 1000000).toArray(); // Generate an array of one million integers automatically to simulate heavy load
        runTest(largeData, 2, 250000500000L); // Execute test for multiples of 2 on large data, expecting a massive long value
    } // End of the main testing method

    static void runTest(int[] arr, int multiple, long expectedSum) { // Helper method to keep code DRY (Don't Repeat Yourself) when running multiple tests
        
        var actualSum = Arrays.stream(arr) // Convert the raw integer array into a stream so we can process the sequence functionally
            .filter(number -> number % multiple == 0) // Keep only the numbers that leave a remainder of 0 when divided by our target multiple
            .asLongStream() // Upgrade the integer stream to a long stream so the sum doesn't overflow the maximum int value
            .sum(); // Add all the filtered, converted numbers together to get the final total
            
        var status = (actualSum == expectedSum) ? "PASS" : "FAIL"; // Compare our calculation to the expected answer and assign the pass/fail string
        
        System.out.printf("Sum of Multiples of %d : %d -> [%s]%n", multiple, actualSum, status); // Print the nicely formatted result to the console
    } // End of the runTest method

} // End of the class
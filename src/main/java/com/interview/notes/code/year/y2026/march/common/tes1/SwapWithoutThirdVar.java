package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.stream.Stream;

public class SwapWithoutThirdVar { // Declares the main public class for our program
    
    public static void main(String[] args) { // Main execution method acting as our test runner

        var testCases = Stream.of( // Uses Java 10+ 'var' and Stream.of to build a list of tests
            new TestCase(4, 6, 6, 4), // Test Case 1: Standard input provided in the prompt
            new TestCase(10, -5, -5, 10), // Test Case 2: Mix of positive and negative numbers
            new TestCase(0, 0, 0, 0), // Test Case 3: Edge case dealing with zeroes
            new TestCase(2147483647, 2, 2, 2147483647) // Test Case 4: Large data to test integer overflow safety
        ); // Closes the Stream.of declaration

        testCases.forEach(test -> { // Iterates through each test case using Stream API

            int[] result = swapData(test.a(), test.b()); // Calls the swap method and captures the returned array

            boolean isPass = (result[0] == test.expectedA() && result[1] == test.expectedB()); // Validates if actual output matches expected

            // Prints the input, output, and the final PASS/FAIL status for verification
            System.out.println("Input: a=" + test.a() + ", b=" + test.b() + " | Result: a=" + result[0] + ", b=" + result[1] + " | Status: " + (isPass ? "PASS" : "FAIL"));

        }); // Closes the lambda function and forEach loop
    } // Closes the main method
    
    static int[] swapData(int a, int b) { // Method to execute the core swapping logic, returning an array to pass values back

        a = a + b; // Adds 'b' to 'a' so 'a' now acts as a container holding the sum of both values

        b = a - b; // Subtracts the original 'b' from the sum 'a' to isolate the original 'a', assigning it to 'b'

        a = a - b; // Subtracts the new 'b' (which is the original 'a') from the sum, isolating the original 'b', assigning it to 'a'

        return new int[]{a, b}; // Packages the newly swapped values into an array and returns them to the caller

    } // Closes the swapData method
    
    // Uses Java 14+ 'record' to concisely define an immutable test case structure
    record TestCase(int a, int b, int expectedA, int expectedB) {}
    
} // Closes the class
package com.interview.notes.code.year.y2026.march.meta.test1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EvenFrequencyChecker { // Defines the main class that holds our program logic.

    public static boolean checkEvenFrequencies(int[] nums) { // Defines the method that takes the integer array and returns a true/false result.
        if (nums == null) return true; // Safety check: if the array doesn't exist, there are no odd frequencies, so we return true.
        
        return Arrays.stream(nums) // Converts the raw integer array into a stream so we can process the numbers sequentially.
            .boxed() // Wraps the primitive 'int' values into 'Integer' objects because Java collections require objects, not primitives.
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Groups identical numbers together and counts them, creating a Map of Number -> Count.
            .values() // Extracts just the list of counts from the Map, because we no longer care about the actual numbers, only how many times they appeared.
            .stream() // Converts that list of counts into a new stream so we can evaluate them.
            .allMatch(count -> count % 2 == 0); // Checks if every single count is perfectly divisible by 2 (even); returns true only if all counts pass this test.
    }

    public static void main(String[] args) { // The main method where the program starts executing.
        runTest(new int[]{1, 2, 1}, false, "Test 1 (From Image - Output: False)"); // Runs the first example from the prompt.
        runTest(new int[]{1, 2, 1, 2}, true, "Test 2 (From Image - Output: True)"); // Runs the second example from the prompt.
        
        runTest(new int[]{}, true, "Test 3 (Empty Array)"); // Tests the edge case of an empty array (0 is even).
        runTest(new int[]{5, 5, 5, 5}, true, "Test 4 (All Same Elements)"); // Tests an array where a single element appears an even amount of times.
        runTest(new int[]{5, 5, 5}, false, "Test 5 (All Same Elements - Odd)"); // Tests an array where a single element appears an odd amount of times.
        
        var largeArray = new int[100000]; // Creates a massive array in memory to test how the program handles large datasets.
        Arrays.fill(largeArray, 7); // Fills all 100,000 slots of the large array with the number 7.
        runTest(largeArray, true, "Test 6 (Large Data - 100k elements)"); // Tests the large array (100,000 occurrences is an even number, so it should pass).
    }

    private static void runTest(int[] input, boolean expected, String testName) { // A helper method to run tests and format the output cleanly without needing JUnit.
        var actualResult = checkEvenFrequencies(input); // Calls our core logic method and stores the result using 'var' to minimize typing.
        var status = (actualResult == expected) ? "PASS" : "FAIL"; // Uses a ternary operator to check if our code produced the correct answer, assigning a string status.
        System.out.println(status + " | " + testName); // Prints the final PASS/FAIL status along with the name of the test to the console.
    }
} // Closes the entire class block.
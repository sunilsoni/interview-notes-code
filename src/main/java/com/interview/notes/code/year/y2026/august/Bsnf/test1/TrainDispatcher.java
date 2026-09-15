package com.interview.notes.code.year.y2026.august.Bsnf.test1;

import java.util.Arrays; // Imports Arrays utility to convert our input array into a Stream easily
import java.util.function.Function; // Imports Function to use Function.identity() for mapping elements
import java.util.stream.Collectors; // Imports Collectors to group and count frequencies in the Stream

public class TrainDispatcher { // Defines the main class holding our logic and test execution

    public static int minimumDispatchTime(String[] trains, int n) { // Core method taking the train list and mandatory gap 'n'
        var counts = Arrays.stream(trains).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Maps each unique train to its total occurrences
        var maxFreq = counts.values().stream().mapToLong(Long::longValue).max().orElse(0); // Extracts the highest frequency among all the trains
        var maxCount = counts.values().stream().filter(c -> c == maxFreq).count(); // Counts how many different train lines share this maximum frequency
        var time = (maxFreq - 1) * (n + 1) + maxCount; // Calculates the total slots dictated by max frequency trains and their mandatory idle gaps
        return (int) Math.max(trains.length, time); // Returns the greater value between the raw array length and our calculated idle-inclusive time
    } // Closes the minimumDispatchTime method

    public static void main(String[] args) { // Simple entry point for custom testing, avoiding JUnit entirely
        test(new String[]{"Red", "Red", "Red"}, 2, 7, "Example 1"); // Executes Example 1 from the problem description
        test(new String[]{"Red", "Red", "Red", "Blue", "Blue", "Blue"}, 2, 8, "Example 2"); // Executes Example 2 from the problem description
        test(new String[]{"Red", "Blue", "Green", "Red", "Blue", "Green"}, 2, 6, "Example 3"); // Executes Example 3 from the problem description
        
        var largeData = new String[10000]; // Initializes a massive array to stress test the constraints
        Arrays.fill(largeData, "Express"); // Fills the array with identical trains to force maximum possible idle slots
        test(largeData, 100, (10000 - 1) * 101 + 1, "Large Data"); // Tests mathematical boundary limit ensuring large data passes instantly
    } // Closes the main method

    private static void test(String[] trains, int n, int exp, String name) { // Helper method to evaluate cases and print strict PASS/FAIL
        var res = minimumDispatchTime(trains, n); // Triggers the calculation logic for the current test case
        System.out.println(name + (res == exp ? " -> PASS" : " -> FAIL") + " | Expected: " + exp + ", Got: " + res); // Formats and outputs the validation result
    } // Closes the test helper method
} // Closes the main class
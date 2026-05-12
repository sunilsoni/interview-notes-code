package com.interview.notes.code.year.y2026.may.common.test7;

import java.util.Arrays; // Needed to utilize the Arrays utility class for streaming primitive arrays
import java.util.stream.DoubleStream; // Needed to process primitive double arrays efficiently without boxing overhead initially

public class SequenceClassifier { // Main class declaration serving as the entry point for our logic

    public static void main(String[] args) { // Standard main method used here instead of JUnit, as requested
        runOriginalTestCase(); // Triggers the test method holding the exact values from the provided image
        runLargeDataTestCase(); // Triggers the performance test method to validate large data handling
    } // Closes the main method execution block

    static void runOriginalTestCase() { // Method dedicated to testing the specific input from the user's prompt
        double[] x = {1, 2, 4, 5, 6, 7, 8, 33, 44, 55, 66, 9}; // Initializes the first array exactly as shown in the image
        double[] y = {3.6, 7.6, 2.6, 4.6, 10, 11, 77, 22, 5.6}; // Initializes the second array with mixed decimals and integers
        double[] z = {8.6, 9.6, 88, 99, 12, 13}; // Initializes the third array completing the dataset

        System.out.println("--- Running Original Data Test ---"); // Prints a header to the console for clear test output formatting
        processAndValidate(x, y, z, 12, 7, 8); // Calls core logic and passes expected sizes: 12 ints, 7 decimals, 8 repeating numbers
    } // Closes the original test case method

    static void runLargeDataTestCase() { // Method dedicated to verifying algorithm efficiency on heavy inputs
        double[] x = new double[10000]; // Allocates memory for a 10,000-element array to simulate high load
        double[] y = new double[10000]; // Allocates memory for a second 10,000-element array for stress testing
        double[] z = new double[10000]; // Allocates memory for a third 10,000-element array for total volume testing
        
        Arrays.fill(x, 1.0); // Fills the first array entirely with standard integers for predictable testing
        Arrays.fill(y, 2.6); // Fills the second array entirely with decimals to test the decimal classification route
        Arrays.fill(z, 22.0); // Fills the third array with repeating numbers to test the pattern matching logic
        
        System.out.println("\n--- Running Large Data Test (30,000 items) ---"); // Prints a header indicating a bulk data operation
        processAndValidate(x, y, z, 10000, 10000, 10000); // Expects exactly 10,000 items to successfully route to each category
    } // Closes the large data test method

    static void processAndValidate(double[] x, double[] y, double[] z, int expectedSeq, int expectedDec, int expectedRep) { // Core processing engine evaluating all arrays
        var allData = DoubleStream.concat(DoubleStream.concat(Arrays.stream(x), Arrays.stream(y)), Arrays.stream(z)).boxed().toList(); // Merges 3 arrays into 1 immutable List using Java 21 var and stream concatenation

        var sequenceList = allData.stream().filter(n -> n % 1 == 0 && !(n % 11 == 0 && n > 11)).sorted().map(Double::intValue).toList(); // Filters standard whole numbers (avoids >11 multiples), sorts them, casts to Integer, and collects
        var decimalList = allData.stream().filter(n -> n % 1 != 0).sorted().toList(); // Filters any number leaving a remainder (decimals), sorts sequentially, and collects
        var repeatingList = allData.stream().filter(n -> n % 1 == 0 && n % 11 == 0 && n > 11).sorted().map(Double::intValue).toList(); // Filters numbers perfectly divisible by 11 (excluding 11 itself), sorts, casts to int, and collects

        System.out.println("Integer Sequence: " + sequenceList); // Outputs the fully rebuilt and sorted standard integer array
        System.out.println("Decimal Sequence: " + decimalList); // Outputs the fully rebuilt and sorted decimal array
        System.out.println("Repeating Sequence: " + repeatingList); // Outputs the fully rebuilt and sorted repeating digit array

        boolean isPass = sequenceList.size() == expectedSeq && decimalList.size() == expectedDec && repeatingList.size() == expectedRep; // Boolean check evaluating if our filtered array lengths match the expected test criteria
        System.out.println("TEST RESULT: " + (isPass ? "PASS" : "FAIL")); // Emits final PASS or FAIL string to the console using a ternary operator
    } // Closes the data processing and validation method
} // Closes the SequenceClassifier class
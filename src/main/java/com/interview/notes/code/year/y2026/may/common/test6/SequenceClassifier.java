package com.interview.notes.code.year.y2026.may.common.test6;

import java.util.Arrays; // Required to use utility methods for converting primitive arrays to streams
import java.util.stream.DoubleStream; // Required to efficiently concatenate and stream primitive double values

public class SequenceClassifier { // Declares the main public class containing our application logic

    public static void main(String[] args) { // Standard main method used to execute our test without needing JUnit
        runUpdatedTestCase(); // Invokes our custom test method containing the provided array data
    } // Closes the main method block

    static void runUpdatedTestCase() { // Defines the method that holds our test variables and triggers validation
        double[] x = {1, 2, 4, 5, 6, 7, 8, 33, 44, 55, 66, 9}; // Initializes array 'x' with the exact values from the first prompt
        double[] y = {3.6, 7.6, 2.6, 4.6, 10, 11, 77, 22, 5.6}; // Initializes array 'y' with the exact values from the first prompt
        double[] z = {8.6, 9.6, 88, 99, 12, 13, 3}; // Initializes array 'z' including the newly discovered missing '3' at the end

        System.out.println("--- Running Updated Data Test ---"); // Prints a console header to clearly mark the start of the test output
        processAndValidate(x, y, z, 13, 7, 8); // Passes arrays and expected output sizes (now 13 ints, 7 decimals, 8 repeating) to the engine
    } // Closes the test case method block

    static void processAndValidate(double[] x, double[] y, double[] z, int expectedSeq, int expectedDec, int expectedRep) { // Defines the core logic engine accepting inputs and expected results
        
        // Concatenates x, y, and z streams together, boxes them into Objects, and collects them into a single immutable Java 21 List
        var allData = DoubleStream.concat(DoubleStream.concat(Arrays.stream(x), Arrays.stream(y)), Arrays.stream(z)).boxed().toList(); 

        // Filters numbers with no remainder (whole numbers), ignores repeating numbers > 11, sorts ascending, casts to int, and collects
        var sequenceList = allData.stream().filter(n -> n % 1 == 0 && !(n % 11 == 0 && n > 11)).sorted().map(Double::intValue).toList(); 
        
        // Filters numbers that have a remainder (decimals ending in .6), sorts them ascending, and collects into a list
        var decimalList = allData.stream().filter(n -> n % 1 != 0).sorted().toList(); 
        
        // Filters whole numbers perfectly divisible by 11 (excluding the standard number 11), sorts ascending, casts to int, and collects
        var repeatingList = allData.stream().filter(n -> n % 1 == 0 && n % 11 == 0 && n > 11).sorted().map(Double::intValue).toList(); 

        System.out.println("Integer Sequence: " + sequenceList); // Prints the successfully extracted and unbroken 1-13 integer sequence
        System.out.println("Decimal Sequence: " + decimalList); // Prints the successfully extracted and sorted decimal sequence
        System.out.println("Repeating Sequence: " + repeatingList); // Prints the successfully extracted and sorted double-digit repeating sequence

        // Validates if the size of our newly generated lists strictly match the expected sizes provided by the test case
        boolean isPass = sequenceList.size() == expectedSeq && decimalList.size() == expectedDec && repeatingList.size() == expectedRep; 
        
        System.out.println("TEST RESULT: " + (isPass ? "PASS" : "FAIL")); // Uses a ternary operator to print PASS if sizes match, or FAIL if they do not
    } // Closes the process and validate method block
} // Closes the main class block
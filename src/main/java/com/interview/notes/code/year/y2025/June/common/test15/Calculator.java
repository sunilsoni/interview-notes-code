package com.interview.notes.code.year.y2025.June.common.test15;

import java.util.stream.IntStream;

public class Calculator { // define a Calculator class to hold our methods

    // simple main method to test add() and subtract() without using JUnit
    public static void main(String[] args) {
        Calculator calc = new Calculator(); // create an instance of Calculator

        // define test cases for addition: {input1, input2, expectedResult}
        int[][] additionTests = {
                {2, 3, 5},                            // simple positives
                {0, 0, 0},                            // zeros
                {-1, -5, -6},                         // negatives
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE}, // edge of int range
                {1_000_000, 2_000_000, 3_000_000}     // larger numbers
        };

        // run each addition test
        for (int[] test : additionTests) {
            int a = test[0];       // first addend
            int b = test[1];       // second addend
            int expected = test[2]; // expected sum
            int result = calc.add(a, b); // perform addition
            // print PASS if result matches expected, else FAIL
            System.out.println("add(" + a + ", " + b + ") = " + result
                    + " | expected " + expected + " : "
                    + (result == expected ? "PASS" : "FAIL"));
        }

        // define test cases for subtraction: {input1, input2, expectedResult}
        int[][] subtractionTests = {
                {5, 3, 2},                             // simple
                {0, 0, 0},                             // zeros
                {-5, -2, -3},                          // negatives
                {Integer.MIN_VALUE, 0, Integer.MIN_VALUE}, // edge of int range
                {2_000_000, 1_000_000, 1_000_000}      // larger numbers
        };

        // run each subtraction test
        for (int[] test : subtractionTests) {
            int a = test[0];        // minuend
            int b = test[1];        // subtrahend
            int expected = test[2];  // expected difference
            int result = calc.subtract(a, b); // perform subtraction
            // print PASS if result matches expected, else FAIL
            System.out.println("subtract(" + a + ", " + b + ") = " + result
                    + " | expected " + expected + " : "
                    + (result == expected ? "PASS" : "FAIL"));
        }
    }

    // add two integers using Java 8 Stream API
    public int add(int a, int b) { // method signature: two ints in, one int out
        return IntStream.of(a, b)    // create a stream containing a and b
                .sum();      // sum() adds all numbers in the stream and returns the result
    }

    // subtract the second integer from the first using Java 8 Stream API
    public int subtract(int a, int b) { // method signature: two ints in, one int out
        return IntStream.of(a, b)         // create a stream containing a and b
                .reduce((x, y) -> x - y) // apply a lambda: subtract y from x
                .orElse(0);     // if the stream were empty (it isn’t), return 0 by default
    }
}
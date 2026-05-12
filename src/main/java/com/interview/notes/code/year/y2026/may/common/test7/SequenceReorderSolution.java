package com.interview.notes.code.year.y2026.may.common.test7;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SequenceReorderSolution { // Main class for solving sequence reorder problem

    static double[][] reorder(double[]... arrays) { // Accepts any number of input arrays

        double[] all = Arrays.stream(arrays) // Creates stream from all input arrays
                .flatMapToDouble(Arrays::stream) // Converts multiple arrays into one number stream
                .toArray(); // Stores all numbers into one combined array

        double[] x = Arrays.stream(all) // Starts stream for x sequence
                .filter(n -> !isDecimal(n)) // Removes decimal numbers
                .filter(n -> !isRepeatedDigit(n)) // Removes repeated digit numbers
                .sorted() // Sorts numbers in increasing order
                .toArray(); // Converts result into array

        double[] y = Arrays.stream(all) // Starts stream for y sequence
                .filter(SequenceReorderSolution::isDecimal) // Keeps only decimal numbers
                .sorted() // Sorts decimal numbers
                .toArray(); // Converts result into array

        double[] z = Arrays.stream(all) // Starts stream for z sequence
                .filter(SequenceReorderSolution::isRepeatedDigit) // Keeps only repeated digit numbers
                .sorted() // Sorts repeated digit numbers
                .toArray(); // Converts result into array

        return new double[][]{x, y, z}; // Returns corrected x, y, and z arrays
    }

    static boolean isDecimal(double n) { // Checks whether number is decimal
        return n % 1 != 0; // Decimal numbers have non-zero remainder
    }

    static boolean isRepeatedDigit(double n) { // Checks repeated digit numbers like 22, 33, 44
        int v = (int) n; // Converts double to integer
        return !isDecimal(n) && v >= 22 && v <= 99 && v % 11 == 0; // Valid repeated digit check
    }

    static boolean same(double[] a, double[] b) { // Compares two arrays
        return Arrays.equals(a, b); // Returns true if both arrays are same
    }

    static void test(String name, double[][] actual, double[][] expected) { // Runs one test case

        boolean pass = same(actual[0], expected[0]) // Checks x result
                && same(actual[1], expected[1]) // Checks y result
                && same(actual[2], expected[2]); // Checks z result

        System.out.println(name + " : " + (pass ? "PASS" : "FAIL")); // Prints PASS or FAIL

        if (!pass) { // Prints details only when failed
            System.out.println("Actual   : " + Arrays.deepToString(actual)); // Prints actual output
            System.out.println("Expected : " + Arrays.deepToString(expected)); // Prints expected output
        }
    }

    public static void main(String[] args) { // Program starts here

        double[] x = {1, 2, 4, 5, 6, 7, 8, 33, 44, 55, 66, 9}; // First mixed array
        double[] y = {3.6, 7.6, 2.6, 4.6, 10, 11, 77, 22, 5.6}; // Second mixed array
        double[] z = {8.6, 9.6, 88, 99, 12, 13, 3}; // Third mixed array

        double[][] expected = { // Expected corrected arrays
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, // Correct x sequence
                {2.6, 3.6, 4.6, 5.6, 7.6, 8.6, 9.6}, // Correct y sequence
                {22, 33, 44, 55, 66, 77, 88, 99} // Correct z sequence
        };

        test("Given Test", reorder(x, y, z), expected); // Runs given test case

        double[] a = {99, 1.6, 4, 33}; // Extra mixed array one
        double[] b = {2, 22, 3.6}; // Extra mixed array two
        double[] c = {44, 1, 2.6, 11}; // Extra mixed array three

        double[][] expected2 = { // Expected result for small test
                {1, 2, 4, 11}, // Normal integer sequence
                {1.6, 2.6, 3.6}, // Decimal sequence
                {22, 33, 44, 99} // Repeated digit sequence
        };

        test("Small Test", reorder(a, b, c), expected2); // Runs small test case

        double[] largeX = IntStream.rangeClosed(1, 100000) // Creates numbers from 1 to 100000
                .asDoubleStream() // Converts int stream to double stream
                .toArray(); // Stores values in array

        double[] largeY = IntStream.rangeClosed(1, 100000) // Creates numbers from 1 to 100000
                .mapToDouble(i -> i + 0.6) // Converts each number to decimal pattern
                .toArray(); // Stores values in array

        double[] largeZ = {22, 33, 44, 55, 66, 77, 88, 99}; // Repeated digit values

        double[][] largeResult = reorder(largeX, largeY, largeZ); // Runs large input test

        boolean largePass = largeResult[0].length == 99992 // x excludes 22 to 99 repeated digits
                && largeResult[1].length == 100000 // y should contain all decimal values
                && largeResult[2].length == 8; // z should contain repeated digits

        System.out.println("Large Test : " + (largePass ? "PASS" : "FAIL")); // Prints large test result
    }
}
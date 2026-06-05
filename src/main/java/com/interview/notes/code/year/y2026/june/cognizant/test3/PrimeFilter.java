package com.interview.notes.code.year.y2026.june.cognizant.test3;

import java.util.List; // Imports the List interface to handle ordered collections of data.
import java.util.Objects; // Imports Objects utility class to safely check for null inputs.

public class PrimeFilter { // Defines the main class that contains our logic and tests.

    public static boolean isPrime(int number) { // Method to check if a given integer is a prime number.
        if (number <= 1) return false; // Numbers 1 or less (including negatives) are never prime by definition.
        if (number == 2) return true; // 2 is the smallest prime and the only even prime number.
        if (number % 2 == 0) return false; // Quickly eliminate all other even numbers to save processing time.
        for (int i = 3; i * i <= number; i += 2) { // Loop odd numbers from 3 up to the square root of the number.
            if (number % i == 0) return false; // If the number divides evenly by 'i', it has a factor, so it is not prime.
        } // Closes the for-loop block.
        return true; // If no factors were found in the loop, the number is definitely prime.
    } // Closes the isPrime method.

    public static List<Integer> filterPrimes(List<Integer> numbers) { // Method that takes a list and returns only the primes.
        if (numbers == null) return List.of(); // If the input list is null, return an empty list safely to avoid crashes.
        return numbers.stream() // Converts the List into a Java Stream to process elements sequentially.
                      .filter(PrimeFilter::isPrime) // Keeps only the elements where our isPrime method returns true.
                      .toList(); // Packages the filtered stream back into a modern, unmodifiable List (Java 16+).
    } // Closes the filterPrimes method.

    public static void main(String[] args) { // The main execution method used here for standalone testing.
        runTest("Normal case", List.of(2, 3, 4, 5, 6, 7, 8, 9, 10), List.of(2, 3, 5, 7)); // Tests standard sequence.
        runTest("Edge case (negatives, 0, 1)", List.of(-5, 0, 1, 2), List.of(2)); // Tests boundary conditions.
        runTest("Only non-primes", List.of(4, 6, 8, 9, 10), List.of()); // Tests a list where nothing should match.
        runTest("Empty list", List.of(), List.of()); // Tests behavior when given absolutely nothing.
        
        var largeInputs = List.of(2147483647, 2147483646, 104729); // Defines a list with max integer and large prime.
        var expectedLarge = List.of(2147483647, 104729); // Defines what we expect back (two known primes).
        runTest("Large data input", largeInputs, expectedLarge); // Tests the performance and correctness on large numbers.
    } // Closes the main method.

    private static void runTest(String testName, List<Integer> input, List<Integer> expected) { // Helper to run test cases.
        var result = filterPrimes(input); // Uses 'var' (Java 10+) to infer type, calling our filter method with the input.
        boolean passed = Objects.equals(result, expected); // Compares the actual result against what we expected.
        if (passed) { // Checks if the condition evaluated to true.
            System.out.println("PASS - " + testName); // Prints a success message to the console.
        } else { // Fallback if the lists did not match.
            System.out.println("FAIL - " + testName + " | Expected: " + expected + ", Got: " + result); // Prints failure details.
        } // Closes the if-else block.
    } // Closes the runTest method.
} // Closes the PrimeFilter class.
package com.interview.notes.code.year.y2025.feb.common.test3;

import java.util.function.Predicate;

public class LambdaTest {
    // Lambda expressions
    static Predicate<Integer> isOdd = num -> num % 2 != 0;

    static Predicate<Integer> isPrime = num -> {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    };

    static Predicate<Integer> isPalindrome = num -> {
        String str = String.valueOf(Math.abs(num));
        return str.equals(new StringBuilder(str).reverse().toString());
    };

    // Test method
    static void runTest(int operation, int number) {
        switch (operation) {
            case 1: // Odd/Even
                System.out.println(isOdd.test(number) ? "ODD" : "EVEN");
                break;
            case 2: // Prime
                System.out.println(isPrime.test(number) ? "PRIME" : "COMPOSITE");
                break;
            case 3: // Palindrome
                System.out.println(isPalindrome.test(number) ? "PALINDROME" : "NOT PALINDROME");
                break;
        }
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test cases from sample input
        int[][] testCases = {
                {1, 4},  // Even
                {2, 5},  // Prime
                {3, 898},// Palindrome
                {1, 3},  // Odd
                {2, 12}  // Composite
        };

        // Run test cases
        for (int[] test : testCases) {
            runTest(test[0], test[1]);
        }

        // Additional edge cases
        System.out.println("\nEdge Cases:");
        runTest(1, 0);  // Even
        runTest(2, 1);  // Neither prime nor composite
        runTest(2, 2);  // Prime
        runTest(3, -121); // Palindrome
        runTest(3, 123); // Not Palindrome
    }
}

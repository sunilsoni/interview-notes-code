package com.interview.notes.code.year.y2025.march.GoldmanSachs.test2;

import java.util.*;

public class PrimeFactorization {

    // Method to find prime factors of an integer
    public static List<Integer> primeFactorization(int x) {
        List<Integer> factors = new ArrayList<>();

        // Handle invalid input (negative numbers and zero)
        if (x <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer greater than 0");
        }

        // Divide by smallest prime (2) until no longer divisible
        for (int i = 2; i <= Math.sqrt(x); i++) {
            while (x % i == 0) {
                factors.add(i);
                x /= i;
            }
        }
        // If remaining x is greater than 1, add as factor
        if (x > 1) factors.add(x);
        return factors;
    }

    // Method to run all tests
    public static void runTests() {
        Map<Integer, List<Integer>> tests = new HashMap<>() {{
            put(6, Arrays.asList(2, 3));
            put(5, Arrays.asList(5));
            put(12, Arrays.asList(2, 2, 3));
            put(1_000_000, Arrays.asList(2,2,2,2,2,2,5,5,5,5,5,5));
            put(1, Collections.emptyList());
            put(-5, Collections.emptyList()); // Negative test case
            put(0, Collections.emptyList());  // Zero test case
        }};

        tests.forEach((input, expected) -> {
            try {
                List<Integer> result = primeFactorization(input);
                if(result.equals(expected)) {
                    System.out.println("PASS for input: " + input);
                } else {
                    System.out.println("FAIL for input: " + input + " | Expected: " + expected + " but got: " + result);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("EXCEPTION for input: " + input + " | Message: " + e.getMessage());
            }
        });
    }

    // Main method
    public static void main(String[] args) {
        runTests();
    }
}

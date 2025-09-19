package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.stream.IntStream;

public class PiSeriesCalculator {

    // Method to compute the value of PI using n terms from the Leibniz series
    public static double computePi(int n) {
        // Stream of integers from 0 to n-1 (to get n terms)
        double pi = IntStream.range(0, n) // 0, 1, 2, ..., n-1
                .mapToDouble(i -> {
                    // Calculate each term as per formula: ((-1)^i) / (2i + 1)
                    // This handles both alternating sign and odd denominator
                    double term = Math.pow(-1, i) / (2 * i + 1);
                    return term; // e.g., 1 - 1/3 + 1/5 ...
                })
                .sum(); // Add all terms in the stream

        return 4 * pi; // Multiply final sum by 4 to approximate π
    }

    // Main method to test multiple values of n
    public static void main(String[] args) {

        // Define test cases with expected results (approximate)
        int[] testCases = {1, 10, 100, 1000, 10000, 100000};

        // Loop over each test case and print output
        for (int n : testCases) {
            // Call computePi with current n
            double result = computePi(n);

            // Print value of π for given n
            System.out.printf("n = %-7d  | Approximated π = %.15f%n", n, result);

            // Validation check (optional)
            if (Math.abs(result - Math.PI) < 0.01) {
                System.out.println("Result: PASS");
            } else {
                System.out.println("Result: FAIL (More terms may be needed)");
            }

            System.out.println("---------------------------------------");
        }
    }
}
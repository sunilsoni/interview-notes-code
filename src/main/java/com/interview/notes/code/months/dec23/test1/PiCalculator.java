package com.interview.notes.code.months.dec23.test1;

public class PiCalculator {

    // Calculate π using Euler's series
    public static double calculatePi(int terms) {
        double pi = 0.0;
        double sign = 1.0; // Start with the positive sign

        // Loop over the first N terms of the series
        for (int i = 1; i <= terms; i++) {
            pi += sign / (i * i); // Add the current term to π
            sign *= -1; // Invert the sign for the next term
        }

        pi = Math.sqrt(pi * 6); // Final adjustment to get π from the sum of the series
        return pi;
    }

    public static void main(String[] args) {
        // Test the method with different numbers of terms
        int[] testTerms = {1000, 10000, 100000, 1000000};
        for (int terms : testTerms) {
            double pi = calculatePi(terms);
            System.out.printf("π with %d terms: %.15f\n", terms, pi);
        }
    }
}

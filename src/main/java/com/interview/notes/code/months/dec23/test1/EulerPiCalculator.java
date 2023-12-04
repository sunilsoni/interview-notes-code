package com.interview.notes.code.months.dec23.test1;

/**
 * 
 */
public class EulerPiCalculator {

    public static double calculatePi(int terms) {
        double sum = 0.0;

        // Calculating the sum of the series
        for (int i = 1; i <= terms; i++) {
            sum += 1.0 / (i * i);
        }

        // Multiplying by 6 and taking the square root to get π
        return Math.sqrt(sum * 6);
    }

    public static void main(String[] args) {
        int terms = 10000; // Number of terms to sum
        double pi = calculatePi(terms);
        System.out.println("Estimate of π using " + terms + " terms: " + pi);
    }
}

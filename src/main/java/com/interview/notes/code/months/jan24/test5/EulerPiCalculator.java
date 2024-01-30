package com.interview.notes.code.months.jan24.test5;


/**
 * Leonhard Euler (15 April 1707 - 18 September 1783) was a Swiss mathematician, physicist, astronomer, geographer, logician and engineer who founded the studies of graph theory and topology and made pioneering and influential discoveries in many other branches of mathematics such as analytic number theory, complex analysis, and infinitesimal calculus.
 * Euler provided a proof to calculate value of pi using (yet another) infinite sum, expressed as:
 * <p>
 * Your job is to write a program to calculate value of pi using first N steps using above formula.
 * To test the code, give value of N = 1000 or 10,000 or 100,000 or 1,000,000 and see how accuracy
 * increases. How many digits after the decimal point are you getting accurate results?
 */
public class EulerPiCalculator {

    public static void main(String[] args) {
        // Test the approximation with various values of N
        testPiApproximation(1000);
        testPiApproximation(10000);
        testPiApproximation(100000);
        testPiApproximation(1000000);
    }

    // Calculates the approximation of π using the first N terms of Euler's series
    private static double calculatePi(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / (i * i);
        }
        return Math.sqrt(sum * 6);
    }

    // Tests the accuracy of the approximation and prints the number of correct decimal places
    private static void testPiApproximation(int n) {
        double piApproximation = calculatePi(n);
        System.out.printf("Approximation of π using %d terms: %.10f\n", n, piApproximation);
    }
}

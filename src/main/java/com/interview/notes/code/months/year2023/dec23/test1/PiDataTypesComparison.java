package com.interview.notes.code.months.year2023.dec23.test1;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * alculate pi using Euler's infinite sum series
 * Leonhard Euler (15 April 1707 –  18 September 1783) was a Swiss mathematician, physicist,
 * astronomer, geographer, logician and engineer who founded the studies of graph theory
 * and topology and made pioneering and influential discoveries in many other branches of
 * mathematics such as analytic number theory, complex analysis, and infinitesimal
 * calculus.
 * Euler provided a proof to calculate value of pi using (yet another) infinite sum, expressed
 * <p>
 * Your job is to write a program to calculate value of pi using first N steps using above
 * formula.
 * To test the code, give value of N = 1000 or 10,000 or 100,000 or 1,000,000 and see how
 * accuracy increases. How many digits after the decimal point are you getting accurate
 * results?
 */
public class PiDataTypesComparison {
    // Method to calculate π using double precision
    public static double calculatePiDouble(int terms) {
        double pi = 0.0;
        for (int i = 1; i <= terms; i++) {
            pi += Math.pow(-1, i + 1) / (2 * i - 1);
        }
        return pi * 4;
    }

    // Method to calculate π using BigDecimal for higher precision
    public static BigDecimal calculatePiBigDecimal(int terms) {
        MathContext mc = new MathContext(100); // Precision set to 100 decimal places
        BigDecimal pi = BigDecimal.ZERO;
        BigDecimal one = BigDecimal.ONE;
        BigDecimal negativeOne = new BigDecimal("-1");

        for (int i = 1; i <= terms; i++) {
            BigDecimal term = negativeOne.pow(i + 1).divide(new BigDecimal(2 * i - 1), mc);
            pi = pi.add(term);
        }
        return pi.multiply(new BigDecimal("4"));
    }

    public static void main(String[] args) {
        int terms = 10000; // Test with 10,000 terms
        System.out.println("π using double: " + calculatePiDouble(terms));
        System.out.println("π using BigDecimal: " + calculatePiBigDecimal(terms));


        // Test the method with different numbers of terms
        int[] testTerms = {1000, 10000, 100000, 1000000};
        for (int term : testTerms) {
            double pi = calculatePiDouble(term);
            System.out.printf("π with %d terms: %.15f\n", term, pi);
        }

    }
}

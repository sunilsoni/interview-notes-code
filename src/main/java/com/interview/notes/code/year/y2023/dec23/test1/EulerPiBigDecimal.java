package com.interview.notes.code.year.y2023.dec23.test1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EulerPiBigDecimal {

    // Method to calculate the value of π using BigDecimal for high precision
    public static BigDecimal calculatePi(int terms, int precision) {
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal one = BigDecimal.ONE;

        // Calculate the sum of the series
        for (int i = 1; i <= terms; i++) {
            BigDecimal denominator = BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(i));
            sum = sum.add(one.divide(denominator, mc));
        }

        // Multiply by 6 and take the square root
        sum = sum.multiply(BigDecimal.valueOf(6));
        return sqrt(sum, mc);
    }

    // Method to calculate the square root of a BigDecimal
    public static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()), mc);
        BigDecimal two = BigDecimal.valueOf(2);
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // Iterate using the Babylonian method
        while (true) {
            BigDecimal y = value.divide(x, mc);
            y = y.add(x);
            y = y.divide(two, mc);

            if (x.subtract(y).abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-mc.getPrecision())) <= 0) {
                break;
            }

            x = y;
        }

        return x;
    }

    public static void main(String[] args) {
        int terms = 1000; // Number of terms to sum
        int precision = 100; // Precision for the BigDecimal operations
        BigDecimal pi = calculatePi(terms, precision);
        System.out.println("Estimate of π using " + terms + " terms with " + precision + " decimal places: " + pi);
    }
}

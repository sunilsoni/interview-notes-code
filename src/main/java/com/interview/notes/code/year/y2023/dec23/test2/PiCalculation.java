package com.interview.notes.code.year.y2023.dec23.test2;

import java.math.BigDecimal;
import java.math.MathContext;

public class PiCalculation {
    public static void main(String[] args) {
        int N = 1000000; // Set N to the desired number of steps

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal one = BigDecimal.ONE;
        BigDecimal four = new BigDecimal(4);

        for (int i = 0; i < N; i++) {
            BigDecimal numerator = one.pow(i);
            BigDecimal denominator = new BigDecimal(2 * i + 1);

            BigDecimal term = numerator.divide(denominator, MathContext.DECIMAL128);

            if (i % 2 == 0) {
                sum = sum.add(term);
            } else {
                sum = sum.subtract(term);
            }
        }


        try {

        } catch (Exception e) {

        }

        BigDecimal pi = sum.multiply(four);
        System.out.println("Approximate value of π with " + N + " steps: " + pi);
    }
}

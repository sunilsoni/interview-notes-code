package com.interview.notes.code.year.y2023.dec23.test1;

import java.math.BigDecimal;
import java.math.MathContext;

public class PiConvergenceImprovement {
    // Gauss-Legendre Algorithm for π calculation
    public static BigDecimal calculatePiGaussLegendre(int iterations) {
        MathContext mc = new MathContext(100);
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ONE.divide(new BigDecimal(Math.sqrt(2)), mc);
        BigDecimal t = new BigDecimal("0.25");
        BigDecimal x = BigDecimal.ONE;
        BigDecimal pi;

        for (int i = 0; i < iterations; i++) {
            BigDecimal y = a;
            a = a.add(b).divide(new BigDecimal(2), mc);
            b = b.multiply(y).sqrt(mc);
            t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
            x = x.multiply(new BigDecimal(2));
        }

        pi = a.add(b).multiply(a.add(b)).divide(t.multiply(new BigDecimal(4)), mc);
        return pi;
    }

    public static void main(String[] args) {
        int iterations = 5; // The Gauss-Legendre algorithm converges very quickly
        System.out.println("π using Gauss-Legendre: " + calculatePiGaussLegendre(iterations));


    }
}

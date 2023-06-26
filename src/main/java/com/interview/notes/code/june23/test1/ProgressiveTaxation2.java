package com.interview.notes.code.june23.test1;

class ProgressiveTaxation2 {

    public static void main(String[] args) {
        assertResult(calculateNetIncome(5000.50), 4500.45);
        assertResult(calculateNetIncome(10000.00), 9000.00);
        assertResult(calculateNetIncome(10000.99), 9000.87);
        assertResult(calculateNetIncome(40000.00), 35400.00);
        assertResult(calculateNetIncome(40000.99), 35400.77);
        assertResult(calculateNetIncome(100000.00), 82200.00);
        assertResult(calculateNetIncome(100000.99), 82200.75);
        assertResult(calculateNetIncome(123456.78), 100027.15);
    }

    public static void assertResult(Double actual, Double expected) {
        System.out.println(expected.equals(actual) ? "PASS" : "FAIL - Actual[" + actual + "] Expected[" + expected + "]");
    }

    public static double calculateNetIncome(Double grossIncome) {
        double tax = 0.0;

        if (grossIncome > 100000) {
            tax += (grossIncome - 100000) * 0.24;
            grossIncome -= (grossIncome - 100000);
        }
        if (grossIncome > 40000) {
            tax += (grossIncome - 40000) * 0.22;
            grossIncome -= (grossIncome - 40000);
        }
        if (grossIncome > 10000) {
            tax += (grossIncome - 10000) * 0.12;
            grossIncome -= (grossIncome - 10000);
        }
        if (grossIncome >= 0) {
            tax += grossIncome * 0.1;
        }

        double netIncome = round(grossIncome - tax);
        return netIncome;
    }

    public static double round(Double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

}

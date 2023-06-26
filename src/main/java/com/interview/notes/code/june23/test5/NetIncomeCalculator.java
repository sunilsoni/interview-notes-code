package com.interview.notes.code.june23.test5;

public class NetIncomeCalculator {
    public static void main(String[] args) {
        double grossIncome = 15000; // Replace with the actual gross income

        double netIncome = calculateNetIncome(grossIncome);
        System.out.println("Net Income: $" + netIncome);
    }

    public static double calculateNetIncome(double grossIncome) {
        double netIncome = 0.0;

        if (grossIncome <= 10000) {
            netIncome = grossIncome * 0.1;
        } else if (grossIncome <= 40000) {
            netIncome = 10000 * 0.1 + (grossIncome - 10000) * 0.12;
        } else if (grossIncome <= 100000) {
            netIncome = 10000 * 0.1 + 30000 * 0.12 + (grossIncome - 40000) * 0.22;
        } else {
            netIncome = 10000 * 0.1 + 30000 * 0.12 + 60000 * 0.22 + (grossIncome - 100000) * 0.24;
        }

        return netIncome;
    }
}

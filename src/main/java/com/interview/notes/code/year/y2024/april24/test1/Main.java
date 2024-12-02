package com.interview.notes.code.year.y2024.april24.test1;

// Main class to use the calculator
public class Main {
    public static void main(String[] args) {
        InterestRateCalculator calculator = new InterestRateCalculator();

        // Valid examples
        try {
            double rate1 = calculator.calculateInterestRate(100, 2);
            System.out.println("Interest Rate for credit score 100 and 2 years: " + rate1);

            double rate2 = calculator.calculateInterestRate(350, 7);
            System.out.println("Interest Rate for credit score 350 and 7 years: " + rate2);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Edge cases
        int[] creditScores = {-100, 0, 300, 500, 501};
        int[] years = {-1, 0, 5, 10, 11};

        for (int creditScore : creditScores) {
            for (int year : years) {
                try {
                    double rate = calculator.calculateInterestRate(creditScore, year);
                    System.out.println("Interest Rate for credit score " + creditScore + " and " + year + " years: " + rate);
                } catch (IllegalArgumentException e) {
                    System.out.println("No interest rate found for credit score " + creditScore + " and " + year + " years.");
                }
            }
        }
    }
}
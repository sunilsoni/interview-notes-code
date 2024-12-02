package com.interview.notes.code.year.y2024.april24.test1;

import java.util.ArrayList;
import java.util.List;

public class InterestRateCalculator {
    private final List<LoanCriteria> loanCriteriaList;

    public InterestRateCalculator() {
        // Initialize the list with LoanCriteria objects
        loanCriteriaList = new ArrayList<>();

        // Add criteria based on the structured data provided
        loanCriteriaList.add(new LoanCriteria(0, 300, 0, 5, 7.0));
        loanCriteriaList.add(new LoanCriteria(0, 300, 6, 10, 6.5));
        loanCriteriaList.add(new LoanCriteria(301, 500, 0, 5, 6.0));
        loanCriteriaList.add(new LoanCriteria(301, 500, 6, 10, 4.5));
    }

    public static void main(String[] args) {
        InterestRateCalculator calculator = new InterestRateCalculator();

        // Example usage
        try {
            double rate1 = calculator.calculateInterestRate(100, 2);
            System.out.println("Interest Rate for credit score 100 and 2 years: " + rate1);

            double rate2 = calculator.calculateInterestRate(350, 7);
            System.out.println("Interest Rate for credit score 350 and 7 years: " + rate2);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public double calculateInterestRate(int creditScore, int numberOfYears) {
        for (LoanCriteria criteria : loanCriteriaList) {
            if (criteria.matchesCriteria(creditScore, numberOfYears)) {
                return criteria.getInterestRate();
            }
        }
        // Throw an exception or return a default value if no criteria match
        throw new IllegalArgumentException("No matching criteria for provided credit score and number of years.");
    }
}

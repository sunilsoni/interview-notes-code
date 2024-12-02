package com.interview.notes.code.year.y2024.april24.test1;

class LoanCriteria {
    private final int minCreditScore;
    private final int maxCreditScore;
    private final int minYears;
    private final int maxYears;
    private final double interestRate;

    public LoanCriteria(int minCreditScore, int maxCreditScore, int minYears, int maxYears, double interestRate) {
        this.minCreditScore = minCreditScore;
        this.maxCreditScore = maxCreditScore;
        this.minYears = minYears;
        this.maxYears = maxYears;
        this.interestRate = interestRate;
    }

    public boolean matchesCriteria(int creditScore, int years) {
        // Logic to check if the credit score and years fall within the criteria
        return creditScore >= minCreditScore && creditScore <= maxCreditScore &&
                years >= minYears && years <= maxYears;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
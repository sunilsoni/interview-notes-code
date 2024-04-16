package com.interview.notes.code.months.april24.test5;

public class PersonalLoanDept implements Bank {
    private int[] loanAmounts;
    private double averageLoan = 0;
    private int maxLoan = Integer.MIN_VALUE;
    private int minLoan = Integer.MAX_VALUE;
    private boolean isUpdated = false;

    public PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];
    }

    @Override
    public void assignLoans(int[] loans) {
        System.arraycopy(loans, 0, loanAmounts, 0, Math.min(loanAmounts.length, loans.length));
        isUpdated = false;
    }

    @Override
    public double averageLoan() {
        if (!isUpdated) {
            updateStatistics();
        }
        // return averageLoan;
        return 0;
    }

    @Override
    public int maxLoan() {
        if (!isUpdated) {
            updateStatistics();
        }
        return maxLoan;
    }

    @Override
    public int minLoan() {
        if (!isUpdated) {
            updateStatistics();
        }
        return minLoan;
    }

    private void updateStatistics() {
        long sum = 0;
        maxLoan = loanAmounts[0];
        minLoan = loanAmounts[0];
        for (int loan : loanAmounts) {
            sum += loan;
            if (loan > maxLoan) {
                maxLoan = loan;
            }
            if (loan < minLoan) {
                minLoan = loan;
            }
        }
        averageLoan = (double) sum / loanAmounts.length;
        isUpdated = true;
    }
}
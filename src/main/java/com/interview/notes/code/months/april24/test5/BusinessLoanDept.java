package com.interview.notes.code.months.april24.test5;

public class BusinessLoanDept implements Bank1 {
    private int[] loanAmounts;

    public BusinessLoanDept(int clients) {
        loanAmounts = new int[clients];
        // Initialize the array with zero values indicating no loans assigned yet.
        for (int i = 0; i < loanAmounts.length; i++) {
            loanAmounts[i] = 0;
        }
    }

    @Override
    public void assignLoans(int[] loans) {
        for (int i = 0; i < loanAmounts.length && i < loans.length; i++) {
            loanAmounts[i] = loans[i];
        }
        System.out.println("Loans for business clients assigned");
    }

    @Override
    public void averageLoan() {
        double sum = 0;
        for (int loan : loanAmounts) {
            sum += loan;
        }
        double average = sum / loanAmounts.length;
        System.out.printf("Average loan amount for business clients is %.2f\n", average);
    }

    @Override
    public void maxLoan() {
        int max = loanAmounts[0];
        for (int loan : loanAmounts) {
            if (loan > max) {
                max = loan;
            }
        }
        System.out.println("Maximum loan amount amongst business clients is " + max);
    }

    @Override
    public void minLoan() {
        // Assuming loanAmounts may have zeroes which should not be considered as min values.
        int min = Integer.MAX_VALUE;
        for (int loan : loanAmounts) {
            if (loan < min && loan > 0) {
                min = loan;
            }
        }
        if (min == Integer.MAX_VALUE) {
            // This means no loans were assigned
            System.out.println("No minimum loan amount can be determined as no loans were assigned");
        } else {
            System.out.println("Minimum loan amount amongst business clients is " + min);
        }
    }
}

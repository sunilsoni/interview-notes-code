package com.interview.notes.code.year.y2024.april24.test5;

public class PersonalLoanDept1 implements Bank1 {
    private int[] loanAmounts;

    public PersonalLoanDept1(int clients) {
        loanAmounts = new int[clients];
    }

    @Override
    public void assignLoans(int[] loans) {
        for (int i = 0; i < loanAmounts.length && i < loans.length; i++) {
            loanAmounts[i] = loans[i];
        }
        System.out.println("Loans for clients assigned");
    }

    @Override
    public void averageLoan() {
        double sum = 0;
        for (int loan : loanAmounts) {
            sum += loan;
        }
        double average = sum / loanAmounts.length;
        System.out.printf("Average loan amount for clients is %.2f\n", average);
    }

    @Override
    public void maxLoan() {
        int max = loanAmounts[0];
        for (int loan : loanAmounts) {
            if (loan > max) {
                max = loan;
            }
        }
        System.out.println("Maximum loan amount amongst clients is " + max);
    }

    @Override
    public void minLoan() {
        int min = loanAmounts[0];
        for (int loan : loanAmounts) {
            if (loan < min) {
                min = loan;
            }
        }
        System.out.println("Minimum loan amount amongst clients is " + min);
    }
}

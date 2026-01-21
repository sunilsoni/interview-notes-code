package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.Arrays;

interface Bank {
    void assignLoans(int[] loans);

    void averageLoan();

    void maxLoan();

    void minLoan();
}

abstract class LoanDepartment implements Bank {
    protected int[] loanAmounts;

    protected abstract String entityType();

    public void assignLoans(int[] loans) {
        System.arraycopy(loans, 0, loanAmounts, 0, Math.min(loans.length, loanAmounts.length));
        System.out.println("Loans for " + entityType() + " processed");
    }

    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts).average().orElse(0);
        System.out.println("Average loan amount for " + entityType() + " is " + String.format("%.2f", avg));
    }

    public void maxLoan() {
        int max = Arrays.stream(loanAmounts).max().orElse(0);
        System.out.println("Maximum loan amount amongst " + entityType() + " is " + max);
    }

    public void minLoan() {
        int min = Arrays.stream(loanAmounts).min().orElse(0);
        System.out.println("Minimum loan amount amongst " + entityType() + " is " + min);
    }
}

class PersonalLoanDept extends LoanDepartment {
    PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];
    }

    protected String entityType() {
        return "clients";
    }
}

class BusinessLoanDept extends LoanDepartment {
    BusinessLoanDept(int businesses) {
        loanAmounts = new int[businesses];
    }

    protected String entityType() {
        return "businesses";
    }

    @Override
    public void minLoan() {
        int min = Arrays.stream(loanAmounts).min().orElse(0);
        System.out.print("Minimum loan amount amongst " + entityType() + " is " + min);
    }
}
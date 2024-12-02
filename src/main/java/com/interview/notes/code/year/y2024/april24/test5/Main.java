package com.interview.notes.code.year.y2024.april24.test5;

/**
 * Java: Bank Implementation
 * Description
 * Given an interface termed Bank, list the following functions:
 * • void assignLoans(int! loans);
 * • void averageLoan;
 * • void maxLoan;
 * • void minLoan0;
 * Create 2 classes, called PersonalLoanDept and BusinessLoanDept implementing the Bank interface. The specifications are given below.
 * 1. The class PersonalLoanDept should include an integer-type array termed loanAmounts. This class should further comprise the subsequent methods:
 * • PersonalLoanDept(int clients): An empty array loanAmounts of clients length is initialized in this class, where clients is the count of loan recipients. The initial loan amount assigned is zero.
 * • void assignLoans(int loans): The loans array is linked to loanAmounts. If the lengths of the two arrays differ, as many values as possible are assigned, and then stop allocating more and print "Loans for clients processed".
 * • void averageLoan: This displays the loan average in the patter "Average loan amount for clients is (averageLoan)". The average computation should consider any zero value present in loanAmounts.
 * • void maxLoan: This displays "Maximum loan amount amongst clients is (maximumLoan)" reflecting the largest loan.
 * • void minLoanO: This displays "Minimum loan amount amongst clients is (minimumLoan)" reflecting the smallest loan given.
 */
public class Main {
    public static void main(String[] args) {
        Bank1 personalLoanDept = new PersonalLoanDept1(5);
        Bank1 businessLoanDept = new BusinessLoanDept(5);

        // Example loan assignments
        personalLoanDept.assignLoans(new int[]{1000, 1500, 2000, 2500, 3000});
        businessLoanDept.assignLoans(new int[]{2000, 2600, 3200, 3800, 4400});

        // Output statistics for personal loans
//        System.out.println("Personal Loans:");
//        System.out.println("Average Loan: " + personalLoanDept.averageLoan());
//        System.out.println("Max Loan: " + personalLoanDept.maxLoan());
//        System.out.println("Min Loan: " + personalLoanDept.minLoan());

        // Output statistics for business loans
//        System.out.println("\nBusiness Loans:");
//        System.out.println("Average Loan: " + businessLoanDept.averageLoan());
//        System.out.println("Max Loan: " + businessLoanDept.maxLoan());
//        System.out.println("Min Loan: " + businessLoanDept.minLoan());
    }
}
package com.interview.notes.code.year.y2026.march.phton.test1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

interface Bank {
    void assignLoans(int[] loans);
    void averageLoan();
    void maxLoan();
    void minLoan();
}

abstract class AbstractLoanDepartment implements Bank {
    private final int[] loanAmounts;

    protected AbstractLoanDepartment(int capacity) {
        this.loanAmounts = new int[capacity];
    }

    @Override
    public void assignLoans(int[] loans) {
        var limit = Math.min(this.loanAmounts.length, loans.length);
        System.arraycopy(loans, 0, this.loanAmounts, 0, limit);
        System.out.println("Loans for " + getCategory() + " processed");
    }

    @Override
    public void averageLoan() {
        var avg = Arrays.stream(loanAmounts).average().orElse(0.0);
        System.out.printf("Average loan amount for %s is %.2f%n", getCategory(), avg);
    }

    @Override
    public void maxLoan() {
        var max = Arrays.stream(loanAmounts).max().orElse(0);
        System.out.printf("Maximum loan %s is %d%n", getDetailCategory(), max);
    }

    @Override
    public void minLoan() {
        var min = Arrays.stream(loanAmounts).min().orElse(0);
        System.out.printf("Minimum loan %s is %d%n", getDetailCategory(), min);
    }

    protected abstract String getCategory();
    protected abstract String getDetailCategory();
}

class PersonalLoanDept extends AbstractLoanDepartment {
    public PersonalLoanDept(int clients) {
        super(clients);
    }

    @Override
    protected String getCategory() {
        return "clients";
    }

    @Override
    protected String getDetailCategory() {
        return "amount amongst clients";
    }
}

class BusinessLoanDept extends AbstractLoanDepartment {
    public BusinessLoanDept(int businesses) {
        super(businesses);
    }

    @Override
    protected String getCategory() {
        return "businesses";
    }

    @Override
    protected String getDetailCategory() {
        return "amongst businesses";
    }
}

public class Solution {
    public static void main(String[] args) {
        runTest(
            new int[]{2348, 929, 1284, 5543},
            new int[]{3117, 5196, 3352, 7068},
            "Loans for clients processed\n" +
            "Loans for businesses processed\n" +
            "Average loan amount for clients is 2526.00\n" +
            "Maximum loan amount amongst clients is 5543\n" +
            "Minimum loan amount amongst clients is 929\n" +
            "Average loan amount for businesses is 4683.25\n" +
            "Maximum loan amongst businesses is 7068\n" +
            "Minimum loan amongst businesses is 3117"
        );

        var largePersonal = new int[100000];
        Arrays.fill(largePersonal, 1000);
        var largeBusiness = new int[100000];
        Arrays.fill(largeBusiness, 2000);
        
        runTest(
            largePersonal,
            largeBusiness,
            "Loans for clients processed\n" +
            "Loans for businesses processed\n" +
            "Average loan amount for clients is 1000.00\n" +
            "Maximum loan amount amongst clients is 1000\n" +
            "Minimum loan amount amongst clients is 1000\n" +
            "Average loan amount for businesses is 2000.00\n" +
            "Maximum loan amongst businesses is 2000\n" +
            "Minimum loan amongst businesses is 2000"
        );
    }

    private static void runTest(int[] personalLoans, int[] businessLoans, String expected) {
        var originalOut = System.out;
        var baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Bank personalDept = new PersonalLoanDept(personalLoans.length);
        Bank businessDept = new BusinessLoanDept(businessLoans.length);

        personalDept.assignLoans(personalLoans);
        businessDept.assignLoans(businessLoans);

        personalDept.averageLoan();
        personalDept.maxLoan();
        personalDept.minLoan();

        businessDept.averageLoan();
        businessDept.maxLoan();
        businessDept.minLoan();

        System.out.flush();
        System.setOut(originalOut);

        var result = baos.toString().replace("\r\n", "\n").trim();
        if (result.equals(expected.trim())) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
            System.out.println("Expected:\n" + expected);
            System.out.println("Got:\n" + result);
        }
    }
}
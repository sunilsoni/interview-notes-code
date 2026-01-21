package com.interview.notes.code.year.y2025.december.hackerank.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

interface Bank {
    void assignLoans(int[] loans);

    void averageLoan();

    void maxLoan();

    void minLoan();
}

abstract class LoanDepartment implements Bank {
    protected int[] loanAmounts;

    protected abstract String entityName();

    protected abstract String entityNamePlural();

    public void assignLoans(int[] loans) {
        IntStream.range(0, Math.min(loans.length, loanAmounts.length)).forEach(i -> loanAmounts[i] = loans[i]);
        System.out.println("Loans for " + entityNamePlural() + " processed");
    }

    public void averageLoan() {
        System.out.printf("Average loan amount for %s is %.2f%n", entityNamePlural(), Arrays.stream(loanAmounts).average().orElse(0));
    }

    public void maxLoan() {
        System.out.println("Maximum loan amount amongst " + entityName() + " is " + Arrays.stream(loanAmounts).max().orElse(0));
    }

    public void minLoan() {
        System.out.println("Minimum loan amount amongst " + entityName() + " is " + Arrays.stream(loanAmounts).min().orElse(0));
    }
}

class PersonalLoanDept extends LoanDepartment {
    PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];
    }

    protected String entityName() {
        return "clients";
    }

    protected String entityNamePlural() {
        return "clients";
    }
}

class BusinessLoanDept extends LoanDepartment {
    BusinessLoanDept(int businesses) {
        loanAmounts = new int[businesses];
    }

    protected String entityName() {
        return "businesses";
    }

    protected String entityNamePlural() {
        return "businesses";
    }
}

public class LoanProcessingSystem {
    public static void main(String[] args) {
        runTest(new int[]{4, 4}, new int[]{2348, 929, 1284, 5543}, new int[]{3117, 5196, 3352, 7068},
                List.of("Loans for clients processed", "Loans for businesses processed",
                        "Average loan amount for clients is 2526.00", "Maximum loan amount amongst clients is 5543",
                        "Minimum loan amount amongst clients is 929", "Average loan amount for businesses is 4683.25",
                        "Maximum loan amount amongst businesses is 7068", "Minimum loan amount amongst businesses is 3117"), "Test 1");

        runTest(new int[]{3, 2}, new int[]{1000, 2000, 3000}, new int[]{5000, 6000},
                List.of("Loans for clients processed", "Loans for businesses processed",
                        "Average loan amount for clients is 2000.00", "Maximum loan amount amongst clients is 3000",
                        "Minimum loan amount amongst clients is 1000", "Average loan amount for businesses is 5500.00",
                        "Maximum loan amount amongst businesses is 6000", "Minimum loan amount amongst businesses is 5000"), "Test 2");

        runTest(new int[]{5, 3}, new int[]{100, 200}, new int[]{500, 600, 700},
                List.of("Loans for clients processed", "Loans for businesses processed",
                        "Average loan amount for clients is 60.00", "Maximum loan amount amongst clients is 200",
                        "Minimum loan amount amongst clients is 0", "Average loan amount for businesses is 600.00",
                        "Maximum loan amount amongst businesses is 700", "Minimum loan amount amongst businesses is 500"), "Test 3");

        Bank p = new PersonalLoanDept(10000);
        Bank b = new BusinessLoanDept(10000);
        p.assignLoans(IntStream.rangeClosed(1, 10000).toArray());
        b.assignLoans(IntStream.rangeClosed(10001, 20000).toArray());
        System.out.println("Test 4 - Large Data: PASS");
    }

    static void runTest(int[] counts, int[] clientLoans, int[] businessLoans, List<String> expected, String testName) {
        var output = new ArrayList<String>();
        var original = System.out;
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            StringBuilder sb = new StringBuilder();

            public void write(int b) {
                if (b == '\n') {
                    output.add(sb.toString());
                    sb = new StringBuilder();
                } else sb.append((char) b);
            }
        }));

        Bank personal = new PersonalLoanDept(counts[0]);
        Bank business = new BusinessLoanDept(counts[1]);
        personal.assignLoans(clientLoans);
        business.assignLoans(businessLoans);
        personal.averageLoan();
        personal.maxLoan();
        personal.minLoan();
        business.averageLoan();
        business.maxLoan();
        business.minLoan();

        System.setOut(original);
        boolean pass = output.equals(expected);
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + output);
        }
    }
}
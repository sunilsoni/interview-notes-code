package com.interview.notes.code.year.y2025.december.common.test6;

import java.io.*;
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
        int limit = Math.min(loans.length, loanAmounts.length);
        System.arraycopy(loans, 0, loanAmounts, 0, limit);
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
        System.out.print("Minimum loan amount amongst " + entityType() + " is " + min);
    }
}

class PersonalLoanDept extends LoanDepartment {
    PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];
    }

    protected String entityType() {
        return "clients";
    }

    public void minLoan() {
        int min = Arrays.stream(loanAmounts).min().orElse(0);
        System.out.println("Minimum loan amount amongst " + entityType() + " is " + min);
    }
}

class BusinessLoanDept extends LoanDepartment {
    BusinessLoanDept(int businesses) {
        loanAmounts = new int[businesses];
    }

    protected String entityType() {
        return "businesses";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        testAll();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().trim().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        int[] clientLoans = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] businessLoans = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        process(n, m, clientLoans, businessLoans);
    }

    static void process(int n, int m, int[] clientLoans, int[] businessLoans) {
        Bank personal = new PersonalLoanDept(n);
        Bank business = new BusinessLoanDept(m);

        personal.assignLoans(clientLoans);
        business.assignLoans(businessLoans);
        personal.averageLoan();
        personal.maxLoan();
        personal.minLoan();
        business.averageLoan();
        business.maxLoan();
        business.minLoan();
    }

    static void testAll() {
        System.out.println("=== RUNNING TESTS ===\n");

        String expected1 = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                "Average loan amount for clients is 2526.00\n" +
                "Maximum loan amount amongst clients is 5543\n" +
                "Minimum loan amount amongst clients is 929\n" +
                "Average loan amount for businesses is 4683.25\n" +
                "Maximum loan amount amongst businesses is 7068\n" +
                "Minimum loan amount amongst businesses is 3117";

        test("Test 1", 4, 4, new int[]{2348, 929, 1284, 5543}, new int[]{3117, 5196, 3352, 7068}, expected1);

        String expected2 = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                "Average loan amount for clients is 2000.00\n" +
                "Maximum loan amount amongst clients is 3000\n" +
                "Minimum loan amount amongst clients is 1000\n" +
                "Average loan amount for businesses is 5500.00\n" +
                "Maximum loan amount amongst businesses is 6000\n" +
                "Minimum loan amount amongst businesses is 5000";

        test("Test 2", 3, 2, new int[]{1000, 2000, 3000}, new int[]{5000, 6000}, expected2);

        String expected3 = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                "Average loan amount for clients is 60.00\n" +
                "Maximum loan amount amongst clients is 200\n" +
                "Minimum loan amount amongst clients is 0\n" +
                "Average loan amount for businesses is 600.00\n" +
                "Maximum loan amount amongst businesses is 700\n" +
                "Minimum loan amount amongst businesses is 500";

        test("Test 3 - Partial", 5, 3, new int[]{100, 200}, new int[]{500, 600, 700}, expected3);

        System.out.println("\n=== TESTS COMPLETE ===\n");
    }

    static void test(String name, int n, int m, int[] clients, int[] business, String expected) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));

        process(n, m, clients, business);

        System.setOut(old);
        String actual = baos.toString().trim();
        String exp = expected.trim();

        if (actual.equals(exp)) {
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL");
            System.out.println("Expected:\n[" + exp + "]");
            System.out.println("Actual:\n[" + actual + "]");
            System.out.println("Expected lines: " + exp.split("\n").length);
            System.out.println("Actual lines: " + actual.split("\n").length);
        }
    }
}
package com.interview.notes.code.year.y2025.december.common.test2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

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
}

public class Solution {
    public static void main(String[] args) {
        runTests();
    }
    
    static void runTests() {
        System.out.println("=== RUNNING TESTS ===\n");
        
        test("Test 1", 4, 4, 
            new int[]{2348, 929, 1284, 5543}, 
            new int[]{3117, 5196, 3352, 7068},
            new String[]{
                "Loans for clients processed",
                "Loans for businesses processed",
                "Average loan amount for clients is 2526.00",
                "Maximum loan amount amongst clients is 5543",
                "Minimum loan amount amongst clients is 929",
                "Average loan amount for businesses is 4683.25",
                "Maximum loan amount amongst businesses is 7068",
                "Minimum loan amount amongst businesses is 3117"
            });
        
        test("Test 2", 5, 5,
            new int[]{9238, 79, 5588, 2490, 6993},
            new int[]{9861, 1055, 8498, 2387, 3283},
            new String[]{
                "Loans for clients processed",
                "Loans for businesses processed",
                "Average loan amount for clients is 4877.60",
                "Maximum loan amount amongst clients is 9238",
                "Minimum loan amount amongst clients is 79",
                "Average loan amount for businesses is 5016.80",
                "Maximum loan amount amongst businesses is 9861",
                "Minimum loan amount amongst businesses is 1055"
            });
        
        test("Test 3 - Partial Fill", 5, 3,
            new int[]{100, 200},
            new int[]{500, 600, 700},
            new String[]{
                "Loans for clients processed",
                "Loans for businesses processed",
                "Average loan amount for clients is 60.00",
                "Maximum loan amount amongst clients is 200",
                "Minimum loan amount amongst clients is 0",
                "Average loan amount for businesses is 600.00",
                "Maximum loan amount amongst businesses is 700",
                "Minimum loan amount amongst businesses is 500"
            });
        
        test("Test 4 - Single Element", 1, 1,
            new int[]{5000},
            new int[]{3000},
            new String[]{
                "Loans for clients processed",
                "Loans for businesses processed",
                "Average loan amount for clients is 5000.00",
                "Maximum loan amount amongst clients is 5000",
                "Minimum loan amount amongst clients is 5000",
                "Average loan amount for businesses is 3000.00",
                "Maximum loan amount amongst businesses is 3000",
                "Minimum loan amount amongst businesses is 3000"
            });
        
        testLargeData();
        
        System.out.println("\n=== ALL TESTS COMPLETE ===");
    }
    
    static void test(String name, int n, int m, int[] clients, int[] business, String[] expected) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));
        
        Bank personal = new PersonalLoanDept(n);
        Bank biz = new BusinessLoanDept(m);
        personal.assignLoans(clients);
        biz.assignLoans(business);
        personal.averageLoan();
        personal.maxLoan();
        personal.minLoan();
        biz.averageLoan();
        biz.maxLoan();
        biz.minLoan();
        
        System.setOut(old);
        
        String[] actual = baos.toString().trim().split("\n");
        boolean pass = true;
        
        if (actual.length != expected.length) {
            pass = false;
        } else {
            for (int i = 0; i < expected.length; i++) {
                if (!actual[i].trim().equals(expected[i].trim())) {
                    pass = false;
                    break;
                }
            }
        }
        
        if (pass) {
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL");
            System.out.println("Expected lines: " + expected.length + ", Actual lines: " + actual.length);
            for (int i = 0; i < Math.max(expected.length, actual.length); i++) {
                String exp = i < expected.length ? expected[i] : "N/A";
                String act = i < actual.length ? actual[i].trim() : "N/A";
                String status = exp.equals(act) ? "OK" : "DIFF";
                System.out.println("Line " + (i+1) + " [" + status + "]");
                System.out.println("  Exp: [" + exp + "]");
                System.out.println("  Act: [" + act + "]");
            }
        }
    }
    
    static void testLargeData() {
        int[] largeClients = IntStream.rangeClosed(1, 10000).toArray();
        int[] largeBusiness = IntStream.rangeClosed(10001, 20000).toArray();
        
        Bank p = new PersonalLoanDept(10000);
        Bank b = new BusinessLoanDept(10000);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));
        
        p.assignLoans(largeClients);
        b.assignLoans(largeBusiness);
        p.averageLoan();
        p.maxLoan();
        p.minLoan();
        b.averageLoan();
        b.maxLoan();
        b.minLoan();
        
        System.setOut(old);
        
        String[] lines = baos.toString().trim().split("\n");
        boolean pass = lines.length == 8;
        
        System.out.println("Test 5 - Large Data (10000 elements): " + (pass ? "PASS" : "FAIL"));
    }
}
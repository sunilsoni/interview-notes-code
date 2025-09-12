package com.interview.notes.code.year.y2025.september.photon.test1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

abstract class LoanDept implements Bank {
    protected int[] loanAmounts;

    public void assignLoans(int[] loans) {
        int len = Math.min(loanAmounts.length, loans.length);
        System.arraycopy(loans, 0, loanAmounts, 0, len);
        printProcessedMessage();
    }

    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts).average().orElse(0);
        System.out.printf(getAverageFormat(), avg);
        System.out.println();
    }

    public void maxLoan() {
        int max = Arrays.stream(loanAmounts).max().orElse(0);
        System.out.printf(getMaxFormat(), max);
        System.out.println();
    }

    public void minLoan() {
        int min = Arrays.stream(loanAmounts).min().orElse(0);
        System.out.printf(getMinFormat(), min);
        System.out.println();
    }

    protected abstract void printProcessedMessage();

    protected abstract String getAverageFormat();

    protected abstract String getMaxFormat();

    protected abstract String getMinFormat();
}

class PersonalLoanDept extends LoanDept {
    public PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];
    }

    protected void printProcessedMessage() {
        System.out.println("Loans for clients processed");
    }

    protected String getAverageFormat() {
        return "Average loan amount for clients is %.2f";
    }

    protected String getMaxFormat() {
        return "Maximum loan amount amongst clients is %d";
    }

    protected String getMinFormat() {
        return "Minimum loan amount amongst clients is %d";
    }
}

class BusinessLoanDept extends LoanDept {
    public BusinessLoanDept(int businesses) {
        loanAmounts = new int[businesses];
    }

    protected void printProcessedMessage() {
        System.out.println("Loans for businesses processed");
    }

    protected String getAverageFormat() {
        return "Average loan amount for businesses is %.2f";
    }

    protected String getMaxFormat() {
        return "Maximum loan amongst businesses is %d";
    }

    protected String getMinFormat() {
        return "Minimum loan amongst businesses is %d";
    }
}

public class Main {
    static String runCase(int n, int m, int[] clients, int[] businesses) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(baos));
        PersonalLoanDept p = new PersonalLoanDept(n);
        p.assignLoans(clients);
        BusinessLoanDept b = new BusinessLoanDept(m);
        b.assignLoans(businesses);
        p.averageLoan();
        p.maxLoan();
        p.minLoan();
        b.averageLoan();
        b.maxLoan();
        b.minLoan();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    public static void main(String[] args) {
        List<Object[]> tests = new ArrayList<>();
        tests.add(new Object[]{4, 4, new int[]{2348, 929, 1284, 5543}, new int[]{3117, 5196, 3352, 7068},
                "Loans for clients processed\n" +
                        "Loans for businesses processed\n" +
                        "Average loan amount for clients is 2526.00\n" +
                        "Maximum loan amount amongst clients is 5543\n" +
                        "Minimum loan amount amongst clients is 929\n" +
                        "Average loan amount for businesses is 4683.25\n" +
                        "Maximum loan amongst businesses is 7068\n" +
                        "Minimum loan amongst businesses is 3117\n"
        });
        tests.add(new Object[]{3, 2, new int[]{100, 200, 300}, new int[]{400, 500},
                "Loans for clients processed\n" +
                        "Loans for businesses processed\n" +
                        "Average loan amount for clients is 200.00\n" +
                        "Maximum loan amount amongst clients is 300\n" +
                        "Minimum loan amount amongst clients is 100\n" +
                        "Average loan amount for businesses is 450.00\n" +
                        "Maximum loan amongst businesses is 500\n" +
                        "Minimum loan amongst businesses is 400\n"
        });
        tests.add(new Object[]{5, 3, new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0},
                "Loans for clients processed\n" +
                        "Loans for businesses processed\n" +
                        "Average loan amount for clients is 0.00\n" +
                        "Maximum loan amount amongst clients is 0\n" +
                        "Minimum loan amount amongst clients is 0\n" +
                        "Average loan amount for businesses is 0.00\n" +
                        "Maximum loan amongst businesses is 0\n" +
                        "Minimum loan amongst businesses is 0\n"
        });
        int largeN = 100000;
        int largeM = 100000;
        int[] largeClients = IntStream.rangeClosed(1, largeN).toArray();
        int[] largeBusinesses = IntStream.rangeClosed(largeN + 1, largeN + largeM).toArray();
        double avgClients = Arrays.stream(largeClients).average().orElse(0);
        double avgBusinesses = Arrays.stream(largeBusinesses).average().orElse(0);
        String expectedLarge = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                String.format("Average loan amount for clients is %.2f\n", avgClients) +
                String.format("Maximum loan amount amongst clients is %d\n", largeN) +
                String.format("Minimum loan amount amongst clients is %d\n", 1) +
                String.format("Average loan amount for businesses is %.2f\n", avgBusinesses) +
                String.format("Maximum loan amongst businesses is %d\n", largeN + largeM) +
                String.format("Minimum loan amongst businesses is %d\n", largeN + 1);
        tests.add(new Object[]{largeN, largeM, largeClients, largeBusinesses, expectedLarge});

        for (int i = 0; i < tests.size(); i++) {
            Object[] t = tests.get(i);
            int n = (int) t[0];
            int m = (int) t[1];
            int[] c = (int[]) t[2];
            int[] b = (int[]) t[3];
            String expected = (String) t[4];
            String actual = runCase(n, m, c, b);
            if (expected.equals(actual)) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL");
                System.out.println("Expected:");
                System.out.print(expected);
                System.out.println("Got:");
                System.out.print(actual);
            }
        }
    }
}
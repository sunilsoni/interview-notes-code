package com.interview.notes.code.year.y2025.march.common.test11;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

interface Bank {
    void assignLoans(int[] loans);

    void averageLoan();

    void maxLoan();

    void minLoan();
}

// Abstract base class to avoid code duplication
abstract class AbstractLoanDept implements Bank {
    protected int[] loanAmounts; // Store loan amounts
    protected String entityType; // "clients" or "businesses"

    // Constructor to initialize the loan array
    public AbstractLoanDept(int count, String entityType) {
        this.loanAmounts = new int[count];
        this.entityType = entityType;
    }

    // Common implementation of assignLoans
    @Override
    public void assignLoans(int[] loans) {
        // Copy as many values as possible from loans to loanAmounts
        int copyLength = Math.min(loans.length, loanAmounts.length);
        System.arraycopy(loans, 0, loanAmounts, 0, copyLength);
        System.out.println("Loans for " + entityType + " processed");
    }

    // Calculate average using Stream API
    @Override
    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts)
                .average()
                .orElse(0.0);
        // Format to two decimal places
        System.out.printf("Average loan amount for %s is %.2f%n", entityType, avg);
    }

    // Find maximum loan using Stream API
    @Override
    public void maxLoan() {
        int max = Arrays.stream(loanAmounts)
                .max()
                .orElse(0);
        System.out.println(getMaxLoanMessage(max));
    }

    // Find minimum loan using Stream API
    @Override
    public void minLoan() {
        int min = Arrays.stream(loanAmounts)
                .min()
                .orElse(0);
        System.out.println(getMinLoanMessage(min));
    }

    // Abstract methods for customized messages
    protected abstract String getMaxLoanMessage(int maxLoan);

    protected abstract String getMinLoanMessage(int minLoan);
}

// Personal loan department implementation
class PersonalLoanDept extends AbstractLoanDept {

    public PersonalLoanDept(int clients) {
        super(clients, "clients");
    }

    @Override
    protected String getMaxLoanMessage(int maxLoan) {
        return "Maximum loan amount amongst clients is " + maxLoan;
    }

    @Override
    protected String getMinLoanMessage(int minLoan) {
        return "Minimum loan amount amongst clients is " + minLoan;
    }
}

// Business loan department implementation
class BusinessLoanDept extends AbstractLoanDept {

    public BusinessLoanDept(int businesses) {
        super(businesses, "businesses");
    }

    @Override
    protected String getMaxLoanMessage(int maxLoan) {
        return "Maximum loan amongst businesses is " + maxLoan;
    }

    @Override
    protected String getMinLoanMessage(int minLoan) {
        return "Minimum loan amongst businesses is " + minLoan;
    }
}

// Main class to test the solution
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of clients and businesses
        String[] counts = scanner.nextLine().split(" ");
        int n = Integer.parseInt(counts[0]); // Number of clients
        int m = Integer.parseInt(counts[1]); // Number of businesses

        // Create departments
        PersonalLoanDept personalDept = new PersonalLoanDept(n);
        BusinessLoanDept businessDept = new BusinessLoanDept(m);

        // Read and assign client loans
        int[] clientLoans = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        personalDept.assignLoans(clientLoans);

        // Read and assign business loans
        int[] businessLoans = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        businessDept.assignLoans(businessLoans);

        // Process and display loan statistics
        personalDept.averageLoan();
        personalDept.maxLoan();
        personalDept.minLoan();

        businessDept.averageLoan();
        businessDept.maxLoan();
        businessDept.minLoan();

        scanner.close();
    }

    // Test method to verify solution against test cases
    public static void testSolution() {
        // Test case 0
        String input0 = "4 4\n2348 929 1284 5543\n3117 5196 3352 7068";
        String expectedOutput0 = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                "Average loan amount for clients is 2526.00\n" +
                "Maximum loan amount amongst clients is 5543\n" +
                "Minimum loan amount amongst clients is 929\n" +
                "Average loan amount for businesses is 4683.25\n" +
                "Maximum loan amongst businesses is 7068\n" +
                "Minimum loan amongst businesses is 3117";

        System.out.println("Test Case 0: " +
                (runTest(input0).trim().equals(expectedOutput0.trim()) ? "PASS" : "FAIL"));

        // Test case 1
        String input1 = "5 3\n1500 3000 4500\n1000 2000";
        String expectedOutput1 = "Loans for clients processed\n" +
                "Loans for businesses processed\n" +
                "Average loan amount for clients is 1800.00\n" +
                "Maximum loan amount amongst clients is 4500\n" +
                "Minimum loan amount amongst clients is 0\n" +
                "Average loan amount for businesses is 1000.00\n" +
                "Maximum loan amongst businesses is 2000\n" +
                "Minimum loan amongst businesses is 0";

        System.out.println("Test Case 1: " +
                (runTest(input1).trim().equals(expectedOutput1.trim()) ? "PASS" : "FAIL"));

        // Add more test cases as needed
    }

    // Helper method to run tests
    private static String runTest(String input) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outStream);

        PrintStream oldOut = System.out;
        InputStream oldIn = System.in;

        try {
            System.setIn(inStream);
            System.setOut(printStream);

            main(new String[0]);

        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }

        return outStream.toString();
    }
}
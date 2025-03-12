package com.interview.notes.code.year.y2025.march.common.test12;

import java.util.*;                      // For Scanner, Arrays, etc.
import java.util.stream.*;               // For Java 8 streams
import java.io.*;                        // For Input/Output redirection in tests

// Define the Bank interface with required methods.
interface Bank {
    void assignLoans(int[] loans);
    void averageLoan();
    void maxLoan();
    void minLoan();
}

// Implementation for PersonalLoanDept that processes client loans.
class PersonalLoanDept implements Bank {
    private int[] loanAmounts;  // Array to store loan amounts for clients

    // Constructor: initializes the loanAmounts array for given number of clients.
    public PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];  // Default values are 0
    }

    // Copies values from the input array to the loanAmounts array.
    // Only as many values as possible are assigned; remaining stay 0.
    @Override
    public void assignLoans(int[] loans) {
        for (int i = 0; i < Math.min(loans.length, loanAmounts.length); i++) {
            loanAmounts[i] = loans[i];
        }
        System.out.println("Loans for clients processed");
    }

    // Calculates and prints the average loan amount for clients.
    @Override
    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts)
                           .average()
                           .orElse(0.0);
        // Print the average formatted to two decimal places.
        System.out.println(String.format("Average loan amount for clients is %.2f", avg));
    }

    // Finds and prints the maximum loan amount among clients.
    @Override
    public void maxLoan() {
        int max = Arrays.stream(loanAmounts)
                        .max()
                        .orElse(0);
        System.out.println("Maximum loan amount amongst clients is " + max);
    }

    // Finds and prints the minimum loan amount among clients.
    @Override
    public void minLoan() {
        int min = Arrays.stream(loanAmounts)
                        .min()
                        .orElse(0);
        System.out.println("Minimum loan amount amongst clients is " + min);
    }
}

// Implementation for BusinessLoanDept that processes business loans.
class BusinessLoanDept implements Bank {
    private int[] loanAmounts;  // Array to store loan amounts for businesses

    // Constructor: initializes the loanAmounts array for given number of businesses.
    public BusinessLoanDept(int businesses) {
        loanAmounts = new int[businesses];
    }

    // Copies values from the input array to the loanAmounts array.
    @Override
    public void assignLoans(int[] loans) {
        for (int i = 0; i < Math.min(loans.length, loanAmounts.length); i++) {
            loanAmounts[i] = loans[i];
        }
        System.out.println("Loans for businesses processed");
    }

    // Calculates and prints the average loan amount for businesses.
    @Override
    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts)
                           .average()
                           .orElse(0.0);
        System.out.println(String.format("Average loan amount for businesses is %.2f", avg));
    }

    // Finds and prints the maximum loan amount among businesses.
    @Override
    public void maxLoan() {
        int max = Arrays.stream(loanAmounts)
                        .max()
                        .orElse(0);
        System.out.println("Maximum loan amongst businesses is " + max);
    }

    // Finds and prints the minimum loan amount among businesses.
    @Override
    public void minLoan() {
        int min = Arrays.stream(loanAmounts)
                        .min()
                        .orElse(0);
        System.out.println("Minimum loan amongst businesses is " + min);
    }
}

// Main class that processes loan inputs and runs tests if needed.
public class LoanProcessingSystem {

    // Main method that either runs tests or processes input.
    public static void main(String[] args) throws Exception {
        // Run tests if "test" argument is provided.
        if (args.length > 0 && args[0].equals("test")) {
            runTests();
            return;
        }

        // Otherwise, process the standard input.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // Number of personal loan applicants
        int m = sc.nextInt();  // Number of business loan applicants

        int[] personalLoans = new int[n];
        for (int i = 0; i < n; i++) {
            personalLoans[i] = sc.nextInt();
        }

        int[] businessLoans = new int[m];
        for (int i = 0; i < m; i++) {
            businessLoans[i] = sc.nextInt();
        }

        // Process personal loans.
        PersonalLoanDept pld = new PersonalLoanDept(n);
        pld.assignLoans(personalLoans);
        // Process business loans.
        BusinessLoanDept bld = new BusinessLoanDept(m);
        bld.assignLoans(businessLoans);

        // Output statistics for personal loans.
        pld.averageLoan();
        pld.maxLoan();
        pld.minLoan();
        // Output statistics for business loans.
        bld.averageLoan();
        bld.maxLoan();
        bld.minLoan();

        sc.close();
    }

    // Method to run multiple test cases and print PASS/FAIL for each.
    public static void runTests() throws Exception {
        List<TestCase> tests = new ArrayList<>();

        // Test Case 0
        tests.add(new TestCase(
            "4 4\n2348 929 1284 5543\n3117 5196 3352 7068\n",
            "Loans for clients processed\n" +
            "Loans for businesses processed\n" +
            "Average loan amount for clients is 2526.00\n" +
            "Maximum loan amount amongst clients is 5543\n" +
            "Minimum loan amount amongst clients is 929\n" +
            "Average loan amount for businesses is 4683.25\n" +
            "Maximum loan amongst businesses is 7068\n" +
            "Minimum loan amongst businesses is 3117"
        ));

        // Test Case 1: Fewer loans than the array size; unassigned positions remain 0.
        tests.add(new TestCase(
            "5 3\n1500 3000 4500\n1000 2000\n",
            "Loans for clients processed\n" +
            "Loans for businesses processed\n" +
            "Average loan amount for clients is 1800.00\n" +
            "Maximum loan amount amongst clients is 4500\n" +
            "Minimum loan amount amongst clients is 0\n" +
            "Average loan amount for businesses is 1000.00\n" +
            "Maximum loan amongst businesses is 2000\n" +
            "Minimum loan amongst businesses is 0"
        ));

        // You can add additional test cases here for further validation.

        // Execute each test case.
        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            String result = runProgram(tc.input);
            if (result.trim().equals(tc.expectedOutput.trim())) {
                System.out.println("Test Case " + i + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + i + ": FAIL");
                System.out.println("Expected:\n" + tc.expectedOutput);
                System.out.println("Got:\n" + result);
            }
        }
        System.out.println("Passed " + passed + " out of " + tests.size() + " tests.");
    }

    // Helper method that simulates program execution using provided input.
    public static String runProgram(String input) throws Exception {
        // Backup original System.in and System.out.
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // Set new System.in based on the test input.
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        // Redirect System.out to capture the output.
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        // Call main() to run the program.
        main(new String[]{});

        // Restore the original System.in and System.out.
        System.setIn(originalIn);
        System.setOut(originalOut);

        return testOut.toString().trim();
    }

    // Inner class to hold test case input and expected output.
    static class TestCase {
        String input;
        String expectedOutput;
        public TestCase(String input, String expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
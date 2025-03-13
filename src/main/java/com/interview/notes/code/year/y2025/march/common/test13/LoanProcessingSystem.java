package com.interview.notes.code.year.y2025.march.common.test13;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*


### **Loan Processing System**

#### **Problem Statement**
You are given an interface called **Bank** that defines the following methods:

```java
void assignLoans(int[] loans);
void averageLoan();
void maxLoan();
void minLoan();
```

You need to create two classes, **PersonalLoanDept** and **BusinessLoanDept**, that implement the **Bank** interface.

---

### **Class Specifications**

#### **1. PersonalLoanDept**
- This class should have an integer array **loanAmounts**.
- It should include the following methods:

    - **PersonalLoanDept(int clients)**: Initializes an empty array **loanAmounts** of length `clients`. All loan amounts are initially set to zero.

    - **void assignLoans(int[] loans)**: Assigns values from `loans` to **loanAmounts**. If the lengths differ, only as many values as possible should be assigned. After that, print:
      ```
      Loans for clients processed
      ```

    - **void averageLoan()**: Prints the average loan amount for clients in the following format:
      ```
      Average loan amount for clients is {averageLoan}
      ```
      - Any zero values in **loanAmounts** should be included in the average.
      - The result should be rounded to **two decimal places**.

    - **void maxLoan()**: Prints the maximum loan amount among clients in the format:
      ```
      Maximum loan amount amongst clients is {maximumLoan}
      ```

    - **void minLoan()**: Prints the minimum loan amount among clients in the format:
      ```
      Minimum loan amount amongst clients is {minimumLoan}
      ```

---

#### **2. BusinessLoanDept**
- This class should also have an integer array **loanAmounts**.
- It should include the following methods:

    - **BusinessLoanDept(int businesses)**: Initializes an empty array **loanAmounts** of length `businesses`, representing business loan recipients.

    - **void assignLoans(int[] loans)**: Assigns values from `loans` to **loanAmounts**. If the lengths differ, only as many values as possible should be assigned. After that, print:
      ```
      Loans for businesses processed
      ```

    - **void averageLoan()**: Prints the average loan amount for businesses in the following format:
      ```
      Average loan amount for businesses is {averageLoan}
      ```
      - Any zero values in **loanAmounts** should be included in the average.
      - The result should be rounded to **two decimal places**.

    - **void maxLoan()**: Prints the maximum loan amount among businesses in the format:
      ```
      Maximum loan amongst businesses is {maximumLoan}
      ```

    - **void minLoan()**: Prints the minimum loan amount among businesses in the format:
      ```
      Minimum loan amongst businesses is {minimumLoan}
      ```

---

### **Constraints**
- Ensure that **inheritance and encapsulation** are used to prevent redundant code.

---

### **Input Format**
- The first line contains two space-separated integers, **n** and **m**, representing:
  - `n`: The number of personal loan applicants.
  - `m`: The number of business loan applicants.

- The second line contains `n` space-separated integers representing the loan amounts of **clients**.

- The third line contains `m` space-separated integers representing the loan amounts of **businesses**.

---

### **Sample Cases**

#### **Sample Case 0**
**Input:**
```
4 4
2348 929 1284 5543
3117 5196 3352 7068
```

**Output:**
```
Loans for clients processed
Loans for businesses processed
Average loan amount for clients is 2526.00
Maximum loan amount amongst clients is 5543
Minimum loan amount amongst clients is 929
Average loan amount for businesses is 4683.25
Maximum loan amongst businesses is 7068
Minimum loan amongst businesses is 3117
```

---

#### **Sample Case 1**
**Input:**
```
5 3
1500 3000 4500
1000 2000
```

**Output:**
```
Loans for clients processed
Loans for businesses processed
Average loan amount for clients is 1800.00
Maximum loan amount amongst clients is 4500
Minimum loan amount amongst clients is 0
Average loan amount for businesses is 1000.00
Maximum loan amongst businesses is 2000
Minimum loan amongst businesses is 0
```

---

#### **Sample Case 2**
**Input:**
```
4 4
1000 2000 0 3000
4000 0 5000 6000
```

**Output:**
```
Loans for clients processed
Loans for businesses processed
Average loan amount for clients is 3000.00
Maximum loan amount amongst clients is 4000
Minimum loan amount amongst clients is 2000
Average loan amount for businesses is 2625.00
Maximum loan amongst businesses is 4500
Minimum loan amongst businesses is 0
```

---

#### **Sample Case 3**
**Input:**
```
3 3
1500 3000 4500
1000 2000
```

**Output:**
```
Loans for clients processed
Loans for businesses processed
Average loan amount for clients is 1500.00
Maximum loan amount amongst clients is 3000
Minimum loan amount amongst clients is 0
Average loan amount for businesses is 3750.00
Maximum loan amongst businesses is 6000
Minimum loan amongst businesses is 0
```

---

This question tests **object-oriented programming (OOP) concepts** like **interfaces, inheritance, and encapsulation**. The implementation should ensure **reusability and proper structure**.

import java.io.*; import
 java.util.*; import java.text.*; import java.math.*;
import java.util.regex.*;
import java.util.stream.*;
interface Bank {
void assignLoans(int[] loans);
void averageLoan) ;
void maxLoan) ; void minLoan);

 */
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

    // Constructor: initializes the loanAmounts array for the given number of clients.
    public PersonalLoanDept(int clients) {
        loanAmounts = new int[clients];  // All elements default to 0.
    }

    @Override
    public void assignLoans(int[] loans) {
        for (int i = 0; i < Math.min(loans.length, loanAmounts.length); i++) {
            loanAmounts[i] = loans[i];
        }
        System.out.println("Loans for clients processed");
    }

    // Calculates and prints the average loan amount for clients using streams.
    @Override
    public void averageLoan() {
        double avg = Arrays.stream(loanAmounts)
                .average()
                .orElse(0.0);
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

    // Constructor: initializes the loanAmounts array for the given number of businesses.
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

    // Calculates and prints the average loan amount for businesses using streams.
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

    // Main method: checks for "test" command or processes input normally.
    public static void main(String[] args) throws Exception {
        // Check for "test" as a command-line argument.
        if (args.length > 0 && args[0].equals("test")) {
            runTests();
            return;
        }

        // Create a Scanner for standard input.
        Scanner sc = new Scanner(System.in);

        // If the first line is "test", run tests.
        if (sc.hasNextLine()) {
            String firstLine = sc.nextLine().trim();
            if (firstLine.equals("test")) {
                runTests();
                return;
            }

            // Split the first line into tokens for 'n' and 'm'.
            String[] firstTokens = firstLine.split("\\s+");
            int n = Integer.parseInt(firstTokens[0]); // Number of personal loan applicants.
            int m = Integer.parseInt(firstTokens[1]); // Number of business loan applicants.

            // Read personal loan amounts.
            int[] personalLoans = new int[n];
            if (sc.hasNextLine()) {
                String personalLine = sc.nextLine().trim();
                String[] personalTokens = personalLine.isEmpty() ? new String[0] : personalLine.split("\\s+");
                for (int i = 0; i < Math.min(n, personalTokens.length); i++) {
                    personalLoans[i] = Integer.parseInt(personalTokens[i]);
                }
            }

            // Read business loan amounts.
            int[] businessLoans = new int[m];
            if (sc.hasNextLine()) {
                String businessLine = sc.nextLine().trim();
                String[] businessTokens = businessLine.isEmpty() ? new String[0] : businessLine.split("\\s+");
                for (int i = 0; i < Math.min(m, businessTokens.length); i++) {
                    businessLoans[i] = Integer.parseInt(businessTokens[i]);
                }
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
        }

        sc.close();
    }

    // Method to run multiple test cases and display PASS/FAIL results.
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

        // Test Case 1
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

        // Run each test case.
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

    // Helper method that simulates program execution using the provided input.
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

        // Call main() with no arguments.
        main(new String[]{});

        // Restore original System.in and System.out.
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
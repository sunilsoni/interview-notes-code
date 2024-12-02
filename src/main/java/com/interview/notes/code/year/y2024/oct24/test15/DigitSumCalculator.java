package com.interview.notes.code.year.y2024.oct24.test15;

public class DigitSumCalculator {

    public static void main(String[] args) {
        DigitSumCalculator calculator = new DigitSumCalculator();
        calculator.runTests();
    }

    // Solution method to sum digits from two strings
    public String solution(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;

        // Loop through both strings from the end towards the beginning
        while (i >= 0 || j >= 0) {
            int digitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j) - '0' : 0;

            // Sum the current digits
            int sum = digitA + digitB;
            result.insert(0, sum); // Insert the sum at the beginning of the result
            i--;
            j--;
        }

        // Return the result as a final string
        return result.toString();
    }

    // Method to run tests and check if they pass or fail
    public void runTests() {
        // Example test cases
        String[] testA = {"99", "11", "123456", "1000"};
        String[] testB = {"99", "9", "654321", "9999"};
        String[] expectedResults = {"1818", "110", "777777", "10999"};

        boolean allTestsPassed = true;

        for (int i = 0; i < testA.length; i++) {
            String result = solution(testA[i], testB[i]);
            boolean testPassed = result.equals(expectedResults[i]);
            System.out.println("Test Case " + (i + 1) + ": " + (testPassed ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] + ", Got: " + result + ")");
            if (!testPassed) {
                allTestsPassed = false;
            }
        }

        // Run large data test
        String largeA = "9".repeat(1000);
        String largeB = "1".repeat(1000);
        String largeResult = solution(largeA, largeB);
        boolean largeTestPassed = largeResult.equals("110".repeat(500));
        System.out.println("Large Data Test: " + (largeTestPassed ? "PASS" : "FAIL") +
                " (Expected: 110 * 500, Got: " + largeResult.substring(0, 10) + "...)");

        if (!allTestsPassed || !largeTestPassed) {
            System.out.println("Some tests failed. Please check the implementation.");
        } else {
            System.out.println("All tests passed successfully.");
        }
    }
}

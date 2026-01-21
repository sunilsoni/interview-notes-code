package com.interview.notes.code.year.y2025.december.microsoft.test1;

import java.util.stream.IntStream;

public class BigIntegerAddition {

    /**
     * Adds two big integers represented as strings
     *
     * @param num1 - First big integer as string
     * @param num2 - Second big integer as string
     * @return Sum of both numbers as string
     */
    public static String addBigIntegers(String num1, String num2) {

        // Handle null inputs by treating them as zero
        // This prevents NullPointerException during operations
        if (num1 == null || num1.isEmpty()) {
            num1 = "0";
        }

        // Same null check for second number
        // Ensures we always have valid string to work with
        if (num2 == null || num2.isEmpty()) {
            num2 = "0";
        }

        // Remove any leading zeros from first number
        // Example: "00123" becomes "123" for clean processing
        num1 = removeLeadingZeros(num1);

        // Remove leading zeros from second number as well
        // Maintains consistency in our input data
        num2 = removeLeadingZeros(num2);

        // StringBuilder is more efficient than String concatenation
        // It allows us to build result character by character
        StringBuilder result = new StringBuilder();

        // Start from rightmost digit of first number
        // We add from right to left like manual addition
        int index1 = num1.length() - 1;

        // Start from rightmost digit of second number
        // Both pointers will move left together
        int index2 = num2.length() - 1;

        // Carry stores overflow from each digit addition
        // When sum >= 10, carry becomes 1
        int carry = 0;

        // Continue loop while either number has digits OR carry exists
        // We must process all digits and any remaining carry
        while (index1 >= 0 || index2 >= 0 || carry > 0) {

            // Get digit from first number if available
            // Character '0' has ASCII value 48, subtracting gives actual digit
            int digit1 = (index1 >= 0) ? num1.charAt(index1) - '0' : 0;

            // Get digit from second number if available
            // If index is negative, use 0 (no more digits)
            int digit2 = (index2 >= 0) ? num2.charAt(index2) - '0' : 0;

            // Add both digits plus any carry from previous addition
            // Example: 7 + 8 + 1(carry) = 16
            int sum = digit1 + digit2 + carry;

            // Extract the ones digit to add to result
            // Example: 16 % 10 = 6 (the digit we keep)
            int digitToAdd = sum % 10;

            // Calculate new carry for next iteration
            // Example: 16 / 10 = 1 (carry forward)
            carry = sum / 10;

            // Append current digit to result
            // We build from right, will reverse later
            result.append(digitToAdd);

            // Move left in first number
            // Decrement to process next digit
            index1--;

            // Move left in second number
            // Both pointers move independently
            index2--;
        }

        // Reverse the result since we built it backwards
        // "654" becomes "456" which is correct order
        String finalResult = result.reverse().toString();

        // Remove any leading zeros from final result
        // Ensures clean output like "456" not "00456"
        return removeLeadingZeros(finalResult);
    }

    /**
     * Removes leading zeros from a number string
     *
     * @param number - String representation of number
     * @return Number without leading zeros
     */
    private static String removeLeadingZeros(String number) {

        // Handle empty string case
        // Return "0" as default for empty input
        if (number == null || number.isEmpty()) {
            return "0";
        }

        // Use Java 8 Stream to find first non-zero index
        // IntStream.range creates sequence 0,1,2,...,length-1
        int firstNonZero = IntStream.range(0, number.length())
                // Filter to find positions where character is not '0'
                // This identifies where actual number starts
                .filter(i -> number.charAt(i) != '0')
                // Get the first such position
                // Returns OptionalInt since stream might be empty
                .findFirst()
                // If all zeros, return last index (for "000" -> "0")
                // Default ensures we keep at least one zero
                .orElse(number.length() - 1);

        // Extract substring from first non-zero position
        // This effectively removes all leading zeros
        return number.substring(firstNonZero);
    }

    /**
     * Test runner that validates expected vs actual results
     *
     * @param testName - Name of the test case
     * @param num1     - First number input
     * @param num2     - Second number input
     * @param expected - Expected result
     */
    private static void runTest(String testName, String num1, String num2, String expected) {

        // Call our addition method with test inputs
        // Store result for comparison
        String actual = addBigIntegers(num1, num2);

        // Compare actual result with expected result
        // equals() handles null safety and content comparison
        boolean passed = actual.equals(expected);

        // Determine pass/fail status string
        // Ternary operator for concise conditional
        String status = passed ? "PASS" : "FAIL";

        // Print test result with all details
        // Helps in debugging when tests fail
        System.out.println("Test: " + testName);
        System.out.println("  Input: num1=" + num1 + ", num2=" + num2);
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual: " + actual);
        System.out.println("  Status: " + status);
        System.out.println("-".repeat(50));
    }

    /**
     * Generates a large number string for stress testing
     *
     * @param length - Number of digits to generate
     * @return String of specified length with digit '9'
     */
    private static String generateLargeNumber(int length) {

        // Use Java 8 Stream to generate repeated character
        // IntStream creates sequence of specified length
        return IntStream.range(0, length)
                // Map each position to character '9'
                // Creates worst case for carry propagation
                .mapToObj(i -> "9")
                // Collect all characters into single string
                // Efficient concatenation using joining collector
                .reduce("", String::concat);
    }

    /**
     * Main method - Entry point for testing
     * Runs all test cases and reports results
     */
    public static void main(String[] args) {

        // Print header for test output
        // Helps organize console output
        System.out.println("=".repeat(50));
        System.out.println("BIG INTEGER ADDITION - TEST RESULTS");
        System.out.println("=".repeat(50));

        // TEST 1: Basic addition with same length numbers
        // Simple case: 123 + 456 = 579
        runTest("Basic Addition - Same Length",
                "123", "456", "579");

        // TEST 2: Addition with different length numbers
        // Tests handling when one number is longer
        runTest("Different Length Numbers",
                "99", "1", "100");

        // TEST 3: Addition with carry propagation
        // Multiple carries: 999 + 1 = 1000
        runTest("Carry Propagation",
                "999", "1", "1000");

        // TEST 4: Adding zeros
        // Zero handling: 0 + 0 = 0
        runTest("Both Zeros",
                "0", "0", "0");

        // TEST 5: Adding with one zero
        // Identity property: x + 0 = x
        runTest("One Zero",
                "12345", "0", "12345");

        // TEST 6: Leading zeros in input
        // Should be stripped: 00123 + 00456 = 579
        runTest("Leading Zeros",
                "00123", "00456", "579");

        // TEST 7: Large numbers that exceed Long range
        // Long.MAX_VALUE is about 19 digits
        runTest("Beyond Long Range",
                "99999999999999999999",
                "1",
                "100000000000000000000");

        // TEST 8: Very large numbers (100 digits each)
        // Stress test for performance
        String largeNum1 = generateLargeNumber(100);
        String largeNum2 = "1";
        // 100 nines + 1 = 1 followed by 100 zeros
        String expectedLarge = "1" + "0".repeat(100);
        runTest("100 Digit Number",
                largeNum1, largeNum2, expectedLarge);

        // TEST 9: Empty string handling
        // Edge case: empty treated as zero
        runTest("Empty String Input",
                "", "123", "123");

        // TEST 10: Null handling
        // Edge case: null treated as zero
        runTest("Null Input",
                null, "456", "456");

        // TEST 11: Single digit addition with carry
        // Basic carry: 9 + 9 = 18
        runTest("Single Digit With Carry",
                "9", "9", "18");

        // TEST 12: Symmetric large addition
        // Both numbers same and large
        runTest("Symmetric Large Numbers",
                "5000000000000000000",
                "5000000000000000000",
                "10000000000000000000");

        // TEST 13: Extra large numbers (500 digits)
        // Performance test with massive numbers
        String veryLarge1 = generateLargeNumber(500);
        String veryLarge2 = generateLargeNumber(500);
        // Track execution time for performance
        long startTime = System.currentTimeMillis();
        String resultVeryLarge = addBigIntegers(veryLarge1, veryLarge2);
        long endTime = System.currentTimeMillis();

        // Verify result length (500 nines + 500 nines = 501 digits)
        // Sum of two n-digit max numbers gives n+1 digit result
        boolean lengthCorrect = resultVeryLarge.length() == 501;
        System.out.println("Test: 500 Digit Numbers");
        System.out.println("  Result Length: " + resultVeryLarge.length());
        System.out.println("  Length Correct (501): " + lengthCorrect);
        System.out.println("  Execution Time: " + (endTime - startTime) + "ms");
        System.out.println("  Status: " + (lengthCorrect ? "PASS" : "FAIL"));
        System.out.println("-".repeat(50));

        // TEST 14: Asymmetric lengths
        // One very long, one short
        runTest("Asymmetric Lengths",
                "1" + "0".repeat(50),
                "1",
                "1" + "0".repeat(49) + "1");

        // Print summary footer
        // Indicates end of test execution
        System.out.println("=".repeat(50));
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("=".repeat(50));
    }
}
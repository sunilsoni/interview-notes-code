package com.interview.notes.code.year.y2025.december.microsoft.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
     * Main method - Entry point for data-driven testing
     * All test cases defined in data structure - just add new row for new test
     */
    public static void main(String[] args) {

        // ============================================================
        // DATA-DRIVEN TEST CASES - Just add new row to add new test!
        // Format: { "TestName", "Number1", "Number2", "ExpectedResult" }
        // ============================================================
        String[][] testData = {
                // Basic test cases
                {"Basic Addition Same Length", "123", "456", "579"},
                {"Different Length Numbers", "99", "1", "100"},
                {"Carry Propagation", "999", "1", "1000"},

                // Zero handling cases
                {"Both Zeros", "0", "0", "0"},
                {"First Zero", "0", "12345", "12345"},
                {"Second Zero", "12345", "0", "12345"},

                // Leading zeros cases
                {"Leading Zeros Both", "00123", "00456", "579"},
                {"Leading Zeros First", "00099", "1", "100"},

                // Single digit cases
                {"Single Digit No Carry", "3", "4", "7"},
                {"Single Digit With Carry", "9", "9", "18"},

                // Large number cases (beyond Long range)
                {"Beyond Long Range", "99999999999999999999", "1", "100000000000000000000"},
                {"Large Symmetric", "5000000000000000000", "5000000000000000000", "10000000000000000000"},

                // Edge cases with empty/null
                {"Empty First", "", "123", "123"},
                {"Empty Second", "456", "", "456"},
                {"Both Empty", "", "", "0"},

                // Complex carry cases
                {"Multiple Carries", "999999", "1", "1000000"},
                {"All Nines Addition", "999", "999", "1998"},

                // Asymmetric length cases
                {"Very Different Lengths", "1", "99999999999", "100000000000"},
                {"Long Plus Short", "123456789012345678901234567890", "1", "123456789012345678901234567891"},

                // *** ADD NEW TEST CASES HERE - Just add new row! ***
                {"Custom Test 1", "12345678901234567890", "98765432109876543210", "111111111011111111100"},
                {"Custom Test 2", "1" + "0".repeat(50), "1", "1" + "0".repeat(49) + "1"},
        };

        // ============================================================
        // DYNAMIC LARGE DATA TEST CASES
        // Generated programmatically for stress testing
        // ============================================================
        List<String[]> allTests = new ArrayList<>();

        // Add all static test data to list
        // Stream.of converts array to stream for easy processing
        Stream.of(testData).forEach(allTests::add);

        // Generate and add large number test cases dynamically
        // 100 digit test case
        allTests.add(new String[]{
                "100 Digit Numbers",                    // Test name
                generateLargeNumber(100),               // First number: 100 nines
                "1",                                    // Second number
                "1" + "0".repeat(100)                   // Expected: 1 followed by 100 zeros
        });

        // 500 digit test case
        allTests.add(new String[]{
                "500 Digit Numbers",                    // Test name
                generateLargeNumber(500),               // First number: 500 nines
                "1",                                    // Second number
                "1" + "0".repeat(500)                   // Expected: 1 followed by 500 zeros
        });

        // 1000 digit test case - extreme stress test
        allTests.add(new String[]{
                "1000 Digit Numbers",                   // Test name
                generateLargeNumber(1000),              // First number: 1000 nines
                generateLargeNumber(1000),              // Second number: 1000 nines
                "1" + "9".repeat(999) + "8"             // Expected result
        });

        // ============================================================
        // TEST EXECUTION ENGINE - Processes all test data automatically
        // ============================================================

        // Print header for test output
        // Visual separator for clarity
        System.out.println("=".repeat(70));
        System.out.println("BIG INTEGER ADDITION - DATA-DRIVEN TEST RESULTS");
        System.out.println("=".repeat(70));

        // Counters to track pass/fail statistics
        // Will be updated as tests run
        int[] passCount = {0};  // Array to allow modification in lambda
        int[] failCount = {0};  // Array to allow modification in lambda

        // Process each test case using Java 8 Stream
        // IntStream gives us index for test numbering
        IntStream.range(0, allTests.size()).forEach(index -> {

            // Get current test data row
            // Each row contains: testName, num1, num2, expected
            String[] test = allTests.get(index);

            // Extract test components from array
            // Index 0 = test name for identification
            String testName = test[0];

            // Index 1 = first number input
            String num1 = test[1];

            // Index 2 = second number input
            String num2 = test[2];

            // Index 3 = expected result for validation
            String expected = test[3];

            // Execute the actual addition
            // This is the method under test
            long startTime = System.currentTimeMillis();
            String actual = addBigIntegers(num1, num2);
            long endTime = System.currentTimeMillis();

            // Compare actual with expected
            // equals() handles content comparison
            boolean passed = actual.equals(expected);

            // Update counters based on result
            // Increment appropriate counter
            if (passed) {
                passCount[0]++;
            } else {
                failCount[0]++;
            }

            // Determine status string for output
            // Green checkmark concept for pass
            String status = passed ? "✓ PASS" : "✗ FAIL";

            // Print test result - concise format
            // Shows test number, name, and status
            System.out.printf("Test #%02d: %-30s | %s | %dms%n",
                    index + 1,                          // Test number (1-based)
                    testName,                           // Test case name
                    status,                             // Pass or fail
                    (endTime - startTime));             // Execution time

            // If test failed, print detailed info for debugging
            // Helps identify what went wrong
            if (!passed) {
                // Truncate long numbers for display
                // Shows first 30 chars with ellipsis if longer
                String displayNum1 = num1.length() > 30 ? num1.substring(0, 30) + "..." : num1;
                String displayNum2 = num2.length() > 30 ? num2.substring(0, 30) + "..." : num2;
                String displayExpected = expected.length() > 30 ? expected.substring(0, 30) + "..." : expected;
                String displayActual = actual.length() > 30 ? actual.substring(0, 30) + "..." : actual;

                System.out.println("         Input1: " + displayNum1);
                System.out.println("         Input2: " + displayNum2);
                System.out.println("         Expected: " + displayExpected);
                System.out.println("         Actual: " + displayActual);
            }
        });

        // ============================================================
        // TEST SUMMARY - Overall results
        // ============================================================
        System.out.println("=".repeat(70));
        System.out.println("TEST SUMMARY");
        System.out.println("-".repeat(70));
        System.out.printf("Total Tests: %d%n", allTests.size());
        System.out.printf("Passed: %d%n", passCount[0]);
        System.out.printf("Failed: %d%n", failCount[0]);
        System.out.printf("Success Rate: %.2f%%%n", (passCount[0] * 100.0 / allTests.size()));
        System.out.println("=".repeat(70));

        // Final verdict
        // Clear indication if all tests passed
        if (failCount[0] == 0) {
            System.out.println("★ ALL TESTS PASSED! ★");
        } else {
            System.out.println("✗ SOME TESTS FAILED - Please review above");
        }
        System.out.println("=".repeat(70));
    }
}
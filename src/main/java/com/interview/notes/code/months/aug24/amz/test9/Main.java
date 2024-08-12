package com.interview.notes.code.months.aug24.amz.test9;

public class Main {
    public static void main(String[] args) {
        // Test cases
        testGetMinErrors("10101", 1, 2, 1);
        testGetMinErrors("00000", 2, 3, 2);
        testGetMinErrors("11111", 3, 2, 3);
        testGetMinErrors("10010", 2, 2, 2);
        testGetMinErrors("", 1, 1, 0);  // Edge case: empty string
        testGetMinErrors("01!0", 2, 3, 8);  //
        // System.out.println(getMinErrors("01!0", 2, 3)); // Output: 8
    }

    /*
     * Complete the 'getMinErrors' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. STRING errorString
     * 2. INTEGER x
     * 3. INTEGER y
     */
    public static int getMinErrors(String errorString, int x, int y) {
        if (errorString == null || errorString.isEmpty()) {
            return 0;  // Handle edge case of empty or null string
        }

        int errors = 0;
        int onesCount = 0;
        int zerosCount = 0;

        for (char c : errorString.toCharArray()) {
            if (c == '1') {
                onesCount++;
                if (onesCount > x) {
                    errors++;
                    onesCount = 1;  // Reset count, keeping the current '1'
                }
                zerosCount = 0;  // Reset zeros count
            } else if (c == '0') {
                zerosCount++;
                if (zerosCount > y) {
                    errors++;
                    zerosCount = 1;  // Reset count, keeping the current '0'
                }
                onesCount = 0;  // Reset ones count
            } else {
                // If the string contains characters other than '0' and '1', count as an error
                errors++;
            }
        }

        return errors;
    }

    // Helper method to test the getMinErrors function
    private static void testGetMinErrors(String errorString, int x, int y, int expectedResult) {
        int result = getMinErrors(errorString, x, y);
        System.out.println("Input: errorString = \"" + errorString + "\", x = " + x + ", y = " + y);
        System.out.println("Expected: " + expectedResult);
        System.out.println("Result: " + result);
        System.out.println("Test " + (result == expectedResult ? "PASSED" : "FAILED"));
        System.out.println();
    }
}

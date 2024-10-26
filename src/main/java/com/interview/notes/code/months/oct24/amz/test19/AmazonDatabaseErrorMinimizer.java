package com.interview.notes.code.months.oct24.amz.test19;

public class AmazonDatabaseErrorMinimizer {

    public static int getMinErrors(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        int n = errorString.length();
        char[] s = errorString.toCharArray();

        // Precompute counts of '0's and '1's to the left of each position
        int[] countZerosLeft = new int[n + 1];
        int[] countOnesLeft = new int[n + 1];
        for (int i = 0; i < n; i++) {
            countZerosLeft[i + 1] = countZerosLeft[i];
            countOnesLeft[i + 1] = countOnesLeft[i];
            if (s[i] == '0') {
                countZerosLeft[i + 1]++;
            } else if (s[i] == '1') {
                countOnesLeft[i + 1]++;
            }
        }

        // Precompute counts of '0's and '1's to the right of each position
        int[] countZerosRight = new int[n + 1];
        int[] countOnesRight = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            countZerosRight[i] = countZerosRight[i + 1];
            countOnesRight[i] = countOnesRight[i + 1];
            if (s[i] == '0') {
                countZerosRight[i]++;
            } else if (s[i] == '1') {
                countOnesRight[i]++;
            }
        }

        long totalErrors = 0;

        // First, compute errors contributed by fixed '0's and '1's
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                totalErrors = (totalErrors + ((long) y * countOnesLeft[i])) % MOD;
            } else if (s[i] == '1') {
                totalErrors = (totalErrors + ((long) x * countZerosLeft[i])) % MOD;
            }
        }

        // Compute minimal errors by deciding each '!' character
        for (int i = 0; i < n; i++) {
            if (s[i] == '!') {
                long costZero = ((long) y * countOnesLeft[i]) + ((long) x * countOnesRight[i + 1]);
                long costOne = ((long) x * countZerosLeft[i]) + ((long) y * countZerosRight[i + 1]);
                if (costZero <= costOne) {
                    // Choose '0' at this position
                    totalErrors = (totalErrors + costZero) % MOD;
                    countZerosLeft[i + 1]++; // Update counts for positions ahead
                } else {
                    // Choose '1' at this position
                    totalErrors = (totalErrors + costOne) % MOD;
                    countOnesLeft[i + 1]++; // Update counts for positions ahead
                }
                // Update countsRight arrays are not needed as we are not changing counts to the right
            }
            // Propagate counts for next positions
            if (i + 1 < n) {
                countZerosLeft[i + 1] = countZerosLeft[i + 1];
                countOnesLeft[i + 1] = countOnesLeft[i + 1];
            }
        }

        return (int) (totalErrors % MOD);
    }

    // Main method for testing
    public static void main(String[] args) {
        testGetMinErrors();
    }

    // Testing method
    public static void testGetMinErrors() {
        String[] errorStrings = {
                "101!1",
                "!!",
                "0!1!",
                "!0!1!0!1!",
                // Edge cases
                "!!!!!",
                "0101010101",
                "0000000",
                "1111111",
                "0!1!0!1!0!1!0!1!0!1!0!1!0!1!0!1!",
        };
        int[] xValues = {
                2,
                1,
                3,
                2,
                1000000000,
                5,
                7,
                7,
                1,
        };
        int[] yValues = {
                3,
                2,
                4,
                3,
                1000000000,
                5,
                7,
                7,
                1,
        };
        int[] expectedResults = {
                9,
                0,
                7,
                18,
                0,
                25,
                0,
                0,
                56,
        };

        boolean allPassed = true;
        for (int i = 0; i < errorStrings.length; i++) {
            int result = getMinErrors(errorStrings[i], xValues[i], yValues[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " PASSED");
            } else {
                System.out.println("Test case " + (i + 1) + " FAILED");
                System.out.println("Input: errorString = " + errorStrings[i] + ", x = " + xValues[i] + ", y = " + yValues[i]);
                System.out.println("Expected: " + expectedResults[i]);
                System.out.println("Got: " + result);
                allPassed = false;
            }
        }
        if (allPassed) {
            System.out.println("All test cases PASSED");
        } else {
            System.out.println("Some test cases FAILED");
        }
    }
}

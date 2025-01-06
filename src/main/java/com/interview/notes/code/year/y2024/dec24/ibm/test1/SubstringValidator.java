package com.interview.notes.code.year.y2024.dec24.ibm.test1;

public class SubstringValidator {
    public static long countValidSubstrings(String s, int minLength, int maxLength) {
        if (s == null || s.isEmpty() || minLength > maxLength) {
            return 0;
        }

        long count = 0;
        int n = s.length();

        // Iterate through all possible substring lengths
        for (int len = minLength; len <= Math.min(maxLength, n); len++) {
            // Use sliding window to check all substrings of current length
            for (int i = 0; i <= n - len; i++) {
                boolean isValid = true;
                // Check adjacent characters
                for (int j = i; j < i + len - 1; j++) {
                    if (s.charAt(j) == s.charAt(j + 1)) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Test cases
        TestCase[] testCases = {
                new TestCase("110", 1, 3, 4),
                new TestCase("1010", 2, 2, 3),
                new TestCase("101010", 2, 4, 7),
                new TestCase("1", 1, 1, 1),
                new TestCase("11", 1, 2, 2),
                new TestCase("", 1, 1, 0),
                new TestCase("101", 3, 2, 0),  // Invalid input case
                // Large input test case
                new TestCase("10".repeat(50000), 2, 4, -1)  // -1 means we don't check exact value
        };

        int totalTests = 0;
        int passedTests = 0;

        for (TestCase test : testCases) {
            totalTests++;
            try {
                long startTime = System.currentTimeMillis();
                long result = countValidSubstrings(test.input, test.minLength, test.maxLength);
                long endTime = System.currentTimeMillis();

                boolean passed = test.expectedOutput == -1 || result == test.expectedOutput;
                if (passed) {
                    passedTests++;
                }

                System.out.printf("Test Case %d: %s%n", totalTests, passed ? "PASS" : "FAIL");
                System.out.printf("Input: %s (length=%d), minLength=%d, maxLength=%d%n",
                        test.input.length() > 50 ? test.input.substring(0, 47) + "..." : test.input,
                        test.input.length(), test.minLength, test.maxLength);
                System.out.printf("Expected: %d, Got: %d%n", test.expectedOutput, result);
                System.out.printf("Time taken: %d ms%n%n", endTime - startTime);

            } catch (Exception e) {
                System.out.printf("Test Case %d: FAIL (Exception: %s)%n%n", totalTests, e.getMessage());
            }
        }

        System.out.printf("Summary: %d/%d tests passed%n", passedTests, totalTests);
    }

    static class TestCase {
        String input;
        int minLength;
        int maxLength;
        long expectedOutput;

        TestCase(String input, int minLength, int maxLength, long expectedOutput) {
            this.input = input;
            this.minLength = minLength;
            this.maxLength = maxLength;
            this.expectedOutput = expectedOutput;
        }
    }
}

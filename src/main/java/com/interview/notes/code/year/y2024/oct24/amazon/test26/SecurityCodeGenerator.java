package com.interview.notes.code.year.y2024.oct24.amazon.test26;

public class SecurityCodeGenerator {

    public static String findSecurityCode(String code, long k) {
        if (code.length() <= 1 || k == 0) return code;

        char[] arr = code.toCharArray();
        int n = arr.length;

        // Find the first 1 from left
        int firstOnePos = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '1') {
                firstOnePos = i;
                break;
            }
        }

        // If no 1's found or already at start, no changes needed
        if (firstOnePos <= 0) return code;

        // We only need one operation to move the first '1' to the leftmost position
        if (k >= 1) {
            // Move the first '1' to the start
            char temp = arr[firstOnePos];
            System.arraycopy(arr, 0, arr, 1, firstOnePos);
            arr[0] = temp;

            // If we have more operations, sort the rest optimally
            if (k >= 2) {
                // Sort the remaining digits (after the first '1') in descending order
                int ones = 0;
                for (int i = 1; i < n; i++) {
                    if (arr[i] == '1') ones++;
                }

                // Fill with 1's followed by 0's
                for (int i = 1; i < n; i++) {
                    arr[i] = i <= ones ? '1' : '0';
                }
            }
        }

        return new String(arr);
    }

    private static void testCase(String code, long k, String expected, int testNumber) {
        long startTime = System.nanoTime();
        String result = findSecurityCode(code, k);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        boolean passed = result.equals(expected);
        System.out.printf("Test Case %d: %s (%.3f ms)%n",
                testNumber,
                passed ? "PASS" : "FAIL",
                executionTime);

        if (!passed) {
            System.out.println("  Input: code=" + code + ", k=" + k);
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Basic test cases
        testCase("0010", 5, "1000", 1);
        testCase("111", 2, "111", 2);
        testCase("00100101", 4, "10010001", 3);
        testCase("1", 1, "1", 4);
        testCase("0000", 3, "0000", 5);

        // Large test cases
        System.out.println("\nTesting large inputs...");

        // Test 1: Large string (1M chars)
        StringBuilder largeCode1 = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            largeCode1.append(i % 2);
        }
        long startTime = System.currentTimeMillis();
        String result1 = findSecurityCode(largeCode1.toString(), 1_000_000);
        long endTime = System.currentTimeMillis();
        System.out.printf("Large Test 1 (1M chars): %.3f seconds%n",
                (endTime - startTime) / 1000.0);

        // Test 2: Maximum k value
        testCase("010", 1_000_000_000_000L, "100", 6);

        // Test 3: Very large k with medium string
        StringBuilder mediumCode = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            mediumCode.append(i % 2);
        }
        startTime = System.currentTimeMillis();
        String result2 = findSecurityCode(mediumCode.toString(), Long.MAX_VALUE);
        endTime = System.currentTimeMillis();
        System.out.printf("Large Test 3 (10K chars, max k): %.3f seconds%n",
                (endTime - startTime) / 1000.0);

        // Test 4: Maximum allowed length
        StringBuilder maxCode = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            maxCode.append((int) (Math.random() * 2));
        }
        startTime = System.currentTimeMillis();
        String result3 = findSecurityCode(maxCode.toString(), 1_000_000_000_000L);
        endTime = System.currentTimeMillis();
        System.out.printf("Large Test 4 (1M random chars, large k): %.3f seconds%n",
                (endTime - startTime) / 1000.0);
    }
}
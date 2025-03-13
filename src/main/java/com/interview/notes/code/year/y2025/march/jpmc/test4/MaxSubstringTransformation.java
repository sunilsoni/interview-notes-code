package com.interview.notes.code.year.y2025.march.jpmc.test4;

public class MaxSubstringTransformation {

    /**
     * Finds the maximum length of a substring of s that can be changed to
     * the corresponding substring of t with a total cost ≤ K.
     *
     * @param s The source string
     * @param t The target string
     * @param K The maximum allowed transformation cost
     * @return The maximum length of a valid substring
     */
    public static int sameSubstring(String s, String t, int K) {
        if (s == null || t == null || s.length() != t.length() || K < 0) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;
        int cost = 0;
        int left = 0;

        // Sliding window approach
        for (int right = 0; right < n; right++) {
            // Add cost of current character transformation
            cost += Math.abs(s.charAt(right) - t.charAt(right));

            // Shrink window from left while cost exceeds K
            while (cost > K) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Tests the sameSubstring method with the given inputs and expected output.
     *
     * @param s        The source string
     * @param t        The target string
     * @param K        The maximum allowed transformation cost
     * @param expected The expected result
     * @return true if the test passes, false otherwise
     */
    private static boolean testCase(String s, String t, int K, int expected) {
        int result = sameSubstring(s, t, K);
        boolean pass = result == expected;
        System.out.println("s = \"" + s + "\", t = \"" + t + "\", K = " + K);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(pass ? "PASS" : "FAIL");
        System.out.println("-------------------");
        return pass;
    }

    public static void main(String[] args) {
        int passCount = 0;
        int totalTests = 0;

        // Test Case 1: Example from problem statement
        if (testCase("adpgki", "cdmxki", 6, 3)) passCount++;
        totalTests++;

        // Test Case 2: Sample Case 0
        if (testCase("uaccd", "gbbeg", 4, 3)) passCount++;
        totalTests++;

        // Test Case 3: Sample Case 1
        if (testCase("hffk", "larb", 3, 0)) passCount++;
        totalTests++;

        // Additional test cases

        // Test Case 4: Empty strings
        if (testCase("", "", 10, 0)) passCount++;
        totalTests++;

        // Test Case 5: Single character, insufficient K
        if (testCase("a", "z", 10, 1)) passCount++;
        totalTests++;

        // Test Case 6: Single character, insufficient K
        if (testCase("a", "z", 20, 1)) passCount++;
        totalTests++;

        // Test Case 7: Identical strings
        if (testCase("abc", "abc", 0, 3)) passCount++;
        totalTests++;

        // Test Case 8: Large K allows entire string
        if (testCase("abcdefg", "zyxwvut", 1000, 7)) passCount++;
        totalTests++;

        // Test Case 9: Edge case - K = 0 but strings have matching parts
        if (testCase("abcdef", "abcxyz", 0, 3)) passCount++;
        totalTests++;

        // Test Case 10: Large strings with limited K
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb1.append('a');
            sb2.append('b');
        }
        if (testCase(sb1.toString(), sb2.toString(), 100, 100)) passCount++;
        totalTests++;

        // Test Case 11: Large strings with characters far apart
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb3.append('a');
            sb4.append('z');
        }
        if (testCase(sb3.toString(), sb4.toString(), 50, 2)) passCount++;
        totalTests++;

        // Test Case 12: More complex pattern
        if (testCase("abcdefghi", "abcxyzghi", 6, 9)) passCount++;
        totalTests++;

        System.out.println("=================");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Tests passed: " + passCount);
        System.out.println("Tests failed: " + (totalTests - passCount));
        System.out.println(passCount == totalTests ? "ALL TESTS PASSED!" : "SOME TESTS FAILED!");
    }
}

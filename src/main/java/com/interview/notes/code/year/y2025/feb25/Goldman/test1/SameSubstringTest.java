package com.interview.notes.code.year.y2025.feb25.Goldman.test1;

public class SameSubstringTest {
    public static int sameSubstring(String s, String t, int K) {
        if (s == null || t == null || s.length() != t.length()) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;

        // Try all possible substring lengths
        for (int start = 0; start < n; start++) {
            int remainingCost = K;
            int currentLength = 0;

            // Try extending current substring
            for (int i = start; i < n; i++) {
                int cost = Math.abs(s.charAt(i) - t.charAt(i));

                if (cost <= remainingCost) {
                    remainingCost -= cost;
                    currentLength++;
                    maxLength = Math.max(maxLength, currentLength);
                } else {
                    break;
                }
            }
        }

        return maxLength;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1
        test("uaccd", "gbbeg", 4, 3, "Test Case 1");

        // Test Case 2
        test("hffk", "larb", 3, 0, "Test Case 2");

        // Edge Cases
        test("", "", 5, 0, "Empty strings");
        test("abc", "abc", 0, 3, "No transformation needed");
        test("zzz", "aaa", 2, 0, "Insufficient K");

        // Large Input Test
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb1.append('a');
            sb2.append('b');
        }
        test(sb1.toString(), sb2.toString(), 100000, 100000, "Large Input Test");
    }

    private static void test(String s, String t, int K, int expected, String testName) {
        int result = sameSubstring(s, t, K);
        System.out.println(testName + ": " +
                (result == expected ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}

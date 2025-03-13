package com.interview.notes.code.year.y2025.march.jpmc.test1;

public class MaxSubstringTransformation {

    /**
     * Finds the maximum length of a substring in s that can be transformed into
     * the corresponding substring in t with a total cost not exceeding K.
     *
     * @param s The source string to be transformed
     * @param t The target string
     * @param K The maximum allowed cost for transformation
     * @return The maximum length of a valid substring
     */
    public static int sameSubstring(String s, String t, int K) {
        int n = s.length();
        int maxLength = 0;
        int currentCost = 0;
        int left = 0;

        // Sliding window approach
        for (int right = 0; right < n; right++) {
            // Calculate the cost to transform current character
            int charCost = Math.abs(s.charAt(right) - t.charAt(right));
            currentCost += charCost;

            // Shrink window from left while cost exceeds K
            while (currentCost > K && left <= right) {
                currentCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Test cases
        testSolution("adpgki", "cdmxki", 6, 3);
        testSolution("uaccd", "gbbeg", 4, 3);
        testSolution("hffk", "larb", 3, 0);

        // Edge cases
        testSolution("a", "a", 0, 1); // No transformation needed
        testSolution("a", "z", 0, 0); // Can't transform with K=0
        testSolution("a", "z", 25, 1); // Exact cost to transform

        // Large input test
        testLargeInput();
    }

    /**
     * Helper method to test the solution with given inputs and expected output
     */
    private static void testSolution(String s, String t, int K, int expected) {
        int result = sameSubstring(s, t, K);
        System.out.println("Input: s = \"" + s + "\", t = \"" + t + "\", K = " + K);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println("----------------------------");
    }

    /**
     * Tests the solution with a large input to verify performance
     */
    private static void testLargeInput() {
        // Generate large strings (length 100,000)
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < 100000; i++) {
            // Create strings with small differences
            char c1 = (char) ('a' + (i % 26));
            char c2 = (char) ('a' + ((i + 1) % 26));
            sb1.append(c1);
            sb2.append(c2);
        }

        String largeS = sb1.toString();
        String largeT = sb2.toString();

        // Test with various K values
        System.out.println("Testing large input (length 100,000)...");
        long startTime = System.currentTimeMillis();
        int result = sameSubstring(largeS, largeT, 1000);
        long endTime = System.currentTimeMillis();

        System.out.println("Result: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("----------------------------");
    }
}
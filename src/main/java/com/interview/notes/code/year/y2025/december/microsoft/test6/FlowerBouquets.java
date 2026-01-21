package com.interview.notes.code.year.y2025.december.microsoft.test6;

public class FlowerBouquets {

    // Main function to calculate max revenue from flower bouquets
    public static int flowerBouquets(int p, int q, String s) {
        // Get string length for iteration bounds
        int n = s.length();
        // DP array: dp[i] stores max revenue using flowers 0 to i-1
        int[] dp = new int[n + 1];

        // Iterate through each position in the garden
        for (int i = 0; i < n; i++) {
            // Option 1: Skip current flower, carry forward previous max
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // Option 2: Try making Type 1 bouquet (3 consecutive roses "000")
            if (i + 2 < n && s.charAt(i) == '0' && s.charAt(i + 1) == '0' && s.charAt(i + 2) == '0') {
                // Update dp[i+3] if this gives better revenue
                dp[i + 3] = Math.max(dp[i + 3], dp[i] + p);
            }

            // Option 3: Try making Type 2 bouquet (one cosmos + one rose)
            if (i + 1 < n) {
                // Get current and next flower types
                char c1 = s.charAt(i), c2 = s.charAt(i + 1);
                // Check if it's "01" or "10" pattern (rose-cosmos pair)
                if ((c1 == '0' && c2 == '1') || (c1 == '1' && c2 == '0')) {
                    // Update dp[i+2] if this gives better revenue
                    dp[i + 2] = Math.max(dp[i + 2], dp[i] + q);
                }
            }
        }
        // Return max revenue using all flowers
        return dp[n];
    }

    // Test runner method to check pass/fail for each case
    static void test(int testNum, int p, int q, String s, int expected) {
        // Call the solution method
        int result = flowerBouquets(p, q, s);
        // Compare result with expected and print status
        String status = (result == expected) ? "PASS" : "FAIL";
        // Print detailed test result
        System.out.println("Test " + testNum + ": " + status + " | Expected: " + expected + " | Got: " + result);
    }

    public static void main(String[] args) {
        // Sample Case 0: "0001000" - can make 1 type1 + 1 type2 = 2+3 = 5
        test(1, 2, 3, "0001000", 5);

        // Sample Case 1: Single rose - no bouquet possible
        test(2, 10, 7, "0", 0);

        // Edge Case: Empty consideration - only cosmos, no bouquet
        test(3, 5, 3, "111", 0);

        // Edge Case: 3 roses exactly - one type1 bouquet
        test(4, 10, 5, "000", 10);

        // Edge Case: 2 roses - no bouquet possible
        test(5, 10, 5, "00", 0);

        // Mixed: "01" pattern - one type2 bouquet
        test(6, 10, 5, "01", 5);

        // Mixed: "10" pattern - one type2 bouquet
        test(7, 10, 5, "10", 5);

        // Complex: 6 roses - two type1 bouquets
        test(8, 10, 5, "000000", 20);

        // Complex: Compare type1 vs type2 - choose better option
        test(9, 5, 10, "0010", 10);

        // Complex: Multiple segments
        test(10, 3, 2, "00010001000", 9);

        // Large Data Test: Generate large string of roses
        StringBuilder sb = new StringBuilder();
        // Build string of 100000 roses
        for (int i = 0; i < 100000; i++) sb.append('0');
        // Expected: 100000/3 = 33333 type1 bouquets
        test(11, 1000, 500, sb.toString(), 33333 * 1000);

        // Large Data Test: Alternating pattern
        StringBuilder sb2 = new StringBuilder();
        // Build alternating 01 pattern
        for (int i = 0; i < 50000; i++) sb2.append("01");
        // Expected: 50000 type2 bouquets
        test(12, 100, 200, sb2.toString(), 50000 * 200);

        // Edge: When type2 is more profitable than type1
        test(13, 2, 5, "000100", 10);

        // Edge: Single cosmos
        test(14, 10, 5, "1", 0);

        // Complex mix: Multiple options
        test(15, 4, 3, "0001001000", 10);
    }
}
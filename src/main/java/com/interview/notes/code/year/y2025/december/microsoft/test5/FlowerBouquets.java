package com.interview.notes.code.year.y2025.december.microsoft.test5;

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
        
        // Edge Case: Only cosmos - no bouquet possible
        test(3, 5, 3, "111", 0);
        
        // Edge Case: Exactly 3 roses - one type1 bouquet
        test(4, 10, 5, "000", 10);
        
        // Edge Case: 2 roses - no bouquet possible
        test(5, 10, 5, "00", 0);
        
        // Mixed: "01" pattern - one type2 bouquet
        test(6, 10, 5, "01", 5);
        
        // Mixed: "10" pattern - one type2 bouquet
        test(7, 10, 5, "10", 5);
        
        // Complex: 6 roses - two type1 bouquets
        test(8, 10, 5, "000000", 20);
        
        // Complex: Choose type2 over partial type1
        test(9, 5, 10, "0010", 10);
        
        // FIXED: Type1(0,1,2) + Type2(3,4) + Type2(6,7) + Type1(8,9,10) = 3+2+2+3 = 10
        test(10, 3, 2, "00010001000", 10);
        
        // Large Data Test: 100000 roses
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append('0');
        // 100000/3 = 33333 type1 bouquets
        test(11, 1000, 500, sb.toString(), 33333 * 1000);
        
        // Large Data Test: Alternating pattern
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb2.append("01");
        // 50000 type2 bouquets
        test(12, 100, 200, sb2.toString(), 50000 * 200);
        
        // FIXED: Type1(0,1,2) + Type2(3,4) = 2+5 = 7
        test(13, 2, 5, "000100", 7);
        
        // Edge: Single cosmos - no bouquet
        test(14, 10, 5, "1", 0);
        
        // FIXED: Type1(0,1,2) + Type2(3,4) + Type2(5,6) + Type1(7,8,9) = 4+3+3+4 = 14
        test(15, 4, 3, "0001001000", 14);
        
        // Additional: When skipping is better
        test(16, 10, 1, "00010", 10);
        
        // Additional: Multiple type2 better than type1
        test(17, 5, 10, "010101", 30);
        
        // Additional: Mix where type1 wins
        test(18, 100, 1, "000", 100);
    }
}
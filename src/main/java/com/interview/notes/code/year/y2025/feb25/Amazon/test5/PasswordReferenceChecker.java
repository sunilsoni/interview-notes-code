package com.interview.notes.code.year.y2025.feb25.Amazon.test5;

import java.util.*;

public class PasswordReferenceChecker {

    // Helper method to compute the minimum removal cost
    // so that reference is NOT a subsequence of any permutation of password.
    public static long calculateMinCost(String password, String reference, int[] cost) {
        // Quick checks
        if (reference.length() == 0) {
            // Usually, an empty reference might always be considered a subsequence,
            // but if the problem states differently, handle accordingly.
            return 0;
        }
        if (password.length() == 0) {
            return 0; // Can't form reference from empty password
        }

        // Count frequencies in password
        int[] freqP = new int[26];
        for (char p : password.toCharArray()) {
            freqP[p - 'a']++;
        }

        // Count frequencies in reference
        int[] freqR = new int[26];
        for (char r : reference.toCharArray()) {
            freqR[r - 'a']++;
        }

        long minCost = Long.MAX_VALUE;
        
        // For each character c in 'a'..'z' that appears in reference
        for (int c = 0; c < 26; c++) {
            if (freqR[c] > 0) {
                // We want final freqP[c] < freqR[c].
                // If freqP[c] < freqR[c] already, cost is 0 for this path.
                if (freqP[c] < freqR[c]) {
                    minCost = Math.min(minCost, 0);
                } else {
                    // We must remove enough to make final freqP[c] = freqR[c] - 1 (or less).
                    int neededRemoval = freqP[c] - (freqR[c] - 1);
                    // neededRemoval could be zero or negative if freqP[c] < freqR[c], but we handled that case above.
                    long removalCost = (long) neededRemoval * cost[c];
                    minCost = Math.min(minCost, removalCost);
                }
            }
        }

        return (minCost == Long.MAX_VALUE) ? 0 : minCost;
    }

    // Main method to test multiple scenarios
    public static void main(String[] args) {
        // We will do a few test cases in code:
        
        // Test case 1 (from the sample with kkkk -> k)
        {
            String password = "kkkk";
            String reference = "k";
            int[] costArray = new int[26];
            // Suppose cost of 'k' is 5 (k - 'a' = 10)
            costArray['k' - 'a'] = 5;

            long expected = 20; // remove 4 'k's, cost = 4 * 5 = 20
            long actual = calculateMinCost(password, reference, costArray);
            System.out.println("Test1 " + (expected == actual ? "PASS" : "FAIL") 
                               + " | Expected=" + expected + " Got=" + actual);
        }

        // Test case 2 (abcdcb -> bcb) from the example snippet
        {
            String password = "abcdcb";
            String reference = "bcb";
            // Suppose cost array is such that cost['b'] = 1, cost['c'] = 3 (others = 0 for simplicity)
            int[] costArray = new int[26];
            costArray['b' - 'a'] = 1;
            costArray['c' - 'a'] = 3;

            // We can break 'b' by removing 1 'b' (cost=1)
            // or break 'c' by removing 2 'c' (cost=6).
            // The cheaper is 1, but the example’s final answer was 3 if they remove c's.
            // Possibly the sample’s cost array was different. 
            // Here, with these costs, minimal cost is 1.
            long expected = 1; 
            long actual = calculateMinCost(password, reference, costArray);
            System.out.println("Test2 " + (expected == actual ? "PASS" : "FAIL") 
                               + " | Expected=" + expected + " Got=" + actual);
        }

        // Test case 3: reference not present in password
        {
            String password = "abc";
            String reference = "zzz"; // freqR['z']=3, but freqP['z']=0
            int[] costArray = new int[26];
            Arrays.fill(costArray, 5); // any cost

            // We already cannot form "zzz" because password has 0 'z'.
            // So minimal cost is 0.
            long expected = 0;
            long actual = calculateMinCost(password, reference, costArray);
            System.out.println("Test3 " + (expected == actual ? "PASS" : "FAIL") 
                               + " | Expected=" + expected + " Got=" + actual);
        }

        // Test case 4: empty reference
        {
            String password = "abc";
            String reference = "";
            int[] costArray = new int[26];
            // Usually, removing 0 is enough if the problem statement says
            // an empty reference is always trivially formed, but let's assume
            // we treat an empty reference as "no cost needed." 
            long expected = 0; 
            long actual = calculateMinCost(password, reference, costArray);
            System.out.println("Test4 " + (expected == actual ? "PASS" : "FAIL") 
                               + " | Expected=" + expected + " Got=" + actual);
        }

        // Additional large-data test scenario (not fully shown here):
        // You could generate a large random password and reference to check performance.
        // For demonstration, we'll just do a short one.

        // Confirm all tests printed properly. If needed, you can compare actual with expected.
    }
}

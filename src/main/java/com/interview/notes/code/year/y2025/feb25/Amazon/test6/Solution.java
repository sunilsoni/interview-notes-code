package com.interview.notes.code.year.y2025.feb25.Amazon.test6;

import java.util.*;

public class Solution {

    public static long calculateMinCost(String password, String reference, List<Integer> cost) {
        // Calculate frequency of each character in the reference
        int[] refFreq = new int[26];
        for (char c : reference.toCharArray()) {
            refFreq[c - 'a']++;
        }

        // Calculate frequency of each character in the password and collect their costs
        int[] passFreq = new int[26];
        List<List<Integer>> charCosts = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            charCosts.add(new ArrayList<>());
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int index = c - 'a';
            passFreq[index]++;
            charCosts.get(index).add(cost.get(index));
        }

        // Check if any character in the reference has insufficient count in the password
        for (int i = 0; i < 26; i++) {
            if (refFreq[i] > 0 && passFreq[i] < refFreq[i]) {
                return 0;
            }
        }

        long minCost = Long.MAX_VALUE;

        // Calculate the minimal cost for each character in the reference
        for (int i = 0; i < 26; i++) {
            if (refFreq[i] == 0) {
                continue;
            }

            int needed = passFreq[i] - (refFreq[i] - 1);
            List<Integer> costs = charCosts.get(i);

            if (costs.size() < needed) {
                // This should not happen as passFreq[i] >= refFreq[i]
                continue;
            }

            Collections.sort(costs);
            long sum = 0;
            for (int j = 0; j < needed; j++) {
                sum += costs.get(j);
            }

            if (sum < minCost) {
                minCost = sum;
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        // Test Case 0
        String password0 = "abcdcbcb";
        String reference0 = "bcb";
        List<Integer> cost0 = new ArrayList<>();
        int[] costArray0 = {2, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int c : costArray0) {
            cost0.add(c);
        }
        long result0 = calculateMinCost(password0, reference0, cost0);
        System.out.println("Test Case 0: " + (result0 == 3 ? "Pass" : "Fail") + " (Expected 3, Got " + result0 + ")");

        // Test Case 1
        String password1 = "kkkk";
        String reference1 = "k";
        List<Integer> cost1 = new ArrayList<>();
        int[] costArray1 = {5, 1, 1, 2, 4, 7, 3, 4, 5, 7, 5, 6, 2, 1, 5, 12, 5, 1, 5, 0, 5, 6, 4, 7, 8, 50};
        for (int c : costArray1) {
            cost1.add(c);
        }
        long result1 = calculateMinCost(password1, reference1, cost1);
        System.out.println("Test Case 1: " + (result1 == 20 ? "Pass" : "Fail") + " (Expected 20, Got " + result1 + ")");

        // Edge Case: Reference has a character not present in password
        String password2 = "abc";
        String reference2 = "abcd";
        List<Integer> cost2 = new ArrayList<>(Collections.nCopies(26, 1));
        long result2 = calculateMinCost(password2, reference2, cost2);
        System.out.println("Edge Case 1: " + (result2 == 0 ? "Pass" : "Fail") + " (Expected 0, Got " + result2 + ")");

        // Edge Case: Exact frequency match
        String password3 = "aaa";
        String reference3 = "aaa";
        List<Integer> cost3 = new ArrayList<>(Collections.nCopies(26, 0));
        cost3.set('a' - 'a', 5); // All 'a's have cost 5
        long result3 = calculateMinCost(password3, reference3, cost3);
        System.out.println("Edge Case 2: " + (result3 == 5 ? "Pass" : "Fail") + " (Expected 5, Got " + result3 + ")");
    }
}
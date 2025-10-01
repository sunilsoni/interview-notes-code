package com.interview.notes.code.year.y2025.september.amazon.test1;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int minOperationsToUnbias(int n, String data1, String data2) {
        int len1 = data1.length(), len2 = data2.length();
        int[] prefixOnes1 = new int[len1 + 1];
        int[] prefixZeros1 = new int[len1 + 1];
        int[] prefixOnes2 = new int[len2 + 1];
        int[] prefixZeros2 = new int[len2 + 1];

        // prefix sums from left for data2
        for (int i = 0; i < len2; i++) {
            prefixOnes2[i + 1] = prefixOnes2[i] + (data2.charAt(i) == '1' ? 1 : 0);
            prefixZeros2[i + 1] = prefixZeros2[i] + (data2.charAt(i) == '0' ? 1 : 0);
        }

        // suffix sums for data1 (but store as prefix arrays reversed)
        for (int i = len1 - 1; i >= 0; i--) {
            prefixOnes1[len1 - i] = prefixOnes1[len1 - i - 1] + (data1.charAt(i) == '1' ? 1 : 0);
            prefixZeros1[len1 - i] = prefixZeros1[len1 - i - 1] + (data1.charAt(i) == '0' ? 1 : 0);
        }

        int totalOnes = 0, totalZeros = 0;
        for (char c : data1.toCharArray())
            if (c == '1') totalOnes++;
            else totalZeros++;
        for (char c : data2.toCharArray())
            if (c == '1') totalOnes++;
            else totalZeros++;

        if (totalOnes == totalZeros) return 0;

        int ans = Integer.MAX_VALUE;

        // try removing i suffix from data1, j prefix from data2
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int ones = totalOnes - prefixOnes1[i] - prefixOnes2[j];
                int zeros = totalZeros - prefixZeros1[i] - prefixZeros2[j];
                if (ones == zeros) {
                    ans = Math.min(ans, i + j);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[]{"2", "01", "11", "2"},
                new String[]{"3", "011", "110", "2"},
                new String[]{"6", "111001", "010110", "6"}
        );

        for (int i = 0; i < tests.size(); i++) {
            int n = Integer.parseInt(tests.get(i)[0]);
            String d1 = tests.get(i)[1];
            String d2 = tests.get(i)[2];
            int expected = Integer.parseInt(tests.get(i)[3]);
            int result = minOperationsToUnbias(n, d1, d2);
            System.out.println("Test " + (i + 1) + ": " + (result == expected ? "PASS" : "FAIL")
                    + " -> Expected: " + expected + ", Got: " + result);
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb1.append(i % 2 == 0 ? '1' : '0');
            sb2.append(i % 2 == 0 ? '0' : '1');
        }
        long start = System.currentTimeMillis();
        int result = minOperationsToUnbias(100000, sb1.toString(), sb2.toString());
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test: Completed in " + (end - start) + "ms, Result = " + result);
    }
}
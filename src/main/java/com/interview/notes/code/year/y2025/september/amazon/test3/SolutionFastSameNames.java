package com.interview.notes.code.year.y2025.september.amazon.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionFastSameNames {

    public static int minOperationsToUnbias(int n, String data1, String data2) {
        int len1 = data1.length(), len2 = data2.length();

        int totalOnes = 0, totalZeros = 0;
        for (char c : data1.toCharArray()) {
            if (c == '1') totalOnes++;
            else totalZeros++;
        }
        for (char c : data2.toCharArray()) {
            if (c == '1') totalOnes++;
            else totalZeros++;
        }
        if (totalOnes == totalZeros) return 0;

        int diff = totalOnes - totalZeros;

        // suffix balance for data1 (reuse preOne1/preZero1 naming idea)
        int[] preOne1 = new int[len1 + 1];   // stores suffix "balance"
        for (int i = 1; i <= len1; i++) {
            char ch = data1.charAt(len1 - i);
            preOne1[i] = preOne1[i - 1] + (ch == '1' ? 1 : -1);
        }

        // prefix balance for data2 (reuse preOne2/preZero2 naming idea)
        Map<Integer, Integer> preOne2 = new HashMap<>();
        int bal = 0;
        preOne2.put(0, 0);
        for (int j = 1; j <= len2; j++) {
            char ch = data2.charAt(j - 1);
            bal += (ch == '1' ? 1 : -1);
            preOne2.putIfAbsent(bal, j);  // keep smallest j
        }

        int ans = Integer.MAX_VALUE;

        // only from data2
        if (preOne2.containsKey(diff)) ans = Math.min(ans, preOne2.get(diff));

        // suffix of data1 + prefix of data2
        for (int i = 1; i <= len1; i++) {
            int need = diff - preOne1[i];
            if (preOne2.containsKey(need)) {
                ans = Math.min(ans, i + preOne2.get(need));
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

        // Large data stress test
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
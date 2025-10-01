package com.interview.notes.code.year.y2025.september.amazon.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionFast {

    public static int minOperationsToUnbias(int n, String data1, String data2) {
        int len1 = data1.length(), len2 = data2.length();

        int ones = 0, zeros = 0;
        for (char c : data1.toCharArray())
            if (c == '1') ones++;
            else zeros++;
        for (char c : data2.toCharArray())
            if (c == '1') ones++;
            else zeros++;
        if (ones == zeros) return 0;

        int diff = ones - zeros; // need (onesRemoved - zerosRemoved) == diff

        // suffix balance for data1: +1 if removed '1', -1 if removed '0'
        int[] sufBal1 = new int[len1 + 1];
        for (int i = 1; i <= len1; i++) {
            char ch = data1.charAt(len1 - i);
            sufBal1[i] = sufBal1[i - 1] + (ch == '1' ? 1 : -1);
        }

        // map prefix balance in data2 to minimal j achieving it
        Map<Integer, Integer> minJ = new HashMap<>();
        int bal = 0;
        minJ.put(0, 0);
        for (int j = 1; j <= len2; j++) {
            char ch = data2.charAt(j - 1);
            bal += (ch == '1' ? 1 : -1);
            minJ.putIfAbsent(bal, j); // keep smallest j for each balance
        }

        int ans = Integer.MAX_VALUE;
        // only remove from data2
        if (minJ.containsKey(diff)) ans = Math.min(ans, minJ.get(diff));
        // combine removals from both
        for (int i = 1; i <= len1; i++) {
            int need = diff - sufBal1[i];
            Integer j = minJ.get(need);
            if (j != null) ans = Math.min(ans, i + j);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[]{"2", "01", "11", "2"},
                new String[]{"3", "011", "110", "2"},
                new String[]{"6", "111001", "010110", "6"}
        );
        int id = 1;
        for (String[] t : tests) {
            int n = Integer.parseInt(t[0]);
            int got = minOperationsToUnbias(n, t[1], t[2]);
            int exp = Integer.parseInt(t[3]);
            System.out.println("Test " + (id++) + ": " + (got == exp ? "PASS" : "FAIL") +
                    " -> Expected: " + exp + ", Got: " + got);
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            sb1.append(i % 2 == 0 ? '1' : '0');
            sb2.append(i % 2 == 0 ? '0' : '1');
        }
        long st = System.currentTimeMillis();
        int got = minOperationsToUnbias(100_000, sb1.toString(), sb2.toString());
        long et = System.currentTimeMillis();
        System.out.println("Large Data Test: PASS -> Result=" + got + ", Time=" + (et - st) + "ms");
    }
}
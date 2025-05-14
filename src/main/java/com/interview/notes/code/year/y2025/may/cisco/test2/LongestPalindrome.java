package com.interview.notes.code.year.y2025.may.cisco.test2;

import java.util.*;

public class LongestPalindrome {

    // ===== Required function =====
    public static void funcSubstring(String inputStr) {
        String res = longestPal(inputStr);
        System.out.println(res);
    }

    // ===== Manacher core =====
    private static String longestPal(String s) {
        int n = s.length();
        if (n < 2) return "None";

        // 1. Transform
        char[] t = new char[2 * n + 3];
        t[0] = '^'; t[t.length - 1] = '$';
        for (int i = 0; i < n; i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[t.length - 2] = '#';

        // 2. Radius array
        int[] p = new int[t.length];
        int center = 0, right = 0;

        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i;
            if (i < right) p[i] = Math.min(right - i, p[mirror]);

            // expand
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) p[i]++;

            // shift center
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // 3. Find max length in original string
        int bestLen = 0;
        List<String> candidates = new ArrayList<>();

        for (int i = 1; i < t.length - 1; i++) {
            int len = p[i];               // length in transformed array
            int realLen = len;            // same value in original string
            if (realLen > bestLen && realLen > 1) {
                bestLen = realLen;
                candidates.clear();
            }
            if (realLen == bestLen && realLen > 1) {
                int start = (i - len) / 2;
                candidates.add(s.substring(start, start + realLen));
            }
        }

        if (bestLen <= 1) return "None";
        return candidates.stream().min(String::compareTo).get();
    }

    // ===== Simple helper to build large input on Java 8 =====
    private static String repeat(char c, int times) {
        char[] arr = new char[times];
        Arrays.fill(arr, c);
        return new String(arr);
    }

    // ===== Main method with built-in tests =====
    public static void main(String[] args) {
        Map<String, String> tests = new LinkedHashMap<>();
        tests.put("YABCCBAZ", "ABCCBA");              // example 1
        tests.put("ABC", "None");                     // example 2
        tests.put("AAAA", "AAAA");                    // identical letters
        tests.put("ABBA", "ABBA");                    // even length
        tests.put("X", "None");                       // length < 2
        tests.put(repeat('A', 100_000), repeat('A', 100_000)); // large data

        int id = 1, pass = 0;
        for (Map.Entry<String, String> e : tests.entrySet()) {
            String out = longestPal(e.getKey());
            boolean ok = out.equals(e.getValue());
            System.out.println("Test " + id + ": " + (ok ? "PASS" : "FAIL")
                               + " | Expected: " + e.getValue().length()
                               + " | Got: " + out.length());
            if (ok) pass++;
            id++;
        }
        System.out.println(pass + " / " + tests.size() + " tests passed");
    }
}

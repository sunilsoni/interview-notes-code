package com.interview.notes.code.year.y2025.may.cisco.test2;

import java.util.*;

public class Main {
    // Returns the longest palindromic substring per spec, or "None"
    public static String longestPalSubstr(String s) {
        if (s == null || s.length() < 2) return "None";
        String best = "";
        int n = s.length();
        for (int center = 0; center < n; center++) {
            // odd length
            best = expandAndUpdate(s, center, center, best);
            // even length
            best = expandAndUpdate(s, center, center + 1, best);
        }
        return best.isEmpty() ? "None" : best;
    }

    private static String expandAndUpdate(String s, int left, int right, String best) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; right++;
        }
        // now s[left+1..right-1] is a palindrome
        int length = right - left - 1;
        if (length > best.length()) {
            best = s.substring(left + 1, right);
        } else if (length == best.length() && length > 1) {
            String candidate = s.substring(left + 1, right);
            if (candidate.compareTo(best) < 0) {
                best = candidate;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // map of test inputs to expected outputs
        Map<String, String> tests = new LinkedHashMap<>();
        tests.put("YABCCBAZ",    "ABCCBA");
        tests.put("ABC",         "None");
        tests.put("XYZZZYX",     "XYZZZYX");
        tests.put("XABCDCBAQ",   "ABCDCBA");
        tests.put("ABA12321ABA","12321");  // lex smaller between "ABA" and "12321"
        tests.put("AAAAAA",      "AAAAAA");
        tests.put("A",           "None");
        tests.put("",            "None");

        System.out.println("=== Functional Tests ===");
        for (Map.Entry<String,String> e : tests.entrySet()) {
            String input = e.getKey(), exp = e.getValue();
            String out = longestPalSubstr(input);
            String status = out.equals(exp) ? "PASS" : "FAIL";
            System.out.printf("%-12s → %-8s [got=%s, expected=%s]%n",
                              "\"" + input + "\"", status, out, exp);
        }

        // Large‐input performance sanity check
        System.out.println("\n=== Large Input Test ===");
        int N = 50_000;
        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) sb.append('A');
        sb.setCharAt(N/2, 'B');  // introduce a single-char deviation
        long start = System.currentTimeMillis();
        String res = longestPalSubstr(sb.toString());
        long time = System.currentTimeMillis() - start;
        // Expect the largest palindrome to be N/2 As on one side + 1 B + N/2 As = full length
        System.out.printf("Length=%d, Time=%dms → %s%n",
                          res.length(), time, res.length() == N ? "PASS" : "FAIL");
    }
}

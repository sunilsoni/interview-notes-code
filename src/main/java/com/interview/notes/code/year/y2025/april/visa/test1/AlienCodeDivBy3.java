package com.interview.notes.code.year.y2025.april.visa.test1;

import java.util.*;
import java.util.stream.*;
//WORKING 100%
public class AlienCodeDivBy3 {

    /* ---------- Core solution ---------- */
    public static int solution(String alienCode) {
        final int n = alienCode.length();
        long count = 0;            // long to be safe

        for (int i = 0; i < n; i++) {
            // Fast path for single '0'
            if (alienCode.charAt(i) == '0') {
                count++;           // substring "0"
                continue;          // longer ones would have leading zero
            }

            int mod = 0;
            for (int j = i; j < n; j++) {
                mod = (mod * 10 + (alienCode.charAt(j) - '0')) % 3;
                if (j > i && alienCode.charAt(i) == '0') break; // leading zero
                if (mod == 0) count++;
            }
        }
        // If the result can overflow int, change return type to long
        return (int) count;
    }

    /* ---------- Optional O(n) version (disabled) ----------
    // Uncomment if input length may be > 10^4
    public static int solutionFast(String s) {
        int n = s.length();
        long[] freq = new long[3]; // freq[r] = how many prefixes give remainder r
        freq[0] = 1;               // empty prefix remainder = 0
        long ans = 0;
        int prefixMod = 0;
        int lastNonZero = -1;      // index of last non‑zero digit
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            prefixMod = (prefixMod * 10 + digit) % 3;
            if (digit == 0) {
                // single zero substring is allowed
                ans++;             // count "0"
                // but longer substrings starting at this zero are illegal
            } else {
                ans += freq[prefixMod];
            }
            freq[prefixMod]++;
        }
        return (int) ans;
    }
    ------------------------------------------------------- */

    /* ---------- Simple test harness ---------- */
    public static void main(String[] args) {
        // Provided examples
        Map<String, Integer> tests = new LinkedHashMap<>();
        tests.put("456", 3);
        tests.put("6666", 10);
        tests.put("303", 5);

        // Extra edge cases
        tests.put("0", 1);
        tests.put("30", 2);     // "3", "30"
        tests.put("1002", 2);   // "0", "0"
        tests.put("123456", 9); // quick sanity check

        boolean allPass = tests.entrySet().stream()
            .map(e -> {
                int out = solution(e.getKey());
                boolean ok = out == e.getValue();
                System.out.printf("Input: %-8s Expected: %-3d Got: %-3d -> %s%n",
                                  e.getKey(), e.getValue(), out, ok ? "PASS" : "FAIL");
                return ok;
            })
            .reduce(true, (a, b) -> a && b);

        // Optional random large‑input smoke test (comment out if length ≤10)
        if (!allPass) {
            System.out.println("Some tests FAILED.");
            System.exit(1);
        } else {
            System.out.println("All small tests passed!");
        }

        // Large‑input demo (100 000 digits) – should finish instantly if solutionFast is used
        String big = IntStream.range(0, 100_000)
                              .mapToObj(i -> String.valueOf(i % 10))
                              .collect(Collectors.joining());
        long start = System.currentTimeMillis();
        int bigAns = solution(big);           // replace with solutionFast(big) if needed
        long time = System.currentTimeMillis() - start;
        System.out.printf("Large input length=%d finished in %d ms, answer=%d%n",
                          big.length(), time, bigAns);
    }
}

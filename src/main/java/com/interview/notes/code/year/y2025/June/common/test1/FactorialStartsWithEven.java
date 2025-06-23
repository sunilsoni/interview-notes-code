package com.interview.notes.code.year.y2025.June.common.test1;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class FactorialStartsWithEven {

    /** Required function */
    public static List<Integer> solve(int m, int n) {
        List<Integer> ans = new ArrayList<>();

        // --- pre-compute factorials up to n ---
        BigInteger[] fact = new BigInteger[n + 1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }

        // --- check leading digit for every i in [m,n] ---
        for (int i = m; i <= n; i++) {
            char lead = fact[i].toString().charAt(0);
            if (lead == '2' || lead == '4' || lead == '6' || lead == '8') {
                ans.add(i);
            }
        }
        return ans;
    }

    private static String formatOutput(List<Integer> list) {
        if (list.isEmpty()) return "0";
        return list.size() + " " +
               list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    /* ---------- Simple test-driver (no JUnit) ---------- */
    private static class TestCase {
        final int m, n;
        final String expected;

        TestCase(int m, int n, String expected) {
            this.m = m; this.n = n; this.expected = expected.trim();
        }
        boolean run() {
            String out = formatOutput(solve(m, n));
            boolean ok = out.equals(expected);
            System.out.printf("Input (%d,%d) → %s | expected %s | %s%n",
                               m, n, out, expected, ok ? "PASS" : "FAIL");
            return ok;
        }
    }

    public static void main(String[] args) {
        /* ❶ If the program is run with no command-line arguments,
              read m and n from stdin and behave like the judge solution. */
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(formatOutput(solve(m, n)));
            return;
        }

        /* ❷ Otherwise run self-tests (add more cases as needed). */
        List<TestCase> tests = Arrays.asList(
                new TestCase(1, 10, "4 2 3 4 8"),
                new TestCase(5, 7,  "0"),
                new TestCase(1, 1,  "0"),
                new TestCase(100, 100, "0"),
                // Large-range check (only verifies the code runs)
                new TestCase(1, 100, formatOutput(solve(1, 100))) // ground truth from our own solver
        );
        boolean allPass = tests.stream().allMatch(TestCase::run);
        System.out.println(allPass ? "ALL TESTS PASSED" : "SOME TESTS FAILED");
    }
}

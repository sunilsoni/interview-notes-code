package com.interview.notes.code.year.y2025.july.apple.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComputeTester {

    /**
     * @param valOne     The first operand (≥0).
     * @param valTwo     The second operand (≥0).
     * @param calcResult The sum as computed by my daughter (may be wrong).
     * @return "ok" if every digit (without carry) is correct; otherwise the index
     * (as a string) of the first wrong digit (0 = units, 1 = tens, …).
     */
    public static String compute(int valOne, int valTwo, int calcResult) {
        // figure out how many digits we need to check
        int maxVal = Math.max(Math.max(valOne, valTwo), calcResult);
        int maxDigits = 1;
        for (int t = maxVal / 10; t > 0; t /= 10) {
            maxDigits++;
        }

        int place = 1;
        for (int idx = 0; idx < maxDigits; idx++, place *= 10) {
            int d1 = (valOne / place) % 10;
            int d2 = (valTwo / place) % 10;
            int dr = (calcResult / place) % 10;
            // since there's no carry, correct digit = d1 + d2
            if (d1 + d2 != dr) {
                return String.valueOf(idx);
            }
        }

        return "ok";
    }

    public static void main(String[] args) {
        // 1) Fixed examples
        List<TestCase> fixed = Arrays.asList(
                new TestCase(123, 672, 785, "1"), // tens digit wrong
                new TestCase(0, 0, 0, "ok"),
                new TestCase(10, 20, 30, "ok"),
                new TestCase(10, 20, 31, "0"), // units digit wrong
                new TestCase(47, 3, 50, "1"), // tens      wrong
                new TestCase(99999, 0, 99999, "ok")
        );

        System.out.println("=== Fixed tests ===");
        fixed.forEach(tc -> {
            String got = compute(tc.a, tc.b, tc.c);
            String status = got.equals(tc.want) ? "PASS" : "FAIL";
            System.out.printf("%s expected=%s got=%s [%s]%n",
                    tc, tc.want, got, status);
        });

        // 2) Randomized “no-carry” stress tests
        System.out.println("\n=== Random stress tests ===");
        Random rnd = new Random();
        final int N = 10_000;
        for (int i = 0; i < N; i++) {
            // pick two 5-digit numbers that obey no‐carry per digit
            int a = rnd.nextInt(100_000);
            int b = rnd.nextInt(100_000);
            // enforce no-carry: if any digit sum ≥ 10, skip and retry
            boolean okNoCarry = true;
            for (int p = 1; p <= 100_000; p *= 10) {
                if (((a / p) % 10) + ((b / p) % 10) >= 10) {
                    okNoCarry = false;
                    break;
                }
            }
            if (!okNoCarry) {
                i--;
                continue;
            }

            int correct = a + b;
            String s = String.valueOf(correct);
            int len = s.length();
            // introduce at most one digit error
            int idx = rnd.nextInt(len);
            int place = (int) Math.pow(10, idx);
            int wrong = correct + (rnd.nextBoolean() ? place : -place);
            if (wrong < 0) wrong = correct; // just in case

            String want = (wrong == correct) ? "ok" : String.valueOf(idx);
            String got = compute(a, b, wrong);
            if (!got.equals(want)) {
                System.err.printf("Stress FAIL: %d+%d wrong=%d => want=%s got=%s%n",
                        a, b, wrong, want, got);
                return;
            }
        }
        System.out.println("All " + N + " random tests passed.");
    }

    private record TestCase(int a, int b, int c, String want) {

        @Override
            public String toString() {
                return String.format("(%d + %d → %d)", a, b, c);
            }
        }
}
package com.interview.notes.code.year.y2025.july.apple.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComputeTester {

    // Replace this with your real compute implementation (or import it).
    public static String compute(int valOne, int valTwo, int calcResult) {
        // … your code here …
        return "foo";
    }

    public static void main(String[] args) {
        // 1) A few fixed examples
        List<TestCase> fixed = Arrays.asList(
                new TestCase(123, 672, 785, "1"),    // tens digit wrong
                new TestCase(0, 0, 0, "ok"),
                new TestCase(10, 20, 30, "ok"),
                new TestCase(10, 20, 31, "0"),    // units digit wrong
                new TestCase(99999, 0, 99999, "ok"),
                new TestCase(50000, 50000, 100000, "5") // hundred-thousands digit wrong
        );

        System.out.println("=== Fixed tests ===");
        fixed.stream().forEach(tc -> {
            String out = compute(tc.a, tc.b, tc.c);
            String status = out.equals(tc.expected) ? "PASS" : "FAIL";
            System.out.printf("%s expected=%s got=%s  [%s]%n",
                    tc, tc.expected, out, status);
        });

        // 2) Randomized “large” checks (no more than one digit off per spec)
        System.out.println("\n=== Random stress tests ===");
        Random rnd = new Random();
        final int N = 10_000;
        for (int i = 0; i < N; i++) {
            int a = rnd.nextInt(100_000);
            int b = rnd.nextInt(100_000);
            int correct = a + b;
            // introduce at most one digit error
            String s = String.valueOf(correct);
            int len = s.length();
            int digitIdx = rnd.nextInt(len);
            int place = (int) Math.pow(10, digitIdx);
            // flip that digit by +1 or –1, staying ≥0
            int wrong = correct + ((rnd.nextBoolean() ? 1 : -1) * place);
            if (wrong < 0) wrong = correct; // avoid negatives

            String expect = (wrong == correct)
                    ? "ok"
                    : String.valueOf(digitIdx);

            String actual = compute(a, b, wrong);
            if (!actual.equals(expect)) {
                System.err.printf("Stress fail at a=%d b=%d wrong=%d → expected %s got %s%n",
                        a, b, wrong, expect, actual);
                return; // stop on first failure
            }
        }
        System.out.println("All random tests passed (" + N + " cases).");
    }

    private record TestCase(int a, int b, int c, String expected) {

        @Override
        public String toString() {
            return String.format("(%d + %d -> %d)", a, b, c);
        }
    }
}
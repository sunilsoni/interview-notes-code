package com.interview.notes.code.year.y2025.july.apple.test4;

public class AdditionCheck {

    // Main logic method
    public static String compute(int valOne, int valTwo, int calcResult) {
        String sum = String.valueOf(valOne + valTwo);
        String daughter = String.valueOf(calcResult);

        // Pad both to same length (from left)
        int maxLen = Math.max(sum.length(), daughter.length());
        sum = String.format("%" + maxLen + "s", sum).replace(' ', '0');
        daughter = String.format("%" + maxLen + "s", daughter).replace(' ', '0');

        // Compare digits from right (unit) to left
        for (int i = 0; i < maxLen; i++) {
            int sIndex = sum.length() - 1 - i;
            int dIndex = daughter.length() - 1 - i;
            if (sum.charAt(sIndex) != daughter.charAt(dIndex)) {
                return String.valueOf(i); // return wrong digit index as string
            }
        }
        return "ok"; // all digits are correct
    }

    // Simple test runner without JUnit, prints pass/fail
    public static void main(String[] args) {
        class TestCase {
            final int a;
            final int b;
            final int c;
            final String expected;

            TestCase(int a, int b, int c, String expected) {
                this.a = a;
                this.b = b;
                this.c = c;
                this.expected = expected;
            }
        }

        TestCase[] tests = {
                new TestCase(123, 672, 785, "1"), // Sample: wrong at tens
                new TestCase(1, 2, 3, "ok"),
                new TestCase(10000, 99999, 109999, "ok"),
                new TestCase(10, 10, 21, "0"), // wrong at unit
                new TestCase(800, 100, 900, "ok"),
                new TestCase(55555, 44444, 99999, "ok"),
                new TestCase(0, 0, 0, "ok"),
                // Large input
                new TestCase(99999, 99999, 199998, "ok"),
                new TestCase(99999, 99999, 199997, "0"), // last digit wrong
                new TestCase(99999, 99998, 199997, "1"), // second last digit wrong
        };

        java.util.stream.IntStream.range(0, tests.length).forEach(i -> {
            TestCase t = tests[i];
            String res = compute(t.a, t.b, t.c);
            if (res.equals(t.expected)) {
                System.out.println("Test " + i + ": PASS");
            } else {
                System.out.println("Test " + i + ": FAIL (got " + res + ", expected " + t.expected + ")");
            }
        });
    }
}

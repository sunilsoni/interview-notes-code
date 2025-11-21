package com.interview.notes.code.year.y2025.july.glider.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LastTwoDigits {

    /**
     * Returns the last two digits of the product of all values in ar.
     * Always prints exactly two digits, with leading zero if needed.
     */
    public static String solve(List<Integer> ar) {
        // multiply each value modulo 100 to keep only last two digits
        int lastTwo = ar.stream()
                .reduce(1, (prod, x) -> (prod * x) % 100);
        // format to two digits
        return String.format("%02d", lastTwo);
    }

    /**
     * Simple test harness: runs each case and prints PASS/FAIL
     */
    public static void main(String[] args) {
        record Test(List<Integer> input, String expected) {
        }

        List<Test> tests = Arrays.asList(
                new Test(Arrays.asList(25, 10), "50"),
                new Test(Arrays.asList(2, 4, 5), "40"),
                // large data case: 100 entries of 100 -> product ≡ 0
                new Test(Collections.nCopies(100, 100), "00")
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            String actual = solve(t.input);
            String result = actual.equals(t.expected) ? "PASS" : "FAIL";
            System.out.printf("Case %d: %s (expected=%s, actual=%s)%n",
                    i + 1, result, t.expected, actual);
        }
    }
}
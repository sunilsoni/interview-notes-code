package com.interview.notes.code.year.y2025.september.Hackerank.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static String solve(String S) {
        if (S == null || S.isEmpty()) return S;
        return Character.toUpperCase(S.charAt(0)) + S.substring(1);
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
                "samsung",
                "zEaL0T",
                "Samsung",
                "apple",
                "JAVA",
                "python",
                "c",
                "X"
        );

        List<String> expected = Arrays.asList(
                "Samsung",
                "ZEaL0T",
                "Samsung",
                "Apple",
                "JAVA",
                "Python",
                "C",
                "X"
        );

        IntStream.range(0, inputs.size()).forEach(i -> {
            String result = solve(inputs.get(i));
            if (result.equals(expected.get(i))) {
                System.out.println("Test " + (i + 1) + ": PASS -> " + result);
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL -> got " + result + " expected " + expected.get(i));
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) sb.append("a");
        long start = System.currentTimeMillis();
        String largeResult = solve(sb.toString());
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test: " + (largeResult.charAt(0) == 'A' ? "PASS" : "FAIL") + " in " + (end - start) + "ms");
    }
}
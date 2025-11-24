package com.interview.notes.code.year.y2025.november.capitalOne.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VowelBoundaryReverser {

    public static void main(String[] args) {
        VowelBoundaryReverser solver = new VowelBoundaryReverser();

        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase(
                "Example1",
                new String[]{"apple", "banana", "OranGe"},
                new String[]{"alppe", "banana", "OGnare"}
        ));

        tests.add(new TestCase(
                "Example2",
                new String[]{"AE", "CodeSignal"},
                new String[]{"AE", "CodeSignal"}
        ));

        tests.add(new TestCase(
                "SingleWordVowelEnds",
                new String[]{"idea"},
                new String[]{"ieda"}
        ));

        tests.add(new TestCase(
                "SingleCharVowel",
                new String[]{"a"},
                new String[]{"a"}
        ));

        tests.add(new TestCase(
                "ConsonantEnds",
                new String[]{"test", "sky"},
                new String[]{"test", "sky"}
        ));

        tests.add(new TestCase(
                "EmptyArray",
                new String[]{},
                new String[]{}
        ));

        int bigSize = 1000;
        String[] bigInput = new String[bigSize];
        Arrays.fill(bigInput, "apple");
        String[] bigExpected = new String[bigSize];
        Arrays.fill(bigExpected, "alppe");
        tests.add(new TestCase("LargeData", bigInput, bigExpected));

        int i = 1;
        for (TestCase t : tests) {
            String[] result = solver.solution(t.input());
            boolean ok = Arrays.equals(result, t.expected());
            System.out.println("Case " + i++ + " (" + t.name() + "): " + (ok ? "PASS" : "FAIL"));
        }
    }

    public String[] solution(String[] text) {
        return Arrays.stream(text)
                .map(this::adjustWord)
                .toArray(String[]::new);
    }

    private String adjustWord(String w) {
        int n = w.length();
        char first = w.charAt(0), last = w.charAt(n - 1);
        if (!isVowel(first) || !isVowel(last) || n <= 2) {
            return w;
        }
        String mid = w.substring(1, n - 1);
        String rev = new StringBuilder(mid).reverse().toString();
        return first + rev + last;
    }

    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    private record TestCase(String name, String[] input, String[] expected) {}
}

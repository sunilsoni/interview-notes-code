package com.interview.notes.code.year.y2025.november.oci.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VowelEndingAnalyzer {

    public static int vowelEnd(int N, List<String> W) {
        String vowels = "AEIOUaeiou";
        return (int) W.stream()
                .filter(s -> !s.isEmpty() && vowels.indexOf(s.charAt(s.length() - 1)) >= 0)
                .count();
    }

    public static void main(String[] args) {
        List<TestData> testCases = Arrays.asList(
                new TestData(3, Arrays.asList("Flow", "Echo", "Cats"), 1),
                new TestData(1, List.of("Flow"), 0),
                new TestData(4, Arrays.asList("Area", "IDEA", "Fly", "floor"), 2),
                new TestData(0, new ArrayList<>(), 0),
                new TestData(5, Arrays.asList("a", "E", "xyz", "", "aeiou"), 3),
                new TestData(1000, Collections.nCopies(1000, "testA"), 1000),
                new TestData(1000, Collections.nCopies(1000, "testB"), 0),
                new TestData(3, Arrays.asList("", "", ""), 0),
                new TestData(2, Arrays.asList("I", "O"), 2)
        );

        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestData test = testCases.get(i);
            int result = vowelEnd(test.n, test.words);
            boolean pass = result == test.expected;
            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL") +
                    " (Expected: " + test.expected + ", Got: " + result + ")");
            if (pass) passed++;
        }
        System.out.println("\nTotal: " + passed + "/" + testCases.size() + " passed");
    }

    static class TestData {
        int n;
        List<String> words;
        int expected;

        TestData(int n, List<String> words, int expected) {
            this.n = n;
            this.words = words;
            this.expected = expected;
        }
    }
}
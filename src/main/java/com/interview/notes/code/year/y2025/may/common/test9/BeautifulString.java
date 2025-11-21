package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BeautifulString {

    /**
     * Returns true if each letter appears no more times than
     * its previous letter in the alphabet.
     */
    public static boolean solution(String inputString) {
        // count how many times each letter appears
        Map<Character, Long> freq = inputString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // check from 'b' to 'z' that count(letter) <= count(prev letter)
        for (char c = 'b'; c <= 'z'; c++) {
            long currentCount = freq.getOrDefault(c, 0L);
            long prevCount = freq.getOrDefault((char) (c - 1), 0L);
            if (currentCount > prevCount) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // list of fixed test cases
        List<TestCase> tests = Arrays.asList(
                new TestCase("bbbaacdafe", true),
                new TestCase("aabbb", false),
                new TestCase("bbc", false),
                new TestCase("abcabcabc", true),
                new TestCase("cba", true),
                new TestCase("abbccc", false)
        );

        System.out.println("=== Fixed Tests ===");
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            boolean result = solution(t.input);
            String status = result == t.expected ? "PASS" : "FAIL";
            System.out.printf("Test %2d: %-12s expected=%-5s got=%-5s %s%n",
                    i + 1, "\"" + t.input + "\"",
                    t.expected, result, status);
        }

        // large data test (100_000 a’s -> should be true)
        System.out.println("\n=== Large Input Test ===");
        String largeInput = IntStream.range(0, 100_000)
                .mapToObj(i -> "a")
                .collect(Collectors.joining());
        long start = System.nanoTime();
        boolean largeResult = solution(largeInput);
        long durationMs = (System.nanoTime() - start) / 1_000_000;
        String largeStatus = largeResult ? "PASS" : "FAIL";
        System.out.printf("Large input (100k chars of 'a'): result=%-5s time=%dms%n",
                largeStatus, durationMs);
    }

    // simple container for a test case
    private static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
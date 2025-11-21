package com.interview.notes.code.year.y2025.july.codility.test1;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// A tiny helper to bundle input→expected
record TestCase<I, E>(I input, E expected) {
}

public class TestRunner {

    public static void main(String[] args) {
        runStringTests();
        runWordTests();
        runKGroupTests();
    }

    private static void runStringTests() {
        List<TestCase<String, String>> tests = Arrays.asList(
                new TestCase<>("Hello", "olleH"),
                new TestCase<>("", ""),
                new TestCase<>("a", "a"),
                new TestCase<>("ab cd", "dc ba")
                // …drop in your other cases here…
        );

        System.out.println("=== reverseString() ===");
        tests.forEach(tc -> {
            String out = BasicStringManipulation.reverseString(tc.input());
            printResult("reverseString",
                    tc.input(),
                    tc.expected(),
                    out);
        });

        // Large random test
        String big = IntStream.range(0, 100_000)
                .mapToObj(i -> "x")
                .collect(Collectors.joining());
        long t0 = System.nanoTime();
        String rev = BasicStringManipulation.reverseString(big);
        long t1 = System.nanoTime();
        System.out.printf("large input of %d chars reversed in %.1f ms%n",
                big.length(), (t1 - t0) / 1e6);
    }

    private static void runWordTests() {
        List<TestCase<String, String>> tests = Arrays.asList(
                new TestCase<>("Welcome to second problem", "problem second to Welcome"),
                new TestCase<>("  multiple   spaces  ", "spaces multiple")
                // …your other cases…
        );

        System.out.println("=== reverseWordsInString() ===");
        tests.forEach(tc -> {
            String out = BasicStringManipulation.reverseWordsInString(tc.input());
            printResult("reverseWordsInString",
                    "\"" + tc.input() + "\"",
                    "\"" + tc.expected() + "\"",
                    "\"" + out + "\"");
        });
    }

    private static void runKGroupTests() {
        // Each test: input array → build list → run → toString → compare string
        List<TestCase<int[], String>> tests = Arrays.asList(
                new TestCase<>(new int[]{1, 2, 3, 4, 5}, "2->1->4->3->5"),
                new TestCase<>(new int[]{1, 2, 3}, "1->2->3") // fewer than k=2 at end
        );

        System.out.println("=== reverseKGroup(head, 2) ===");
        tests.forEach(tc -> {
            // build
            ListNode head = ReverseNodes.createLinkedList(tc.input());
            ListNode outHead = ReverseNodes.reverseKGroup(head, 2);
            String outStr = ReverseNodes.linkedListToString(outHead);
            printResult("reverseKGroup",
                    Arrays.toString(tc.input()),
                    tc.expected(),
                    outStr);
        });
    }

    private static <I, E> void printResult(String fn,
                                           I input,
                                           E expect,
                                           E actual) {
        String status = Objects.equals(expect, actual) ? "PASS" : "FAIL";
        System.out.printf("%-20s | in=%s | expected=%s | got=%s | %s%n",
                fn, input, expect, actual, status);
    }
}
package com.interview.notes.code.year.y2025.may.amazon.test5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaxCircularServers {

    public static int getMaxServers(List<Integer> powers) {
        Map<Integer, Long> freq = powers.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        int maxGroup = 0;

        for (int key : freq.keySet()) {
            long currentGroup = freq.getOrDefault(key - 1, 0L) +
                    freq.getOrDefault(key, 0L) +
                    freq.getOrDefault(key + 1, 0L);
            maxGroup = (int) Math.max(maxGroup, currentGroup);
        }

        return maxGroup;
    }

    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(3, 7, 5, 1, 5), 2),
                new TestCase(Arrays.asList(2, 2, 3, 2, 1, 2, 2), 7),
                new TestCase(Arrays.asList(4, 3, 5, 1, 2, 2, 1), 5),
                new TestCase(Arrays.asList(1, 1, 1, 1, 1), 5),
                new TestCase(generateLargeCase(200000, 3), 200000) // all 3s
        );

        for (int i = 0; i < testCases.size(); i++) {
            TestCase t = testCases.get(i);
            int result = getMaxServers(t.powers);
            String status = result == t.expected ? "PASS" : "FAIL";
            System.out.printf("Test %d: Expected = %d, Got = %d [%s]\n",
                    i + 1, t.expected, result, status);
        }
    }

    static List<Integer> generateLargeCase(int size, int value) {
        return IntStream.range(0, size).map(i -> value).boxed().collect(Collectors.toList());
    }

    static class TestCase {
        List<Integer> powers;
        int expected;

        TestCase(List<Integer> powers, int expected) {
            this.powers = powers;
            this.expected = expected;
        }
    }
}

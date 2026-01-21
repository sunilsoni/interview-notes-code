package com.interview.notes.code.year.y2025.may.amazon.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    /* ---------- core logic ---------- */
    // Returns the maximum number of servers that can be bought
    public static int getMaxServers(List<Integer> powers) {

        /* Build frequency map: value -> occurrences */
        Map<Integer, Long> freq =
                powers.stream()
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));

        int best = 0;

        /* For each distinct value, check itself and its next neighbour (+1) */
        for (Integer val : freq.keySet()) {
            long onlyThis = freq.get(val);                 // all val
            long withNext = onlyThis + freq.getOrDefault(val + 1, 0L);
            best = (int) Math.max(best, withNext);
        }
        return best;
    }

    public static void main(String[] args) {

        /* Sample tests from the statement */
        List<TestCase> tests = new ArrayList<>();

        // Sample 0
        tests.add(new TestCase(Arrays.asList(3, 7, 5, 1, 5), 2)); // [5,5]

        // Sample 1
        tests.add(new TestCase(Arrays.asList(2, 2, 3, 2, 1, 2, 2), 7)); // [1,2,2,2,2,3,2] → 7

        /* Extra edge-case checks */
        tests.add(new TestCase(List.of(6), 1));                       // single item
        tests.add(new TestCase(Arrays.asList(1, 3, 5, 7), 1));              // no neighbours
        tests.add(new TestCase(Arrays.asList(5, 5, 5, 5), 4));              // one value only
        tests.add(new TestCase(Arrays.asList(1, 2, 1, 2, 1, 2), 6));        // perfect mix

        /* Large-data performance check: 100 000 × 100 plus 100 000 × 101 */
        List<Integer> big = IntStream.concat(
                IntStream.generate(() -> 100).limit(100_000),
                IntStream.generate(() -> 101).limit(100_000)
        ).boxed().collect(Collectors.toList());
        tests.add(new TestCase(big, 200_000));

        /* Run and report */
        int idx = 1;
        for (TestCase tc : tests) {
            int got = getMaxServers(tc.data);
            System.out.printf("Test %2d: %s (got=%d, expected=%d)%n",
                    idx++, (got == tc.expected ? "PASS" : "FAIL"),
                    got, tc.expected);
        }
    }

    /* ---------- simple test harness ---------- */
    private record TestCase(List<Integer> data, int expected) {
    }
}

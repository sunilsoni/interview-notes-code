package com.interview.notes.code.year.y2025.september.common.test10;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RelativeOrder {

    public static List<Integer> sortAByB(int[] A, int[] B) {
        Map<Integer, Long> freq = Arrays.stream(A).boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int b : B) {
            if (seen.add(b)) {
                Long count = freq.remove(b);
                if (count != null) {
                    IntStream.range(0, count.intValue()).forEach(i -> result.add(b));
                }
            }
        }
        freq.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> IntStream.range(0, e.getValue().intValue()).forEach(i -> result.add(e.getKey())));
        return result;
    }

    public static void main(String[] args) {
        record TestCase(int[] A, int[] B, List<Integer> expected) {
        }
        List<TestCase> tests = Arrays.asList(
                new TestCase(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6}, Arrays.asList(2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19)),
                new TestCase(new int[]{5, 5, 5}, new int[]{5}, Arrays.asList(5, 5, 5)),
                new TestCase(new int[]{1, 2, 3}, new int[]{4, 5}, Arrays.asList(1, 2, 3))
        );
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            List<Integer> out = sortAByB(tc.A, tc.B);
            System.out.println("Test " + (i + 1) + ": " + (out.equals(tc.expected) ? "PASS" : "FAIL"));
        }
        int N = 100000, M = 1000;
        Random rnd = new Random(0);
        int[] A = rnd.ints(N, 0, 10000).toArray();
        int[] B = Arrays.stream(A).distinct().limit(M).toArray();
        List<Integer> large = sortAByB(A, B);
        System.out.println("Large test completed, output size = " + large.size());
    }
}
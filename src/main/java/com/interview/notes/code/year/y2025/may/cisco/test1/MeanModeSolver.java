package com.interview.notes.code.year.y2025.may.cisco.test1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class MeanModeSolver {

    // ---------- core routine ----------
    public static int[] funcMeanMode(int[] inputArr) {
        int n = inputArr.length;

        // mean (floor)
        long sum = IntStream.of(inputArr).asLongStream().sum();
        int mean = (int) (sum / n);   // integer division == floor

        // mode
        Map<Integer, Long> freq =
                Arrays.stream(inputArr)
                      .boxed()
                      .collect(Collectors.groupingBy(Function.identity(),
                                                     Collectors.counting()));

        int mode = freq.entrySet()
                       .stream()
                       .sorted(Comparator.<Map.Entry<Integer,Long>>comparingLong(Map.Entry::getValue)
                                         .reversed()               // biggest count first
                                         .thenComparingInt(Map.Entry::getKey)) // smallest value on tie
                       .findFirst()
                       .get()
                       .getKey();

        return new int[]{mean, mode};
    }

    // ---------- simple test runner ----------
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        // Provided example
        tests.add(new TestCase(new int[]{1, 2, 7, 3, 2}, 3, 2));

        // Edge cases
        tests.add(new TestCase(new int[]{5}, 5, 5));                       // single element
        tests.add(new TestCase(new int[]{4, 4, 4, 4}, 4, 4));              // all same
        tests.add(new TestCase(new int[]{9, 8, 7, 6}, 7, 6));              // all distinct
        tests.add(new TestCase(new int[]{-1, -1, 0, 0}, -1, -1));          // tie, choose smaller

        // Large random test (1 000 000 numbers)
        int size = 1_000_000;
        int[] big = new Random(42).ints(size, 1, 1000).toArray();
        // Pre-compute expected with same method (ground truth)
        int[] expectedBig = funcMeanMode(big);
        tests.add(new TestCase(big, expectedBig[0], expectedBig[1]));

        // Run tests
        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int[] res = funcMeanMode(t.input);
            boolean ok = res[0] == t.expMean && res[1] == t.expMode;
            System.out.println("Test " + i + ": " + (ok ? "PASS" : "FAIL")
                               + "  expected=(" + t.expMean + "," + t.expMode + ")"
                               + " got=(" + res[0] + "," + res[1] + ")");
            if (ok) pass++;
        }
        System.out.println(pass + "/" + tests.size() + " tests passed");
    }

    private static class TestCase {
        int[] input;
        int expMean;
        int expMode;
        TestCase(int[] input, int expMean, int expMode) {
            this.input = input;
            this.expMean = expMean;
            this.expMode = expMode;
        }
    }
}

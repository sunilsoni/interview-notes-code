package com.interview.notes.code.year.y2026.jan.assessments.Codesignal.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayDistributor {

    public static void main(String[] args) {
        var distributor = new ArrayDistributor();

        // Test Case 1: Example from problem
        // logic: 6 goes to second (1>6 vs 0>6), 9 goes first (0 vs 0, size 1 vs 2), 2 goes first (2 vs 2, size equal)
        runTest(distributor, new int[]{5, 7, 6, 9, 2}, new int[]{5, 9, 2, 7, 6}, "Example Case");

        // Test Case 2: Min Constraints
        runTest(distributor, new int[]{10, 20}, new int[]{10, 20}, "Min Length");

        // Test Case 3: Identical values
        runTest(distributor, new int[]{5, 5, 5, 5}, new int[]{5, 5, 5, 5}, "Identical Inputs");

        // Test Case 4: Large Data Performance
        int[] large = IntStream.range(0, 1000).toArray();
        long start = System.currentTimeMillis();
        int[] res = distributor.solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Check: " + ((end - start) < 3000 ? "PASS" : "FAIL") + " (Time: " + (end - start) + "ms)");
    }

    private static void runTest(ArrayDistributor d, int[] input, int[] expected, String name) {
        int[] result = d.solution(input);
        boolean pass = Arrays.equals(result, expected);
        System.out.printf("%-15s: %s [Expected: %s, Got: %s]%n",
                name, pass ? "PASS" : "FAIL",
                Arrays.toString(expected), Arrays.toString(result));
    }

    int[] solution(int[] numbers) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        first.add(numbers[0]);
        second.add(numbers[1]);

        for (int i = 2; i < numbers.length; i++) {
            int val = numbers[i];
            long count1 = first.stream().filter(n -> n > val).count();
            long count2 = second.stream().filter(n -> n > val).count();

            if (count1 > count2) {
                first.add(val);
            } else if (count2 > count1) {
                second.add(val);
            } else {
                // Tie in counts: check sizes. If sizes equal, prefer first.
                if (first.size() <= second.size()) {
                    first.add(val);
                } else {
                    second.add(val);
                }
            }
        }

        return Stream.concat(first.stream(), second.stream())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
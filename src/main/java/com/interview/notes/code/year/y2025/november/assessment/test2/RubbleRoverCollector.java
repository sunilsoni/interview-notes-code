package com.interview.notes.code.year.y2025.november.assessment.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RubbleRoverCollector {

    public static int solve(int P, List<Integer> a) {
        int n = a.size();
        int maxDistance = Math.max(P - 1, n - P);

        return IntStream.rangeClosed(0, maxDistance)
                .map(d -> collectedAtDistance(d, P, a))
                .sum();
    }

    private static int collectedAtDistance(int d, int P, List<Integer> a) {
        int n = a.size();
        int leftIndex = P - d;
        int rightIndex = P + d;

        if (leftIndex >= 1 && rightIndex <= n) {
            if (leftIndex == rightIndex) {
                return a.get(leftIndex - 1);
            } else {
                int leftValue = a.get(leftIndex - 1);
                int rightValue = a.get(rightIndex - 1);
                if (leftValue == 1 && rightValue == 1) {
                    return 2;
                }
                return 0;
            }
        } else if (leftIndex >= 1 && leftIndex <= n) {
            return a.get(leftIndex - 1);
        } else if (rightIndex >= 1 && rightIndex <= n) {
            return a.get(rightIndex - 1);
        } else {
            return 0;
        }
    }

    private static void runSingleTest(String name, int P, List<Integer> blocks, int expected) {
        int actual = solve(P, blocks);
        if (actual == expected) {
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    public static void main(String[] args) {
        runSingleTest(
                "Example_1",
                3,
                Arrays.asList(1, 0, 1, 1, 1),
                3
        );

        runSingleTest(
                "Example_2",
                4,
                Arrays.asList(0, 1, 1, 0),
                2
        );

        runSingleTest(
                "Edge_StartPosition",
                1,
                Arrays.asList(1, 1, 0, 1, 1),
                4
        );

        runSingleTest(
                "Middle_Position",
                4,
                Arrays.asList(1, 0, 1, 0, 0, 1, 1),
                2
        );

        List<Integer> largeBlocks = IntStream.rangeClosed(1, 100)
                .map(i -> i % 2 == 0 ? 1 : 0)
                .boxed()
                .collect(Collectors.toList());

        runSingleTest(
                "Large_Data_Set",
                50,
                largeBlocks,
                50
        );
    }
}

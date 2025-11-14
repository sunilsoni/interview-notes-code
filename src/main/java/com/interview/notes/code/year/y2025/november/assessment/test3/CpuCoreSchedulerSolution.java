package com.interview.notes.code.year.y2025.november.assessment.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CpuCoreSchedulerSolution {

    public static int getMinCores(List<Integer> start, List<Integer> end) {
        int n = start.size();
        int[] s = new int[n];
        int[] e = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = start.get(i);
            e[i] = end.get(i);
        }

        Arrays.sort(s);
        Arrays.sort(e);

        int i = 0;
        int j = 0;
        int current = 0;
        int max = 0;

        while (i < n && j < n) {
            if (s[i] <= e[j]) {
                current++;
                if (current > max) {
                    max = current;
                }
                i++;
            } else {
                current--;
                j++;
            }
        }

        return max;
    }

    private static boolean runSingleTest(List<Integer> start, List<Integer> end, int expected, String testName) {
        int actual = getMinCores(start, end);
        boolean pass = actual == expected;
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL")
                + " (expected=" + expected + ", actual=" + actual + ")");
        return pass;
    }

    public static void main(String[] args) {
        boolean allPass = true;

        allPass &= runSingleTest(
                Arrays.asList(1, 3, 4),
                Arrays.asList(3, 5, 6),
                2,
                "ExampleTest"
        );

        allPass &= runSingleTest(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 3, 5),
                3,
                "SampleTest0"
        );

        allPass &= runSingleTest(
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 4, 10),
                1,
                "SampleTest1"
        );

        allPass &= runSingleTest(
                List.of(5),
                List.of(5),
                1,
                "SingleProcessSameStartEnd"
        );

        allPass &= runSingleTest(
                Arrays.asList(1, 10, 20),
                Arrays.asList(5, 15, 25),
                1,
                "NonOverlappingWideIntervals"
        );

        allPass &= runSingleTest(
                Arrays.asList(1, 1, 1, 1),
                Arrays.asList(1, 1, 1, 1),
                4,
                "AllSameInstant"
        );

        int largeN = 100000;
        List<Integer> largeStart = new ArrayList<>(largeN);
        List<Integer> largeEnd = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeStart.add(1);
            largeEnd.add(1000000000);
        }
        allPass &= runSingleTest(
                largeStart,
                largeEnd,
                largeN,
                "LargeOverlappingTest"
        );

        System.out.println("Overall result: " + (allPass ? "PASS" : "FAIL"));
    }
}

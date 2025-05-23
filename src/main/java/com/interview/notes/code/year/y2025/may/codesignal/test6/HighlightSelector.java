package com.interview.notes.code.year.y2025.may.codesignal.test6;

import java.util.*;
import java.util.stream.Collectors;

public class HighlightSelector {

    public static int[] solution(int[] heights) {
        List<Integer> list = Arrays.stream(heights).boxed().collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();

        while (!list.isEmpty()) {
            List<Integer> highlights = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                int h = list.get(i);
                boolean leftOk = (i == 0) || h > list.get(i - 1);
                boolean rightOk = (i == list.size() - 1) || h > list.get(i + 1);
                // change here: highlight if EITHER side is OK
                if (leftOk || rightOk) {
                    highlights.add(h);
                }
            }
            int minH = highlights.stream().mapToInt(x -> x).min().getAsInt();
            result.add(minH);
            list.remove((Integer) minH);
        }

        return result.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        runTest(new int[]{2, 7, 8, 5, 1, 6, 3, 9, 4},
                new int[]{6, 8, 7, 5, 2, 9, 4, 3, 1}, "Example Test");

        runTest(new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5}, "Ascending Test");

        runTest(new int[]{5, 4, 3, 2, 1},
                new int[]{5, 4, 3, 2, 1}, "Descending Test");

        runTest(new int[]{42},
                new int[]{42}, "Single Element Test");

        runTest(new int[]{3, 1},
                new int[]{3, 1}, "Two Elements Test");

        // Large random test of size 100
        int n = 100;
        Random rand = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < n) set.add(rand.nextInt(1_000_000) + 1);
        int[] large = set.stream().mapToInt(x -> x).toArray();
        int[] out = solution(large);
        if (out.length == large.length &&
                Arrays.stream(out).boxed().collect(Collectors.toSet())
                        .equals(Arrays.stream(large).boxed().collect(Collectors.toSet()))) {
            System.out.println("Large Random Test: PASS");
        } else {
            System.out.println("Large Random Test: FAIL");
        }
    }

    private static void runTest(int[] heights, int[] expected, String name) {
        int[] got = solution(heights);
        if (Arrays.equals(got, expected)) {
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL | expected=" +
                    Arrays.toString(expected) + ", got=" + Arrays.toString(got));
        }
    }
}
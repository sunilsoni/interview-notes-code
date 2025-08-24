package com.interview.notes.code.year.y2025.august.Uber.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static int[] solution(int[] numbers) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            if (i == 0) {
                first.add(x);
            } else if (i == 1) {
                second.add(x);
            } else {
                long c1 = first.stream().filter(v -> v > x).count();
                long c2 = second.stream().filter(v -> v > x).count();
                if (c1 > c2) {
                    first.add(x);
                } else if (c2 > c1) {
                    second.add(x);
                } else {
                    if (first.size() <= second.size()) {
                        first.add(x);
                    } else {
                        second.add(x);
                    }
                }
            }
        }
        return IntStream.concat(first.stream().mapToInt(i -> i), second.stream().mapToInt(i -> i)).toArray();
    }

    public static void main(String[] args) {
        int[][] cases = {
                {5, 7, 6, 9, 2},
                {1, 2},
                {10, 5, 8, 3, 6, 7},
                {4, 4, 4, 4}
        };
        int[][] expect = {
                {5, 9, 2, 7, 6},
                {1, 2},
                {10, 8, 6, 5, 3, 7},
                {4, 4, 4, 4}
        };
        for (int i = 0; i < cases.length; i++) {
            int[] out = solution(cases[i]);
            boolean pass = Arrays.equals(out, expect[i]);
            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL") + " got=" + Arrays.toString(out));
        }
        int n = 1000;
        int[] large = IntStream.range(0, n).toArray();
        int[] out = solution(large);
        System.out.println("Large test length: " + out.length);
    }
}

package com.interview.notes.code.year.y2025.october.capitalOne.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TideLevels {

    static int solution(int[] tide_levels) {
        int count = 0;
        int maxRight = Integer.MIN_VALUE;
        for (int i = tide_levels.length - 1; i >= 0; i--) {
            if (tide_levels[i] >= maxRight) {
                count++;
                maxRight = tide_levels[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
            new int[]{130, 140, 120, 150, 110, 160},
            new int[]{100, 160, 150, 130, 140},
            new int[]{200, 190, 180, 170, 160},
            new int[]{10, 20, 30, 40, 50},
            new int[]{50, 40, 30, 20, 10}
        );

        List<Integer> expected = Arrays.asList(1, 3, 5, 1, 5);

        IntStream.range(0, tests.size()).forEach(i -> {
            int[] t = tests.get(i);
            int result = solution(t);
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        int[] large = IntStream.range(0, 1000000).map(x -> new Random().nextInt(1000000)).toArray();
        long start = System.currentTimeMillis();
        int result = solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + result);
    }
}
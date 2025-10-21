package com.interview.notes.code.year.y2025.october.capitalOne.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DiceScore {

    static int solution(int a, int b, int c) {
        if (a == b && b == c) return 1000 * a;
        else if (a == b || b == c || a == c) {
            int x = (a == b) ? a : (b == c ? b : a);
            return 500 * x;
        } else return 100 * IntStream.of(a, b, c).min().getAsInt();
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[]{3, 3, 3},
                new int[]{3, 6, 3},
                new int[]{3, 2, 5},
                new int[]{1, 1, 2},
                new int[]{2, 3, 4},
                new int[]{6, 6, 6}
        );

        List<Integer> expected = Arrays.asList(3000, 1500, 200, 500, 200, 6000);

        IntStream.range(0, tests.size()).forEach(i -> {
            int[] t = tests.get(i);
            int result = solution(t[0], t[1], t[2]);
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        Random rand = new Random();
        long start = System.currentTimeMillis();
        IntStream.range(0, 1000000).forEach(i -> solution(rand.nextInt(6) + 1, rand.nextInt(6) + 1, rand.nextInt(6) + 1));
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms");
    }
}
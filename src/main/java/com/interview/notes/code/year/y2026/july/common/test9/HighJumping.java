package com.interview.notes.code.year.y2026.july.common.test9;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HighJumping {

    static int highJump(int[] points) {
        if (points.length == 1) {
            return points[0];
        }

        int max = Arrays.stream(points).max().orElse(0);
        int[] coins = new int[max + 2];

        IntStream.range(0, points.length - 1).forEach(i -> {
            int start = Math.min(points[i], points[i + 1]);
            int end = Math.max(points[i], points[i + 1]);
            coins[start]++;
            coins[end + 1]--;
        });

        int position = 0;
        int current = 0;
        int highest = -1;

        for (int i = 0; i <= max; i++) {
            current += coins[i];

            if (current > highest) {
                highest = current;
                position = i;
            }
        }

        return position;
    }

    static void test(int number, int[] points, int expected) {
        int actual = highJump(points);
        System.out.println("Test " + number + ": " +
                (actual == expected ? "PASS" : "FAIL"));
    }

    static void runTests() {
        test(1, new int[]{1, 5, 3, 6}, 3);
        test(2, new int[]{2, 4}, 2);
        test(3, new int[]{5, 1, 5}, 1);
        test(4, new int[]{7}, 7);
        test(5, new int[]{0, 0, 1}, 0);

        int[] large = IntStream.rangeClosed(0, 1000)
                .map(i -> i % 2 == 0 ? 0 : 10000)
                .toArray();

        test(6, large, 0);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (!input.hasNextInt()) {
            runTests();
            return;
        }

        int n = input.nextInt();
        int[] points = IntStream.range(0, n)
                .map(i -> input.nextInt())
                .toArray();

        System.out.print(highJump(points));
    }
}
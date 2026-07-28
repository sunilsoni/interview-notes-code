package com.interview.notes.code.year.y2026.july.common.test6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Outcome {
    public static int solve(List<Integer> ar) {
        int total = ar.stream().mapToInt(Integer::intValue).sum();
        boolean[] possible = new boolean[total / 2 + 1];
        possible[0] = true;

        for (int value : ar) {
            for (int sum = total / 2; sum >= value; sum--) {
                possible[sum] |= possible[sum - value];
            }
        }

        for (int sum = total / 2; sum >= 0; sum--) {
            if (possible[sum]) {
                return total - 2 * sum;
            }
        }

        return total;
    }

    static void test(String name, List<Integer> input, int expected) {
        int actual = solve(input);
        System.out.println(name + ": " + (actual == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("Example 1", Arrays.asList(12, 3, 6, 4, 2), 1);
        test("Example 2", Arrays.asList(7, 3, 1, 8, 4, 5), 0);
        test("Single bag", Collections.singletonList(7), 7);
        test("Equal bags", Arrays.asList(10, 10), 0);
        test("Odd total", Arrays.asList(1, 2, 4), 1);
        test("Large input", Collections.nCopies(50, 60), 0);
    }
}
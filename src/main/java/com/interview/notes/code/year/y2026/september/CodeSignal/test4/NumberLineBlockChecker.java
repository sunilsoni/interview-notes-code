package com.interview.notes.code.year.y2026.september.CodeSignal.test4;

import java.util.TreeSet;

public class NumberLineBlockChecker {

    static String solution(int[][] operations) {
        var obstacles = new TreeSet<Integer>();
        var result = new StringBuilder();

        for (var op : operations) {
            if (op[0] == 1) {
                obstacles.add(op[1]);
            } else {
                var p = obstacles.lower(op[1]);
                result.append(p == null || p < op[1] - op[2] ? '1' : '0');
            }
        }

        return result.toString();
    }

    static void test(int[][] operations, String expected) {
        System.out.println(solution(operations).equals(expected) ? "PASS" : "FAIL");
    }

    static void main(String[] args) {
        test(
                new int[][]{
                        {1, 2},
                        {1, 5},
                        {2, 5, 2},
                        {2, 6, 3},
                        {2, 2, 1},
                        {2, 3, 2}
                },
                "1010"
        );

        test(
                new int[][]{
                        {2, 5, 3},
                        {2, -5, 3}
                },
                "11"
        );

        test(
                new int[][]{
                        {1, 2},
                        {2, 3, 1},
                        {2, 4, 1},
                        {2, 2, 1}
                },
                "011"
        );

        test(
                new int[][]{
                        {1, -3},
                        {2, -1, 2},
                        {2, 0, 2}
                },
                "01"
        );

        test(
                new int[][]{
                        {1, 1_000_000_000},
                        {1, -1_000_000_000},
                        {2, 1_000_000_000, 1_000_000_000},
                        {2, 0, 1_000_000_000}
                },
                "10"
        );

        var large = new int[100_000][];
        for (int i = 0; i < 50_000; i++) {
            large[i * 2] = new int[]{1, i * 2};
            large[i * 2 + 1] = new int[]{2, i * 2 + 1, 1};
        }
        test(large, "0".repeat(50_000));
    }
}
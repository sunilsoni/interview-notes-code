package com.interview.notes.code.year.y2025.october.capitalOne.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MatrixPattern {

    static int solution(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    for (int[] dir : directions) {
                        int len = 1, x = i, y = j, expected = 2;
                        while (true) {
                            x += dir[0];
                            y += dir[1];
                            if (x < 0 || y < 0 || x >= n || y >= m) break;
                            if (matrix[x][y] != expected) break;
                            len++;
                            expected = expected == 2 ? 0 : 2;
                        }
                        if (x < 0 || y < 0 || x >= n || y >= m) maxLen = Math.max(maxLen, len);
                    }
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][]{
                        {0, 0, 1, 2},
                        {0, 2, 2, 2},
                        {2, 1, 0, 1}
                },
                new int[][]{
                        {2, 1, 2, 2, 0},
                        {0, 2, 0, 2, 2},
                        {0, 0, 0, 0, 0},
                        {0, 1, 2, 2, 1},
                        {2, 2, 0, 2, 1},
                        {0, 2, 0, 0, 2}
                }
        );

        List<Integer> expected = Arrays.asList(3, 3);

        IntStream.range(0, tests.size()).forEach(i -> {
            int result = solution(tests.get(i));
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        int[][] large = new int[100][100];
        Random r = new Random();
        IntStream.range(0, 100).forEach(i -> IntStream.range(0, 100).forEach(j -> large[i][j] = r.nextInt(3)));
        long start = System.currentTimeMillis();
        int res = solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + res);
    }
}
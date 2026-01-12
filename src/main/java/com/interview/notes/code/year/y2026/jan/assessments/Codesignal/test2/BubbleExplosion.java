package com.interview.notes.code.year.y2026.jan.assessments.Codesignal.test2;

import java.util.Arrays;

public class BubbleExplosion {

    public static void main(String[] args) {
        BubbleExplosion solver = new BubbleExplosion();

        // Test Case 1: Example from problem
        // Note: Problem implies a single explosion event, not recursive until stable.
        int[][] input1 = {{3, 1, 2, 1}, {1, 1, 1, 4}, {3, 1, 2, 2}, {3, 3, 3, 4}};
        int[][] expect1 = {{0, 0, 0, 1}, {0, 0, 0, 4}, {0, 0, 2, 2}, {3, 0, 2, 4}};
        runTest(solver, input1, expect1, "Example Case");

        // Test Case 2: No Explosion (Less than 2 neighbors)
        int[][] input2 = {{1, 2}, {3, 4}};
        runTest(solver, input2, input2, "No Explosion");

        // Test Case 3: Line (Middle matches 2, triggers neighbors)
        // 1-1-1 vertical. Middle (1,0) has 2 neighbors. Explodes self + (0,0) and (2,0).
        int[][] input3 = {{1}, {1}, {1}};
        int[][] expect3 = {{0}, {0}, {0}};
        runTest(solver, input3, expect3, "Vertical Line Clear");

        // Test Case 4: L-Shape
        // 1 0
        // 1 1
        // Corner (1,0) matches (0,0) and (1,1). All 3 explode.
        int[][] input4 = {{1, 0}, {1, 1}};
        int[][] expect4 = {{0, 0}, {0, 0}};
        runTest(solver, input4, expect4, "L-Shape Clear");
    }

    private static void runTest(BubbleExplosion s, int[][] in, int[][] exp, String name) {
        int[][] copy = Arrays.stream(in).map(int[]::clone).toArray(int[][]::new);
        int[][] res = s.solution(copy);
        boolean pass = Arrays.deepEquals(res, exp);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) System.out.println("Expected: " + Arrays.deepToString(exp) + "\nGot: " + Arrays.deepToString(res));
    }

    int[][] solution(int[][] bubbles) {
        if (bubbles == null || bubbles.length == 0) return bubbles;
        int rows = bubbles.length, cols = bubbles[0].length;
        boolean[][] explode = new boolean[rows][cols];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int color = bubbles[r][c];
                if (color == 0) continue;
                int same = 0;
                for (var d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && bubbles[nr][nc] == color) same++;
                }
                if (same >= 2) {
                    explode[r][c] = true;
                    for (var d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && bubbles[nr][nc] == color)
                            explode[nr][nc] = true;
                    }
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (explode[r][c]) bubbles[r][c] = 0;
            }
        }

        for (int c = 0; c < cols; c++) {
            int w = rows - 1;
            for (int r = rows - 1; r >= 0; r--) {
                if (bubbles[r][c] != 0) bubbles[w--][c] = bubbles[r][c];
            }
            while (w >= 0) bubbles[w--][c] = 0;
        }
        return bubbles;
    }
}
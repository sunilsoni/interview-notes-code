package com.interview.notes.code.year.y2026.april.assessments.test2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GamePieceDistanceMaximizer {

    public static void main(String[] args) {
        var solver = new GamePieceDistanceMaximizer();

        test(solver, new int[]{1, 1, 0, 0, 1}, 4);
        test(solver, new int[]{0, 1}, 1);
        test(solver, new int[]{0, 0, 0}, 0);

        var largeBoard = new int[40000];
        Arrays.fill(largeBoard, 0, 20000, 1);
        test(solver, largeBoard, 400000000);
    }

    private static void test(GamePieceDistanceMaximizer solver, int[] board, int expected) {
        var result = solver.solution(board);
        System.out.println((result == expected ? "PASS" : "FAIL") + " | Expected: " + expected + " | Actual: " + result);
    }

    public int solution(int[] board) {
        var p = IntStream.range(0, board.length).filter(i -> board[i] == 1).toArray();
        var k = p.length;
        if (k == 0 || k == board.length) {
            return 0;
        }

        var E = board.length - k;
        var dp = new int[E + 1];

        for (var e = 0; e <= E; e++) {
            dp[e] = Math.abs(p[0] - e);
        }

        for (var i = 1; i < k; i++) {
            var next = new int[E + 1];
            var max = dp[0];
            var pi = p[i];

            for (var e = 0; e <= E; e++) {
                if (dp[e] > max) {
                    max = dp[e];
                }
                next[e] = max + Math.abs(pi - (i + e));
            }
            dp = next;
        }

        return Arrays.stream(dp).max().orElse(0);
    }
}
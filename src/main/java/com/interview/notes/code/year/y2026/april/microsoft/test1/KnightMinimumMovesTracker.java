package com.interview.notes.code.year.y2026.april.microsoft.test1;

import java.util.LinkedList;
import java.util.stream.Stream;

public class KnightMinimumMovesTracker {

    public static int minMoves(int n, int startRow, int startCol, int endRow, int endCol) {
        int[][] dirs = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
        var vis = new boolean[n][n];
        var q = new LinkedList<Pos>();

        q.add(new Pos(startRow, startCol, 0));
        vis[startRow][startCol] = true;

        while (!q.isEmpty()) {
            var p = q.poll();

            if (p.r == endRow && p.c == endCol) {
                return p.d;
            }

            for (int[] d : dirs) {
                int nr = p.r + d[0];
                int nc = p.c + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.add(new Pos(nr, nc, p.d + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Stream.of(
            new Test(9, 4, 4, 4, 8, 2),
            new Test(10, 0, 0, 0, 2, 2),
            new Test(6, 5, 1, 0, 5, 3),
            new Test(4, 0, 0, 0, 0, 0),
            new Test(150, 0, 0, 2, 1, 1),
            new Test(150, 149, 149, 147, 148, 1)
        ).forEach(t -> {
            int res = minMoves(t.n, t.sr, t.sc, t.er, t.ec);
            System.out.println((res == t.exp ? "PASS" : "FAIL") + " | Expected: " + t.exp + " | Got: " + res);
        });
    }

    record Pos(int r, int c, int d) {}

    record Test(int n, int sr, int sc, int er, int ec, int exp) {}
}
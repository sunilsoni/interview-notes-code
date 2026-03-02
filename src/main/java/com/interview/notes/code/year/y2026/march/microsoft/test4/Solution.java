package com.interview.notes.code.year.y2026.march.microsoft.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
    static int minPath;

    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        int n = maze.size(), m = maze.get(0).size();
        var pois = new ArrayList<>(List.of(new Point(0, 0))); // Index 0: Start

        // Compact stream to find all coins and add them directly to our POI list
        IntStream.range(0, n).forEach(r -> IntStream.range(0, m)
            .filter(c -> maze.get(r).get(c) == 2).forEach(c -> pois.add(new Point(r, c))));
        pois.add(new Point(x, y)); // Last Index: Alex

        int k = pois.size();
        int[][] dist = new int[k][k];
        for (int i = 0; i < k; i++) dist[i] = bfs(pois.get(i), pois, maze, n, m);

        minPath = Integer.MAX_VALUE;
        dfs(0, 0, 0, k - 2, dist, new boolean[k - 1]); // k-2 represents the number of coins
        return minPath == Integer.MAX_VALUE ? -1 : minPath;
    }

    private static int[] bfs(Point st, List<Point> pois, List<List<Integer>> maze, int n, int m) {
        var q = new LinkedList<Point>();
        int[][] steps = new int[n][m];
        for (var row : steps) Arrays.fill(row, -1);

        q.offer(st);
        steps[st.r()][st.c()] = 0;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}, res = new int[pois.size()];

        while (!q.isEmpty()) {
            var p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.r() + dr[i], nc = p.c() + dc[i];
                // Check bounds, walls (1), and if unvisited (-1)
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && maze.get(nr).get(nc) != 1 && steps[nr][nc] < 0) {
                    steps[nr][nc] = steps[p.r()][p.c()] + 1;
                    q.offer(new Point(nr, nc));
                }
            }
        }
        // Java 8+ Arrays.setAll replaces the manual loop to populate the result array
        Arrays.setAll(res, i -> steps[pois.get(i).r()][pois.get(i).c()]);
        return res;
    }

    private static void dfs(int u, int coins, int d, int total, int[][] dist, boolean[] vis) {
        if (d >= minPath) return; // Prune bad paths
        if (coins == total) {
            if (dist[u][total + 1] > -1) minPath = Math.min(minPath, d + dist[u][total + 1]);
            return;
        }
        for (int i = 1; i <= total; i++) {
            if (!vis[i] && dist[u][i] > -1) {
                vis[i] = true;
                dfs(i, coins + 1, d + dist[u][i], total, dist, vis);
                vis[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        runTest("Test Case 0", new int[][]{{0, 2, 0}, {0, 0, 1}, {1, 1, 1}}, 1, 1, 2);
        runTest("Test Case 1", new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 2, 2}}, 1, 1, -1);
        runTest("Test Case 2", new int[][]{{0, 2, 0}, {1, 1, 2}, {1, 0, 0}}, 2, 1, 5);
    }

    private static void runTest(String name, int[][] grid, int x, int y, int expected) {
        var maze = Arrays.stream(grid).map(r -> Arrays.stream(r).boxed().toList()).toList();
        int result = minMoves(maze, x, y);
        System.out.printf("%s -> %s (Expected: %d, Got: %d)%n", name, (result == expected ? "PASS!" : "FAIL!"), expected, result);
    }

    record Point(int r, int c) {}
}
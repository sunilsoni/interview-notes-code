package com.interview.notes.code.year.y2026.july.amazon.test1;

import java.util.*;

public class DeliveryCenterOptimizer {

    public static int getMinInconvenience(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.getFirst().size();
        int[][] dist = new int[n][m];
        
        for (var row : dist) {
            Arrays.fill(row, 1 << 20);
        }
        
        var queue = new ArrayDeque<int[]>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }
                
        for (int[] r = queue.poll(); r != null; r = queue.poll()) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    int nx = r[0] + x;
                    int ny = r[1] + y;
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && dist[nx][ny] > dist[r[0]][r[1]] + 1) {
                        dist[nx][ny] = dist[r[0]][r[1]] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        int low = 0;
        int high = Math.max(n, m);
        
        while (low < high) {
            int mid = (low + high) / 2;
            int minR = 0;
            int maxR = n - 1;
            int minC = 0;
            int maxC = m - 1;
            boolean requiresNewCenter = false;
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][j] > mid) {
                        requiresNewCenter = true;
                        minR = Math.max(minR, i - mid);
                        maxR = Math.min(maxR, i + mid);
                        minC = Math.max(minC, j - mid);
                        maxC = Math.min(maxC, j + mid);
                    }
                }
            }
            
            if (!requiresNewCenter || (minR <= maxR && minC <= maxC)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }

    public static void main(String[] args) {
        runTest(List.of(
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0)
        ), 2);

        runTest(List.of(
            List.of(0)
        ), 0);

        runTest(List.of(
            List.of(0, 0, 0, 1),
            List.of(0, 0, 0, 1)
        ), 1);

        var largeGrid = new ArrayList<List<Integer>>();
        for (int i = 0; i < 500; i++) {
            largeGrid.add(new ArrayList<>(Collections.nCopies(500, 0)));
        }
        runTest(largeGrid, 250);
    }

    private static void runTest(List<List<Integer>> grid, int expected) {
        long start = System.nanoTime();
        int result = getMinInconvenience(grid);
        long time = (System.nanoTime() - start) / 1000000;
        System.out.printf("Result: %-4d | Expected: %-4d | Status: %-4s | Time: %dms%n", 
                          result, expected, result == expected ? "PASS" : "FAIL", time);
    }
}
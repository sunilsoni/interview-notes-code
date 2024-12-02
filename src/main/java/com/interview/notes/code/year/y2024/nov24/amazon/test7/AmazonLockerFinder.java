package com.interview.notes.code.year.y2024.nov24.amazon.test7;

import java.util.*;

/*
Given a map of a city and a list of Amazon Locker locations within that city, find a location that reaches all lockers in the shortest distance possible.

You are given an m x n grid of values X, L, or 0, where:
* each 0 marks an empty location that you can pass by freely,
* each L marks an Amazon Locker that you cannot pass through, and
* each X marks an obstacle that you cannot pass through.

Example grid:
```
L - 0 - X - 0 - L
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - L - 0 - 0
```

You can only move up, down, left, and right.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

If there’s no such locker location, return -1.

 */
public class AmazonLockerFinder {
    // Main method to find optimal location
    public static Point findOptimalLocation(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return null;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        List<Point> lockers = new ArrayList<>();

        // Find all locker locations
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'L') {
                    lockers.add(new Point(i, j));
                }
            }
        }

        if (lockers.isEmpty()) return null;

        Point optimalLocation = null;
        int minTotalDistance = Integer.MAX_VALUE;

        // Try each empty cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    int totalDistance = calculateTotalDistance(grid, new Point(i, j), lockers);
                    if (totalDistance != -1 && totalDistance < minTotalDistance) {
                        minTotalDistance = totalDistance;
                        optimalLocation = new Point(i, j);
                    }
                }
            }
        }

        return optimalLocation;
    }

    // Calculate total distance from point to all lockers
    private static int calculateTotalDistance(char[][] grid, Point start, List<Point> lockers) {
        int totalDistance = 0;

        for (Point locker : lockers) {
            int distance = findShortestPath(grid, start, locker);
            if (distance == -1) return -1;
            totalDistance += distance;
        }

        return totalDistance;
    }

    // BFS to find shortest path between two points
    private static int findShortestPath(char[][] grid, Point start, Point end) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        queue.offer(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == end.x && current.y == end.y) {
                return distance[current.x][current.y];
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(grid, newX, newY) && !visited[newX][newY] &&
                        (grid[newX][newY] == '0' || grid[newX][newY] == 'L')) {
                    queue.offer(new Point(newX, newY));
                    visited[newX][newY] = true;
                    distance[newX][newY] = distance[current.x][current.y] + 1;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        char[][] grid1 = {
                {'L', '0', 'X', '0', 'L'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', 'L', '0', '0'}
        };
        System.out.println("Test Case 1: Basic test");
        Point result1 = findOptimalLocation(grid1);
        boolean test1Pass = result1 != null && result1.x == 1 && result1.y == 2;
        System.out.println("Test Case 1: " + (test1Pass ? "PASS" : "FAIL"));

        // Test Case 2: No valid path
        char[][] grid2 = {
                {'L', 'X', 'L'},
                {'X', 'X', 'X'},
                {'L', 'X', 'L'}
        };
        System.out.println("\nTest Case 2: No valid path");
        Point result2 = findOptimalLocation(grid2);
        boolean test2Pass = result2 == null;
        System.out.println("Test Case 2: " + (test2Pass ? "PASS" : "FAIL"));

        // Test Case 3: Single locker
        char[][] grid3 = {
                {'0', '0', 'L'},
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        System.out.println("\nTest Case 3: Single locker");
        Point result3 = findOptimalLocation(grid3);
        boolean test3Pass = result3 != null;
        System.out.println("Test Case 3: " + (test3Pass ? "PASS" : "FAIL"));

        // Test Case 4: Large grid test
        char[][] grid4 = new char[50][50];
        for (char[] row : grid4) {
            Arrays.fill(row, '0');
        }
        grid4[0][0] = 'L';
        grid4[49][49] = 'L';
        System.out.println("\nTest Case 4: Large grid");
        Point result4 = findOptimalLocation(grid4);
        boolean test4Pass = result4 != null;
        System.out.println("Test Case 4: " + (test4Pass ? "PASS" : "FAIL"));

        // Test Case 5: Empty grid
        char[][] grid5 = new char[0][0];
        System.out.println("\nTest Case 5: Empty grid");
        Point result5 = findOptimalLocation(grid5);
        boolean test5Pass = result5 == null;
        System.out.println("Test Case 5: " + (test5Pass ? "PASS" : "FAIL"));
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
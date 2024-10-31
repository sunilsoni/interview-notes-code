package com.interview.notes.code.months.oct24.amazon.test23;

import java.util.*;

public class WarehouseRoutingBFS {
    // Main method to find all routes
    public static List<List<Point>> findRoutes(int[][] warehouse) {
        List<List<Point>> routes = new ArrayList<>();
        boolean[][] visited = new boolean[warehouse.length][warehouse[0].length];

        // Scan entire warehouse
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[0].length; j++) {
                if (warehouse[i][j] == 1 && !visited[i][j]) {
                    // Found unvisited shelf, start new route
                    List<Point> currentRoute = bfs(warehouse, i, j, visited);
                    routes.add(currentRoute);
                }
            }
        }
        return routes;
    }

    // BFS implementation to find connected shelves
    private static List<Point> bfs(int[][] warehouse, int startRow, int startCol,
                                   boolean[][] visited) {
        List<Point> route = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();

        // Start BFS from given point
        queue.offer(new Point(startRow, startCol));
        visited[startRow][startCol] = true;

        // Define all 8 directions (including diagonals)
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            route.add(current);

            // Check all 8 adjacent cells
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                // Validate new position and check if it's an unvisited shelf
                if (isValid(warehouse, newRow, newCol) &&
                        warehouse[newRow][newCol] == 1 &&
                        !visited[newRow][newCol]) {
                    queue.offer(new Point(newRow, newCol));
                    visited[newRow][newCol] = true;
                }
            }
        }

        // Sort route for consistent output
        sortRoute(route);
        return route;
    }

    // Validate if position is within warehouse bounds
    private static boolean isValid(int[][] warehouse, int row, int col) {
        return row >= 0 && row < warehouse.length &&
                col >= 0 && col < warehouse[0].length;
    }

    // Sort route for consistent output
    private static void sortRoute(List<Point> route) {
        Collections.sort(route, (a, b) -> {
            if (a.col != b.col) return a.col - b.col;
            return a.row - b.row;
        });
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Given example
        int[][] warehouse1 = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}
        };

        System.out.println("Test Case 1 (Given Example):");
        testWarehouse(warehouse1);

        // Test Case 2: Empty warehouse
        int[][] warehouse2 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        System.out.println("\nTest Case 2 (Empty Warehouse):");
        testWarehouse(warehouse2);

        // Test Case 3: Single cell
        int[][] warehouse3 = {{1}};

        System.out.println("\nTest Case 3 (Single Cell):");
        testWarehouse(warehouse3);

        // Test Case 4: All cells connected
        int[][] warehouse4 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        System.out.println("\nTest Case 4 (All Cells Connected):");
        testWarehouse(warehouse4);
    }

    // Helper method to test and print results
    private static void testWarehouse(int[][] warehouse) {
        System.out.println("Warehouse Layout:");
        printWarehouse(warehouse);

        long startTime = System.currentTimeMillis();
        List<List<Point>> routes = findRoutes(warehouse);
        long endTime = System.currentTimeMillis();

        System.out.println("\nRoutes found: " + routes.size());
        for (int i = 0; i < routes.size(); i++) {
            System.out.println("Route" + (i + 1) + " " + routes.get(i));
        }

        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }

    // Helper method to print warehouse layout
    private static void printWarehouse(int[][] warehouse) {
        for (int i = 0; i < warehouse.length; i++) {
            for (int j = 0; j < warehouse[0].length; j++) {
                System.out.print(warehouse[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Point class to store coordinates
    static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            // Converting to 1-based indexing for output
            return "(" + (col + 1) + "," + (row + 1) + ")";
        }
    }
}
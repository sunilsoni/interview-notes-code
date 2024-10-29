package com.interview.notes.code.months.oct24.amz.test25;

import java.util.*;

/**
 A warehouse automation system tracks the routes for a robot to pick items from shelves.
 A two-dimensional binary matrix maps the location of shelves that contain packages for pickup by marking the cell with 1.
 We need an optimization algorithm that determines the number of routes by grouping the number of shelves with orders adjacent to each other.

 For example:

 ```
 1 2 3 4 5 6 7 8
 ---------------
 1 | 0 0 0 0 0 0 0 1
 2 | 0 0 0 1 0 0 0 1
 3 | 0 0 1 0 1 0 0 0
 4 | 0 1 1 1 1 0 0 0
 5 | 0 0 0 1 0 0 0 0
 ```

 Returns:

 Route1 {(8,1) (8,2)}
 Route2 {(5,2) (4,3) (2,4) (3,4) (4,4) (5,4) (5,5)}
 */


public class WarehouseRouteOptimizer {
    
    static class Point {
        int row, col;
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public String toString() {
            return "(" + (col + 1) + "," + (row + 1) + ")";
        }
    }
    
    public static List<List<Point>> findOptimalRoutes(int[][] warehouse) {
        List<List<Point>> routes = new ArrayList<>();
        boolean[][] visited = new boolean[warehouse.length][warehouse[0].length];
        
        for(int i = 0; i < warehouse.length; i++) {
            for(int j = 0; j < warehouse[0].length; j++) {
                if(warehouse[i][j] == 1 && !visited[i][j]) {
                    List<Point> currentRoute = new ArrayList<>();
                    dfs(warehouse, i, j, visited, currentRoute);
                    if(!currentRoute.isEmpty()) {
                        routes.add(currentRoute);
                    }
                }
            }
        }
        
        return routes;
    }
    
    private static void dfs(int[][] warehouse, int row, int col, boolean[][] visited, List<Point> currentRoute) {
        if(row < 0 || row >= warehouse.length || col < 0 || col >= warehouse[0].length 
           || visited[row][col] || warehouse[row][col] != 1) {
            return;
        }
        
        visited[row][col] = true;
        currentRoute.add(new Point(row, col));
        
        // Check all 8 adjacent cells
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) continue;
                dfs(warehouse, row + i, col + j, visited, currentRoute);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Given example
        int[][] warehouse1 = {
            {0,0,0,0,0,0,0,1},
            {0,0,0,0,1,0,0,1},
            {0,0,0,1,0,0,0,0},
            {0,1,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,0}
        };
        
        testCase(warehouse1, "Test Case 1 (Given Example)");
        
        // Test Case 2: Empty warehouse
        int[][] warehouse2 = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        
        testCase(warehouse2, "Test Case 2 (Empty Warehouse)");
        
        // Test Case 3: Large warehouse (20x20)
        int[][] warehouse3 = new int[20][20];
        // Add some scattered 1's
        warehouse3[0][0] = 1;
        warehouse3[0][1] = 1;
        warehouse3[19][19] = 1;
        warehouse3[18][18] = 1;
        
        testCase(warehouse3, "Test Case 3 (Large Warehouse)");
        
        // Test Case 4: Single cell
        int[][] warehouse4 = {{1}};
        
        testCase(warehouse4, "Test Case 4 (Single Cell)");
    }
    
    private static void testCase(int[][] warehouse, String testName) {
        System.out.println("\nExecuting " + testName);
        System.out.println("Warehouse dimensions: " + warehouse.length + "x" + warehouse[0].length);
        
        long startTime = System.currentTimeMillis();
        List<List<Point>> routes = findOptimalRoutes(warehouse);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Number of routes found: " + routes.size());
        for(int i = 0; i < routes.size(); i++) {
            System.out.println("Route" + (i+1) + " " + routes.get(i));
        }
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        
        // Validation
        boolean valid = validateRoutes(warehouse, routes);
        System.out.println("Test Result: " + (valid ? "PASS" : "FAIL"));
    }
    
    private static boolean validateRoutes(int[][] warehouse, List<List<Point>> routes) {
        boolean[][] covered = new boolean[warehouse.length][warehouse[0].length];
        
        // Mark all points in routes
        for(List<Point> route : routes) {
            for(Point p : route) {
                if(covered[p.row][p.col]) return false; // Point already covered
                if(warehouse[p.row][p.col] != 1) return false; // Invalid point
                covered[p.row][p.col] = true;
            }
        }
        
        // Check if all 1's are covered
        for(int i = 0; i < warehouse.length; i++) {
            for(int j = 0; j < warehouse[0].length; j++) {
                if(warehouse[i][j] == 1 && !covered[i][j]) return false;
            }
        }
        return true;
    }
}
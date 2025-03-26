package com.interview.notes.code.year.y2025.march.amazon.test9;

import java.util.stream.IntStream;

public class NumberOfIslands {

    private static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }

        return islands;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int rows = grid.length, cols = grid[0].length;
        
        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') return;

        grid[i][j] = '0';

        IntStream.of(-1, 1).forEach(offset -> {
            dfs(grid, i + offset, j);
            dfs(grid, i, j + offset);
        });
    }

    private static char[][] cloneGrid(char[][] grid) {
        return IntStream.range(0, grid.length)
                        .mapToObj(i -> grid[i].clone())
                        .toArray(char[][]::new);
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        Object[][] testCases = new Object[][] {
            {new char[][] {{'1','1','0','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','1'}, {'0','0','0','1','0'}}, 3},
            {new char[][] {{'0','0','1'}}, 1},
            {new char[][] {{'1','0','1'}}, 2},
            {new char[][] {{'0','0','0'}}, 0},
            {new char[][] {}, 0},
            {IntStream.range(0, 500).mapToObj(i -> IntStream.range(0, 500).mapToObj(j -> '1').map(String::valueOf).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().toCharArray()).toArray(char[][]::new), 1}
        };

        int testNo = 1;
        for (Object[] test : testCases) {
            char[][] grid = cloneGrid((char[][])test[0]);
            int expected = (int)test[1];
            int actual = numIslands(grid);
            
            System.out.println("Test Case #" + testNo + ": " + 
                (actual == expected ? "PASS ✅" : "FAIL ❌ (Expected " + expected + ", Got " + actual + ")"));
            testNo++;
        }
    }
}

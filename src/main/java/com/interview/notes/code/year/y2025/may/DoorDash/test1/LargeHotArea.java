package com.interview.notes.code.year.y2025.may.DoorDash.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LargeHotArea {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 1}
        };

        System.out.println(findLargestHotArea(grid));
    }

    public static int findLargestHotArea(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int[][] componentId = new int[n][n];
        Map<Integer, Integer> componentSizes = new HashMap<>();
        int componentCount = 0;

        // Step 1: Find all existing connected components
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && componentId[i][j] == 0) {
                    componentCount++;
                    int size = dfs(grid, componentId, i, j, componentCount);
                    componentSizes.put(componentCount, size);
                }
            }
        }

        // If no components exist, changing one 0 to 1 creates a component of size 1
        int maxSize = componentSizes.values().stream()
                .max(Integer::compareTo)
                .orElse(0);

        // Step 2: For each 0, calculate the potential size if we change it to 1
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // Use Stream API to find unique adjacent components
                    int finalJ = j;
                    int finalI = i;
                    Set<Integer> adjacentComponents = Arrays.stream(directions)
                            .map(dir -> new int[]{finalI + dir[0], finalJ + dir[1]})
                            .filter(pos -> isValid(pos[0], pos[1], n))
                            .map(pos -> componentId[pos[0]][pos[1]])
                            .filter(id -> id > 0)
                            .collect(Collectors.toSet());

                    // Calculate total size if we change this 0 to 1
                    int potentialSize = 1 + adjacentComponents.stream()
                            .mapToInt(componentSizes::get)
                            .sum();

                    maxSize = Math.max(maxSize, potentialSize);
                }
            }
        }

        // Special case: if the entire grid is already 1s
        if (maxSize == 0) {
            maxSize = 1;
        }

        return maxSize;
    }

    private static int dfs(int[][] grid, int[][] componentId, int i, int j, int id) {
        if (!isValid(i, j, grid.length) || grid[i][j] == 0 || componentId[i][j] != 0) {
            return 0;
        }

        componentId[i][j] = id;
        int size = 1;

        // Explore all 4 directions
        size += dfs(grid, componentId, i - 1, j, id);
        size += dfs(grid, componentId, i + 1, j, id);
        size += dfs(grid, componentId, i, j - 1, id);
        size += dfs(grid, componentId, i, j + 1, id);

        return size;
    }

    private static boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}
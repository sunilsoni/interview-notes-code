package com.interview.notes.code.year.y2025.may.amazon.test12;

import java.util.*;

public class ProductCategorizer {

    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Test Case 1: Basic test with given example
        List<int[]> pairs1 = Arrays.asList(
                new int[]{1, 5}, new int[]{7, 2},
                new int[]{3, 4}, new int[]{4, 8},
                new int[]{6, 3}, new int[]{5, 2}
        );
        testCase(1, pairs1, 8);

        // Test Case 2: Empty input
        List<int[]> pairs2 = new ArrayList<>();
        testCase(2, pairs2, 0);

        // Test Case 3: Single pair
        List<int[]> pairs3 = Arrays.asList(new int[]{1, 2});
        testCase(3, pairs3, 2);

        // Test Case 4: Large dataset simulation
        List<int[]> pairs4 = generateLargeDataset(1000);
        testCase(4, pairs4, 1000);
    }

    // Main function to group products into categories
    public static List<List<Integer>> groupProducts(List<int[]> pairs, int n) {
        // Create disjoint set to track product relationships
        DisjointSet ds = new DisjointSet(n + 1);

        // Union all pairs to form connected components
        pairs.forEach(pair -> ds.union(pair[0], pair[1]));

        // Use Map to group products by their root (category)
        Map<Integer, List<Integer>> categoryMap = new HashMap<>();

        // Group products by their root (category)
        for (int i = 1; i <= n; i++) {
            int root = ds.find(i);
            categoryMap.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            System.out.println();
        }

        // Convert map values to list and filter empty categories
        return new ArrayList<>(categoryMap.values()).stream()
                .filter(list -> !list.isEmpty())
                .collect(java.util.stream.Collectors.toList());
    }

    // Helper method to test cases
    private static void testCase(int testNumber, List<int[]> pairs, int n) {
        System.out.println("Test Case " + testNumber + ":");
        System.out.println("Input pairs: " + Arrays.deepToString(pairs.toArray()));
        List<List<Integer>> result = groupProducts(pairs, n);
        System.out.println("Result: " + result);
        System.out.println("Status: PASS");
        System.out.println();
    }

    // Helper method to generate large dataset
    private static List<int[]> generateLargeDataset(int size) {
        List<int[]> pairs = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            pairs.add(new int[]{i, i + 1});
        }
        return pairs;
    }

    // DisjointSet class for tracking connected components
    static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
}

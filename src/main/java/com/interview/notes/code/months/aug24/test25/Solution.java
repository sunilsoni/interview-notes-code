package com.interview.notes.code.months.aug24.test25;

class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}}; // right and down

    public int solution(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        UnionFind uf = new UnionFind(rows * cols);
        int maxSize = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int[] dir : DIRECTIONS) {
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni < rows && nj < cols && Math.abs(A[i][j] - A[ni][nj]) <= 1) {
                        int size = uf.union(i * cols + j, ni * cols + nj);
                        maxSize = Math.max(maxSize, size);
                    }
                }
            }
        }

        return maxSize;
    }

    private static class UnionFind {
        private int[] parent;
        private int[] size;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        int union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (size[rootX] < size[rootY]) {
                    int temp = rootX;
                    rootX = rootY;
                    rootY = temp;
                }
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            return size[rootX];
        }
    }

    // Main method for testing
    public static void main(String[] args) {
         Solution solution = new Solution();

        // Test case 1
        int[][] A1 = {{3, 4, 6}, {2, 7, 6}};
        System.out.println("Test case 1 result: " + solution.solution(A1));

        // Test case 2
        int[][] A2 = {
                {3, 3, 5, 6},
                {6, 7, 2, 2},
                {5, 2, 3, 8},
                {5, 9, 2, 3},
                {1, 2, 3, 4}
        };
        System.out.println("Test case 2 result: " + solution.solution(A2));
        //Test case 2 result: 10 but expected is 8

        // Test case 3
        int[][] A3 = {{4, 4, 2, 4, 4, 4}};
        System.out.println("Test case 3 result: " + solution.solution(A3));

        // Test case 4
        int[][] A4 = {{0}, {3}, {5}};
        System.out.println("Test case 4 result: " + solution.solution(A4));
    }
}
package com.interview.notes.code.year.y2025.july.amazon.test1;

import java.util.*;
import java.util.stream.*;

public class BitwiseAndSort {

    /**
     * Finds the maximum k for which array can be sorted using only allowed swaps.
     */
    public static int getSortingFactorK(List<Integer> arr) {
        int n = arr.size();
        // If array already sorted, answer is 0
        boolean alreadySorted = true;
        for (int i = 0; i < n; i++) {
            if (arr.get(i) != i) {
                alreadySorted = false;
                break;
            }
        }
        if (alreadySorted) return 0;

        // Try all possible k from high to low (from n-1 down to 0)
        // As k increases, swap is more restrictive. Try highest possible first.

        // Find max bit needed to represent n-1
        int maxBit = 0;
        int maxVal = n - 1;
        while ((1 << maxBit) <= maxVal) maxBit++;
        int maxPossibleK = (1 << maxBit) - 1;

        // Try k from maxPossibleK down to 0
        for (int k = maxPossibleK; k >= 0; k--) {
            if (canSort(arr, k)) {
                return k;
            }
        }
        // At least 0 will always work.
        return 0;
    }

    // Checks if we can sort arr using swaps where (arr[i] & arr[j]) == k
    private static boolean canSort(List<Integer> arr, int k) {
        int n = arr.size();
        // Build groups using Union-Find (DSU)
        UnionFind uf = new UnionFind(n);
        // Only connect nodes (indices) where arr[i] & arr[j] == k
        // Since permutation, just connect arr[i] with arr[j] if condition met
        // To speed up: for each value, precompute which values have arr[i] & arr[j] == k
        // But for n=1e5, we can use map from value to index for quick lookup
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) valueToIndex.put(arr.get(i), i);

        for (int i = 0; i < n; i++) {
            int x = arr.get(i);
            // Try all y in arr where x & y == k
            // Since all values 0..n-1, only those y where x & y == k
            // So for x, enumerate all y where x & y == k and y exists in arr
            // Actually, for each x, for all j>i, check if x & arr[j] == k
            for (int j = i + 1; j < n; j++) {
                int y = arr.get(j);
                if ((x & y) == k) {
                    uf.union(i, j);
                }
            }
        }

        // For each group (component), all values can be rearranged within group
        // For each index i, arr[i] must be able to reach its sorted position i
        // That is, index i and index of value i must be in the same group
        for (int i = 0; i < n; i++) {
            int posOfI = valueToIndex.get(i);
            if (uf.find(i) != uf.find(posOfI)) {
                return false;
            }
        }
        return true;
    }

    // Simple Union Find / Disjoint Set Union structure
    static class UnionFind {
        int[] parent;
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px != py) parent[px] = py;
        }
    }

    // ----------- TESTING -----------

    public static void main(String[] args) {
        // Test cases as described
        test(Arrays.asList(3, 0, 2, 1), 0, "Sample Case 0");
        test(Arrays.asList(0, 1, 3, 2, 4), 2, "Sample Case 1");
        test(Arrays.asList(0, 1, 2, 3, 4, 5), 0, "Already Sorted");

        // Edge case: two elements reversed
        test(Arrays.asList(1, 0), 0, "Two Elements, Reversed");

        // Edge case: max n = 1
        test(Arrays.asList(0), 0, "Single Element");

        // Large random permutation, answer should be 0 (all bits different)
        int n = 100000;
        List<Integer> largeRandom = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(largeRandom, new Random(42));
        test(largeRandom, 0, "Large Random");

        // Custom: For [2, 0, 1, 3], only 2 & 0 == 0, etc., test result
        test(Arrays.asList(2, 0, 1, 3), 0, "Custom Case");

        // More complex pattern
        test(Arrays.asList(0, 2, 3, 1), 2, "Another Case");

        // All bits set
        test(Arrays.asList(7, 6, 5, 4, 3, 2, 1, 0), 0, "All Descending");

        // Test all outputs and time for large input
    }

    // Helper test method
    static void test(List<Integer> arr, int expected, String caseName) {
        long start = System.currentTimeMillis();
        int res = getSortingFactorK(arr);
        long time = System.currentTimeMillis() - start;
        String status = (res == expected) ? "PASS" : "FAIL";
        System.out.printf("%s: got=%d, exp=%d [%s] (time: %dms)%n", caseName, res, expected, status, time);
    }
}

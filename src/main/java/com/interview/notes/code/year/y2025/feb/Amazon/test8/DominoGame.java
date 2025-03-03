package com.interview.notes.code.year.y2025.feb.Amazon.test8;

import java.util.*;

public class DominoGame {

    /**
     * Returns the maximum number of moves (removals) that can be made
     * such that the remaining dominoes have an order (LIS) >= min_order.
     *
     * @param domino    List of domino sizes.
     * @param remove    List of indices representing the removal order.
     * @param min_order The minimum required length of the longest increasing subsequence.
     * @return Maximum number of safe moves.
     */
    public static int getMaxPoints(List<Integer> domino, List<Integer> remove, int min_order) {
        int n = domino.size();
        int left = 0, right = n;
        int ans = 0;

        // Binary search on the number of removals
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean[] removed = new boolean[n];
            // Mark the first 'mid' dominoes in the removal order as removed
            for (int i = 0; i < mid; i++) {
                removed[remove.get(i)] = true;
            }
            // Compute the LIS of the remaining dominoes
            int lis = computeLIS(domino, removed);
            if (lis >= min_order) {
                ans = mid;      // This many removals are safe
                left = mid + 1; // Try for more removals
            } else {
                right = mid - 1; // Too many removals; reduce
            }
        }
        return ans;
    }

    /**
     * Computes the length of the longest strictly increasing subsequence (LIS)
     * in the domino list while ignoring removed indices.
     *
     * @param domino  List of domino sizes.
     * @param removed Boolean array indicating which indices are removed.
     * @return The length of the longest increasing subsequence.
     */
    private static int computeLIS(List<Integer> domino, boolean[] removed) {
        ArrayList<Integer> lis = new ArrayList<>();
        for (int i = 0; i < domino.size(); i++) {
            if (removed[i]) continue;
            int num = domino.get(i);
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) pos = -(pos + 1);
            if (pos == lis.size()) {
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
        }
        return lis.size();
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Test Case 0
        // domino = [1, 2, 3, 4], remove = [3, 2, 1, 0], min_order = 2
        // Expected output: 2 (after two removals, the sequence [1,2] maintains an LIS of 2)
        List<Integer> domino0 = Arrays.asList(1, 2, 3, 4);
        List<Integer> remove0 = Arrays.asList(3, 2, 1, 0);
        int min_order0 = 2;
        int result0 = getMaxPoints(domino0, remove0, min_order0);
        System.out.println("Test Case 0 Expected: 2, Got: " + result0);

        // Test Case 1
        // domino = [4, 5, 58, 5, 4], remove = [1, 0, 2, 3, 4], min_order = 1
        // Expected output: 4 (even with four removals, one element remains which is enough for order 1)
        List<Integer> domino1 = Arrays.asList(4, 5, 58, 5, 4);
        List<Integer> remove1 = Arrays.asList(1, 0, 2, 3, 4);
        int min_order1 = 1;
        int result1 = getMaxPoints(domino1, remove1, min_order1);
        System.out.println("Test Case 1 Expected: 4, Got: " + result1);

        // Test Case 2
        // domino = [1, 4, 4, 2, 5, 3], remove = [2, 1, 4, 0, 5, 3], min_order = 3
        // Expected output: 3 (after three removals, the remaining dominoes still yield an LIS >= 3)
        List<Integer> domino2 = Arrays.asList(1, 4, 4, 2, 5, 3);
        List<Integer> remove2 = Arrays.asList(2, 1, 4, 0, 5, 3);
        int min_order2 = 3;
        int result2 = getMaxPoints(domino2, remove2, min_order2);
        System.out.println("Test Case 2 Expected: 3, Got: " + result2);

        // Edge Test Case: When even the first removal reduces the order below min_order.
        // Example: domino = [3, 1, 2], remove = [0, 1, 2], min_order = 2
        // Full domino sequence has an LIS [1,2] of length 2, but any removal might drop the order.
        List<Integer> domino3 = Arrays.asList(3, 1, 2);
        List<Integer> remove3 = Arrays.asList(0, 1, 2);
        int min_order3 = 2;
        int result3 = getMaxPoints(domino3, remove3, min_order3);
        System.out.println("Test Case 3 Expected: 0, Got: " + result3);

        // Large Input Test Case: To handle large data inputs
        int n = 100000;
        Random rand = new Random();
        List<Integer> dominoLarge = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dominoLarge.add(rand.nextInt(1000000) + 1);
        }
        List<Integer> removeLarge = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            removeLarge.add(i);
        }
        Collections.shuffle(removeLarge, rand);
        int min_orderLarge = 50; // an arbitrary minimum order requirement
        long startTime = System.currentTimeMillis();
        int resultLarge = getMaxPoints(dominoLarge, removeLarge, min_orderLarge);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Test Case Result: " + resultLarge +
                " (computed in " + (endTime - startTime) + "ms)");
    }
}

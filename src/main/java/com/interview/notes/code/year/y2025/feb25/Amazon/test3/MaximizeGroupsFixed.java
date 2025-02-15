package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.*;

/*
10/15 Not working
 */
public class MaximizeGroupsFixed {

    public static int maximizeGroups(List<Integer> products) {
        // 1) Basic stats
        long sum = 0L;
        for (int p : products) {
            sum += p;
        }
        int n = products.size();

        // 2) Based on total items, x(x+1)/2 <= sum => x up to about sqrt(2*sum).
        long maxFromSum = (long) Math.floor(Math.sqrt(2.0 * sum)) + 2;

        // Also x cannot exceed the number of distinct product types n
        long upperBound = Math.min(n, maxFromSum);

        // Convert products to long array
        long[] counts = new long[n];
        for (int i = 0; i < n; i++) {
            counts[i] = products.get(i);
        }

        // 3) Binary search in [0 .. upperBound] using sum-of-mins check (necessary condition)
        long left = 0, right = upperBound, answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (canFormBatchesNecessary(counts, mid)) {
                answer = mid; // mid is at least *potentially* feasible
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 'answer' is the largest x that passes the sum-of-mins check
        // But that check can overestimate. We do a final downward check:
        // We'll try x = answer, then x = answer-1, etc., verifying via direct simulation 
        // until we find a truly feasible x or 0.
        int finalAnswer = (int) answer;
        while (finalAnswer > 0) {
            if (canReallyFormBatches(products, finalAnswer)) {
                break; // success: we can form finalAnswer batches
            }
            finalAnswer--;
        }

        return finalAnswer;
    }

    /*
     * NECESSARY check (sum-of-mins):
     * "We have enough 'type slots' to fill the total items across x batches"
     * but not always sufficient if distribution is tricky.
     */
    private static boolean canFormBatchesNecessary(long[] counts, long x) {
        if (x == 0) return true;  // 0 batches always possible
        long needed = x * (x + 1) / 2; // total distinct items needed
        long sumMins = 0;
        for (long c : counts) {
            sumMins += Math.min(c, x);
            if (sumMins >= needed) {
                return true;
            }
        }
        return (sumMins >= needed);
    }

    /*
     * SUFFICIENT check:
     * Try to form batches 1..x in ascending order, each with distinct product types.
     * We'll use a greedy approach with a max-heap:
     *   - Batch #1 => pick 1 distinct type
     *   - Batch #2 => pick 2 distinct types
     *   - ...
     *   - Batch #x => pick x distinct types
     */
    private static boolean canReallyFormBatches(List<Integer> products, int x) {
        // Copy to a max-heap so we don't destroy the original data
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(products);

        for (int batchNum = 1; batchNum <= x; batchNum++) {
            int need = batchNum;  // we need 'need' distinct items
            if (need > maxHeap.size()) {
                // Not enough distinct product types left at all
                return false;
            }
            List<Integer> used = new ArrayList<>();
            // Extract 'need' types that still have at least 1 item
            for (int i = 0; i < need; i++) {
                if (maxHeap.isEmpty() || maxHeap.peek() == 0) {
                    return false;
                }
                int top = maxHeap.poll();
                used.add(top);
            }
            // Decrement each used count and push back if still > 0
            for (int c : used) {
                int newCount = c - 1;
                if (newCount > 0) {
                    maxHeap.offer(newCount);
                }
            }
        }

        return true; // if we formed all x batches, success
    }

    /*
     * Simple main() to test the tricky input as well as others.
     */
    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 7), 3, "Sample #0 => expected 3");
        test(Arrays.asList(1, 2, 8, 9), 4, "Sample #1 => expected 4");

        // Tricky distribution
        // The sum-of-mins check may incorrectly say 7 is possible, 
        // but the real max is 6
        test(Arrays.asList(1, 1, 8, 11, 11, 12, 12), 6, "Tricky distribution => expected 6");

        // A few extras:
        test(Arrays.asList(0, 0, 0), 0, "All zero => expected 0");
        test(Arrays.asList(10_000_000), 1, "Single large type => expected 1");
        test(Arrays.asList(5, 5, 5), 3, "Three product types => expected 3");
        test(Arrays.asList(0, 1, 1, 1, 1), 2, "One zero, four ones => expected 2");
    }

    private static void test(List<Integer> products, int expected, String label) {
        int result = maximizeGroups(products);
        System.out.println("Test: " + label);
        System.out.println("Products: " + products);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println("----------------------------------");
    }
}

package com.interview.notes.code.year.y2025.feb.Amazon.test3;

import java.util.*;

public class MaxBatchesDirect {

    public static int maximizeGroups(List<Integer> products) {
        // Sort descending so we can pick largest leftover counts first
        // (This helps us avoid running out of distinct items prematurely.)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(products);

        int batch = 0;

        // We'll form consecutive batches: 1, 2, 3, ...
        while (true) {
            int need = batch + 1; // how many distinct types we need for the next batch
            if (need > maxHeap.size()) {
                // If we have fewer distinct product types total than 'need', we can't form the next batch
                break;
            }

            // We will pick 'need' items from the top leftover counts
            List<Integer> used = new ArrayList<>();
            for (int i = 0; i < need; i++) {
                // If the top is zero or the heap is too small, we fail
                if (maxHeap.isEmpty() || maxHeap.peek() <= 0) {
                    return batch;
                }
                int top = maxHeap.poll();
                used.add(top);
            }

            // We successfully formed the (batch+1)-th batch
            batch++;

            // Decrement each used count by 1 and push back if still > 0
            for (int cnt : used) {
                int newCount = cnt - 1;
                if (newCount > 0) {
                    maxHeap.offer(newCount);
                }
            }
        }

        return batch;
    }

    // Quick test
    public static void main(String[] args) {
        List<Integer> test = Arrays.asList(1, 1, 8, 11, 11, 12, 12);
        int result = maximizeGroups(test);
        System.out.println("Products: " + test);
        System.out.println("Computed: " + result);
        // You should see 6 for this test case, matching the correct scheduling logic.
    }
}

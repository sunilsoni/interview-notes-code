package com.interview.notes.code.year.y2025.july.common.test3;

public class NStableSubarrayOptimized {
    public static void main(String[] args) {
        int[] a = {4,2,3,6,2,2,3,2,7};
        System.out.println(longestNStableSubarray(a, 1)); // 4
    }

    /**
     * Returns length of longest subarray where max–min ≤ N.
     * Uses two custom monotonic queues (primitive arrays) for min/max.
     */
    public static int longestNStableSubarray(int[] arr, int N) {
        int n = arr.length;
        // Monotonic queues for indices, storing values in decreasing/increasing order
        MonoQueue maxQ = new MonoQueue(n);
        MonoQueue minQ = new MonoQueue(n);
        int left = 0, best = 0;

        for (int right = 0; right < n; right++) {
            // Push current index into both queues
            maxQ.push(arr[right], right, /*isMax=*/true);
            minQ.push(arr[right], right, /*isMax=*/false);

            // Shrink from left while window violates max–min ≤ N
            while (maxQ.firstValue() - minQ.firstValue() > N) {
                // If left index is exiting window, pop from queues
                maxQ.popIfIndex(left);
                minQ.popIfIndex(left);
                left++;
            }

            // Update best length seen
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    /**
     * A fixed-capacity monotonic queue of (value,index) pairs.
     * If isMax==true, maintains values in DECREASING order;
     * else in INCREASING order.
     */
    static class MonoQueue {
        private final int[] vals, idxs;
        private int head = 0, tail = 0;

        MonoQueue(int capacity) {
            vals = new int[capacity];
            idxs = new int[capacity];
        }

        /**
         * Push a new (value, index) pair.
         * For max-queue we drop from tail while vals[tail] < value.
         * For min-queue we drop while vals[tail] > value.
         */
        void push(int value, int index, boolean isMax) {
            if (isMax) {
                while (head < tail && vals[(tail-1)] < value) tail--;
            } else {
                while (head < tail && vals[(tail-1)] > value) tail--;
            }
            vals[tail] = value;
            idxs[tail] = index;
            tail++;
        }

        /** Return the value at head (current window’s max or min). */
        int firstValue() {
            return vals[head];
        }

        /**
         * If the index at head has slid out of the window (equals `i`),
         * pop it. Otherwise do nothing.
         */
        void popIfIndex(int i) {
            if (head < tail && idxs[head] == i) {
                head++;
            }
        }
    }
}
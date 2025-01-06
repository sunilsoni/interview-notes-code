package com.interview.notes.code.year.y2024.dec24.amazon.test13;

import java.util.*;

public class MaxSumArrSolution {

    public static int getMaxSumarr(List<Integer> item_weights) {
        int n = item_weights.size();
        int m = n / 3;
        int[] arr = item_weights.stream().mapToInt(i -> i).toArray();

        // prefix_sums_max: max sum of m elements from first part
        long[] prefix_sums_max = new long[n + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long currentSum = 0;

        for (int i = 0; i < m; i++) {
            currentSum += arr[i];
            minHeap.offer(arr[i]);
        }
        prefix_sums_max[m] = currentSum;

        for (int i = m; i < 2 * m; i++) {
            currentSum += arr[i];
            minHeap.offer(arr[i]);
            currentSum -= minHeap.poll();
            prefix_sums_max[i + 1] = currentSum;
        }

        // suffix_sums_min: min sum of m elements from the end part
        long[] suffix_sums_min = new long[n + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        currentSum = 0;

        for (int i = n - 1; i >= 2 * m; i--) {
            currentSum += arr[i];
            maxHeap.offer(arr[i]);
        }
        suffix_sums_min[2 * m] = currentSum;

        for (int i = 2 * m - 1; i >= m; i--) {
            currentSum += arr[i];
            maxHeap.offer(arr[i]);
            currentSum -= maxHeap.poll();
            suffix_sums_min[i] = currentSum;
        }

        long result = Long.MIN_VALUE;
        for (int i = m; i <= 2 * m; i++) {
            long candidate = prefix_sums_max[i] - suffix_sums_min[i];
            if (candidate > result) {
                result = candidate;
            }
        }

        return (int) result;
    }

    // Updated test method to handle input format [n, ... n items ...]
    private static void test(int[] input, int expected) {
        int n = input[0];
        int[] items = Arrays.copyOfRange(input, 1, 1 + n);
        List<Integer> list = new ArrayList<>();
        for (int i : items) list.add(i);

        int result = getMaxSumarr(list);
        System.out.println("Input: " + Arrays.toString(input) +
                " | Expected: " + expected +
                " | Got: " + result +
                " | " + (result == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[]{6, 1, 3, 4, 7, 5, 2}, 4);  // Should PASS now
        //test(new int[]{3, -3, -2, -1}, -1);       // Should PASS now
    }
}

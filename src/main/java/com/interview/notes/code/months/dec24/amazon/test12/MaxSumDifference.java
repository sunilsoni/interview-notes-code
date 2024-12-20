package com.interview.notes.code.months.dec24.amazon.test12;

import java.util.*;

public class MaxSumDifference {

    public static int getMaxSumarr(List<Integer> item_weights) {
     /*   int n = item_weights.size();
        int m = n / 3;
        int[] arr = item_weights.stream().mapToInt(i -> i).toArray();*/

        // Write your code here
        System.out.println("item_weights= " + item_weights);

        int n = item_weights.size();
        int m = n / 3;
        System.out.println("n= " + n);
        System.out.println("m= " + m);
        int[] arr = item_weights.stream().mapToInt(i -> i).toArray();
        System.out.println("arr: " + Arrays.toString(arr));

        // Array to store the maximum prefix sum of m elements
        long[] max = new long[n + 1];
        long csum = 0;


        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();

        // Calculate prefix sums max
        for (int i = 0; i < m; i++) {
            csum += arr[i];
            minPriorityQueue.offer(arr[i]);
        }
        max[m] = csum;

        for (int i = m; i < 2 * m; i++) {
            csum += arr[i];
            minPriorityQueue.offer(arr[i]);
            csum -= minPriorityQueue.poll(); // Remove the smallest element
            max[i + 1] = csum;
        }
        System.out.println("max: " + Arrays.toString(max));
        // Array to store the minimum suffix sum of m elements
        long[] min = new long[n + 1];
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        csum = 0;

        // Calculate suffix sums min
        for (int i = n - 1; i >= 2 * m; i--) {
            csum += arr[i];
            maxPriorityQueue.offer(arr[i]);
        }
        min[2 * m] = csum;

        for (int i = 2 * m - 1; i >= m; i--) {
            csum += arr[i];
            maxPriorityQueue.offer(arr[i]);
            csum -= maxPriorityQueue.poll(); // Remove the largest element
            min[i] = csum;
        }
        System.out.println("min: " + Arrays.toString(min));
        // Find the maximum difference between prefix_sums_max and suffix_sums_min
        long result = Long.MIN_VALUE;
        for (int i = m; i <= 2 * m; i++) {
            long c = max[i] - min[i];
            if (c > result) {
                result = c;
            }
        }

        return (int) result;
    }

    // Simple testing in main
    public static void main(String[] args) {
        test(new int[]{1, 3, 4, 7, 5, 2}, 4); // from the example
        //  test(new int[]{3,2,1}, 2); // from given smaller example
        //  test(new int[]{-3,-2,-1}, -1); // negative case
        // test(new int[]{1,3,4,7,5,2}, 4); // n
        //   test(new int[]{-3,-2,-1}, -1); // n
        // Add more tests as needed
    }

    private static void test(int[] input, int expected) {
        List<Integer> list = new ArrayList<>();
        for (int i : input) list.add(i);
        int result = getMaxSumarr(list);
        System.out.println("Input: " + Arrays.toString(input) +
                " | Expected: " + expected +
                " | Got: " + result +
                " | " + (result == expected ? "PASS" : "FAIL"));
    }
}

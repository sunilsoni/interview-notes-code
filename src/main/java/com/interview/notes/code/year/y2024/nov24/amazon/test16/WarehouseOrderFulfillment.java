package com.interview.notes.code.year.y2024.nov24.amazon.test16;

import java.util.*;

public class WarehouseOrderFulfillment {

    public static List<Long> getSmallerItems(List<Integer> items, List<Integer> start, List<Integer> end, List<Integer> query) {
        int n = items.size();
        int[] prefixSum = new int[n + 1];

        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + items.get(i);
        }

        // Process orders and build frequency array
        int maxValue = Collections.max(items);
        int[] freq = new int[maxValue + 1];
        for (int i = 0; i < start.size(); i++) {
            int s = start.get(i);
            int e = end.get(i);
            for (int j = s; j <= e; j++) {
                freq[items.get(j)]++;
            }
        }

        // Build cumulative frequency array
        int[] cumulativeFreq = new int[maxValue + 1];
        cumulativeFreq[0] = freq[0];
        for (int i = 1; i <= maxValue; i++) {
            cumulativeFreq[i] = cumulativeFreq[i - 1] + freq[i];
        }

        // Process queries
        List<Long> result = new ArrayList<>();
        for (int q : query) {
            if (q > maxValue) {
                result.add((long) cumulativeFreq[maxValue]);
            } else {
                result.add((long) cumulativeFreq[q - 1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> items1 = Arrays.asList(4, 4, 5, 3, 2);
        List<Integer> start1 = Arrays.asList(0, 1, 0, 2);
        List<Integer> end1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> query1 = Arrays.asList(5, 4, 1);
        List<Long> expected1 = Arrays.asList(8L, 3L, 0L);
        List<Long> result1 = getSmallerItems(items1, start1, end1, query1);
        System.out.println("Test case 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test case 2
        List<Integer> items2 = Arrays.asList(1, 2, 3, 2, 4, 1);
        List<Integer> start2 = Arrays.asList(2, 0);
        List<Integer> end2 = Arrays.asList(4, 0);
        List<Integer> query2 = Arrays.asList(5, 3);
        List<Long> expected2 = Arrays.asList(4L, 2L);
        List<Long> result2 = getSmallerItems(items2, start2, end2, query2);
        System.out.println("Test case 2: " + (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Test case 3 (Large input)
        List<Integer> items3 = new ArrayList<>();
        List<Integer> start3 = new ArrayList<>();
        List<Integer> end3 = new ArrayList<>();
        List<Integer> query3 = new ArrayList<>();
        Random rand = new Random(42);
        int n = 100000;
        int m = 100000;
        int q = 100000;
        for (int i = 0; i < n; i++) {
            items3.add(rand.nextInt(1000000000) + 1);
        }
        for (int i = 0; i < m; i++) {
            int s = rand.nextInt(n);
            int e = rand.nextInt(n - s) + s;
            start3.add(s);
            end3.add(e);
        }
        for (int i = 0; i < q; i++) {
            query3.add(rand.nextInt(1000000000) + 1);
        }
        long startTime = System.currentTimeMillis();
        List<Long> result3 = getSmallerItems(items3, start3, end3, query3);
        long endTime = System.currentTimeMillis();
        System.out.println("Test case 3 (Large input): PASS");
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}

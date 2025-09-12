package com.interview.notes.code.year.y2025.september.Hackerank.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static long getMaximumScore(List<Integer> stockPrice) {
        int n = stockPrice.size();
        long ans = Long.MIN_VALUE;
        long currentSum = 0;
        long currentDiff = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                currentSum = stockPrice.get(i);
                ans = Math.max(ans, currentSum);
            } else {
                long diff = stockPrice.get(i) - stockPrice.get(i - 1);
                if (currentDiff == Long.MAX_VALUE || diff == currentDiff) {
                    currentSum += stockPrice.get(i);
                } else {
                    currentSum = stockPrice.get(i - 1) + stockPrice.get(i);
                }
                currentDiff = diff;
                ans = Math.max(ans, currentSum);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> stock1 = Arrays.asList(1, 2, 3);
        long expected1 = 6;
        long result1 = getMaximumScore(stock1);
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL") + " | Expected=" + expected1 + ", Got=" + result1);

        List<Integer> stock2 = Arrays.asList(3, 2, 1);
        long expected2 = 3;
        long result2 = getMaximumScore(stock2);
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL") + " | Expected=" + expected2 + ", Got=" + result2);

        List<Integer> stock3 = Arrays.asList(1, 5, 3, 7, 8);
        long expected3 = 20;
        long result3 = getMaximumScore(stock3);
        System.out.println("Test 3: " + (result3 == expected3 ? "PASS" : "FAIL") + " | Expected=" + expected3 + ", Got=" + result3);

        List<Integer> stock4 = List.of(10);
        long expected4 = 10;
        long result4 = getMaximumScore(stock4);
        System.out.println("Test 4: " + (result4 == expected4 ? "PASS" : "FAIL") + " | Expected=" + expected4 + ", Got=" + result4);

        List<Integer> largeStock = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
        long result5 = getMaximumScore(largeStock);
        System.out.println("Large Test Completed | Result=" + result5);
    }
}
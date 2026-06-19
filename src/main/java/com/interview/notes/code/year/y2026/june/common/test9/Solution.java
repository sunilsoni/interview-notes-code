package com.interview.notes.code.year.y2026.june.common.test9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static int calculateMaximumConsecutiveSum(int k, List<List<Integer>> segment) {
        List<Segment> normalSegs = new ArrayList<>();
        List<Segment> mirroredSegs = new ArrayList<>();

        for (List<Integer> list : segment) {
            long start = list.get(0);
            long end = list.get(1);
            long val = list.get(2);
            normalSegs.add(new Segment(start, end, val));
            mirroredSegs.add(new Segment(-end, -start, val));
        }

        normalSegs.sort(Comparator.comparingLong(Segment::start));
        mirroredSegs.sort(Comparator.comparingLong(Segment::start));

        long maxNormal = getMaxSum(normalSegs, k);
        long maxMirrored = getMaxSum(mirroredSegs, k);

        long overallMax = Math.max(maxNormal, maxMirrored);
        return (int) (overallMax % 1_000_000_007);
    }

    private static long getMaxSum(List<Segment> segs, long k) {
        int n = segs.size();
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            long bagsCount = segs.get(i).end() - segs.get(i).start() + 1;
            long totalMoney = bagsCount * segs.get(i).value();
            prefix[i + 1] = prefix[i] + totalMoney;
        }

        long maxSum = 0;

        for (int i = 0; i < n; i++) {
            long windowStart = segs.get(i).start();
            long windowEnd = windowStart + k - 1;

            int left = i;
            int right = n - 1;
            int bestJ = i;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (segs.get(mid).start() <= windowEnd) {
                    bestJ = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            long fullSegmentsSum = prefix[bestJ] - prefix[i];
            long overlapStart = segs.get(bestJ).start();
            long overlapEnd = Math.min(segs.get(bestJ).end(), windowEnd);
            long overlapMoney = (overlapEnd - overlapStart + 1) * segs.get(bestJ).value();

            long currentSum = fullSegmentsSum + overlapMoney;
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> seg1 = Arrays.asList(
            Arrays.asList(1, 9, 5),
            Arrays.asList(10, 20, 5)
        );
        int res1 = calculateMaximumConsecutiveSum(3, seg1);
        System.out.println("Test 1 | Expected: 15 | Actual: " + res1 + " | " + (res1 == 15 ? "PASS" : "FAIL"));

        List<List<Integer>> seg2 = Arrays.asList(
            Arrays.asList(1, 1, 10),
            Arrays.asList(2, 2, 20),
            Arrays.asList(3, 3, 30)
        );
        int res2 = calculateMaximumConsecutiveSum(1, seg2);
        System.out.println("Test 2 | Expected: 30 | Actual: " + res2 + " | " + (res2 == 30 ? "PASS" : "FAIL"));

        List<List<Integer>> seg3 = List.of(
                Arrays.asList(1, 1000000000, 1000000)
        );
        long hugeSum = 1000000000L * 1000000L;
        int expected3 = (int) (hugeSum % 1000000007);
        int res3 = calculateMaximumConsecutiveSum(1000000000, seg3);
        System.out.println("Test 3 | Expected: " + expected3 + " | Actual: " + res3 + " | " + (res3 == expected3 ? "PASS" : "FAIL"));
    }

    record Segment(long start, long end, long value) {}
}
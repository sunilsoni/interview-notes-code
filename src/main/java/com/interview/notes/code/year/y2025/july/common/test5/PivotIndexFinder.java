package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PivotIndexFinder {

    /**
     * Returns the leftmost pivot index where the sum of elements to the left
     * equals the sum of elements to the right. If none, returns -1.
     */
    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        AtomicInteger leftSum = new AtomicInteger(0);

        return IntStream.range(0, nums.length)
                .filter(i -> {
                    int ls = leftSum.get();
                    int rs = total - ls - nums[i];
                    boolean isPivot = (ls == rs);
                    leftSum.addAndGet(nums[i]);
                    return isPivot;
                })
                .findFirst()
                .orElse(-1);
    }
}
package com.interview.notes.code.months.aug24.amz.test5;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static int getMaxNegativePnL(List<Integer> PnL) {
        // Initialize variables to keep track of maximum negative and total profit
        int maxNegatives = 0;

        // Create a thread-safe atomic reference for last changed index
        AtomicReference<Integer> lastChangedIndexRef = new AtomicReference<>(Integer.MIN_VALUE);

        // Iterate over each element in the list (except the last one)
        for (int i = 1; i < PnL.size(); i++) {
            // Calculate cumulative sum up to current index
            long cumSum = getCumulativeSum(PnL, i);

            // If cumulative sum is negative and we can make it positive by changing sign of the next element,
            // increment maxNegatives count and update last changed index if necessary
            if (cumSum < 0 && PnL.get(i) > -cumSum) {
                maxNegatives++;

                // Update last changed index only when current element is greater than negative cumulative sum
                long lastIndex = getLastChangedIndex(lastChangedIndexRef, i);
                if (lastIndex == Integer.MIN_VALUE || Math.abs(lastIndex - i + 1) < Math.abs(i - lastIndex)) {
                    updateLastChangedIndex(lastChangedIndexRef, i);
                }
            } else {
                // Reset last changed index when cumulative sum is non-negative
                resetLastChangedIndex(lastChangedIndexRef, i);
            }
        }

        return maxNegatives;
    }

    private static long getCumulativeSum(List<Integer> PnL, int upTo) {
        long cumSum = 0;

        for (int j = 1; j <= upTo; j++) {
            cumSum += PnL.get(j);
        }

        return cumSum;
    }

    private static void updateLastChangedIndex(AtomicReference<Integer> lastChangedIndexRef, int index) {
        lastChangedIndexRef.set(index + 1); // Update the value of last changed index
    }

    private static void resetLastChangedIndex(AtomicReference<Integer> lastChangedIndexRef, int index) {
        lastChangedIndexRef.set(Integer.MIN_VALUE);
    }

    public static long getLastChangedIndex(AtomicReference<Integer> lastChangedIndexRef, int upTo) {
        return lastChangedIndexRef.get();
    }
}
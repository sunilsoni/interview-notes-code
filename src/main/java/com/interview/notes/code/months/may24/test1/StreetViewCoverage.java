package com.interview.notes.code.months.may24.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreetViewCoverage {
    public static void main(String[] args) {
        List<int[]> pictures = Arrays.asList(new int[]{1, 3}, new int[]{6, 7}, new int[]{2, 4});
        int[] block = {1, 6};
        boolean isCovered = isBlockCovered(pictures, block);
        System.out.println("Covered: " + isCovered);
    }

    public static boolean isBlockCovered(List<int[]> pictures, int[] block) {
        if (pictures.isEmpty()) return false;

        // Step 1: Sort the intervals by starting point, and by ending point if starting points are the same
        Collections.sort(pictures, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        // Step 2: Merge intervals
        List<int[]> merged = new ArrayList<>();
        int[] current = pictures.get(0);
        merged.add(current);
        for (int[] interval : pictures) {
            if (interval[0] <= current[1]) { // Overlapping or touching intervals
                current[1] = Math.max(current[1], interval[1]); // Merge intervals
            } else {
                current = interval; // No overlap
                merged.add(current);
            }
        }

        // Step 3: Check if the merged intervals cover the block [a, b]
        int start = block[0];
        int end = block[1];
        for (int[] interval : merged) {
            if (interval[0] <= start && interval[1] >= end) {
                return true; // Block is fully covered
            }
        }

        return false; // Block is not fully covered
    }
}

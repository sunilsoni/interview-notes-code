package com.interview.notes.code.year.y2024.jan24.test5;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        List<Integer> riceBags1 = Arrays.asList(625, 4, 2, 5, 25);
        System.out.println(maxSetSize(riceBags1)); // Example 1

        List<Integer> riceBags2 = Arrays.asList(7, 4, 8, 9);
        System.out.println(maxSetSize(riceBags2)); // Example 2
    }

    public static int maxSetSize1(List<Integer> riceBags) {
        Collections.sort(riceBags);
        int maxSetSize = 0;
        int setSize = 1;

        for (int i = 1; i < riceBags.size(); i++) {
            if (riceBags.get(i) % riceBags.get(i - 1) == 0) {
                setSize++;
                maxSetSize = Math.max(maxSetSize, setSize);
            } else {
                setSize = 1;
            }
        }

        return maxSetSize == 1 ? -1 : maxSetSize;
    }

    public static int maxSetSize(List<Integer> riceBags) {
        Set<Long> seen = new HashSet<>(); // Store squares of rice counts
        int maxSize = 0;

        for (int riceCount : riceBags) {
            long square = (long) riceCount * riceCount;

            // Handle cases with 1 rice grain separately to avoid potential overflow
            if (riceCount == 1) {
                maxSize++; // Count 1s separately
            } else if (seen.contains(square)) {
                int currentSize = 2; // Start with 2 (the square and its root)

                // Extend the set backward as long as possible
                while (seen.contains(square / riceCount)) {
                    currentSize++;
                    square /= riceCount;
                }

                maxSize = Math.max(maxSize, currentSize);
            } else {
                //seen.add(riceCount); // Add potential roots for future sets
            }
        }

        return maxSize == 0 ? -1 : maxSize; // Return -1 if no perfect set found
    }
}

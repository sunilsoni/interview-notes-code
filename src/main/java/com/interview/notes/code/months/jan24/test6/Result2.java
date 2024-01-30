package com.interview.notes.code.months.jan24.test6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result2 {
    public static void main(String[] args) {
        List<Integer> riceBags1 = Arrays.asList(625, 4, 2, 5, 25);
        System.out.println(maxSetSize(riceBags1)); // Should return 3

        List<Integer> riceBags2 = Arrays.asList(7, 4, 8, 9);
        System.out.println(maxSetSize(riceBags2)); // Should return -1

        List<Integer> riceBags3 = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        System.out.println(maxSetSize(riceBags3)); // Should return 3
    }

    public static int maxSetSize(List<Integer> riceBags) {
        Collections.sort(riceBags);
        int[] longestChain = new int[riceBags.size()];
        int maxSetSize = 0;

        for (int i = 0; i < riceBags.size(); i++) {
            longestChain[i] = 1;
            for (int j = 0; j < i; j++) {
                if (riceBags.get(i) % riceBags.get(j) == 0) {
                    longestChain[i] = Math.max(longestChain[i], longestChain[j] + 1);
                }
            }
            maxSetSize = Math.max(maxSetSize, longestChain[i]);
        }

        return maxSetSize > 1 ? maxSetSize : -1;
    }
}

package com.interview.notes.code.year.y2024.jan24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result {
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
        int maxSetSize = 0;
        int currentSetSize = 1;

        for (int i = 1; i < riceBags.size(); i++) {
            if (riceBags.get(i) % riceBags.get(i - 1) == 0) {
                currentSetSize++;
                maxSetSize = Math.max(maxSetSize, currentSetSize);
            } else {
                currentSetSize = 1;
            }
        }

        return maxSetSize > 1 ? maxSetSize : -1;
    }
}

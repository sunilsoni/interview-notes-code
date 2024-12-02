package com.interview.notes.code.year.y2024.jan24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> riceBags1 = Arrays.asList(625, 4, 2, 5, 25);
        System.out.println(maxSetSize(riceBags1)); // Should return 3

        List<Integer> riceBags2 = Arrays.asList(7, 4, 8, 9);
        System.out.println(maxSetSize(riceBags2)); // Should return -1
    }

    public static int maxSetSize(List<Integer> riceBags) {
        Collections.sort(riceBags);
        int maxSetSize = 0;
        int currentSetSize = 1;

        for (int i = 0; i < riceBags.size() - 1; i++) {
            if (riceBags.get(i + 1) % riceBags.get(i) == 0) {
                currentSetSize++;
            } else {
                currentSetSize = 1;
            }
            maxSetSize = Math.max(maxSetSize, currentSetSize);
        }

        return maxSetSize > 1 ? maxSetSize : -1;
    }
}

package com.interview.notes.code.months.jan24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution3 {
    public static void main(String[] args) {
        List<Integer> riceBags1 = Arrays.asList(625, 4, 2, 5, 25);
        System.out.println(maxSetSize(riceBags1)); // Example 1

        List<Integer> riceBags2 = Arrays.asList(7, 4, 8, 9);
        System.out.println(maxSetSize(riceBags2)); // Example 2


        List<Integer> riceBags3 = Arrays.asList(4, 7, 4, 8, 9);//expected -1
        System.out.println(maxSetSize(riceBags3)); // Example 2
    }

    public static int maxSetSize(List<Integer> riceBags) {
        Collections.sort(riceBags);
        int maxSetSize = 0;
        int setSize = 1;
        List<Integer> currentSet = new ArrayList<>();

        for (int i = 0; i < riceBags.size(); i++) {
            currentSet.clear();
            currentSet.add(riceBags.get(i));
            for (int j = i + 1; j < riceBags.size(); j++) {
                if (riceBags.get(j) % currentSet.get(currentSet.size() - 1) == 0) {
                    currentSet.add(riceBags.get(j));
                }
            }
            maxSetSize = Math.max(maxSetSize, currentSet.size());
        }

        return maxSetSize > 1 ? maxSetSize : -1;
    }
}

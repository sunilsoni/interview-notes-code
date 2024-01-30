package com.interview.notes.code.months.jan24.test5;

import java.util.*;

class Result1 {
    public static void main(String[] args) {
        // Example 1
        System.out.println(Result1.maxSetSize(Arrays.asList(625, 4, 2, 5, 25)));

        // Example 2
        System.out.println(Result1.maxSetSize(Arrays.asList(7, 4, 8, 9)));//-1
    }

    public static int maxSetSize(List<Integer> riceBags) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : riceBags) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(countMap.values());
        Collections.sort(counts, Collections.reverseOrder());

        int maxSetSize = -1;
        for (int i = 1; i <= riceBags.size(); i++) {
            int setSize = 0;
            for (int count : counts) {
                if (count >= i) {
                    setSize++;
                } else {
                    break;
                }
            }
            if (setSize >= i) {
                maxSetSize = i;
            } else {
                break;
            }
        }
        return maxSetSize;
    }
}

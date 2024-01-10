package com.interview.notes.code.months.jan24.test1;

import java.util.HashMap;

/**
 * Write a function that, when passed a list and a target sum, returns, efficiently with respect to time used, two distinct zero-based indices of any two of the numbers, whose sum is equal to the target sum. If there are no two numbers, the function should return null.
 * For example, findTwoSum(new int/] { 3, 1, 5, 7, 5, 9 }, 10) should return a single dimensional array with two elements and contain any of the following pairs of indices:
 * • 0 and 3 (or 3 and 0) as 3 + 7 = 10
 * • 1 and 5 (or 5 and 1) as 1 + 9 = 10
 * • 2 an
 */
public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            int complement = sum - list[i];
            if (indexMap.containsKey(complement)) {
                return new int[]{indexMap.get(complement), i};
            }
            indexMap.put(list[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[]{3, 1, 5, 7, 5, 9}, 10);
        if (indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        } else {
            System.out.println("No two sum solution found.");
        }
    }
}

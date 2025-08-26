package com.interview.notes.code.year.y2025.august.common.test7;

import java.util.HashSet;

public class PairSumFinder {

    public static void findPairs(int[] arr, int targetSum) {
        HashSet<Integer> seen = new HashSet<>();

        System.out.println("Pairs with sum " + targetSum + ":");
        for (int num : arr) {
            int complement = targetSum - num;
            if (seen.contains(complement)) {
                System.out.println("(" + complement + ", " + num + ")");
            }
            seen.add(num);
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 7, 11, 9, 13, 8, 12};
        int target = 20;

        findPairs(array, target);
    }
}

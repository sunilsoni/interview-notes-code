package com.interview.notes.code.year.y2025.august.Uber.test4;

import java.util.*;

public class Solution1 {
    static int[] solution(int[] primary, int[] secondary, int[][] operations) {
        List<Integer> result = new ArrayList<>();

        for (int[] op : operations) {
            if (op[0] == 0) {
                // Update operation: [0, index, newValue]
                int index = op[1];
                int newValue = op[2];
                secondary[index] = newValue;
            } else if (op[0] == 1) {
                // Query operation: [1, targetSum]
                int targetSum = op[1];
                int count = 0;
                for (int p : primary) {
                    int need = targetSum - p;
                    for (int s : secondary) {
                        if (s == need) count++;
                    }
                }
                result.add(count);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] primary1 = {1, 2, 3};
        int[] secondary1 = {3, 4};
        int[][] operations1 = {
            {1, 5}, {0, 0, 1}, {1, 5}
        };
        int[] expected1 = {2, 1};
        int[] result1 = solution(primary1, secondary1, operations1);
        System.out.println("Test 1: " + (Arrays.equals(result1, expected1) ? "PASS" : "FAIL")
                + " Expected=" + Arrays.toString(expected1) + " Got=" + Arrays.toString(result1));

        int[] primary2 = {1, 2, 2};
        int[] secondary2 = {2, 3};
        int[][] operations2 = {
            {1, 4}, {0, 0, 3}, {1, 5}
        };
        int[] expected2 = {3, 4};
        int[] result2 = solution(primary2, secondary2, operations2);
        System.out.println("Test 2: " + (Arrays.equals(result2, expected2) ? "PASS" : "FAIL")
                + " Expected=" + Arrays.toString(expected2) + " Got=" + Arrays.toString(result2));
    }
}
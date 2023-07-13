package com.interview.notes.code.july23.test3;

import java.util.ArrayList;
import java.util.List;

public class FindCombinations {
    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 5, 1, 7};
        int sum = 7;
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(arr, sum, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

    public static void findCombinations(int[] arr, int sum, int index, List<Integer> current, List<List<Integer>> result) {
        if (sum < 0) {
            return;
        }
        if (sum == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);
            findCombinations(arr, sum - arr[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}

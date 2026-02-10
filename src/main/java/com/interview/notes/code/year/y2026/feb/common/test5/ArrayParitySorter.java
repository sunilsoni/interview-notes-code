package com.interview.notes.code.year.y2026.feb.common.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ArrayParitySorter {

    public static int moves(List<Integer> arr) {
        int left = 0;
        int right = arr.size() - 1;
        int swapCount = 0;

        while (left < right) {
            while (left < right && arr.get(left) % 2 == 0) left++;
            while (left < right && arr.get(right) % 2 != 0) right--;

            if (left < right) {
                swapCount++;
                left++;
                right--;
            }
        }
        return swapCount;
    }

    public static void main(String[] args) {
        runTest("Sample 0", List.of(13, 10, 21, 20), 1);
        runTest("Sample 1", List.of(8, 5, 11, 4, 6), 2);
        runTest("Large Data", IntStream.range(0, 100000)
                .mapToObj(i -> i % 2 == 0 ? 1 : 2).collect(Collectors.toList()), 25000);
    }

    private static void runTest(String name, List<Integer> input, int expected) {
        int result = moves(new ArrayList<>(input));
        System.out.println(name + ": " + (result == expected ? "PASS" : "FAIL (Got " + result + ")"));
    }
}
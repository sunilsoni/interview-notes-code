package com.interview.notes.code.year.y2026.feb.common.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueWindow {
    // method to compute unique counts in each window
    static List<Integer> uniqueCounts(int[] arr, int k) {
        // IntStream.range creates sliding window indices
        return IntStream.range(0, arr.length - k + 1)
                // for each start index, take subarray of size k
                .mapToObj(i -> Arrays.stream(arr, i, i + k)
                        // put elements into Set to remove duplicates
                        .boxed().collect(Collectors.toSet()).size())
                // collect results into list
                .toList();
    }

    public static void main(String[] args) {
        // test cases
        int[] arr1 = {10, 20, 30, 10, 20};
        int k1 = 3;
        List<Integer> out1 = uniqueCounts(arr1, k1);
        System.out.println("Test1: " + out1 + " => " + (out1.equals(List.of(3, 3, 3)) ? "PASS" : "FAIL"));

        // edge case: empty array
        int[] arr2 = {};
        int k2 = 3;
        List<Integer> out2 = uniqueCounts(arr2, k2);
        System.out.println("Test2: " + out2 + " => " + (out2.isEmpty() ? "PASS" : "FAIL"));

        // edge case: k > length
        int[] arr3 = {1, 2};
        int k3 = 3;
        List<Integer> out3 = uniqueCounts(arr3, k3);
        System.out.println("Test3: " + out3 + " => " + (out3.isEmpty() ? "PASS" : "FAIL"));

        // large data test
        int[] arr4 = IntStream.range(0, 100000).toArray();
        int k4 = 1000;
        List<Integer> out4 = uniqueCounts(arr4, k4);
        System.out.println("Test4 size: " + out4.size() + " => " + (out4.size() == arr4.length - k4 + 1 ? "PASS" : "FAIL"));
    }
}

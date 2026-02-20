package com.interview.notes.code.year.y2026.feb.HackerRank.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumDistinctSubarrayFinder {

    public static int findMinimumLengthSubarray(List<Integer> arr, int k) {
        int n = arr.size(), l = 0, min = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < n; r++) {
            map.merge(arr.get(r), 1, Integer::sum);
            while (map.size() >= k) {
                min = Math.min(min, r - l + 1);
                map.compute(arr.get(l), (key, v) -> v == 1 ? null : v - 1);
                l++;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static void test(String name, List<Integer> arr, int k, int expected) {
        int result = findMinimumLengthSubarray(arr, k);
        System.out.println(name + " : " + (result == expected ? "PASS" : "FAIL") + " -> " + result);
    }

    public static void main(String[] args) {
        test("Sample1", Arrays.asList(3,2,3,3,1,3), 3, 4);
        test("Sample2", Arrays.asList(1,2,2,1,2), 4, -1);
        test("Example", Arrays.asList(2,2,1,1,3), 3, 4);
        test("Edge1", Arrays.asList(1,1,1,1), 1, 1);
        test("Edge2", Arrays.asList(1,2,3,4,5), 5, 5);

        int size = 100000;
        List<Integer> large = IntStream.range(0, size).map(i -> i % 1000).boxed().collect(Collectors.toList());
        int res = findMinimumLengthSubarray(large, 500);
        System.out.println("LargeData : " + (res > 0 ? "PASS" : "FAIL") + " -> " + res);
    }
}

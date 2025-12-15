package com.interview.notes.code.year.y2025.december.common.test6;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FirstTargetSumIndexFinder {

    static int[] find(int[] arr, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        return IntStream.range(0, arr.length)
                .mapToObj(i -> {
                    int need = target - arr[i];
                    if (seen.containsKey(need)) return new int[]{seen.get(need), i};
                    seen.put(arr[i], i);
                    return null;
                })
                .filter(e -> e != null)
                .findFirst()
                .orElse(new int[]{-1, -1});
    }

    static void test(String name, int[] arr, int target, int[] expected) {
        int[] r = find(arr, target);
        boolean pass = r[0] == expected[0] && r[1] == expected[1];
        System.out.println(name + " : " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,9,8,7,6,5,4,3,2};

        test("Basic", arr, 9, new int[]{3,4});
        test("NoMatch", arr, 100, new int[]{-1,-1});
        test("Immediate", new int[]{4,5,1,2}, 9, new int[]{0,1});

        int[] large = IntStream.range(0, 1_000_000).toArray();
        test("LargeData", large, 1_999_997, new int[]{999_998, 999_999});
    }
}
package com.interview.notes.code.year.y2025.may.eHealth.test1;

import java.util.*;
import java.util.stream.*;

public class RemoveDuplicates {
    /**
     * Removes duplicates so each number appears at most twice.
     * Returns the new length of the array.
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 3) return n;           // 0,1,2 elements are always OK
        int j = 2;                      // write pointer
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static void main(String[] args) {
        // list of test cases: {input array, expected length}
        List<int[]> inputs = Arrays.asList(
            new int[] {1,1,1,2,2,3},
            new int[] {0,0,1,1,1,1,2,3,3},
            new int[] {}, 
            new int[] {5},
            new int[] {2,2},
            new int[] {3,3,3,3}
        );
        int[] expected = {5, 7, 0, 1, 2, 2};

        // test each case
        for (int t = 0; t < inputs.size(); t++) {
            int[] arr = Arrays.copyOf(inputs.get(t), inputs.get(t).length);
            int got = removeDuplicates(arr);
            // verify length and that no number appears >2 times
            boolean ok = (got == expected[t]);
            Map<Integer,Integer> count = new HashMap<>();
            for (int i = 0; i < got; i++) {
                count.merge(arr[i], 1, Integer::sum);
                if (count.get(arr[i]) > 2) ok = false;
            }
            System.out.printf("Test %d: expected=%d, got=%d → %s%n",
                              t+1, expected[t], got, ok ? "PASS" : "FAIL");
        }

        // large data input: generate 200k sorted array where each value i appears i%3 + 1 times
        int N = 200_000;
        int[] large = IntStream
                       .range(0, N)
                       .flatMap(i -> IntStream.generate(() -> i)
                                             .limit(i % 3 + 1))
                       .toArray();
        long start = System.nanoTime();
        int newLen = removeDuplicates(large);
        long duration = System.nanoTime() - start;
        // quick sanity: no count >2 in the first newLen elements
        boolean valid = IntStream.range(0, newLen)
                           .boxed()
                           .collect(Collectors.groupingBy(i -> large[i], Collectors.counting()))
                           .values().stream().allMatch(c -> c <= 2);
        System.out.printf("Large input: newLen=%d, time=%.2fms, valid=%s%n",
                          newLen, duration/1e6, valid ? "PASS" : "FAIL");
    }
}
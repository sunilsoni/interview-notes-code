package com.interview.notes.code.year.y2026.jan.assessments.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintSubarraysSumK {

    static void printSubarrays(int[] nums, int k) {
        Map<Long, List<Integer>> map = new HashMap<>(); // prefixSum -> indices
        long prefix = 0; // running sum

        map.put(0L, new ArrayList<>(List.of(-1))); // handles subarrays starting at index 0

        for (int i = 0; i < nums.length; i++) { // loop through array
            prefix += nums[i]; // update prefix sum
            long need = prefix - k; // required previous prefix

            if (map.containsKey(need)) { // if matching prefix exists
                for (int start : map.get(need)) { // all possible starts
                    System.out.print("[ ");
                    for (int j = start + 1; j <= i; j++) { // print subarray
                        System.out.print(nums[j] + " ");
                    }
                    System.out.println("]");
                }
            }

            map.computeIfAbsent(prefix, x -> new ArrayList<>()).add(i); // store current index
        }
    }

    public static void main(String[] args) {
        int[] nums = {7, 3, 6, 8, 1, 2};
        int k = 9;

        System.out.println("Subarrays with sum = " + k + ":");
        printSubarrays(nums, k);
    }
}

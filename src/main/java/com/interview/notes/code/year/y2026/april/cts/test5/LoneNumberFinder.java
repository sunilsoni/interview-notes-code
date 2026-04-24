package com.interview.notes.code.year.y2026.april.cts.test5;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LoneNumberFinder {
    public static void main(String[] args) {
        int[] nums = {10, 6, 5, 8, 11, 12};
        
        Set<Integer> numSet = Arrays.stream(nums)
                                    .boxed()
                                    .collect(Collectors.toSet());

        int loneNumber = Arrays.stream(nums)
                .filter(n -> !numSet.contains(n - 1) && !numSet.contains(n + 1))
                .findFirst()
                .orElse(-1);

        System.out.println("Lone Number: " + loneNumber);
    }
}
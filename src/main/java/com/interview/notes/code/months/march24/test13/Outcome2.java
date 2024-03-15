package com.interview.notes.code.months.march24.test13;

import java.util.HashSet;
import java.util.Set;

class Outcome2 {
    public static int solve(Integer[] doll) {
        Set<Integer> seen = new HashSet<>();
        for (Integer size : doll) {
            seen.add(size);
        }
        return seen.size();
    }

    public static void main(String[] args) {
        // Example #1
        System.out.println(solve(new Integer[]{2, 2, 3, 3})); // Output: 2

        // Example #2
        System.out.println(solve(new Integer[]{1, 2, 2, 3, 4, 5})); // Output: 2
    }
}

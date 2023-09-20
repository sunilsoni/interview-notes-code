package com.interview.notes.code.months.Aug23.test2;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(new int[]{})); // Output: 0
        System.out.println(sol.solution(new int[]{1, -1, 5, 5, 3, 5, 2, 3})); // Output: 10
        System.out.println(sol.solution(new int[]{5, 120, -2, 11, 120, 2})); // Output: 136
        System.out.println(sol.solution(new int[]{1, 1, 1, 1, 1})); // Output: 1
    }

    public int solution(int[] A) {
        return Arrays.stream(A)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .mapToInt(entry -> entry.getKey())
                .sum();
    }
}

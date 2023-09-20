package com.interview.notes.code.months.july23.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        int[] numbers = {11, 9, 5, 8, 21, 6, 3, 10};
        List<Integer> result = Arrays.stream(numbers)
                .boxed()

                .sorted(Comparator.reverseOrder())
                .skip(1).limit(1)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}


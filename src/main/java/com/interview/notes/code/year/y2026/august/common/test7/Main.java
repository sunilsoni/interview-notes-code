package com.interview.notes.code.year.y2026.august.common.test7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> removeLessThanThree(List<Integer> list) {
        // Filter out any numbers that are less than 3
        return list.stream()
                   .filter(num -> num >= 3)
                   .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 10, 1, 2, 2, 3, 3, 10, 3, 4, 5, 5);
        System.out.println(removeLessThanThree(list)); 
        // Output: [10, 3, 3, 10, 3, 4, 5, 5]
    }
}
package com.interview.notes.code.year.y2024.june24.test10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {11, 9, 5, 8, 21, 6, 3, 10};
        List<Integer> result = Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())  // Correct missing line
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println("Result: " + result);
    }
}

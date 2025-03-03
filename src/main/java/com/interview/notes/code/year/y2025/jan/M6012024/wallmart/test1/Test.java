package com.interview.notes.code.year.y2025.jan.M6012024.wallmart.test1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 2, 3};

        int mostFrequent = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
                .get()
                .getKey();

        System.out.println("Most frequent number: " + mostFrequent); // Output: 2

    }
}

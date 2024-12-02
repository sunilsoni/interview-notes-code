package com.interview.notes.code.year.y2024.may24.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 8, 9, 9};

        int result = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> i, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .max(Integer::compareTo)
                .orElseThrow(() -> new RuntimeException("No such element found"));

        System.out.println("Highest number occurring only once: " + result);
    }
}

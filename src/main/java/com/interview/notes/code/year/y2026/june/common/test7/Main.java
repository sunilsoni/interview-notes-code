package com.interview.notes.code.year.y2026.june.common.test7;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String[] arr = {"a", "b", "c", "a", "b", "a", "d"};

        Map<String, Long> counts =
                Arrays.stream(arr)
                      .collect(Collectors.groupingBy(
                              Function.identity(),
                              Collectors.counting()));

        System.out.println(counts);
    }
}
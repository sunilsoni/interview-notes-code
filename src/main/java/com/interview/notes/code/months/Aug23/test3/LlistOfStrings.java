package com.interview.notes.code.months.Aug23.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LlistOfStrings {
    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("apple", "banana", "cherry", "date");

        // Convert the list to a map with the length of the string as the key and the string itself as the value.
        Map<Integer, String> map = listOfStrings.stream()
                .collect(Collectors.toMap(String::length, Function.identity(), (v1, v2) -> v1));

        System.out.println(map);
    }
}

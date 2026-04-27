package com.interview.notes.code.year.y2026.april.common.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PalindromeStream {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("radar", "level", "java", "stream");

        Map<Boolean, List<String>> result = words.stream()
            .collect(Collectors.partitioningBy(word -> {
                String reversed = new StringBuilder(word).reverse().toString();
                return word.equals(reversed);
            }));

        System.out.println(result);
    }
}
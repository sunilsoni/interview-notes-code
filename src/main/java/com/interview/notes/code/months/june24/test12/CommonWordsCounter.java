package com.interview.notes.code.months.june24.test12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonWordsCounter {

    public static int countCommonWords(List<String> list1, List<String> list2) {
        // Create maps to count occurrences of each word in both lists
        Map<String, Long> map1 = list1.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> map2 = list2.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Count common words that appear exactly once in both lists
        return (int) map1.entrySet().stream()
                .filter(entry -> entry.getValue() == 1 && map2.getOrDefault(entry.getKey(), 0L) == 1)
                .count();
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("java", "is", "amazing", "as", "is");
        List<String> list2 = Arrays.asList("amazing", "java", "is", "world", "hello");

        int result = countCommonWords(list1, list2);
        System.out.println("Output: " + result); // Output: 2
    }
}

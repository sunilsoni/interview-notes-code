package com.interview.notes.code.months.year2023.june23.test11;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount {
    public static void main(String[] args) {
        String s = "this is Teja. Teja is a full stack developer with 8 yrs of experience";

        Map<String, Long> wordCount = Arrays.stream(s.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        wordCount.forEach((word, count) -> System.out.println(word + " -> " + count));
    }
}

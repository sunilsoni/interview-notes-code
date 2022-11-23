package com.interview.notes.code.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount {
    public static void main(String[] args) {
        List<String> computers = Arrays.asList(
                "Dell",
                "HP",
                "IBM",
                "Apple",
                "HP",
                "Apple"
        );

        Map<String, Integer> computerOccurrences = computers.stream()
                .collect(Collectors
                        .toMap(Function.identity(), computer -> 1, Integer::sum));

        System.out.println(computerOccurrences);//{Dell=1, Apple=2, IBM=1, HP=2}
    }
}

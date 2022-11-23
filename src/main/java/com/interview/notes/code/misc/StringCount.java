package com.interview.notes.code.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCount {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tomy", "Rahul", "Boboy", "John", "Rahul", "John");

        //count for more than  1 occurence
        Map<String, Long> nameCountMap = names.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1l, Long::sum))
                .entrySet()
                .stream()
                .filter(v -> v.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(nameCountMap);//{Rahul=2, John=2}

        //for all count
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

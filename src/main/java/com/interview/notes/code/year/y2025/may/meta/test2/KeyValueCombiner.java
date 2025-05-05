package com.interview.notes.code.year.y2025.may.meta.test2;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyValueCombiner {

    public static Map<Character, Integer> combineValues(String[] input) {
        return Arrays.stream(input)
                .flatMap(line -> Stream.of(line.split(",")))  // split each line into pairs
                .map(String::trim)                            // remove whitespace
                .collect(Collectors.groupingBy(
                        str -> str.charAt(0),                     // key is first character
                        TreeMap::new,                            // use TreeMap for sorting
                        Collectors.summingInt(str ->             // sum the values
                                Character.getNumericValue(str.charAt(2)))
                ));
    }

    public static void main(String[] args) {
        String[] input = {
                "a:7, c:6, d:1, e:2",
                "b:2, a:1, e:3, f:1",
                "f:2, d:5, c:4, b:1"
        };

        System.out.println("Input array:");
        Arrays.stream(input).forEach(System.out::println);

        Map<Character, Integer> result = combineValues(input);
        System.out.println("\nOutput: " + result);
    }
}

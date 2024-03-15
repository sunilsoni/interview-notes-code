package com.interview.notes.code.months.march24.test12;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main5 {
    public static void main(String[] args) {
        String input = "sreedhar"; // Replace with your input string

        // Convert the input string to a character stream, collect into a map with character counts
        Map<Character, Long> charCountMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Sort the entries by character and collect into a new map to remove duplicates
        Map<Character, Long> sortedCharCountMap = charCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // Print the sorted character count map
        sortedCharCountMap.forEach((character, count) -> System.out.println(character + ": " + count));
    }
}

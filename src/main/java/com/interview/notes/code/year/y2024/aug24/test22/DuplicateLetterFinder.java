package com.interview.notes.code.year.y2024.aug24.test22;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DuplicateLetterFinder {
    public static Map<Character, Long> findDuplicateLetters(String input) {
        return input.toLowerCase().chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        TreeMap::new
                ));
    }

    public static void main(String[] args) {
        // Example test cases
        String[] testCases = {
                "hello",
                "Programming",
                "java",
                "stream",
                "aabbccddee",
                "",
                "abcdefg",
                "aaaaaa"
        };

        for (String testCase : testCases) {
            Map<Character, Long> duplicates = findDuplicateLetters(testCase);
            System.out.println(testCase + ": " + duplicates);
        }
    }
}

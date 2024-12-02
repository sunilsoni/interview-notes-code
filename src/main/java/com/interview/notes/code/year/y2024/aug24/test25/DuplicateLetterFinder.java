package com.interview.notes.code.year.y2024.aug24.test25;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DuplicateLetterFinder {
    public static Set<Character> findDuplicateLetters(String input) {
        return input.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public static void main(String[] args) {
        // Example test cases
        String[] testCases = {
                "hello",
                "programming",
                "java",
                "stream",
                "aabbccddee",
                "",
                "abcdefg",
                "aaaaaa"
        };

        for (String testCase : testCases) {
            Set<Character> duplicates = findDuplicateLetters(testCase);
            System.out.println("Input: \"" + testCase + "\"");
            System.out.println("Duplicate letters: " + duplicates);
            System.out.println();
        }
    }
}

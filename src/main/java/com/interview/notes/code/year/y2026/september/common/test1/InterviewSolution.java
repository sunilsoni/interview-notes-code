package com.interview.notes.code.year.y2026.september.common.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterviewSolution {
    static void main(String[] args) {
        String s = "This is Java Development Interview";

        // 1. Reverse the words
        String reversedWords = Arrays.stream(s.trim().split("\\s+"))
                .toList()
                .reversed()
                .stream()
                .collect(Collectors.joining(" "));

        System.out.println("Reversed Words: " + reversedWords);

        // 2. Count repeating characters (excluding spaces, case-insensitive)
        Map<Character, Long> duplicateChars = s.toLowerCase()
                .chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Repeating Characters: " + duplicateChars);
    }
}
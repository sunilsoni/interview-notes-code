package com.interview.notes.code.months.nov24.test10;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCount {
    public static void main(String[] args) {
        String s = "I am a java developer";

        // Using stream to count characters
        Map<Character, Long> charCount = s.chars()  // Convert string to IntStream
                .mapToObj(c -> (char) c)  // Convert int to Character
                .filter(c -> c != ' ')  // Optional: Remove spaces
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Print the result
        charCount.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}

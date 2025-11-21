package com.interview.notes.code.year.y2025.november.Amazon.test2;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFrequencyCounter {

    public static void main(String[] args) {
        // Input string
        String sentence = "Hello world";

        // Step 1: Convert to lowercase for case-insensitive counting
        // Step 2: Remove spaces if needed
        // Step 3: Convert to character stream and count frequencies
        Map<Character, Long> frequencyMap = sentence.toLowerCase()
                .chars() // convert to IntStream of character codes
                .mapToObj(c -> (char) c) // convert each int to char
                .filter(c -> Character.isLetter(c)) // optional: count only letters
                .collect(Collectors.groupingBy(
                        Function.identity(), // group by character itself
                        Collectors.counting() // count occurrences
                ));

        // Print result
        frequencyMap.forEach((ch, count) ->
                System.out.println("'" + ch + "' appears " + count + " times")
        );
    }
}

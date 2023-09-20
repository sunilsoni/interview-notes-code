package com.interview.notes.code.months.june23.test8;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCounter {
    public static Map<Character, Long> countCharacters(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static void main(String[] args) {

        try {
            countCharacters("s");
        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }

        String str = "Hello, World!";
        Map<Character, Long> characterCount = countCharacters(str);

        // Print the character count
        characterCount.forEach((character, count) -> System.out.println(character + ": " + count));
    }
}

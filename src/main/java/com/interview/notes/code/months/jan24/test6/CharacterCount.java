package com.interview.notes.code.months.jan24.test6;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCount {
    public static void main(String[] args) {
        String input = "girish";

        Map<Character, Long> charCountMap = countCharacters(input);

        charCountMap.forEach((character, count) ->
                System.out.println(character + "-" + count)
        );
    }

    public static Map<Character, Long> countCharacters(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}


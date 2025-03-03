package com.interview.notes.code.year.y2025.feb.common.test6;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WordReverser {
    public static String reverseWords(String input) {
        return Arrays.stream(input.split(" "))
                .map(word -> word.chars()
                        .mapToObj(ch -> String.valueOf((char) ch))
                        .reduce("", (s1, s2) -> s2 + s1))
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        String input = "I work as a Java Engineer";
        String output = reverseWords(input);
        System.out.println(output); // I krow sa a avaJ reenignE
    }
}

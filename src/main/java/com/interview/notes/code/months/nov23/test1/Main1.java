package com.interview.notes.code.months.nov23.test1;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {
        String input = "java stream find no repeated char in string using java8 stream";
        
        Optional<Character> firstNonRepeatedChar = input.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst();

        firstNonRepeatedChar.ifPresentOrElse(
            ch -> System.out.println("First non-repeated character: " + ch),
            () -> System.out.println("No non-repeated character found.")
        );
    }
}

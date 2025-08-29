package com.interview.notes.code.year.y2025.august.common.test15;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SecondNonRepeatedChar {
    public static void main(String[] args) {
        String str = "deekshit";

        // Step 1: Count frequencies with LinkedHashMap (to preserve order)
        LinkedHashMap<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c) // convert int -> Character
                .collect(Collectors.groupingBy(
                        Function.identity(), // group by character itself
                        LinkedHashMap::new, // preserve insertion order
                        Collectors.counting() // count occurrences
                ));

        // Step 2: Filter only non-repeated (count == 1), skip first, get second
        Optional<Character> secondNonRepeated = freqMap.entrySet().stream()
                .filter(e -> e.getValue() == 1) // keep only unique chars
                .map(Map.Entry::getKey) // extract character
                .skip(1) // skip the 1st non-repeated
                .findFirst(); // get the 2nd

        // Step 3: Print result
        secondNonRepeated.ifPresentOrElse(
                ch -> System.out.println("Second non-repeated char: " + ch),
                () -> System.out.println("No second non-repeated char found")
        );
    }
}
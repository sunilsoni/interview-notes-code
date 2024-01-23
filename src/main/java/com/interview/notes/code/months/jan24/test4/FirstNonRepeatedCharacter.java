package com.interview.notes.code.months.jan24.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


//Given a String, find the first non-repeated character in it using Stream functions?
public class FirstNonRepeatedCharacter {
    public static Character findFirstNonRepeatedCharacter(String str) {
        Map<Character, Long> charCounts = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));

        return str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> charCounts.get(c) == 1)
                .findFirst()
                .orElse(null); // or throw an exception if no such character exists
    }

    public static void main(String[] args) {
        String str = "javastreams";
        Character result = findFirstNonRepeatedCharacter(str);
        if (result != null) {
            System.out.println("The first non-repeated character is: " + result);
        } else {
            //System.out.println("No non

        }
    }
}
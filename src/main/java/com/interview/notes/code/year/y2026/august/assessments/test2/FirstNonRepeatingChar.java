package com.interview.notes.code.year.y2026.august.assessments.test2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String s = "I come through infosys.";

        // Find the first non-repeating character
        Character firstNonRepeating = s.chars()           // 1. Create an IntStream of characters
            .mapToObj(c -> (char) c)                      // 2. Convert int to Character object
            .collect(Collectors.groupingBy(               // 3. Group them to count occurrences
                Function.identity(),                      //    Key: the character itself
                LinkedHashMap::new,                       //    Map: LinkedHashMap (maintains insertion order)
                Collectors.counting()                     //    Value: count of the character
            ))
            .entrySet()
            .stream()                                     // 4. Stream the map entries
            .filter(entry -> entry.getValue() == 1L)      // 5. Filter for characters that appear exactly once
            .map(Map.Entry::getKey)                       // 6. Get the character (key)
            .findFirst()                                  // 7. Grab the first one that matches
            .orElse(null);                                // 8. Return null if none are found

        System.out.println("String: \"" + s + "\"");
        
        if (firstNonRepeating != null) {
            System.out.println("First non-repeating character: '" + firstNonRepeating + "'");
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
}
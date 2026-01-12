package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FruitCount {
    public static void main(String[] args) {
        // Step 1: Create the list
        List<String> fruits = Arrays.asList("apple", "banana", "apple");

        // Step 2: Use streams to group and count
        Map<String, Long> fruitCount = fruits.stream()
            .collect(Collectors.groupingBy(
                Function.identity(),   // key = element itself
                Collectors.counting()  // value = count of occurrences
            ));

        // Step 3: Print the result
        System.out.println(fruitCount);
    }
}

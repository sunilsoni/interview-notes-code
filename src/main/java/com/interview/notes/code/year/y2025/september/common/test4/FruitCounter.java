package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FruitCounter {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "apple", "banana");

        Map<String, Long> fruitCount = fruits.stream()
                .collect(Collectors.groupingBy(
                        fruit -> fruit,
                        Collectors.counting()
                ));

        System.out.println(fruitCount); // Output: {apple=2, banana=1}
    }
}

package com.interview.notes.code.july23.test1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapExample {
    public static void main(String[] args) {
        // HashMap example
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("John", 25);
        hashMap.put("Alice", 30);
        hashMap.put("Bob", 40);

        System.out.println("HashMap: " + hashMap);

        // TreeMap example
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("John", 25);
        treeMap.put("Alice", 30);
        treeMap.put("Bob", 40);

        System.out.println("TreeMap: " + treeMap);


        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);

        Map<Integer, Long> occurrences = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }
}

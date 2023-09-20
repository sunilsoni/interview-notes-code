package com.interview.notes.code.months.july23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountDuplicatesExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("orange");
        stringList.add("banana");
        stringList.add("kiwi");

        // Count duplicates using Java 8 stream
        Map<String, Long> duplicateCount = stringList.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        // Print the duplicate count
        for (Map.Entry<String, Long> entry : duplicateCount.entrySet()) {
            String element = entry.getKey();
            Long count = entry.getValue();
            if (count > 1) {
                System.out.println("Element: " + element + ", Count: " + count);
            }
        }
    }
}

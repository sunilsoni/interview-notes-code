package com.interview.notes.code.months.july23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesExample {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("orange");
        stringList.add("banana");
        stringList.add("kiwi");

        // Remove duplicates using Java 8 stream
        List<String> distinctStrings = stringList.stream()
                .distinct()
                .collect(Collectors.toList());

        // Print the distinct strings
        for (String str : distinctStrings) {
            System.out.println(str);
        }
    }
}

package com.interview.notes.code.july23.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesExampleJava8 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("s");
        names.add("s");
        names.add("a");
        names.add("ab");
        names.add("ab");
        names.add("b");
        names.add("b");
        names.add("c");
        names.add("d");
        names.add("e");

        // Remove duplicates using Java 8 streams and collectors
        List<String> result = names.stream()
                .distinct()
                .collect(Collectors.toList());

        // Print the result
        System.out.println("Original List: " + names);
        System.out.println("List with duplicates removed: " + result);
    }
}

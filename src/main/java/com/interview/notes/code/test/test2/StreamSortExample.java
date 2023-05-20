package com.interview.notes.code.test.test2;

import java.util.Arrays;
import java.util.Comparator;

public class StreamSortExample {
    public static void main(String[] args) {
        String[] nameArray = {"David", "Scott", "Aravind", "Raman", "Harshita"};

        String[] sortedNames = Arrays.stream(nameArray)
                .sorted(Comparator.reverseOrder())
                .toArray(String[]::new);

        // Print the sorted names
        for (String name : sortedNames) {
            System.out.println(name);
        }
    }
}

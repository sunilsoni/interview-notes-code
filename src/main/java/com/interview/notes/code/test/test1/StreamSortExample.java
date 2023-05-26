package com.interview.notes.code.test.test1;

import java.util.Arrays;

public class StreamSortExample {
    public static void main(String[] args) {
        String[] nameArray = {"David", "Scott", "Aravind", "Raman", "Harshita"};

        String[] sortedNames = Arrays.stream(nameArray)
                .sorted()
                .toArray(String[]::new);

        // Print the sorted names
        for (String name : sortedNames) {
            System.out.println(name);
        }
    }
}
package com.interview.notes.code.months.Oct23.test9;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Create HashSet and add student objects
        HashSet<Student> studentSet = new HashSet<>();
        studentSet.add(new Student(1, "Alice"));
        studentSet.add(new Student(2, "Bob"));

        // Sort using Java 8 Streams
        List<Student> sortedList = studentSet.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        // Output sorted list
        sortedList.forEach(System.out::println);
    }
}

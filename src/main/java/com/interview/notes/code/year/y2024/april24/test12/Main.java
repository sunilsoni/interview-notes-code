package com.interview.notes.code.year.y2024.april24.test12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Example usage
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "John", "Doe"));
        students.add(new Student(1, "Alice", "Smith"));
        students.add(new Student(3, "John", "Doe"));

        func(students);

        students.forEach(System.out::println);
    }

    public static void func(List<Student> list) {
        // Remove duplicates based on firstname and sort based on id
        // list.clear();
        list.addAll(list.stream()
                .collect(Collectors.toMap(Student::getFirstname, s -> s, (s1, s2) -> s1))
                .values()
                .stream()
                .sorted(Comparator.comparingInt(Student::getId))
                .collect(Collectors.toList()));
    }
}
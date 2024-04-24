package com.interview.notes.code.months.april24.test12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueSortedStudents {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe"));
        students.add(new Student(2, "Jane", "Doe"));
        students.add(new Student(3, "John", "Smith")); // This 'John' will be filtered out

        List<Student> uniqueSortedStudents = students.stream()
                // Remove duplicates based on the firstname
                .collect(Collectors.toMap(
                        Student::getFirstname, // Key by firstname
                        Function.identity(),   // Value as the student object
                        (existing, replacement) -> existing, // In case of conflict, keep the existing
                        LinkedHashMap::new     // Preserve the order
                ))
                .values()               // Get the collection of values
                .stream()               // Stream the values
                // Sort by ID
                .sorted(Comparator.comparingInt(Student::getId))
                .collect(Collectors.toList()); // Collect into a list

        // Print the list of unique and sorted students
        uniqueSortedStudents.forEach(System.out::println);
    }
}
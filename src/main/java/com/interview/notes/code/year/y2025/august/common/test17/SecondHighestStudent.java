package com.interview.notes.code.year.y2025.august.common.test17;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SecondHighestStudent {
    public static void main(String[] args) {
        // Sample student data
        List<Student> students = List.of(
                new Student(1, "Alice", "CSE", 450),
                new Student(2, "Bob", "ECE", 480),
                new Student(3, "Charlie", "ME", 470),
                new Student(4, "David", "CSE", 490),
                new Student(5, "Eva", "EEE", 460)
        );

        // Find second highest total marks
        Optional<Student> secondTop = students.stream()
                .sorted(Comparator.comparingInt(Student::totalMarks).reversed())
                .skip(1)  // skip highest
                .findFirst();

        // Print result
        secondTop.ifPresentOrElse(
                s -> System.out.println("Second Highest: " + s),
                () -> System.out.println("Not enough students")
        );

        // Count students per department
        Map<String, Long> countByDept = students.stream()
                .collect(Collectors.groupingBy(Student::dept, Collectors.counting()));

        System.out.println(countByDept);
    }

    // Record class for Student
    record Student(int id, String name, String dept, int totalMarks) {
    }
}

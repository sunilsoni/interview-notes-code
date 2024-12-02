package com.interview.notes.code.year.y2024.sept24.test6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Student {
    int id;
    String name;
    int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}

public class SecondHighestMarks {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "Alice", 85),
                new Student(2, "Bob", 90),
                new Student(3, "Charlie", 80),
                new Student(4, "David", 95),
                new Student(5, "Eve", 88)
        );

        Optional<String> secondHighestStudent = students.stream()
                .sorted(Comparator.comparingInt((Student s) -> s.marks).reversed())
                .skip(1)
                .findFirst()
                .map(s -> s.name);

        secondHighestStudent.ifPresentOrElse(
                name -> System.out.println("Student with second highest marks: " + name),
                () -> System.out.println("No student found with second highest marks")
        );
    }
}

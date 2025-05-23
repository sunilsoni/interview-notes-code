package com.interview.notes.code.year.y2025.may.meta.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    String name;
    String subject;

    Student(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}

public class Main {
    public static Map<String, List<Student>> groupStudentsBySubject(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getSubject));
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", "Math"),
                new Student("Bob", "Science"),
                new Student("Charlie", "Math"),
                new Student("Dave", "Science"),
                new Student("Eve", "English")
        );

        Map<String, List<Student>> result = groupStudentsBySubject(students);
        System.out.println(result);
    }
}

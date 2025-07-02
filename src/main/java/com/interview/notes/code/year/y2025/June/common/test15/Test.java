package com.interview.notes.code.year.y2025.June.common.test15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test
{
    public static void main(String[] args) {
        // Assume we already have this list from the parsed input
        List<Student> students = Arrays.asList(
                new Student("Riya", "IT", 480),
                new Student("Arjun", "Mechanical", 450),
                new Student("Sneha", "IT", 510),
                new Student("Karan", "CS", 495)
        );

// ✅ Filter names of students from IT branch
        List<String> itStudents = students.stream()
                .filter(s -> "IT".equalsIgnoreCase(s.getBranch()))
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("Students from IT branch: " + itStudents);
    }
}

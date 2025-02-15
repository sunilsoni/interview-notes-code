package com.interview.notes.code.year.y2025.feb25.common.test5;

import java.util.*;

class Student {
    String name;
    Map<String, Integer> subjectMarks;

    public Student(String name, Map<String, Integer> subjectMarks) {
        this.name = name;
        this.subjectMarks = subjectMarks;
    }

    public int getTotalMarks() {
        return subjectMarks.values().stream().mapToInt(Integer::intValue).sum();
    }
}

public class FifthHighestStudent {
    public static String getFifthHighestStudentNameOrDefault(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getTotalMarks).reversed())
                .skip(4)
                .findFirst()
                .map(student -> student.name)
                .orElse("No 5th highest student found");
    }

    public static void main(String[] args) {
        // Create test data: 30 students with 7 subjects each.
        List<Student> testStudents = new ArrayList<>();
        String[] subjects = {"Math", "Physics", "Chemistry", "Biology", "English", "History", "Geography"};
        Random rand = new Random(42); // fixed seed for reproducibility

        for (int i = 1; i <= 30; i++) {
            Map<String, Integer> marks = new HashMap<>();
            for (String subject : subjects) {
                marks.put(subject, rand.nextInt(101)); // marks between 0 and 100
            }
            testStudents.add(new Student("Student" + i, marks));
        }

        // Get and print the fifth highest student's name, or default if not found.
        String fifthHighestName = getFifthHighestStudentNameOrDefault(testStudents);
        System.out.println("Fifth Highest Student: " + fifthHighestName);
    }
}
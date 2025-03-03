package com.interview.notes.code.year.y2025.feb.common.test4;

import java.util.*;
import java.util.stream.Collectors;

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
    // Returns an Optional containing the student with the 5th highest total marks.
    public static Optional<Student> getFifthHighestStudent(List<Student> students) {
        // Sort students by total marks in descending order
        List<Student> sorted = students.stream()
                .sorted(Comparator.comparingInt(Student::getTotalMarks).reversed())
                .collect(Collectors.toList());
        if (sorted.size() < 5) {
            return Optional.empty();
        }
        return Optional.of(sorted.get(4));
    }

    // Test method: simple main method for PASS/FAIL testing including edge and large cases.
    public static void main(String[] args) {
        // Test case 1: 30 students with 7 subjects each.
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

        Optional<Student> result = getFifthHighestStudent(testStudents);
        if (result.isPresent()) {
            Student student = result.get();
            System.out.println("Fifth Highest Student: " + student.name + " with total marks: " + student.getTotalMarks());
        } else {
            System.out.println("Not enough students to determine the 5th highest.");
        }

        // Additional Test Case 2: Edge case with less than 5 students.
        List<Student> smallList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Map<String, Integer> marks = new HashMap<>();
            for (String subject : subjects) {
                marks.put(subject, 50);
            }
            smallList.add(new Student("S" + i, marks));
        }
        Optional<Student> edgeResult = getFifthHighestStudent(smallList);
        if (!edgeResult.isPresent()) {
            System.out.println("Edge Test PASS: Correctly handled list with fewer than 5 students.");
        } else {
            System.out.println("Edge Test FAIL: Incorrectly returned a student for fewer than 5 entries.");
        }

        // Additional Test Case 3: Large data input test (simulate 10000 students).
        List<Student> largeList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Map<String, Integer> marks = new HashMap<>();
            for (String subject : subjects) {
                marks.put(subject, rand.nextInt(101));
            }
            largeList.add(new Student("LargeStudent" + i, marks));
        }
        long startTime = System.currentTimeMillis();
        Optional<Student> largeResult = getFifthHighestStudent(largeList);
        long endTime = System.currentTimeMillis();
        if (largeResult.isPresent()) {
            Student student = largeResult.get();
            System.out.println("Large Data Test PASS: 5th Highest Student: " + student.name + " with total marks: " + student.getTotalMarks());
        } else {
            System.out.println("Large Data Test FAIL: No student returned.");
        }
        System.out.println("Large data processing time: " + (endTime - startTime) + " ms");
    }
}
package com.interview.notes.code.year.y2025.feb25.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StudentMarks {
    // Returns the 5th highest student based on total marks
    public static Student getFifthHighestStudent(List<Student> students) {
        students.sort((s1, s2) -> Integer.compare(s2.total, s1.total));
        return students.size() < 5 ? null : students.get(4);
    }

    // Simple test method that prints PASS/FAIL for a test case
    public static boolean runTestCase(String testName, List<Student> students, String expectedName, int expectedTotal) {
        Student result = getFifthHighestStudent(students);
        boolean pass = (result != null && result.name.equals(expectedName) && result.total == expectedTotal);
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    public static void main(String[] args) {
        // Test Case 1: Small set of 6 students
        List<Student> students1 = new ArrayList<>();
        students1.add(new Student("Student1", new int[]{10, 10, 10, 10, 10, 10, 10})); // total 70
        students1.add(new Student("Student2", new int[]{20, 20, 20, 20, 20, 20, 20})); // total 140
        students1.add(new Student("Student3", new int[]{30, 30, 30, 30, 30, 30, 30})); // total 210
        students1.add(new Student("Student4", new int[]{40, 40, 40, 40, 40, 40, 40})); // total 280
        students1.add(new Student("Student5", new int[]{50, 50, 50, 50, 50, 50, 50})); // total 350
        students1.add(new Student("Student6", new int[]{60, 60, 60, 60, 60, 60, 60})); // total 420
        // Sorted totals descending: 420,350,280,210,140,70 => 5th highest: Student2 (140)
        runTestCase("Test Case 1", students1, "Student2", 140);

        // Test Case 2: 30 students with increasing total marks
        List<Student> students30 = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            int[] marks = new int[7];
            Arrays.fill(marks, i * 2); // Each subject mark = i*2, so total = 14*i
            students30.add(new Student("Student" + i, marks));
        }
        // Highest total: Student30, then Student29, ... so 5th highest: Student26 with total 14*26 = 364
        runTestCase("Test Case 2", students30, "Student26", 14 * 26);

        // Edge Test: Fewer than 5 students
        List<Student> fewStudents = new ArrayList<>();
        fewStudents.add(new Student("A", new int[]{10, 10, 10, 10, 10, 10, 10}));
        fewStudents.add(new Student("B", new int[]{20, 20, 20, 20, 20, 20, 20}));
        fewStudents.add(new Student("C", new int[]{30, 30, 30, 30, 30, 30, 30}));
        fewStudents.add(new Student("D", new int[]{40, 40, 40, 40, 40, 40, 40}));
        Student result = getFifthHighestStudent(fewStudents);
        System.out.println("Edge Test (Fewer than 5): " + (result == null ? "PASS" : "FAIL"));

        // Additional test for large data inputs
        // Create 10000 students with random marks between 0 and 100 for each of 7 subjects.
        List<Student> largeData = new ArrayList<>();
        Random rand = new Random();
        for (int i = 1; i <= 10000; i++) {
            int[] marks = new int[7];
            for (int j = 0; j < 7; j++) {
                marks[j] = rand.nextInt(101);
            }
            largeData.add(new Student("Student" + i, marks));
        }
        // Retrieve and print the 5th highest student's details
        Student fifthHighest = getFifthHighestStudent(largeData);
        if (fifthHighest != null) {
            System.out.println("Large Data Test: 5th Highest Student is " + fifthHighest.name + " with total " + fifthHighest.total);
        } else {
            System.out.println("Large Data Test: Not enough students");
        }
    }

    static class Student {
        String name;
        int[] marks;
        int total;

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
            this.total = Arrays.stream(marks).sum();
        }
    }
}
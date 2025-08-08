package com.interview.notes.code.year.y2025.july.common.test9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {                    // Main class containing everything

    // Main method to run our tests
    public static void main(String[] args) {
        // ----- Test 1: Empty list -----
        List<Student> emptyList = new ArrayList<>();  // Create an empty list
        // Build map: key = student ID, value = student object
        Map<Integer, Student> map1 = emptyList.stream()
                .collect(Collectors.toMap(Student::getId, Function.identity()));
        // Check if map is empty
        if (map1.isEmpty()) {
            System.out.println("Test 1 PASS");        // PASS if empty
        } else {
            System.out.println("Test 1 FAIL");        // FAIL otherwise
        }

        // ----- Test 2: Small list -----
        List<Student> smallList = Arrays.asList(      // Create a list of 3 students
                new Student(101, "Alice", 85.5),
                new Student(102, "Bob", 91.0),
                new Student(103, "Carol", 78.0)
        );
        // Build lookup map from smallList
        Map<Integer, Student> map2 = smallList.stream()
                .collect(Collectors.toMap(Student::getId, Function.identity()));
        // Check size and a sample value
        if (map2.size() == 3
                && map2.get(102).getName().equals("Bob")) {
            System.out.println("Test 2 PASS");        // PASS if size 3 and ID 102 → "Bob"
        } else {
            System.out.println("Test 2 FAIL");        // FAIL otherwise
        }

        // ----- Test 3: Large data list -----
        List<Student> largeList = new ArrayList<>();  // Prepare an ArrayList
        // Fill list with 100,000 Student objects
        for (int i = 1; i <= 100_000; i++) {
            largeList.add(new Student(i, "Std" + i, i % 100));  // Name = "Std"+i, marks = i%100
        }
        // Build lookup map for largeList
        Map<Integer, Student> map3 = largeList.stream()
                .collect(Collectors.toMap(Student::getId, Function.identity()));
        // Check if map size matches list size
        if (map3.size() == largeList.size()) {
            System.out.println("Test 3 PASS");        // PASS if 100,000 entries
        } else {
            System.out.println("Test 3 FAIL");        // FAIL otherwise
        }
    }

    // Define Student class with id, name, and marks
    static class Student {
        private int id;                  // Field to store student ID
        private String name;             // Field to store student name
        private double marks;            // Field to store student marks

        // Constructor to create a Student object from id, name, and marks
        public Student(int id, String name, double marks) {
            this.id = id;               // Assign given id to this.id
            this.name = name;           // Assign given name to this.name
            this.marks = marks;         // Assign given marks to this.marks
        }

        // Getter to retrieve student ID
        public int getId() {
            return id;                  // Return the id field
        }

        // Getter to retrieve student name
        public String getName() {
            return name;                // Return the name field
        }

        // Getter to retrieve student marks
        public double getMarks() {
            return marks;               // Return the marks field
        }

        // toString override for easy printing of Student objects
        @Override
        public String toString() {
            return "Student{id=" + id +
                    ", name='" + name + '\'' +
                    ", marks=" + marks + '}';  // Format the student info
        }
    }
}
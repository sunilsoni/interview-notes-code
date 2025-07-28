package com.interview.notes.code.year.y2025.july.common.test1;

import java.util.*;
import java.util.stream.Collectors;

// Student class to represent student information
class Student {
    private int id;          // Unique identifier for student
    private String name;     // Student's name
    private String className; // Class/Grade of student
    private int marks;       // Academic marks
    private String stream;   // Academic stream (Science, Commerce, etc.)

    // Constructor to initialize student object
    public Student(int id, String name, String className, int marks, String stream) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.marks = marks;
        this.stream = stream;
    }

    // Getter for student ID
    public int getId() {
        return id;
    }

    // toString method for readable output
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", class='" + className + '\'' +
                ", marks=" + marks +
                ", stream='" + stream + '\'' +
                '}';
    }
}

public class StudentLookupDemo {
    public static void main(String[] args) {
        // Test case 1: Normal case with multiple students
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "12A", 85, "Science"));
        students.add(new Student(2, "Alice", "12B", 90, "Commerce"));
        students.add(new Student(3, "Bob", "12A", 75, "Science"));

        // Creating lookup map using Stream API
        Map<Integer, Student> studentLookup = students.stream()
                .collect(Collectors.toMap(
                        s->s.getId(),    // Key mapper (student ID)
                        student -> student // Value mapper (student object)
                ));

        // Test case 2: Looking up existing student
        System.out.println("Test Case 1 - Looking up student with ID 1:");
        Student student1 = studentLookup.get(1);
        System.out.println("Found: " + (student1 != null));
        System.out.println(student1);

        // Test case 3: Looking up non-existent student
        System.out.println("\nTest Case 2 - Looking up non-existent student (ID 99):");
        Student nonExistentStudent = studentLookup.get(99);
        System.out.println("Found: " + (nonExistentStudent != null));

        // Test case 4: Large data test
        System.out.println("\nTest Case 3 - Testing with large dataset:");
        List<Student> largeDataset = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeDataset.add(new Student(
                i, 
                "Student" + i,
                "Class" + (i % 10),
                60 + (i % 40),
                i % 2 == 0 ? "Science" : "Commerce"
            ));
        }

        // Measure time for creating lookup map with large dataset
        long startTime = System.currentTimeMillis();
        Map<Integer, Student> largeLookup = largeDataset.stream()
                .collect(Collectors.toMap(
                        Student::getId,
                        student -> student
                ));
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken for large dataset: " + (endTime - startTime) + "ms");
        System.out.println("Large lookup map size: " + largeLookup.size());
        
        // Verify random access time
        startTime = System.currentTimeMillis();
        Student randomStudent = largeLookup.get(50000);
        endTime = System.currentTimeMillis();
        System.out.println("Random access time: " + (endTime - startTime) + "ms");
        System.out.println("Random student found: " + (randomStudent != null));
    }
}

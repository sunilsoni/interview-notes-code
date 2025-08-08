package com.interview.notes.code.year.y2025.july.common.test8;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String name;
    private String className;
    private int marks;
    private String stream;

    // Constructor
    public Student(int id, String name, String className, int marks, String stream) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.marks = marks;
        this.stream = stream;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public int getMarks() {
        return marks;
    }

    public String getStream() {
        return stream;
    }

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

public class StudentGroupingDemo {
    public static void main(String[] args) {
        // Create sample student data
        List<Student> students = Arrays.asList(
                new Student(1, "John", "12A", 85, "Science"),
                new Student(2, "Alice", "12B", 90, "Commerce"),
                new Student(3, "Bob", "12A", 75, "Science"),
                new Student(4, "Carol", "12B", 95, "Science"),
                new Student(5, "David", "12A", 80, "Commerce")
        );

        // 1. Group students by stream (Science/Commerce)
        Map<String, List<Student>> streamWise = students.stream()
                .collect(Collectors.groupingBy(Student::getStream));

        System.out.println("1. Students grouped by stream:");
        streamWise.forEach((stream, streamStudents) -> {
            System.out.println("\n" + stream + " Students:");
            streamStudents.forEach(student -> System.out.println("  " + student));
        });

        // 2. Group students by class with count
        Map<String, Long> classCount = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getClassName,
                        Collectors.counting()
                ));

        System.out.println("\n2. Number of students in each class:");
        classCount.forEach((className, count) ->
                System.out.println("  " + className + ": " + count + " students"));

        // 3. Group by stream and calculate average marks
        Map<String, Double> streamAverages = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getStream,
                        Collectors.averagingInt(Student::getMarks)
                ));

        System.out.println("\n3. Average marks by stream:");
        streamAverages.forEach((stream, avg) ->
                System.out.println("  " + stream + ": " + String.format("%.2f", avg)));

        // 4. Group by class and find highest marks in each class
        Map<String, Optional<Student>> toppersByClass = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getClassName,
                        Collectors.maxBy(Comparator.comparing(Student::getMarks))
                ));

        System.out.println("\n4. Toppers in each class:");
        toppersByClass.forEach((className, studentOpt) ->
                studentOpt.ifPresent(student ->
                        System.out.println("  " + className + ": " + student.getName() +
                                " (" + student.getMarks() + " marks)")
                ));

        // 5. Group by stream and class (nested grouping)
        Map<String, Map<String, List<Student>>> streamAndClass = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getStream,
                        Collectors.groupingBy(Student::getClassName)
                ));

        System.out.println("\n5. Students grouped by stream and class:");
        streamAndClass.forEach((stream, classMap) -> {
            System.out.println("\n" + stream + ":");
            classMap.forEach((className, studentList) -> {
                System.out.println("  " + className + ":");
                studentList.forEach(student ->
                        System.out.println("    " + student.getName() +
                                " (" + student.getMarks() + " marks)")
                );
            });
        });

        // 6. Performance test with large dataset
        System.out.println("\n6. Performance test with large dataset:");
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

        long startTime = System.currentTimeMillis();
        Map<String, List<Student>> largeGrouping = largeDataset.stream()
                .collect(Collectors.groupingBy(Student::getStream));
        long endTime = System.currentTimeMillis();

        System.out.println("  Time taken for grouping 100,000 students: " +
                (endTime - startTime) + "ms");
        System.out.println("  Number of groups: " + largeGrouping.size());
        largeGrouping.forEach((stream, streamStudents) ->
                System.out.println("  " + stream + " students: " + streamStudents.size())
        );
    }
}

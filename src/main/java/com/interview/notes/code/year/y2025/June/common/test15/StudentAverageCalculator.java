package com.interview.notes.code.year.y2025.June.common.test15;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StudentAverageCalculator {
    
    // Student class
    static class Student {
        private String name;
        private int marks;
        
        public Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
        
        public String getName() {
            return name;
        }
        
        public int getMarks() {
            return marks;
        }
        
        @Override
        public String toString() {
            return "Student{name='" + name + "', marks=" + marks + "}";
        }
    }
    
    public static void main(String[] args) {
        // Create sample student data
        List<Student> students = Arrays.asList(
            new Student("John", 80),
            new Student("Alice", 90),
            new Student("Bob", 85),
            new Student("Carol", 95),
            new Student("David", 75)
        );
        
        System.out.println("Student List:");
        students.forEach(System.out::println);
        System.out.println();
        
        // Method 1: Using mapToInt and average()
        double average1 = students.stream()
            .mapToInt(Student::getMarks)
            .average()
            .orElse(0.0);
        System.out.println("Average using mapToInt(): " + average1);
        
        // Method 2: Using Collectors.averagingInt()
        double average2 = students.stream()
            .collect(Collectors.averagingInt(Student::getMarks));
        System.out.println("Average using Collectors.averagingInt(): " + average2);
        
        // Method 3: Using summaryStatistics()
        IntSummaryStatistics stats = students.stream()
            .mapToInt(Student::getMarks)
            .summaryStatistics();
        System.out.println("\nSummary Statistics:");
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Count: " + stats.getCount());
        
        // Method 4: Manual calculation using reduce()
        double average4 = students.stream()
            .map(Student::getMarks)
            .reduce(0, Integer::sum) / (double) students.size();
        System.out.println("\nAverage using reduce(): " + average4);
        
        // Example with filtering
        double highScorersAverage = students.stream()
            .filter(student -> student.getMarks() > 85)
            .mapToInt(Student::getMarks)
            .average()
            .orElse(0.0);
        System.out.println("\nAverage of students scoring above 85: " + highScorersAverage);
        
        // Example with parallel processing
        double parallelAverage = students.parallelStream()
            .mapToInt(Student::getMarks)
            .average()
            .orElse(0.0);
        System.out.println("Average using parallel stream: " + parallelAverage);
        
        // Example with error handling
        try {
            // Creating an empty list to demonstrate error handling
            List<Student> emptyList = Arrays.asList();
            double averageWithError = emptyList.stream()
                .mapToInt(Student::getMarks)
                .average()
                .orElseThrow(() -> new RuntimeException("No students found"));
        } catch (RuntimeException e) {
            System.out.println("\nError handling demonstration: " + e.getMessage());
        }
    }
}

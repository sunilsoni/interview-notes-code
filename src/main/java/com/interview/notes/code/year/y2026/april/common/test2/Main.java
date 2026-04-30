package com.interview.notes.code.year.y2026.april.common.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Student {
    private final String name;
    private final int rollNumber;
    private final double marks;

    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    // getters for name, rollNumber, marks...

    @Override
    public boolean equals(Object o) {
        // 1. Check for same memory address
        if (this == o) return true;
        // 2. Null check and use pattern matching for type check
        if (!(o instanceof Student student)) return false;
        // 3. Compare unique identifiers
        return rollNumber == student.rollNumber && 
               Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        // 4. Generate hash using the same identity fields
        return Objects.hash(name, rollNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        Map<Student, Double> studentMarksMap = new HashMap<>();

        // Adding students
        Student s1 = new Student("Oishi", 101, 95.0);
        Student s2 = new Student("Aadriti", 102, 98.0);
        
        //studentMarksMap.put(s1, s1.getMarks());
       // studentMarksMap.put(s2, s2.getMarks());

        // Demonstrating retrieval with a "new" object that has the same identity
        Student lookUp = new Student("Oishi", 101, 0.0); // Marks don't matter for lookup
        System.out.println("Marks for Oishi: " + studentMarksMap.get(lookUp));
    }
}
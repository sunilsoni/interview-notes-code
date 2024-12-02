package com.interview.notes.code.year.y2024.july24.test10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main1 {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 89.5));
        students.add(new Student("Bob", 92.0));
        students.add(new Student("Charlie", 88.0));

        Optional<Student> highestGradeStudent = students.stream()
                .max(Comparator.comparingDouble(Student::getGrade));

        highestGradeStudent.ifPresent(student -> System.out.println("Student with the highest grade: " + student.getName()));
    }

    public static class Student {
        private String name;
        private double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public double getGrade() {
            return grade;
        }

        // You might want to override the toString method for easy printing
        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }
}

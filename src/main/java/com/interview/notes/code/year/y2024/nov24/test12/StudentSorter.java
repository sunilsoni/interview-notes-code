package com.interview.notes.code.year.y2024.nov24.test12;

import lombok.Data;

import java.util.List;

@Data
class Student {
    private String name;
    private int sub1;
    private int sub2;
    private int sub3;

    // Constructor, getters, and setters
    // ...

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sub1=" + sub1 +
                ", sub2=" + sub2 +
                ", sub3=" + sub3 +
                '}';
    }
}

public class StudentSorter {
    public static void main(String[] args) {
        List<Student> students = null;
        ;// Initialize your list of students here

      /*  List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparingInt(Student::getSub1)
                        .thenComparingInt(Student::getSub2)
                        .thenComparingInt(Student::getSub3))
                .collect(Collectors.toList());

        // Print sorted students
        sortedStudents.forEach(System.out::println);*/
    }
}

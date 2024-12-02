package com.interview.notes.code.year.y2024.feb24.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

public class TestClass {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 29),
                new Student("Bob", 25),
                new Student("Charlie", 29),
                new Student("David", 25),
                new Student("Eve", 29)
        );

        Map<Integer, Long> ageCount = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        ageCount.forEach((age, count) -> System.out.println(age + ": " + count));
    }


    public static String formatAgeCounts(List<Student> students) {
        Map<Integer, Long> ageCount = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        StringBuilder output = new StringBuilder();
        ageCount.forEach((age, count) -> output.append(age).append(": ").append(count).append("\n"));

        return output.toString();
    }
}

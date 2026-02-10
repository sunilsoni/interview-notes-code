package com.interview.notes.code.year.y2026.feb.common.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

class Student {
    private static int counter = 0;
    private final int enrollmentNumber;
    private final String name;

    public Student(String name) {
        this.enrollmentNumber = ++counter;
        this.name = name;
    }

    @Override
    public String toString() {
        return enrollmentNumber + ": " + name;
    }
}

public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);

    public static void main(String[] args) {
        if (System.getProperty("test") != null) {
            runInternalTests();
            return;
        }

        int numberOfStudents = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfStudents-- > 0) {
            String studentName = INPUT_READER.nextLine();
            Student student = new Student(studentName);
            System.out.println(student);
        }
    }

    private static void runInternalTests() {
        testCase("Standard", List.of("Pat", "Sam", "Chris"), List.of("1: Pat", "2: Sam", "3: Chris"));
        testCase("Large Data", IntStream.rangeClosed(1, 1000).mapToObj(i -> "User" + i).collect(java.util.stream.Collectors.toList()), null);
    }

    private static void testCase(String name, List<String> inputs, List<String> expected) {
        try {
            java.lang.reflect.Field field = Student.class.getDeclaredField("counter");
            field.setAccessible(true);
            field.set(null, 0);

            List<String> results = new ArrayList<>();
            inputs.forEach(in -> results.add(new Student(in).toString()));

            boolean pass = expected == null || IntStream.range(0, expected.size()).allMatch(i -> results.get(i).equals(expected.get(i)));
            System.out.println(name + ": " + (pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println(name + ": FAIL (Error)");
        }
    }
}
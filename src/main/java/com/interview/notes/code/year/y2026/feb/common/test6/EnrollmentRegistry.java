package com.interview.notes.code.year.y2026.feb.common.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class EnrollmentRegistry {
    private static int counter = 0;
    private final int enrollmentNumber;
    private final String name;

    public EnrollmentRegistry(String name) {
        this.enrollmentNumber = ++counter;
        this.name = name;
    }

    public static void main(String[] args) {
        testBasicFlow();
        testLargeDataInput();
    }

    private static void testBasicFlow() {
        counter = 0;
        List<String> inputs = List.of("Pat", "Sam", "Chris");
        List<String> expected = List.of("1: Pat", "2: Sam", "3: Chris");

        List<EnrollmentRegistry> students = new ArrayList<>();
        inputs.forEach(n -> students.add(new EnrollmentRegistry(n)));

        boolean pass = IntStream.range(0, expected.size())
                .allMatch(i -> students.get(i).toString().equals(expected.get(i)));

        System.out.println("Basic Flow: " + (pass ? "PASS" : "FAIL"));
    }

    private static void testLargeDataInput() {
        counter = 0;
        int limit = 1000;
        List<EnrollmentRegistry> largeList = new ArrayList<>();

        IntStream.rangeClosed(1, limit)
                .forEach(i -> largeList.add(new EnrollmentRegistry("Student" + i)));

        boolean pass = largeList.size() == limit &&
                largeList.get(limit - 1).toString().equals("1000: Student1000");

        System.out.println("Large Data (" + limit + "): " + (pass ? "PASS" : "FAIL"));
    }

    @Override
    public String toString() {
        return enrollmentNumber + ": " + name;
    }
}
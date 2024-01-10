package com.interview.notes.code.months.year2023.dec23.test3;

class Student {
    private static int nextEnrollmentNumber = 1;
    private int enrollmentNumber;
    private String name;

    public Student(String name) {
        this.name = name;
        this.enrollmentNumber = nextEnrollmentNumber++;
    }

    @Override
    public String toString() {
        return this.enrollmentNumber + ": " + this.name;
    }
}

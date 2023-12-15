package com.interview.notes.code.months.dec23.test3;

import java.util.ArrayList;
import java.util.List;

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
        return  this.enrollmentNumber + ": " + this.name;
    }
}

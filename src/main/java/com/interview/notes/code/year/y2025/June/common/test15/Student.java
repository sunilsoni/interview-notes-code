package com.interview.notes.code.year.y2025.June.common.test15;

class Student {
    private final String name;
    private final String branch;
    private final int totalMarks;

    // Constructor, Getters, Setters, toString()
    public Student(String name, String branch, int totalMarks) {
        this.name = name;
        this.branch = branch;
        this.totalMarks = totalMarks;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getTotalMarks() {
        return totalMarks;
    }
}
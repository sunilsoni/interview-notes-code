package com.interview.notes.code.months.dec23.test6;

public class Student {
    private String name;
    private int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public static void main(String[] args) {
        // Create a Student object using a constructor reference
        Student student = createStudent("John Doe", 12345);

        // Display the Student's information using a lambda expression
        displayStudentInfo(student);
    }

    // Factory method using a constructor reference
    public static Student createStudent(String name, int rollNumber) {
        return new Student(name, rollNumber);
    }

    // Display Student information using a lambda expression
    public static void displayStudentInfo(Student student) {
        StudentInfoPrinter infoPrinter = (s) -> {
            System.out.println("Name: " + s.getName());
            System.out.println("Roll Number: " + s.getRollNumber());
        };

        infoPrinter.print(student);
    }

    // Functional interface for printing Student information
    @FunctionalInterface
    interface StudentInfoPrinter {
        void print(Student student);
    }
}

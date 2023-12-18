package com.interview.notes.code.months.dec23.test3;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a student registration and student information retrieval system for a school using a simple class roster in Java. When a student is registered, the system must assign an integer ID (enrollmentNumber), starting at 1 and adding 1 as each student is registered. The student's name is stored with the assigned enrollmentNumber. The retrieval request should return a student's registration information.
 * The Student class should implement:
 * • The constructor. Student(String name)
 * • The method String toString() to return the string "{enrollmentNumber}: {name}"
 * The locked stub code in the editor validates the implementation of the Student class.
 * After each student is registered, the code stub requests and prints the student's information to test your code.
 * Constraints
 * • 1 ≤ numberOfStudents ≤ 103
 * • Input Format For Custom Testing
 * The first line contains the value of numberOfStudents that describes the total number of students being registered.
 * Each of the next numberOfStudents lines contains the student name.
 * <p>
 * <p>
 * <p>
 * <p>
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * - - -
 * Function
 * 3
 * →
 * numberOfStudents = 3
 * Pat
 * →
 * student 1 name = 'Pat'
 * Sam
 * →
 * student 2 name = 'Sam'
 * Chris
 * →
 * student 3 name = 'Chris'
 * Sample Output
 * 1: Pat
 * 2: Sam
 * 3: Chris
 * Explanation
 * The three students are registered in the following order:
 * • The first student to be registered is Pat so Pat is assigned 1 as the enrollment number by the portal.
 * • Sam is second, so Sam is assigned the number 2.
 * • Chris is third, so Chris is assigned the number 3.
 * Now, the information of all the students is printed in the order in which they are registered.
 */
public class School {
    private List<Student> students;

    public School() {
        students = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Simulation of the registration process
        School school = new School();
        school.registerStudent("Pat");
        school.registerStudent("Sam");
        school.registerStudent("Chris");

        // Print all students' information
        school.printStudentsInformation();
    }

    public void registerStudent(String name) {
        students.add(new Student(name));
    }

    public void printStudentsInformation() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
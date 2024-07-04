package com.interview.notes.code.months.july24.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;  // Correct import for IntStream

class StudentTest {
    public static void main(String[] args) {
        final List<String> students = Arrays.asList("Student A", "Student B");
        final List<String> courses = Arrays.asList("Course A", "Course B");
        
        List<StudentCourse> studentCourses =
            IntStream.range(0, students.size())  // Correct method to generate a range of indices
                .mapToObj(p -> new StudentCourse(students.get(p), courses.get(p)))  // Correct lambda to create StudentCourse objects
                .collect(Collectors.toList());  // Collect results into a list

        for (StudentCourse studentCourse : studentCourses) {
            System.out.println(studentCourse);
        }
    }
}

class StudentCourse {
    private final String studentName;
    private final String courseName;

    public StudentCourse(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
               "studentName='" + studentName + '\'' +
               ", courseName='" + courseName + '\'' +
               '}';
    }
}

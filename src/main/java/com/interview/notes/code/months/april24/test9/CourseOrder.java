package com.interview.notes.code.months.april24.test9;

import java.util.HashSet;
import java.util.Set;

public class CourseOrder {

    public static String findFirstCourse(String[][] pairs) {
        Set<String> allCourses = new HashSet<>();
        Set<String> secondCourses = new HashSet<>();

        // Populate the sets
        for (String[] pair : pairs) {
            allCourses.add(pair[0]);
            allCourses.add(pair[1]);
            secondCourses.add(pair[1]);
        }

        // The first course is the one that doesn't appear as a second course
        for (String course : allCourses) {
            if (!secondCourses.contains(course)) {
                return course;
            }
        }

        // If we don't find such a course, it means the input might be incorrect
        // or there is a cycle in prerequisites.
        return null;
    }

    public static void main(String[] args) {
        String[][] pairs = {
            {"Algorithms", "Foundations of Computer Science"},
            {"Data Structures", "Algorithms"},
            {"Foundations of Computer Science", "Logic"},
            {"Logic", "Compilers"},
            {"Compilers", "Distributed Systems"}
        };

        String firstCourse = findFirstCourse(pairs);
        System.out.println("The first course should be: " + firstCourse);
    }
}

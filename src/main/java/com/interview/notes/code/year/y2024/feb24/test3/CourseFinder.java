package com.interview.notes.code.year.y2024.feb24.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseFinder {

    // Method to find shared courses between students
    public static Map<String, List<String>> findPairs(String[][] enrollments) {
        Map<String, List<String>> studentCourses = new HashMap<>();
        Map<String, List<String>> sharedCourses = new HashMap<>();

        // Step 1: Parse input data
        for (String[] enrollment : enrollments) {
            studentCourses.computeIfAbsent(enrollment[0], k -> new ArrayList<>()).add(enrollment[1]);
        }

        // Generate unique pairs of students
        List<String> students = new ArrayList<>(studentCourses.keySet());
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                String student1 = students.get(i);
                String student2 = students.get(j);
                String pairKey = student1 + "," + student2;

                // Step 3: Find shared courses
                List<String> courses1 = studentCourses.get(student1);
                List<String> courses2 = studentCourses.get(student2);
                List<String> commonCourses = new ArrayList<>(courses1);
                commonCourses.retainAll(courses2);

                // Step 4: Output data structure
                sharedCourses.put(pairKey, commonCourses);
            }
        }

        return sharedCourses;
    }

    // Main method for testing
    public static void main(String[] args) {
        String[][] enrollments1 = {
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
                {"58", "Software Design"}
        };

        Map<String, List<String>> result = findPairs(enrollments1);
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

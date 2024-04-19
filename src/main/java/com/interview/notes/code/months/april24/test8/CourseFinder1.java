package com.interview.notes.code.months.april24.test8;

import java.util.*;

public class CourseFinder1 {
    public static Map<String, List<String>> findPairs(String[][] enrollments) {
        Map<String, List<String>> studentCourses = new HashMap<>();

        // Populate studentCourses map
        for (String[] enrollment : enrollments) {
            String studentID = enrollment[0];
            String course = enrollment[1];

            // Add course to student's list of courses
            studentCourses.putIfAbsent(studentID, new ArrayList<>());
            studentCourses.get(studentID).add(course);
        }

        // Find common courses between each pair of students
        Map<String, List<String>> sharedCourses = new HashMap<>();
        List<String> studentIds = new ArrayList<>(studentCourses.keySet());
        for (int i = 0; i < studentIds.size(); i++) {
            String student1 = studentIds.get(i);
            List<String> courses1 = studentCourses.get(student1);
            for (int j = i + 1; j < studentIds.size(); j++) {
                String student2 = studentIds.get(j);
                List<String> courses2 = studentCourses.get(student2);

                // Find common courses between students
                List<String> commonCourses = new ArrayList<>(courses1);
                commonCourses.retainAll(courses2);

                // Store common courses in sharedCourses map
                if (!commonCourses.isEmpty()) {
                    sharedCourses.put(student1 + "," + student2, commonCourses);
                }
            }
        }
        return sharedCourses;
    }

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

        String[][] enrollments2 = {
            {"®", "Advanced Mechanics"},
            {"0", "Art History"},
            {"1", "Course 1"}, {"1", "Course 2"},
            {"2", "Computer Architecture"},
            {"3", "Course 1"}, {"3", "Course 2"},
            {"4", "Algorithms"}
        };

        Map<String, List<String>> result1 = findPairs(enrollments1);
        Map<String, List<String>> result2 = findPairs(enrollments2);

        // Print results
        System.out.println("Result for enrollments1:");
        for (Map.Entry<String, List<String>> entry : result1.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nResult for enrollments2:");
        for (Map.Entry<String, List<String>> entry : result2.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
/**
 * Result for enrollments1:
 * 58,94: [Economics]
 * 58,25: [Economics]
 * 58,17: [Linear Algebra, Software Design]
 * 25,94: [Economics]
 *
 * Result for enrollments2:
 * 1,3: [Course 1, Course 2]
 *
 * Process finished with exit code 0
 */

package com.interview.notes.code.months.april24.test8;

import java.util.*;

public class CourseFinder {

    public static Map<String, List<String>> findPairs(List<List<String>> enrollments) {
        // Create a map where the key is the student ID and the value is a set of courses
        Map<String, Set<String>> studentsCourses = new HashMap<>();
        for (List<String> enrollment : enrollments) {
            String studentId = enrollment.get(0);
            String course = enrollment.get(1);
            studentsCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(course);
        }

        // Create a map to store the shared courses between each pair of students
        Map<String, List<String>> sharedCourses = new HashMap<>();

        // Convert the student IDs to a list to access by index
        List<String> studentIds = new ArrayList<>(studentsCourses.keySet());

        // Compare each pair of students
        for (int i = 0; i < studentIds.size(); i++) {
            for (int j = i + 1; j < studentIds.size(); j++) {
                String student1 = studentIds.get(i);
                String student2 = studentIds.get(j);
                // Intersect the sets of courses to find common ones
                Set<String> commonCourses = new HashSet<>(studentsCourses.get(student1));
                commonCourses.retainAll(studentsCourses.get(student2));
                // Add to the map with a key combining both student IDs
                sharedCourses.put(student1 + "," + student2, new ArrayList<>(commonCourses));
            }
        }

        return sharedCourses;
    }

    public static void main(String[] args) {
        List<List<String>> enrollments = Arrays.asList(
                Arrays.asList("58", "Linear Algebra"),
                Arrays.asList("94", "Art History"),
                Arrays.asList("94", "Operating Systems"),
                Arrays.asList("17", "Software Design"),
                Arrays.asList("58", "Mechanics"),
                Arrays.asList("58", "Economics"),
                Arrays.asList("17", "Linear Algebra"),
                Arrays.asList("17", "Political Science"),
                Arrays.asList("94", "Economics"),
                Arrays.asList("25", "Economics"),
                Arrays.asList("58", "Software Design")
        );

        Map<String, List<String>> result = findPairs(enrollments);
        for (String key : result.keySet()) {
            System.out.println(key + " => " + result.get(key));
        }
    }
}
/**
 * 58,94 => [Economics]
 * 25,58 => [Economics]
 * 25,17 => []
 * 58,17 => [Software Design, Linear Algebra]
 * 25,94 => [Economics]
 * 17,94 => []
 * <p>
 * Process finished with exit code 0
 */
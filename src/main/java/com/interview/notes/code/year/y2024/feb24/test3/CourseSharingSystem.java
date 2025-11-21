package com.interview.notes.code.year.y2024.feb24.test3;

import java.util.*;

public class CourseSharingSystem {

    public static Map<String, List<String>> findPairs(List<Pair<String, String>> enrollments) {
        Map<String, List<String>> sharedCourses = new HashMap<>();

        // Create a map to store courses for each student
        Map<String, Set<String>> studentCourses = new HashMap<>();

        // Populate the studentCourses map
        for (Pair<String, String> enrollment : enrollments) {
            String studentId = enrollment.key();
            String course = enrollment.value();

            studentCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(course);
        }

        // Compare courses for each pair of students
        for (String student1 : studentCourses.keySet()) {
            for (String student2 : studentCourses.keySet()) {
                if (!student1.equals(student2)) {
                    Set<String> courses1 = studentCourses.get(student1);
                    Set<String> courses2 = studentCourses.get(student2);

                    // Find shared courses
                    List<String> shared = new ArrayList<>(courses1);
                    shared.retainAll(courses2);

                    // Add to the result map
                    String studentPair = student1 + "," + student2;
                    sharedCourses.put(studentPair, shared);
                }
            }
        }

        return sharedCourses;
    }

    public static void main(String[] args) {
        List<Pair<String, String>> enrollments1 = Arrays.asList(
                new Pair<>("58", "Linear Algebra"),
                new Pair<>("94", "Art History"),
                new Pair<>("94", "Operating Systems"),
                new Pair<>("17", "Software Design"),
                new Pair<>("58", "Mechanics"),
                new Pair<>("58", "Economics"),
                new Pair<>("17", "Linear Algebra"),
                new Pair<>("17", "Political Science"),
                new Pair<>("94", "Economics"),
                new Pair<>("25", "Economics"),
                new Pair<>("58", "Software Design")
        );

        Map<String, List<String>> result = findPairs(enrollments1);

        // Print the result
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        List<Pair<String, String>> enrollments2 = Arrays.asList(
                new Pair("58", "Linear Algebra"),
                new Pair("94", "Art History"),
                new Pair("94", "Operating Systems"),
                new Pair("17", "Software Design"),
                new Pair("58", "Mechanics"),
                new Pair("58", "Economics"),
                new Pair("17", "Linear Algebra"),
                new Pair("17", "Political Science"),
                new Pair("94", "Economics"),
                new Pair("25", "Economics"),
                new Pair("58", "Software Design")
        );

        Map<String, List<String>> pairs2 = findPairs(enrollments2);

        for (Map.Entry<String, List<String>> entry : pairs2.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());

        }
    }
}

package com.interview.notes.code.year.y2025.jan24.test18;

import java.util.*;

public class CourseValidation {
    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Valid Catalog
        Map<String, List<String>> validCatalog = new HashMap<>();
        validCatalog.put("Databases", Arrays.asList("Security", "Logging"));
        validCatalog.put("Security", Arrays.asList("Logging"));
        validCatalog.put("Logging", Collections.emptyList());
        System.out.println("Valid Catalog Test: " + validateCourseCatalog(validCatalog));

        // Test Case 2: Circular Dependency
        Map<String, List<String>> circularCatalog = new HashMap<>();
        circularCatalog.put("Databases", Arrays.asList("Security"));
        circularCatalog.put("Security", Arrays.asList("Databases"));
        System.out.println("Circular Dependency Test: " + validateCourseCatalog(circularCatalog));

        // Test Case 3: Non-Existent Dependency
        Map<String, List<String>> invalidDependencyCatalog = new HashMap<>();
        invalidDependencyCatalog.put("Databases", Arrays.asList("NonExistentCourse"));
        System.out.println("Non-Existent Dependency Test: " + validateCourseCatalog(invalidDependencyCatalog));

        // Large Input Test
        Map<String, List<String>> largeCatalog = generateLargeCatalog(1000);
        System.out.println("Large Catalog Test: " + validateCourseCatalog(largeCatalog));
    }

    // Course Catalog Validation Method
    public static boolean validateCourseCatalog(Map<String, List<String>> catalog) {
        // Check for null or empty catalog
        if (catalog == null || catalog.isEmpty()) {
            return false;
        }

        // Validate dependencies
        Set<String> allCourses = catalog.keySet();

        for (String course : allCourses) {
            // Check if dependencies exist
            if (!validateCourseDependencies(course, catalog, new HashSet<>())) {
                return false;
            }
        }

        return true;
    }

    // Recursive Dependency Validation
    private static boolean validateCourseDependencies(
            String course,
            Map<String, List<String>> catalog,
            Set<String> visited
    ) {
        // Prevent infinite recursion and detect circular dependencies
        if (visited.contains(course)) {
            return false;
        }

        visited.add(course);

        // Validate each dependency
        List<String> dependencies = catalog.getOrDefault(course, Collections.emptyList());
        for (String dependency : dependencies) {
            // Check if dependency exists in catalog
            if (!catalog.containsKey(dependency)) {
                return false;
            }

            // Recursively validate dependencies
            if (!validateCourseDependencies(dependency, catalog, new HashSet<>(visited))) {
                return false;
            }
        }

        return true;
    }

    // Generate Large Catalog for Performance Testing
    private static Map<String, List<String>> generateLargeCatalog(int size) {
        Map<String, List<String>> largeCatalog = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String courseName = "Course" + i;
            List<String> dependencies = new ArrayList<>();

            // Create some dependencies
            if (i > 0) {
                dependencies.add("Course" + (i - 1));
            }

            largeCatalog.put(courseName, dependencies);
        }

        return largeCatalog;
    }
}
package com.interview.notes.code.year.y2025.jan.test18;

import java.util.*;

public class CourseCatalogValidator {
    public static boolean isValidCatalog(Map<String, List<String>> catalog) {
        // Check if all referenced courses exist
        for (List<String> dependencies : catalog.values()) {
            for (String dep : dependencies) {
                if (!catalog.containsKey(dep)) {
                    return false;
                }
            }
        }

        // Check for circular dependencies using DFS
        Set<String> visited = new HashSet<>();
        Set<String> currentPath = new HashSet<>();

        for (String course : catalog.keySet()) {
            if (!visited.contains(course)) {
                if (hasCycle(course, catalog, visited, currentPath)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasCycle(String course, Map<String, List<String>> catalog,
                                    Set<String> visited, Set<String> currentPath) {
        visited.add(course);
        currentPath.add(course);

        for (String dependency : catalog.get(course)) {
            if (!visited.contains(dependency)) {
                if (hasCycle(dependency, catalog, visited, currentPath)) {
                    return true;
                }
            } else if (currentPath.contains(dependency)) {
                return true;
            }
        }

        currentPath.remove(course);
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Valid catalog
        Map<String, List<String>> validCatalog = new HashMap<>();
        validCatalog.put("Databases", Arrays.asList("Security", "Logging"));
        validCatalog.put("Security", Collections.singletonList("Logging"));
        validCatalog.put("Logging", Collections.emptyList());
        System.out.println("Test 1 (Valid catalog): " + isValidCatalog(validCatalog));

        // Test Case 2: Circular dependency
        Map<String, List<String>> circularCatalog = new HashMap<>();
        circularCatalog.put("A", Collections.singletonList("B"));
        circularCatalog.put("B", Collections.singletonList("A"));
        System.out.println("Test 2 (Circular dependency): " + isValidCatalog(circularCatalog));

        // Test Case 3: Missing dependency
        Map<String, List<String>> missingDependency = new HashMap<>();
        missingDependency.put("A", Collections.singletonList("C"));
        missingDependency.put("B", Collections.emptyList());
        System.out.println("Test 3 (Missing dependency): " + isValidCatalog(missingDependency));

        // Test Case 4: Large catalog
        Map<String, List<String>> largeCatalog = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            largeCatalog.put("Course" + i,
                    i > 0 ? Collections.singletonList("Course" + (i - 1)) : Collections.emptyList());
        }
        System.out.println("Test 4 (Large catalog): " + isValidCatalog(largeCatalog));
    }
}

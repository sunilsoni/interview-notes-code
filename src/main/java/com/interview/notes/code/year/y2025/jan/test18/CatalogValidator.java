package com.interview.notes.code.year.y2025.jan.test18;

import java.util.*;

/*

Amazon has a catalog of training courses. Each course has a unique String name and can have a dependency on previous courses that have to be taken before you can start.

An example of a valid catalog might look like this:

```
"Databases": ["Security", "Logging"],
"Security": ["Logging"],
"Logging": []
```

Your task is to write a validation function that will be invoked when someone updates the catalog. It must indicate whether the new catalog structure is valid or it contains errors.

 */
public class CatalogValidator {

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        CatalogValidator validator = new CatalogValidator();

        // Test Case 1: Example from the problem statement (Valid)
        Map<String, List<String>> catalog1 = new HashMap<>();
        catalog1.put("Databases", Arrays.asList("Security", "Logging"));
        catalog1.put("Security", Arrays.asList("Logging"));
        catalog1.put("Logging", Arrays.asList());
        System.out.println("Test Case 1: " + (validator.isValidCatalog(catalog1) ? "PASS" : "FAIL"));

        // Test Case 2: Simple cycle (Invalid)
        Map<String, List<String>> catalog2 = new HashMap<>();
        catalog2.put("A", Arrays.asList("B"));
        catalog2.put("B", Arrays.asList("A"));
        System.out.println("Test Case 2: " + (!validator.isValidCatalog(catalog2) ? "PASS" : "FAIL"));

        // Test Case 3: Self-dependency (Invalid)
        Map<String, List<String>> catalog3 = new HashMap<>();
        catalog3.put("A", Arrays.asList("A"));
        System.out.println("Test Case 3: " + (!validator.isValidCatalog(catalog3) ? "PASS" : "FAIL"));

        // Test Case 4: Non-existent dependency (Invalid)
        Map<String, List<String>> catalog4 = new HashMap<>();
        catalog4.put("A", Arrays.asList("B"));
        System.out.println("Test Case 4: " + (!validator.isValidCatalog(catalog4) ? "PASS" : "FAIL"));

        // Test Case 5: Multiple independent chains (Valid)
        Map<String, List<String>> catalog5 = new HashMap<>();
        catalog5.put("A", Arrays.asList("B"));
        catalog5.put("B", Arrays.asList());
        catalog5.put("C", Arrays.asList("D"));
        catalog5.put("D", Arrays.asList());
        System.out.println("Test Case 5: " + (validator.isValidCatalog(catalog5) ? "PASS" : "FAIL"));

        // Test Case 6: Large catalog without cycles (Valid)
        Map<String, List<String>> catalog6 = new HashMap<>();
        int largeSize = 1000;
        for (int i = 0; i < largeSize; i++) {
            String course = "Course" + i;
            List<String> deps = new ArrayList<>();
            if (i > 0) {
                deps.add("Course" + (i - 1));
            }
            catalog6.put(course, deps);
        }
        System.out.println("Test Case 6: " + (validator.isValidCatalog(catalog6) ? "PASS" : "FAIL"));

        // Test Case 7: Large catalog with a cycle (Invalid)
        Map<String, List<String>> catalog7 = new HashMap<>();
        for (int i = 0; i < largeSize; i++) {
            String course = "Course" + i;
            List<String> deps = new ArrayList<>();
            if (i > 0) {
                deps.add("Course" + (i - 1));
            }
            catalog7.put(course, deps);
        }
        // Introduce a cycle
        catalog7.get("Course0").add("Course999");
        System.out.println("Test Case 7: " + (!validator.isValidCatalog(catalog7) ? "PASS" : "FAIL"));

        // Test Case 8: Empty catalog (Valid)
        Map<String, List<String>> catalog8 = new HashMap<>();
        System.out.println("Test Case 8: " + (validator.isValidCatalog(catalog8) ? "PASS" : "FAIL"));

        // Test Case 9: Null catalog (Invalid)
        System.out.println("Test Case 9: " + (!validator.isValidCatalog(null) ? "PASS" : "FAIL"));
    }

    /**
     * Validates the course catalog.
     *
     * @param catalog A map where the key is the course name and the value is a list of dependencies.
     * @return true if the catalog is valid, false otherwise.
     */
    public boolean isValidCatalog(Map<String, List<String>> catalog) {
        // Check for null or empty catalog
        if (catalog == null) {
            return false;
        }

        // Ensure all dependencies reference existing courses
        for (Map.Entry<String, List<String>> entry : catalog.entrySet()) {
            for (String dependency : entry.getValue()) {
                if (!catalog.containsKey(dependency)) {
                    return false; // Dependency does not exist
                }
            }
        }

        // Use DFS to detect cycles
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String course : catalog.keySet()) {
            if (!visited.contains(course)) {
                if (isCyclic(course, catalog, visited, recursionStack)) {
                    return false; // Cycle detected
                }
            }
        }

        return true; // No cycles detected
    }

    /**
     * Helper method to perform DFS and detect cycles.
     *
     * @param current        The current course being visited.
     * @param catalog        The course catalog.
     * @param visited        Set of already visited courses.
     * @param recursionStack Set of courses in the current DFS path.
     * @return true if a cycle is detected, false otherwise.
     */
    private boolean isCyclic(String current, Map<String, List<String>> catalog,
                             Set<String> visited, Set<String> recursionStack) {
        visited.add(current);
        recursionStack.add(current);

        for (String neighbor : catalog.get(current)) {
            if (!visited.contains(neighbor)) {
                if (isCyclic(neighbor, catalog, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                return true; // Cycle detected
            }
        }

        recursionStack.remove(current);
        return false;
    }
}

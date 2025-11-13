package com.interview.notes.code.year.y2025.november.oci.test1;

import java.util.*;

public class FamilyHierarchy {

    // This method finds all ancestors of a person using recursion
    public static List<String> findAncestors(int n, List<List<String>> relationships, String person) {
        // Map to store child → (father, mother)
        Map<String, List<String>> familyMap = new HashMap<>();

        // Fill the map with relationships
        for (List<String> relation : relationships) {
            String father = relation.get(0); // First name is father
            String mother = relation.get(1); // Second name is mother
            String child = relation.get(2);  // Third name is child
            familyMap.put(child, Arrays.asList(father, mother)); // Map child to parents
        }

        // Set to store ancestors in order and avoid duplicates
        LinkedHashSet<String> result = new LinkedHashSet<>();

        // Recursive helper to collect ancestors
        collectAncestors(person, familyMap, result, new HashSet<>());

        // Convert set to list and return
        return new ArrayList<>(result);
    }

    // Recursive method to collect ancestors
    private static void collectAncestors(String person, Map<String, List<String>> familyMap,
                                         LinkedHashSet<String> result, Set<String> visited) {
        // Avoid infinite loops in case of cycles
        if (visited.contains(person)) return;
        visited.add(person);

        // Get parents of the person
        List<String> parents = familyMap.get(person);
        if (parents == null) return; // No parents found

        String father = parents.get(0);
        String mother = parents.get(1);

        // First collect father's ancestors
        collectAncestors(father, familyMap, result, visited);
        result.add(father); // Then add father

        // Then collect mother's ancestors
        collectAncestors(mother, familyMap, result, visited);
        result.add(mother); // Then add mother
    }

    // Main method to test multiple cases
    public static void main(String[] args) {
        // Test Case 1: Sample from problem
        List<List<String>> relationships1 = Arrays.asList(
                Arrays.asList("Ronald", "Paula", "Jason"),
                Arrays.asList("Travis", "Judy", "Mary"),
                Arrays.asList("Jason", "Mary", "Benjamin"),
                Arrays.asList("Homer", "Marge", "Bart"),
                Arrays.asList("Ervin", "Marie", "Paula"),
                Arrays.asList("Clancy", "Jacky", "Marge")
        );
        String person1 = "Benjamin";
        List<String> expected1 = Arrays.asList("Ronald", "Ervin", "Marie", "Paula", "Jason", "Travis", "Judy", "Mary");
        testCase(relationships1, person1, expected1, "Test Case 1");

        // Test Case 2: Person with no parents
        List<List<String>> relationships2 = List.of(
                Arrays.asList("A", "B", "C")
        );
        String person2 = "A";
        List<String> expected2 = Collections.emptyList();
        testCase(relationships2, person2, expected2, "Test Case 2");

        // Test Case 3: Large data test
        List<List<String>> relationships3 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            relationships3.add(Arrays.asList("F" + i, "M" + i, "C" + i));
        }
        relationships3.add(Arrays.asList("C99999", "M99999", "Target"));
        String person3 = "Target";
        List<String> expected3 = Arrays.asList("F99999", "M99999", "C99999");
        testCase(relationships3, person3, expected3, "Test Case 3 - Large Data");
    }

    // Method to test and print PASS/FAIL
    private static void testCase(List<List<String>> relationships, String person,
                                 List<String> expected, String testName) {
        List<String> actual = findAncestors(relationships.size(), relationships, person);
        if (actual.containsAll(expected) && expected.containsAll(actual)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Actual  : " + actual);
        }
    }
}

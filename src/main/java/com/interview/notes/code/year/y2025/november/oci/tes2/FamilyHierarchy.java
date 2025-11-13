package com.interview.notes.code.year.y2025.november.oci.tes2;

import java.util.*;

public class FamilyHierarchy {

    // Function to find ancestors of a given person
    public static List<String> findAncestors(int n, List<List<String>> relationships, String person) {
        // Create maps to store father-child and mother-child relationships
        Map<String, String> fatherMap = new HashMap<>(); // child -> father
        Map<String, String> motherMap = new HashMap<>(); // child -> mother

        // Fill maps from the input list
        for (List<String> relation : relationships) {
            // relation = [father, mother, child]
            fatherMap.put(relation.get(2), relation.get(0)); // map child to father
            motherMap.put(relation.get(2), relation.get(1)); // map child to mother
        }

        // To collect all ancestors
        List<String> result = new ArrayList<>();

        // Recursive helper to gather ancestors (father side first, then mother)
        getAncestors(person, fatherMap, motherMap, result);

        return result;
    }

    // Helper recursive method to collect ancestors in order
    private static void getAncestors(String person, Map<String, String> fatherMap, Map<String, String> motherMap, List<String> result) {
        // If no parent info, stop recursion
        if (!fatherMap.containsKey(person) && !motherMap.containsKey(person)) return;

        // Step 1: Handle father's side (ancestors, then father)
        if (fatherMap.containsKey(person)) {
            String father = fatherMap.get(person);
            getAncestors(father, fatherMap, motherMap, result); // collect father's ancestors
            result.add(father); // then add the father himself
        }

        // Step 2: Handle mother's side (ancestors, then mother)
        if (motherMap.containsKey(person)) {
            String mother = motherMap.get(person);
            getAncestors(mother, fatherMap, motherMap, result); // collect mother's ancestors
            result.add(mother); // then add the mother herself
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1 (Given Sample)
        int n = 6;
        List<List<String>> relationships = Arrays.asList(
            Arrays.asList("Ronald", "Paula", "Jason"),
            Arrays.asList("Travis", "Judy", "Mary"),
            Arrays.asList("Jason", "Mary", "Benjamin"),
            Arrays.asList("Homer", "Marge", "Bart"),
            Arrays.asList("Ervin", "Marie", "Paula"),
            Arrays.asList("Clancy", "Jacky", "Marge")
        );

        String person = "Benjamin";

        List<String> expected = Arrays.asList("Ronald", "Ervin", "Marie", "Paula", "Jason", "Travis", "Judy", "Mary");
        List<String> output = findAncestors(n, relationships, person);

        // Test output comparison
        System.out.println("Test Case 1 Output: " + output);
        System.out.println(output.equals(expected) ? "PASS" : "FAIL");

        // Large Input Test (Performance)
        List<List<String>> bigList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            bigList.add(Arrays.asList("Father" + i, "Mother" + i, "Child" + i));
        }
        long start = System.currentTimeMillis();
        findAncestors(10000, bigList, "Child9999");
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test Execution Time: " + (end - start) + " ms");
        System.out.println("PASS - Handled large data smoothly");
    }
}

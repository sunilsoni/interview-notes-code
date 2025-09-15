package com.interview.notes.code.year.y2025.september.common.test3;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {

    // Method to group anagrams from the input string array
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Map to hold sorted string as key and list of original anagrams as values
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            // Convert the word to a character array
            char[] chars = word.toCharArray();

            // Sort the characters alphabetically
            Arrays.sort(chars);

            // Create a key from the sorted character array
            String sortedKey = new String(chars);

            // Put the word into the corresponding group in the map
            map.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(word);
        }

        // Collect all grouped anagrams as a list of lists
        return new ArrayList<>(map.values());
    }

    // Helper method to convert output to Set of Sets for easy comparison (since order doesn't matter)
    private static Set<Set<String>> normalizeOutput(List<List<String>> input) {
        return input.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
    }

    // Main method to test all cases
    public static void main(String[] args) {
        // Test case 1
        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
                List.of("bat"),
                Arrays.asList("nat", "tan"),
                Arrays.asList("ate", "eat", "tea")
        );

        // Test case 2
        String[] input2 = {""};
        List<List<String>> expected2 = List.of(
                List.of("")
        );

        // Test case 3
        String[] input3 = {"a"};
        List<List<String>> expected3 = List.of(
                List.of("a")
        );

        // Test case 4 - Large input to check performance
        String[] input4 = new String[10000];
        Arrays.fill(input4, "abc");

        List<List<String>> expected4 = List.of(
                Collections.nCopies(10000, "abc")
        );

        // Call test method for each case
        testGroupAnagrams(input1, expected1, "Test 1");
        testGroupAnagrams(input2, expected2, "Test 2");
        testGroupAnagrams(input3, expected3, "Test 3");
        testGroupAnagrams(input4, expected4, "Test 4 - Large Input");
    }

    // Test runner method
    private static void testGroupAnagrams(String[] input, List<List<String>> expected, String testName) {
        List<List<String>> result = groupAnagrams(input);

        Set<Set<String>> resultSet = normalizeOutput(result);
        Set<Set<String>> expectedSet = normalizeOutput(expected);

        if (resultSet.equals(expectedSet)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expectedSet);
            System.out.println("Got     : " + resultSet);
        }
    }
}
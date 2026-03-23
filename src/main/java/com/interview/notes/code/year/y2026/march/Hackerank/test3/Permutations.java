package com.interview.notes.code.year.y2026.march.Hackerank.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations { // Wrapper class for our logic

    // Main method to generate permutations, takes array of strings
    public static List<String> find(String[] arr) { 
        List<String> res = new ArrayList<>(); // Creates an empty list to store our generated strings
        boolean[] used = new boolean[arr.length]; // Checklist array so we don't reuse the same letter in a single word
        dfs(arr, used, "", res); // Kicks off the recursive builder starting with an empty string
        // Stream API sorts by length descending (b.length - a.length) and collects to a List (Java 16+ feature)
        return res.stream().sorted((a, b) -> b.length() - a.length()).toList(); 
    }

    // Recursive helper function to build the strings letter by letter
    static void dfs(String[] arr, boolean[] used, String cur, List<String> res) { 
        if (!cur.isEmpty()) res.add(cur); // If our current string has characters, save it to the result list
        for (int i = 0; i < arr.length; i++) { // Loop through every available letter in the input array
            if (!used[i]) { // Check our checklist to see if this letter is available to use
                used[i] = true; // Mark the letter as 'used' for this specific path
                dfs(arr, used, cur + arr[i], res); // Recursively call the method, adding the new letter to our word
                used[i] = false; // "Backtrack" - unmark the letter so it can be used in completely different combinations
            }
        }
    }

    // Standard Java main method acting as our test suite (No JUnit)
    public static void main(String[] args) { 
        // Run first base test case provided in requirements
        test(new String[]{"a", "b", "c"}, List.of("abc", "acb", "bac", "bca", "cab", "cba", "ab", "ba", "ac", "ca", "bc", "cb", "a", "b", "c"));
        
        // Run edge case with smaller input
        test(new String[]{"x", "y"}, List.of("xy", "yx", "x", "y")); 
        
        System.out.println("Running large data input test (6 items)..."); // Print log for large input processing
        List<String> large = find(new String[]{"1", "2", "3", "4", "5", "6"}); // Process 6 items. Factorial math dictates 1956 variations.
        System.out.println(large.size() == 1956 ? "PASS -> Large Input" : "FAIL -> Large Input"); // Verify total generated variations handles load
    }

    // Helper method to validate arrays against expected outputs
    static void test(String[] in, List<String> exp) { 
        List<String> res = find(in); // Run our algorithm on the input array
        // Validates: Do we have the exact right amount of results AND are all expected items present?
        boolean passed = res.size() == exp.size() && res.containsAll(exp); 
        System.out.println((passed ? "PASS" : "FAIL") + " -> Input: " + Arrays.toString(in)); // Console output of the test result
    }
}
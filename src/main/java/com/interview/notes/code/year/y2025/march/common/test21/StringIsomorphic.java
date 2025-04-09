// Package declaration indicating the location of the file in the project structure
package com.interview.notes.code.year.y2025.march.common.test21;

// Importing required classes from the Java standard library

import java.util.HashMap;
import java.util.Map;

// Main class containing the isomorphic string logic
public class StringIsomorphic {

    // Main method - Entry point of the program
    public static void main(String[] args) {
        // Test different pairs of strings to check if they are isomorphic
        test("egg", "add");      // Expected: true (e->a, g->d)
        test("foo", "bar");      // Expected: false (o maps to two different characters)
        test("paper", "title");  // Expected: true (p->t, a->i, e->l, r->e)
        test("badc", "baba");    // Expected: false (conflicting mapping for d and a)
        test("", "");            // Expected: true (empty strings are trivially isomorphic)
        test("a", "b");          // Expected: true (single characters can be mapped)
    }

    // Method that determines whether two strings are isomorphic
    public static boolean isIsomorphic(String s, String t) {
        // Step 1: If strings have different lengths, they cannot be isomorphic
        if (s.length() != t.length()) {
            return false;
        }

        // Step 2: Initialize two hash maps to track character mapping in both directions
        Map<Character, Character> sToT = new HashMap<>(); // Mapping from string s -> t
        Map<Character, Character> tToS = new HashMap<>(); // Mapping from string t -> s

        // Step 3: Loop through each character index
        for (int i = 0; i < s.length(); i++) {
            // Get the characters at the current index
            char charS = s.charAt(i); // Character from first string
            char charT = t.charAt(i); // Corresponding character from second string

            // Step 4: Check if current charS is already mapped in sToT
            if (sToT.containsKey(charS)) {
                // If mapped, the value must match charT; otherwise, not isomorphic
                if (sToT.get(charS) != charT) {
                    return false;
                }
            } else {
                // If not mapped, create a new mapping from charS -> charT
                sToT.put(charS, charT);
            }

            // Step 5: Similarly, check if current charT is already mapped in tToS
            if (tToS.containsKey(charT)) {
                // If mapped, the value must match charS; otherwise, not isomorphic
                if (tToS.get(charT) != charS) {
                    return false;
                }
            } else {
                // If not mapped, create a new mapping from charT -> charS
                tToS.put(charT, charS);
            }
        }

        // Step 6: If loop completes without mismatches, strings are isomorphic
        return true;
    }

    // Helper method to format and print the result of each test case
    private static void test(String s, String t) {
        boolean result = isIsomorphic(s, t); // Call the main logic
        // Print test result in a readable format
        System.out.printf("Test: '%s' and '%s' -> %b%n", s, t, result);
    }
}

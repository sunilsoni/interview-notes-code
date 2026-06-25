package com.interview.notes.code.year.y2026.june.common.test4;

import java.util.Arrays; // Provides the method used to compare two arrays.

public class AnagramChecker { // Defines the program class.

    static boolean isAnagram(String first, String second) { // Checks whether two strings are anagrams.

        if (first == null || second == null) return false; // Null strings cannot be compared.

        if (first.length() != second.length()) return false; // Anagrams must have the same length.

        return Arrays.equals( // Compares the two sorted character arrays.

                first.chars().sorted().toArray(), // Sorts characters of the first string.

                second.chars().sorted().toArray() // Sorts characters of the second string.

        ); // Returns true when both sorted arrays are equal.

    } // Ends the anagram method.

    static void test(String name, String first, String second, boolean expected) { // Runs one test case.

        boolean actual = isAnagram(first, second); // Gets the actual result.

        System.out.println( // Prints the test result.

                (actual == expected ? "PASS" : "FAIL") + " | " + name // Compares actual and expected results.

        ); // Ends the print statement.

    } // Ends the test method.

    public static void main(String[] args) { // Starts the program.

        test("Given input", "manoj", "anjom", true); // Tests the given strings.

        test("Normal anagram", "java", "avaj", true); // Tests another valid anagram.

        test("Not anagram", "java", "javascript", false); // Tests different strings.

        test("Empty strings", "", "", true); // Tests empty strings.

        test("Null input", null, "abc", false); // Tests a null value.

        String largeFirst = "a".repeat(100_000) + "b"; // Creates a large test string.

        String largeSecond = "b" + "a".repeat(100_000); // Creates its large anagram.

        test("Large input", largeFirst, largeSecond, true); // Tests large input handling.

    } // Ends the main method.

} // Ends the class.
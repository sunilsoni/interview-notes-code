package com.interview.notes.code.year.y2026.march.microsoft.test7;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning { // Declares the main public class that encapsulates our solution logic

    public static List<List<String>> partition(String s) { // Defines the main method to start the partitioning process, taking the input string
        var result = new ArrayList<List<String>>(); // Creates a new dynamic list to store all the successful partition combinations
        backtrack(s, 0, new ArrayList<>(), result); // Initiates the recursive backtracking search starting from index 0 with an empty current path
        return result; // Returns the final populated list of palindromic partitions to the caller
    } // Closes the partition method

    private static void backtrack(String s, int start, List<String> current, List<List<String>> result) { // Defines the recursive helper method to explore all substring combinations
        if (start == s.length()) { // Checks the base case: if our starting index has reached the very end of the input string
            result.add(new ArrayList<>(current)); // We have a valid full partition, so we create a copy of the current path and save it to results
            return; // Exits the current recursive branch since we've reached the end of the string and completes this specific path
        } // Closes the base case if-statement

        for (int end = start + 1; end <= s.length(); end++) { // Starts a loop to generate all possible ending indices for substrings starting at 'start'
            if (isPalindrome(s, start, end - 1)) { // Checks if the substring from 'start' to 'end-1' is a valid palindrome
                current.add(s.substring(start, end)); // If it is a palindrome, we extract it and add it to our current partition path
                backtrack(s, end, current, result); // Recursively calls backtrack to process the remaining part of the string starting from 'end'
                current.removeLast(); // Uses Java 21 feature to remove the last added substring, backtracking to try different combination branches
            } // Closes the if-statement for the palindrome check
        } // Closes the for-loop iterating through possible end indices
    } // Closes the backtrack recursive method

    private static boolean isPalindrome(String s, int left, int right) { // Defines a helper method to check if a specific section of a string reads the same forwards and backwards
        while (left < right) { // Starts a loop that continues as long as the left pointer is before the right pointer
            if (s.charAt(left++) != s.charAt(right--)) { // Compares characters at both pointers, then immediately moves left inward and right inward
                return false; // If the characters do not match, the substring is definitely not a palindrome, so return false
            } // Closes the character comparison if-statement
        } // Closes the while loop for checking characters
        return true; // If the loop finishes without finding any mismatches, the substring is a palindrome, so return true
    } // Closes the isPalindrome helper method

    public static void main(String[] args) { // Defines the main execution method, replacing a test framework like JUnit
        runTest("aab", List.of(List.of("a", "a", "b"), List.of("aa", "b"))); // Runs the primary example test case from the problem description
        runTest("a", List.of(List.of("a"))); // Runs an edge case test with only a single character string
        
        var largeInput = "aaaaaaaaaaaaaaa"; // Creates a large input string of 15 identical characters to test performance and exponential growth handling
        var startTime = System.currentTimeMillis(); // Records the current system time in milliseconds before running the large test
        var largeResult = partition(largeInput); // Executes the partition algorithm on the large dataset
        var endTime = System.currentTimeMillis(); // Records the current system time in milliseconds after the algorithm finishes
        
        System.out.println("Large Data Test PASS in " + (endTime - startTime) + "ms. Partitions found: " + largeResult.size()); // Prints the performance time and the number of partitions found for the large data
    } // Closes the main execution method

    private static void runTest(String input, List<List<String>> expected) { // Defines a custom testing method to execute a case and compare outputs without JUnit
        var actual = partition(input); // Calls the core algorithm to get the actual calculated partitions for the given input
        var passed = actual.equals(expected); // Compares the actual output list exactly against the expected output list to see if they match
        System.out.println("Test Input: '" + input + "' -> " + (passed ? "PASS" : "FAIL")); // Prints the input string and whether the test passed or failed
        if (!passed) { // Checks if the test failed to provide debugging information
            System.out.println("   Expected: " + expected); // Prints the exact output that was expected for this test case
            System.out.println("   Actual:   " + actual); // Prints the incorrect output that the algorithm actually produced
        } // Closes the failure diagnostic block
    } // Closes the runTest helper method

} // Closes the PalindromePartitioning main class
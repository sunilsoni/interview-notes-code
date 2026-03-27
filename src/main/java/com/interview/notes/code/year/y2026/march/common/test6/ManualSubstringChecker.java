package com.interview.notes.code.year.y2026.march.common.test6;

// A Java Record to cleanly store our test inputs and the expected boolean result without boilerplate code.
record TestCase(String word, String word2, boolean expected) {} // We need this to efficiently loop through our test scenarios later.

public class ManualSubstringChecker { // A public class is required to encapsulate our logic and run the program.

    public static boolean checkWordExists(String word, String word2) { // Our custom method to check for substrings without using built-in methods.
        
        if (word == null || word2 == null) return false; // Safety check: if either string is missing (null), we return false to prevent application crashes.
        if (word.isEmpty()) return true; // Edge case: an empty search string is technically always found inside any valid string.
        if (word.length() > word2.length()) return false; // Optimization: if the search word is longer than the target, it's impossible to find a match.

        // We loop through the full string, but stop early if the remaining characters are fewer than our search word's length.
        for (int i = 0; i <= word2.length() - word.length(); i++) { // 'i' represents our starting position in the long string (word2).
            
            int j = 0; // 'j' is used to track how many consecutive characters match our search word.
            
            // We use a while loop to check characters one by one, stopping if they don't match or if we reach the end of the search word.
            while (j < word.length() && word2.charAt(i + j) == word.charAt(j)) { // We check if the character in word2 matches the character in word.
                j++; // If it matches, we increment 'j' to check the very next character in the sequence.
            } // Closes the while loop.
            
            if (j == word.length()) return true; // If 'j' grew to be the exact length of our search word, we found a complete match and return true immediately.
            
        } // Closes the outer for loop. If the loop finishes completely, it means we never found a match.
        
        return false; // If we checked every possible starting position and found nothing, we return false.
    } // Closes the checkWordExists method.

    public static void main(String[] args) { // The standard main method required by Java to execute our code.
        
        var largeText = "abc".repeat(50000) + "cat" + "xyz".repeat(50000); // We build a massive string using 'repeat' to test how our manual loop handles large data.
        
        var tests = java.util.List.of( // We use an immutable list to hold all our test cases in one clean block.
            new TestCase("cat", "abcabdcat", true), // Standard test case provided in your prompt.
            new TestCase("dog", "abcabdcat", false), // Standard failing case to ensure our loops don't accidentally return true.
            new TestCase("cat", "cat", true), // Edge case: both strings are identical.
            new TestCase("", "abc", true), // Edge case: checking an empty string.
            new TestCase(null, "abc", false), // Edge case: null search word.
            new TestCase("cat", null, false), // Edge case: null target word.
            new TestCase("cat", largeText, true) // Large data test case to ensure our manual loop doesn't timeout on huge inputs.
        ); // Closes the list of test cases.

        tests.stream().forEach(test -> { // We use the Java 8 Stream API to iterate through each test case cleanly.
            
            boolean actual = checkWordExists(test.word(), test.word2()); // We execute our manual loop method and store the result.
            
            boolean isPass = (actual == test.expected()); // We compare our manual result against the expected result to see if the test passed.
            
            String status = isPass ? "PASS" : "FAIL"; // We use a ternary operator to create a clean PASS/FAIL text label.
            
            // We print the final result of each test to the console for easy verification.
            System.out.println(status + " -> Word: '" + test.word() + "' | Expected: " + test.expected() + " | Actual: " + actual);
            
        }); // Closes the forEach loop.
    } // Closes the main method.
} // Closes the ManualSubstringChecker class.
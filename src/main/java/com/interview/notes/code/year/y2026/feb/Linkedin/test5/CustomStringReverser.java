package com.interview.notes.code.year.y2026.feb.Linkedin.test5;

public class CustomStringReverser { // Defines the main class to hold our custom algorithm and tests

    public static String reverse(String str) { // Declares a method that takes a String input and outputs the reversed String
        if (str == null) return null; // Safely catches null inputs immediately to prevent the program from crashing
        
        var chars = str.toCharArray(); // Converts the text into a mutable array of characters so we can move them around
        var left = 0; // Creates a starting pointer at the very first character's index (position 0)
        var right = chars.length - 1; // Creates an ending pointer at the very last character's index
        
        while (left < right) { // Loops continuously until the two pointers meet in the exact middle of the array
            var temp = chars[left]; // Saves the character at the left pointer into a temporary variable so it isn't erased
            chars[left] = chars[right]; // Overwrites the left position with the character currently at the right position
            chars[right] = temp; // Places the saved left character into the right position, completing the swap
            
            left++; // Moves the left pointer one step closer to the center
            right--; // Moves the right pointer one step closer to the center
        } // Closes the loop once every character pair has been successfully swapped
        
        return new String(chars); // Packages the modified character array back into a standard String and returns it
    } // Closes the custom reverse method

    public static void main(String[] args) { // Serves as the standard entry point to execute our manual test cases
        
        // --- STANDARD & EDGE CASE TESTING --- // Visually separates the standard test section for better readability
        var testInputs = new String[]{"hello", "", "a", "racecar", null}; // Sets up an array with normal, empty, single, palindrome, and null inputs
        var expectedOutputs = new String[]{"olleh", "", "a", "racecar", null}; // Sets up an array with the exact correct answers for comparison

        for (var i = 0; i < testInputs.length; i++) { // Loops sequentially through every test case index provided above
            var result = reverse(testInputs[i]); // Executes our custom reverse logic on the current test string
            var passed = (result == null && expectedOutputs[i] == null) || (result != null && result.equals(expectedOutputs[i])); // Checks if the result matches the expectation, handling nulls safely
            System.out.println("Test " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") + " -> Input: '" + testInputs[i] + "'"); // Prints PASS or FAIL to the console for immediate feedback
        } // Closes the standard testing loop

        // --- LARGE DATA TESTING --- // Visually separates the stress test section for large data sets
        var largeInput = "a".repeat(10000000) + "b"; // Java 11+: Generates a massive string of 10 million 'a's ending with one 'b' to test memory capability
        var largeExpected = "b" + "a".repeat(10000000); // Generates the exact expected result for the massive string
        
        var startTime = System.currentTimeMillis(); // Records the exact system time before the massive reversal begins
        var largeResult = reverse(largeInput); // Passes the 10-million character string into our custom method
        var endTime = System.currentTimeMillis(); // Records the exact system time the moment the reversal finishes
        
        var largePassed = largeExpected.equals(largeResult); // Verifies that our custom algorithm successfully reversed the massive string
        System.out.println("Large Data Test: " + (largePassed ? "PASS" : "FAIL") + " (Time taken: " + (endTime - startTime) + "ms)"); // Outputs the stress test result and execution speed in milliseconds
    } // Closes the main execution method
} // Closes the entire class definition
package com.interview.notes.code.year.y2026.march.common.test2;

import java.util.stream.Stream;

// Java 14+ Record feature creates a simple class for test cases with zero boilerplate code
record TestCase(String s, boolean expected, String name) {}

// Main class containing our logic and custom testing framework
public class ValidParentheses {

    // Main method acts as our custom test runner instead of using JUnit
    public static void main(String[] args) {
        
        // Use StringBuilder for highly efficient string concatenation for our large data test
        var largeStringBuilder = new StringBuilder(); 
        
        // Loop 50,000 times to create a massive 100,000 character string
        for (var i = 0; i < 50_000; i++) {
            
            // Append a valid pair of parentheses to the string builder during each loop
            largeStringBuilder.append("()"); 
            
        } // End of large string population loop
        
        // Convert the finished builder to a final String object for testing
        var largeString = largeStringBuilder.toString();

        // Use Stream API to process all test cases cleanly in one flow
        Stream.of(
            // Example 1 from the provided problem image
            new TestCase("[()()]{}", true, "Example 1"),
            // Example 2 from the provided problem image (mismatched closing order)
            new TestCase("([{]}){}", false, "Example 2"),
            // Edge case: single character which is mathematically impossible to be balanced
            new TestCase("[", false, "Single Bracket Edge Case"),
            // Edge case: starts with a closing bracket which instantly invalidates it
            new TestCase("][", false, "Start with Closing Bracket"),
            // Performance case: massive dataset to ensure O(N) efficiency doesn't time out
            new TestCase(largeString, true, "Large Data Performance Test")
            
        // Iterate through the stream of our defined test cases
        ).forEach(test -> {
            
            // Execute the core algorithm against the test case input string
            var actual = isValid(test.s());
            
            // Determine if the actual boolean result matches our expected boolean result
            var isPass = actual == test.expected();
            
            // Format the output string using a ternary operator for brevity (PASS or FAIL)
            var status = isPass ? "PASS" : "FAIL";
            
            // Print the final test result clearly to the console for the user to see
            System.out.println(test.name() + ": " + status + " (Expected: " + test.expected() + ", Got: " + actual + ")");
            
        }); // End of stream processing
    }

    // Core algorithm method that uses an array as a lightweight stack
    static boolean isValid(String s) {
        
        // Create a primitive char array to act as a highly efficient stack
        var stack = new char[s.length()]; 
        
        // Initialize a pointer to track the current "top" index of our custom stack
        var head = 0; 
        
        // Loop through every single character in the input string
        for (var c : s.toCharArray()) { 
            
            // Check if the current character is an open parenthesis
            if (c == '(') {
                
                // Push the expected closing parenthesis onto the stack and increment pointer
                stack[head++] = ')'; 
                
            } 
            // Check if the current character is an open square bracket
            else if (c == '[') {
                
                // Push the expected closing square bracket onto the stack and increment pointer
                stack[head++] = ']'; 
                
            } 
            // Check if the current character is an open curly brace
            else if (c == '{') {
                
                // Push the expected closing curly brace onto the stack and increment pointer
                stack[head++] = '}'; 
                
            } 
            // If it's a closing bracket, check if stack is empty (head == 0) or if it doesn't match
            else if (head == 0 || stack[--head] != c) {
                
                // Return false immediately because the sequence is definitively invalid
                return false; 
                
            }
        } // End of character loop
        
        // If the stack pointer is back to 0, all brackets matched perfectly; otherwise, false
        return head == 0; 
    }
}
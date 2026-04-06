package com.interview.notes.code.year.y2026.april.assessments.test4;

public class DuplicateRemover {

    // Custom method to remove duplicates from a character array without using built-in Sets or Streams
    public static char[] removeDuplicates(char[] input) {
        // Guard clause: immediately return null if the input is null to prevent runtime crashes
        if (input == null) return null;
        
        // Create a temporary array of the exact same size to hold unique elements safely
        char[] temp = new char[input.length];
        
        // Counter to keep track of exactly how many unique elements we have found so far
        int uniqueCount = 0;
        
        // Create a boolean array to act as a fast, custom map for ASCII characters (handles large data efficiently in O(N) time)
        boolean[] seen = new boolean[256]; 
        
        // Loop through every single character in the provided input array one by one
        for (int i = 0; i < input.length; i++) {
            // Extract the current character from the array into a temporary variable
            char currentChar = input[i];
            
            // Check our 'seen' array to see if this character's ASCII value has already been encountered
            if (!seen[currentChar]) {
                // If not seen, mark this character's position as true so we know we have it now
                seen[currentChar] = true;
                // Add the unique character to our temporary array and increment the unique counter
                temp[uniqueCount++] = currentChar;
            }
        }
        
        // Create a perfectly sized final array based on the exact number of unique elements we counted
        char[] result = new char[uniqueCount];
        
        // Loop through the temporary array strictly up to the unique count to copy the values over
        // Manually copy each unique character into our perfectly sized final array
        System.arraycopy(temp, 0, result, 0, uniqueCount);
        
        // Return the new, cleanly sized array containing absolutely no duplicate characters
        return result;
    }

    // Standard main method serving as our lightweight, custom testing framework
    public static void main(String[] args) {
        
        // Java 16+ 'record': Defines our custom test structure (input and expected output arrays) clearly in one line
        record TestCase(char[] input, char[] expected) {}
        
        // Java 11+ 'repeat()': Create a massive 100,000 character array to prove our O(N) algorithm handles large data efficiently
        char[] largeInput = "abcd".repeat(25000).toCharArray();
        // The expected result for the large array is just the four unique letters in their first-seen order
        char[] largeExpected = new char[]{'a', 'b', 'c', 'd'};
        
        // Java 9+ 'List.of' and Java 10+ 'var': Quickly builds an immutable list of our testing scenarios
        var tests = java.util.List.of(
            // Primary test case requested by the image prompt to verify standard functionality
            new TestCase(new char[]{'a', 'b', 'c', 'b', 'a', 'd'}, new char[]{'a', 'b', 'c', 'd'}),
            // Edge case: empty arrays should return empty arrays without crashing the loops
            new TestCase(new char[]{}, new char[]{}),
            // Edge case: null inputs must be safely bypassed by our guard clause without breaking the app
            new TestCase(null, null),
            // Edge case: an array where every element is identical should compress down to one single element
            new TestCase(new char[]{'z', 'z', 'z', 'z'}, new char[]{'z'}),
            // Large data case: ensures memory and CPU handle 100,000 array items instantly
            new TestCase(largeInput, largeExpected)
        );

        // Enhanced for-loop to iterate sequentially through every test case we defined in our list
        for (var test : tests) {
            // Execute our core logic by passing the test input into our custom deduplication method
            var result = removeDuplicates(test.input());
            
            // Compare primitive arrays using Arrays.equals to verify if actual output matches our expected output
            boolean isPass = java.util.Arrays.equals(result, test.expected());
            
            // Print whether the test PASSED or FAILED to the console so the developer knows the status
            System.out.print(isPass ? "PASS" : "FAIL");
            
            // Check if the input was null to prevent null pointer exceptions during the print statement layout
            if (test.input() == null) {
                // Print 'null' literally if the input was null
                System.out.println(" -> Input: null");
            } else {
                // Convert the array to a String, cap it at 15 chars to keep console output readable, and print
                String inputStr = new String(test.input());
                System.out.println(" -> Input: [" + (inputStr.length() > 15 ? inputStr.substring(0, 15) + "..." : inputStr) + "]");
            }
        }
    }
}
package com.interview.notes.code.year.y2025.august.adobe.test1;

import java.util.Arrays;

public class ReverseStringTest {

    // Method to reverse a given string manually without inbuilt reverse()
    static String reverse(String str) {
        // Convert the string to a char array for easy swapping
        char[] chars = str.toCharArray();

        // Initialize two pointers: one at start, one at end
        int left = 0;
        int right = chars.length - 1;

        // Swap characters until the pointers meet
        while (left < right) {
            // Temporary store the left character
            char temp = chars[left];
            // Assign right char to left position
            chars[left] = chars[right];
            // Assign temp (original left) to right position
            chars[right] = temp;

            // Move both pointers inward
            left++;
            right--;
        }

        // Create a new String from the reversed char array
        return new String(chars);
    }

    // Test runner to check PASS/FAIL
    static void runTests() {
        // Array of test inputs
        String[] inputs = {
            "hello",
            "world",
            "a",
            "",
            "Data Structure"
        };

        // Expected outputs for above inputs
        String[] expected = {
            "olleh",
            "dlrow",
            "a",
            "",
            "erutcurtS ataD"
        };

        // Run each test and check result
        for (int i = 0; i < inputs.length; i++) {
            String result = reverse(inputs[i]);
            boolean pass = result.equals(expected[i]);
            System.out.println("Test " + (i+1) + ": \"" + inputs[i] + 
                               "\" -> \"" + result + "\" | " + (pass ? "PASS" : "FAIL"));
        }

        // Large data test: 10 million 'a's
        char[] bigArray = new char[10_000_000];
        Arrays.fill(bigArray, 'a');
        String bigStr = new String(bigArray);
        long startTime = System.currentTimeMillis();
        String reversedBig = reverse(bigStr);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test: " + 
                           (reversedBig.equals(bigStr) ? "PASS" : "FAIL") + 
                           " | Time: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        System.out.println("\n--- Running Reverse String Tests ---");
        runTests();
    }
}
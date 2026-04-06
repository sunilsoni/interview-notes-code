package com.interview.notes.code.year.y2026.april.assessments.test3;

public class ReverseCustom {
    // Custom reverse method
    static String reverse(String s) {
        // Convert string to char array for direct manipulation
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1; // pointers at start and end

        // Swap characters until pointers meet
        while (left < right) {
            char temp = arr[left];   // store left char
            arr[left] = arr[right];  // move right char to left
            arr[right] = temp;       // move stored char to right
            left++;                  // move left pointer forward
            right--;                 // move right pointer backward
        }

        // Build new string from reversed char array
        return new String(arr);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases: input + expected output
        String[][] tests = {
            {"Hello World 123", "321 dlroW olleH"}, // normal case
            {"", ""},                              // empty string
            {"A", "A"},                            // single char
            {"12345", "54321"},                    // numbers only
            {"racecar", "racecar"},                // palindrome
            { "a".repeat(1000000), "a".repeat(1000000) } // large input
        };

        // Run each test case
        for (int i = 0; i < tests.length; i++) {
            String input = tests[i][0];    // original string
            String expected = tests[i][1]; // expected reversed
            String actual = reverse(input); // call custom reverse

            // Print PASS/FAIL
            if (actual.equals(expected)) {
                System.out.println("Test " + (i+1) + ": PASS");
            } else {
                System.out.println("Test " + (i+1) + ": FAIL");
            }
        }
    }
}

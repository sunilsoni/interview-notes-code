package com.interview.notes.code.year.y2025.november.common.test3;

public class PalindromeCheckerManual {

    // Method to check if a string is a palindrome using two-pointer technique
    public static boolean isPalindrome(String input) {
        // Step 1: Normalize input by removing spaces and converting to lowercase
        // This ensures consistent comparison regardless of case or spacing
        String normalized = input.replaceAll("\\s+", "").toLowerCase();

        // Step 2: Initialize two pointers at start and end of the string
        int left = 0;
        int right = normalized.length() - 1;

        // Step 3: Loop until pointers meet in the middle
        while (left < right) {
            // Compare characters at both ends
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false; // If mismatch, it's not a palindrome
            }
            // Move pointers inward
            left++;
            right--;
        }

        // Step 4: If all characters matched, it's a palindrome
        return true;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Define test cases with expected results
        String[] inputs = {
                "MADAM", "RaceCar", "hello", "A man a plan a canal Panama",
                "", "a", "No lemon no melon"
        };
        boolean[] expected = {
                true, true, false, true,
                true, true, true
        };

        // Generate a large palindrome string (1 million characters)
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 500_000; i++) large.append("A");
        for (int i = 0; i < 500_000; i++) large.append("A");
        String largeInput = large.toString();

        // Add large test case
        String[] allInputs = new String[inputs.length + 1];
        boolean[] allExpected = new boolean[expected.length + 1];
        System.arraycopy(inputs, 0, allInputs, 0, inputs.length);
        System.arraycopy(expected, 0, allExpected, 0, expected.length);
        allInputs[inputs.length] = largeInput;
        allExpected[expected.length] = true;

        // Run each test case and print result
        System.out.println("Running Manual Palindrome Tests:");
        for (int i = 0; i < allInputs.length; i++) {
            String input = allInputs[i];
            boolean result = isPalindrome(input);
            boolean pass = result == allExpected[i];

            // Print result with PASS/FAIL
            System.out.println("Input: " + (input.length() > 50 ? "[Large Input]" : "\"" + input + "\"")
                    + " → Expected: " + allExpected[i] + ", Actual: " + result
                    + " → " + (pass ? "PASS" : "FAIL"));
        }
    }
}

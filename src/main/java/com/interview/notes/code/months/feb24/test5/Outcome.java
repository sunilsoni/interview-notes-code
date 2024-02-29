package com.interview.notes.code.months.feb24.test5;

public class Outcome {
    public static String solve(String S1, String S2, String R) {
        // Create an array to count the occurrences of each letter
        int[] counts = new int[26];

        // Iterate through the letters in the box and increment the count array
        for (char c : R.toCharArray()) {
            counts[c - 'A']++;
        }

        // Iterate through the first string and decrement the count array
        for (char c : S1.toCharArray()) {
            counts[c - 'A']--;
        }

        // Iterate through the second string and decrement the count array
        for (char c : S2.toCharArray()) {
            counts[c - 'A']--;
        }

        // Check if any count is negative, indicating a letter is missing
        for (int count : counts) {
            if (count < 0) {
                return "No";
            }
        }

        // Check if there are any remaining letters in the count array
        for (int count : counts) {
            if (count > 0) {
                return "No";
            }
        }

        // If all checks pass, return "Yes"
        return "Yes";
    }
}


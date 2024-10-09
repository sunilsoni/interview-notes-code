package com.interview.notes.code.months.oct24.amz.test2;

public class DominantStringOptimized {
    
    public static long getDominantStringCount(String s) {
        long count = 0;

        // Iterate over all possible even lengths of substrings
        for (int length = 2; length <= s.length(); length += 2) {
            int[] freq = new int[26];  // Frequency array for 'a' to 'z'
            int halfLength = length / 2;

            // Initialize the frequency for the first window
            for (int i = 0; i < length; i++) {
                freq[s.charAt(i) - 'a']++;
            }

            // Check if the initial substring is dominant
            if (isDominant(freq, halfLength)) {
                count++;
            }

            // Slide the window across the string
            for (int i = length; i < s.length(); i++) {
                // Remove the character leaving the window
                freq[s.charAt(i - length) - 'a']--;
                // Add the new character entering the window
                freq[s.charAt(i) - 'a']++;

                // Check if the current window is dominant
                if (isDominant(freq, halfLength)) {
                    count++;
                }
            }
        }

        return count;
    }

    // Helper function to check if any character has frequency exactly half of the length
    private static boolean isDominant(int[] freq, int halfLength) {
        for (int f : freq) {
            if (f == halfLength) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";  // Expected: 3
        String testCase2 = "aidfg";    // Expected: 4
        String testCase3 = "dfdffdfi"; // Expected: 13

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));  
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));  
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3));  
    }
}

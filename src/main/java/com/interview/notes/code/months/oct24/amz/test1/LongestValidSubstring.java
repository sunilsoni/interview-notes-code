package com.interview.notes.code.months.oct24.amz.test1;

public class LongestValidSubstring {

    // Function to get the longest valid substring
    public static int getLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        // Iterate through the string with a sliding window approach
        for (int start = 0; start < n - 1; start++) {
            // Check for the farthest valid end position
            for (int end = n - 1; end > start; end--) {
                if (s.charAt(start) < s.charAt(end)) {
                    maxLen = Math.max(maxLen, end - start + 1);
                    break;  // No need to check shorter substrings for this start
                }
            }
        }

        return maxLen;
    }

    // Test method to verify test cases
    public static void runTests() {
        // Test case 1
        String testCase1 = "fghbbadcba";
        int expected1 = 5;
        int result1 = getLongestSubstring(testCase1);
        System.out.println("Test case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));

        // Test case 2
        String testCase2 = "abcd";
        int expected2 = 4;
        int result2 = getLongestSubstring(testCase2);
        System.out.println("Test case 2: " + (result2 == expected2 ? "PASS" : "FAIL"));

        // Test case 3 (no valid substring)
        String testCase3 = "zyxwv";
        int expected3 = 0;
        int result3 = getLongestSubstring(testCase3);
        System.out.println("Test case 3: " + (result3 == expected3 ? "PASS" : "FAIL"));

        // Test case 4 (entire string is valid)
        String testCase4 = "azbycx";
        int expected4 = 6;
        int result4 = getLongestSubstring(testCase4);
        System.out.println("Test case 4: " + (result4 == expected4 ? "PASS" : "FAIL"));

        // Test case 5 (edge case with 2 characters)
        String testCase5 = "ab";
        int expected5 = 2;
        int result5 = getLongestSubstring(testCase5);
        System.out.println("Test case 5: " + (result5 == expected5 ? "PASS" : "FAIL"));

        // Test case 6 (large input)
        String testCase6 = "a".repeat(100000) + "b";
        int expected6 = 100001;  // The entire string is valid
        int result6 = getLongestSubstring(testCase6);
        System.out.println("Test case 6: " + (result6 == expected6 ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        // Run all test cases
        runTests();
    }
}

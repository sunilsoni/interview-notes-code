package com.interview.notes.code.year.y2025.jan24.amazon.test5;

public class Solution {
    public static int minPalindromeSwaps(String s) {
        if (s == null || s.length() <= 1) return 0;
        
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        int swaps = 0;
        
        // Count mismatched characters
        int[] count = new int[2];  // count[0] for '0's, count[1] for '1's
        for (char c : chars) {
            count[c - '0']++;
        }
        
        // Check if palindrome is possible
        // For odd length, at most one character can have odd count
        if (chars.length % 2 == 0) {
            if (count[0] % 2 != 0 || count[1] % 2 != 0) return -1;
        } else {
            int oddCount = 0;
            if (count[0] % 2 != 0) oddCount++;
            if (count[1] % 2 != 0) oddCount++;
            if (oddCount > 1) return -1;
        }
        
        // Count minimum swaps needed
        while (left < right) {
            if (chars[left] != chars[right]) {
                // Find matching character for left
                int k = right - 1;
                boolean found = false;
                while (k > left) {
                    if (chars[k] == chars[left]) {
                        // Swap k and right
                        char temp = chars[k];
                        chars[k] = chars[right];
                        chars[right] = temp;
                        swaps++;
                        found = true;
                        break;
                    }
                    k--;
                }
                
                if (!found) {
                    // If no match found, swap adjacent characters
                    char temp = chars[left];
                    chars[left] = chars[left + 1];
                    chars[left + 1] = temp;
                    swaps++;
                    continue;  // Recheck current position
                }
            }
            left++;
            right--;
        }
        
        return swaps;
    }

    public static void main(String[] args) {
        // Test cases
        runTest("0100101", 2, "Test 1: Example from problem");
        runTest("101000", 1, "Test 2: Sample case 0");
        runTest("1110", -1, "Test 3: Impossible case");
        runTest("0", 0, "Test 4: Single character");
        runTest("00", 0, "Test 5: Already palindrome");
        runTest("01", 1, "Test 6: Two different characters");
        
        // Large test case
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            large.append(i % 2);
        }
        runTest(large.toString(), large.length() / 4, "Test 7: Large input");
    }

    private static void runTest(String input, int expected, String testName) {
        System.out.println(testName);
        System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 47) + "..." : input));
        int result = minPalindromeSwaps(input);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}

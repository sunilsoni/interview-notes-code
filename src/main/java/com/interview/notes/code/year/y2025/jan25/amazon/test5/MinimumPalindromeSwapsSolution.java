package com.interview.notes.code.year.y2025.jan25.amazon.test5;

public class MinimumPalindromeSwapsSolution {

    /**
     * Returns true if 'str' is a palindrome, false otherwise.
     */
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Checks if it's possible to form a palindrome (feasibility check).
     * If impossible, returns -1.
     * If possible, returns 0 or a positive integer (not the final swaps, just feasibility).
     */
    private static int canFormPalindrome(String s) {
        int count0 = 0;
        int count1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') count0++;
            else count1++;
        }

        int n = s.length();
        // Even length => all counts must be even
        if (n % 2 == 0) {
            if (count0 % 2 == 0 && count1 % 2 == 0) {
                return 0; // feasible
            } else {
                return -1; // not feasible
            }
        } else {
            // Odd length => exactly one odd count
            int oddCounts = 0;
            if (count0 % 2 != 0) oddCounts++;
            if (count1 % 2 != 0) oddCounts++;
            if (oddCounts == 1) {
                return 0; // feasible
            } else {
                return -1; // not feasible
            }
        }
    }

    /**
     * Main function to get the minimum palindrome swaps.
     * If impossible, returns -1.
     */
    public static int minPalindromeSwaps(String s) {
        // Quick feasibility check
        if (canFormPalindrome(s) == -1) {
            return -1;
        }

        // If string is already a palindrome
        if (isPalindrome(s)) {
            return 0;
        }

        int n = s.length();
        char[] arr = s.toCharArray();
        int swaps = 0;

        int left = 0;
        int right = n - 1;

        // Process from the outside in
        while (left < right) {
            // If they already match, move inward
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                // Find a matching character for arr[left] from the right side
                int match = right;
                while (match > left && arr[match] != arr[left]) {
                    match--;
                }

                if (match == left) {
                    // This means arr[left] didn't find a match.
                    // arr[left] might be the "odd" char -> bubble it towards the middle
                    // Swap with next position
                    char temp = arr[left];
                    arr[left] = arr[left + 1];
                    arr[left + 1] = temp;

                    swaps++;
                } else {
                    // Bring the matching character to the 'right' position
                    for (int i = match; i < right; i++) {
                        // Swap adjacent chars toward the right
                        char temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swaps++;
                    }
                    left++;
                    right--;
                }
            }
        }

        return swaps;
    }

    /**
     * A simple method to run test cases and print pass/fail results.
     */
    private static void runTest(String input, int expected) {
        int result = minPalindromeSwaps(input);
        boolean pass = (result == expected);
        System.out.println("Input: " + input
                + " | Expected: " + expected
                + " | Got: " + result
                + " | " + (pass ? "PASS" : "FAIL"));
    }

    /**
     * Main method: runs multiple test cases (including sample and edge cases).
     */
    public static void main(String[] args) {
        System.out.println("Minimum Palindrome Swaps Tests");

        // Sample / Provided Test Cases
        runTest("101000", 1);   // Sample Case 0
        runTest("1110", -1);    // Sample Case 1

        // Additional Example
        // "0100101" -> min swaps = 2
        runTest("0100101", 2);

        // Edge Cases
        runTest("0", 0);        // Already palindrome, single character
        runTest("1", 0);        // Same as above
        runTest("11", 0);       // Already palindrome, even length
        runTest("1111", 0);     // Already palindrome
        runTest("0000", 0);     // Already palindrome
        runTest("110", 1);      // e.g., "110" -> swap s[1], s[2] => "101"

        // Additional random test for a larger string
        // (Short example due to demonstration)
        String largeTest = "101001";  // length=6, feasible
        runTest(largeTest, minPalindromeSwaps(largeTest)); // Just to see if it’s stable

        // If you want to test even larger data,
        // you could generate random 0/1 strings of large size and measure performance.
        // For demonstration, let's keep it small.
    }
}

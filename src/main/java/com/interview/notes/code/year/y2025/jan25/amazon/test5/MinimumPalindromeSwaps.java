package com.interview.notes.code.year.y2025.jan25.amazon.test5;

public class MinimumPalindromeSwaps {

    public static void main(String[] args) {
        testMinPalindromeSwaps("1110", -1, 1);
        testMinPalindromeSwaps("101000", 1, 2);
        testMinPalindromeSwaps("0100101", 2, 3);
        testMinPalindromeSwaps("110011", 1, 4);
        testMinPalindromeSwaps("1100", 1, 5);
        testMinPalindromeSwaps("1001", 0, 6);
        testMinPalindromeSwaps("1", 0, 7);
        testMinPalindromeSwaps("0", 0, 8);
        testLargeInputTestCase(9);
    }

    public static int minPalindromeSwaps(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int n = s.length();
        char[] charArray = s.toCharArray();
        int swaps = 0;
        int left = 0, right = n - 1;

        // Check if a palindrome is possible
        if (!canFormPalindrome(s)) {
            return -1;
        }

        while (left < right) {
            if (charArray[left] == charArray[right]) {
                left++;
                right--;
            } else {
                int k = right;
                while (k > left && charArray[k] != charArray[left]) {
                    k--;
                }
                if (k == left) {
                    // No matching character found on the right side
                    // Try to find a matching character on the left side
                    k = left + 1;
                    while (k < right && charArray[k] != charArray[right]) {
                        k++;
                    }
                    if (k == right) {
                        // Cannot form a palindrome
                        return -1;
                    } else {
                        // Swap charArray[k] with charArray[left]
                        swap(charArray, k, left);
                        swaps++;
                        left++;
                        right--;
                    }
                } else {
                    // Swap charArray[k] with charArray[right]
                    swap(charArray, k, right);
                    swaps++;
                    left++;
                    right--;
                }
            }
        }

        return swaps;
    }

    private static boolean canFormPalindrome(String s) {
        int count0 = 0;
        int count1 = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                count0++;
            } else {
                count1++;
            }
        }

        if (s.length() % 2 == 0) {
            return count0 % 2 == 0 && count1 % 2 == 0;
        } else {
            return count0 % 2 + count1 % 2 == 1;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void testMinPalindromeSwaps(String input, int expected, int testCaseNumber) {
        int result = minPalindromeSwaps(input);
        boolean passed = result == expected;
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output:   " + result);
            System.out.println("-----------------------------------");
        }
    }

    private static void testLargeInputTestCase(int testCaseNumber) {
        // Generate a large palindrome string
        int size = 200000;
        char[] arr = new char[size];
        for (int i = 0; i < size / 2; i++) {
            arr[i] = '0';
            arr[size - i - 1] = '0';
        }
        if (size % 2 == 1) {
            arr[size / 2] = '1';
        }
        String input = new String(arr);
        int expected = 0;
        int result = minPalindromeSwaps(input);
        boolean passed = result == expected;
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED" : "FAILED (Large Input Test)"));
        if (!passed) {
            System.out.println("Large input test failed. Expected Output: " + expected + ", Actual Output: " + result);
            System.out.println("-----------------------------------");
        }
    }
}
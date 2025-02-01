package com.interview.notes.code.year.y2025.jan25.CASPEX.test3;

public class MinPalindromeSwaps {

    /**
     * Calculates the minimum number of swaps required to make the binary string a palindrome.
     *
     * @param s The binary string consisting of '0' and '1'.
     * @return The minimum number of swaps required, or -1 if it's impossible.
     */
    public static int minPalindromeSwaps(String s) {
        int n = s.length();
        int swaps = 0;

        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                int j = i + 1;
                while (j < n / 2 && s.charAt(j) != s.charAt(n - i - 1)) {
                    j++;
                }
                if (j == n / 2) {
                    return -1;
                }
                swaps++;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + (minPalindromeSwaps("1110") == -1 ? "PASS" : "FAIL"));
        System.out.println("Test 2: " + (minPalindromeSwaps("101000") == 1 ? "PASS" : "FAIL"));
        System.out.println("Test 3: " + (minPalindromeSwaps("0100101") == 2 ? "PASS" : "FAIL"));
        System.out.println("Test 4: " + (minPalindromeSwaps("1000001") == 0 ? "PASS" : "FAIL"));
        System.out.println("Test 5: " + (minPalindromeSwaps("1111111") == 0 ? "PASS" : "FAIL"));
    }
}
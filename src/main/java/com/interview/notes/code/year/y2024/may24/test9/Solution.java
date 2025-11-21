package com.interview.notes.code.year.y2024.may24.test9;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("39878"));  // Should return "898"
        System.out.println(sol.solution("00900"));  // Should return "9"
        System.out.println(sol.solution("0000"));   // Should return "0"
        System.out.println(sol.solution("54321"));  // Should return "5"
    }

    public String solution(String S) {
        int[] count = new int[10];
        for (char c : S.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        StringBuilder mid = new StringBuilder();

        // Create the largest number possible for the first half of the palindrome
        for (int i = 9; i >= 0; i--) {
            if (count[i] > 0) {
                if (count[i] % 2 == 1) {  // There's an odd count of this digit
                    if (mid.length() == 0) {  // Only add one middle character if it's empty
                        mid.append(i);
                    }
                }
                // Add half of the available even count to the first half of the palindrome
                char[] chars = new char[count[i] / 2];
                java.util.Arrays.fill(chars, (char) (i + '0'));
                firstHalf.append(chars);
            }
        }

        // Combine the first half, middle character if any, and the reverse of the first half
        String result = firstHalf.toString() + mid + firstHalf.reverse();

        // If no valid palindrome is constructed (only zero found and added), or it's full of zeroes
        if (result.matches("0+")) {
            return "0";
        }

        // Check for leading zeros in non-zero numbers and optimize the result
        if (result.replaceAll("0", "").length() == 1) {
            // If the result is essentially one non-zero digit surrounded by zeros, return that digit
            return result.replaceAll("0", "");
        } else {
            // Remove leading zeros from the result for non-zero palindromes
            return result.replaceFirst("^0+", "");
        }
    }
}

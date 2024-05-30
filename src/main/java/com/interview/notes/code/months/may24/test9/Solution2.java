package com.interview.notes.code.months.may24.test9;

class Solution2 {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
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

        // If no valid palindrome is constructed (only zero found and added), simply return "0"
        if (firstHalf.length() == 0 && mid.length() > 0 && mid.charAt(0) == '0') {
            return "0";
        }

        // Combine the first half, middle character if any, and the reverse of the first half
        return firstHalf.toString() + mid.toString() + firstHalf.reverse().toString();
    }
}
/**
 * 898
 * 00900
 * 0000
 * 5
 *
 * @param args
 */
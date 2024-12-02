package com.interview.notes.code.year.y2024.may24.test9;

class Solution4 {

    /**
     * 898
     * 00900
     * 0000
     * 5
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println(sol.solution("39878"));  // Should return "898"
        System.out.println(sol.solution("00900"));  // Should return "9"
        System.out.println(sol.solution("0000"));   // Should return "0"
        System.out.println(sol.solution("54321"));  // Should return "5"
    }

    public String solution(String S) {
        int[] count = new int[10];

        // Count the occurrences of each digit
        for (char c : S.toCharArray()) {
            count[c - '0']++;
        }

        // To build the largest palindrome
        StringBuilder leftHalf = new StringBuilder();
        StringBuilder rightHalf = new StringBuilder();
        char middleChar = 0;

        // Build the left and right halves
        for (int digit = 9; digit >= 0; digit--) {
            while (count[digit] > 1) {
                leftHalf.append(digit);
                rightHalf.insert(0, digit);
                count[digit] -= 2;
            }
        }

        // Find the middle character if any
        for (int digit = 9; digit >= 0; digit--) {
            if (count[digit] == 1) {
                middleChar = (char) (digit + '0');
                break;
            }
        }

        // Construct the final palindrome
        StringBuilder palindrome = new StringBuilder();
        palindrome.append(leftHalf);
        if (middleChar != 0) {
            palindrome.append(middleChar);
        }
        palindrome.append(rightHalf);

        // Handle the case where the palindrome might be empty
        if (palindrome.length() == 0) {
            return "0";
        }

        return palindrome.toString();
    }
}

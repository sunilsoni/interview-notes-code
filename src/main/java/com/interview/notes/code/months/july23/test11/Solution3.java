package com.interview.notes.code.months.july23.test11;

class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution("39878")); // 898
        System.out.println(solution.solution("00900")); // 9
        System.out.println(solution.solution("0000")); // 0
        System.out.println(solution.solution("54321")); // 5
    }

    public String solution(String S) {
        int[] counts = new int[10];

        // Count occurrences of each digit.
        for (char c : S.toCharArray()) {
            counts[c - '0']++;
        }

        // If all are zeros, return "0".
        if (counts[0] == S.length()) {
            return "0";
        }

        StringBuilder leftHalf = new StringBuilder();
        char middle = '\0';

        // Construct the palindrome.
        for (int i = 9; i >= 0; i--) {
            // Use symmetrically if there are 2 or more occurrences.
            while (counts[i] >= 2) {
                leftHalf.append(i);
                counts[i] -= 2;
            }

            // Save the largest unused digit.
            if (counts[i] == 1 && middle == '\0') {
                middle = (char) (i + '0');
            }
        }

        // Construct the right half by reversing the left half.
        StringBuilder rightHalf = new StringBuilder(leftHalf).reverse();

        // If middle character exists, add it.
        if (middle != '\0') {
            return leftHalf.toString() + middle + rightHalf.toString();
        } else {
            return leftHalf.toString() + rightHalf.toString();
        }
    }
}

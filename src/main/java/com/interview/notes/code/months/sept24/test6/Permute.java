package com.interview.notes.code.months.sept24.test6;

import java.util.HashSet;
import java.util.Set;

class Permute {
    public int solution(int A, int B, int C, int D) {
        int[] digits = {A, B, C, D};
        Set<String> validTimes = new HashSet<>();

        // Generate all permutations
        permute(digits, 0, validTimes);

        return validTimes.size();
    }

    private void permute(int[] digits, int start, Set<String> validTimes) {
        if (start == digits.length) {
            checkAndAddValidTime(digits, validTimes);
        } else {
            for (int i = start; i < digits.length; i++) {
                swap(digits, start, i);
                permute(digits, start + 1, validTimes);
                swap(digits, start, i);
            }
        }
    }

    private void swap(int[] digits, int i, int j) {
        int temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    private void checkAndAddValidTime(int[] digits, Set<String> validTimes) {
        int hours = digits[0] * 10 + digits[1];
        int minutes = digits[2] * 10 + digits[3];

        if (hours < 24 && minutes < 60) {
            String time = String.format("%02d:%02d", hours, minutes);
            validTimes.add(time);
        }
    }

    public static void main(String[] args) {
        Permute solution = new Permute();

        // Test cases
        System.out.println(solution.solution(1, 8, 3, 2) == 6 ? "Test case 1 passed" : "Test case 1 failed");
        System.out.println(solution.solution(2, 3, 3, 2) == 3 ? "Test case 2 passed" : "Test case 2 failed");
        System.out.println(solution.solution(6, 2, 4, 7) == 0 ? "Test case 3 passed" : "Test case 3 failed");

        // Additional test cases
        System.out.println(solution.solution(0, 0, 0, 0) == 1 ? "Test case 4 passed" : "Test case 4 failed");
        System.out.println(solution.solution(2, 4, 0, 0) == 4 ? "Test case 5 passed" : "Test case 5 failed");
    }
}

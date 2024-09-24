package com.interview.notes.code.months.sept24.test11;

public class Solution1 {

    // Method to check if a subarray matches the pattern
    public static boolean matchesPattern(int[] numbers, int start, int[] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            int diff = numbers[start + i + 1] - numbers[start + i];
            if (pattern[i] == 1 && diff <= 0) {
                return false; // Should be strictly increasing
            }
            if (pattern[i] == 0 && diff != 0) {
                return false; // Should be equal
            }
            if (pattern[i] == -1 && diff >= 0) {
                return false; // Should be strictly decreasing
            }
        }
        return true; // Subarray matches the pattern
    }

    // Main solution method
    public static int solution(int[] numbers, int[] pattern) {
        int count = 0;
        int subarrayLength = pattern.length + 1;

        // Iterate over all possible starting points for subarrays of length pattern.length + 1
        for (int i = 0; i <= numbers.length - subarrayLength; i++) {
            if (matchesPattern(numbers, i, pattern)) {
                count++;
            }
        }

        return count;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test case 1
        int[] numbers1 = {4, 1, 3, 4, 4, 5, 1};
        int[] pattern1 = {1, 0, -1};
        System.out.println(solution(numbers1, pattern1)); // Expected output: 1

        // Test case 2
        int[] numbers2 = {5, 5, 5, 5};
        int[] pattern2 = {0, 0};
        System.out.println(solution(numbers2, pattern2)); // Expected output: 2

        // Additional test case
        int[] numbers3 = {1, 2, 3, 4, 5};
        int[] pattern3 = {1, 1, 1};
        System.out.println(solution(numbers3, pattern3)); // Expected output: 1
    }
}

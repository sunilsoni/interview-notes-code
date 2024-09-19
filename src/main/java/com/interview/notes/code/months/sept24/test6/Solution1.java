package com.interview.notes.code.months.sept24.test6;

class Solution1 {
    // Test cases
    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        // Test case 1: Given example
        int[] A1 = {1, 2, -3, 4, 5, -6};
        System.out.println("Test case 1: " + sol.solution(A1)); // Expected output: 9

        // Test case 2: All negative numbers
        int[] A2 = {-1, -2, -3, -4};
        System.out.println("Test case 2: " + sol.solution(A2)); // Expected output: -1

        // Test case 3: All positive numbers
        int[] A3 = {1, 2, 3, 4};
        System.out.println("Test case 3: " + sol.solution(A3)); // Expected output: 10

        // Test case 4: Mixed positive and negative, max sum at the end
        int[] A4 = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Test case 4: " + sol.solution(A4)); // Expected output: 7

        // Test case 5: Single element array (positive)
        int[] A5 = {5};
        System.out.println("Test case 5: " + sol.solution(A5)); // Expected output: 5

        // Test case 6: Single element array (negative)
        int[] A6 = {-5};
        System.out.println("Test case 6: " + sol.solution(A6)); // Expected output: -1

        // Test case 7: Array with only zeros
        int[] A7 = {0, 0, 0};
        System.out.println("Test case 7: " + sol.solution(A7)); // Expected output: -1
    }

    public int solution(int[] S) {
        int max_sum = 0;
        int current_sum = 0;
        int n = S.length;
        for (int i = 0; i < n; ++i) {
            if (current_sum < 0) {
                current_sum = 0;
            }
            current_sum += S[i];
            if (current_sum > max_sum) {
                max_sum = current_sum;
            }
        }
        return max_sum > 0 ? max_sum : -1;
    }
}

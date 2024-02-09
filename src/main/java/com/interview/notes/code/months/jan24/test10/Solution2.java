package com.interview.notes.code.months.jan24.test10;

class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Example test cases
        System.out.println(solution.solution(10, 21)); // Expected output: 7
        System.out.println(solution.solution(13, 11)); // Expected output: 5
        System.out.println(solution.solution(2, 1));  // Expected output: 0
        System.out.println(solution.solution(1, 8));  // Expected output: 2
    }

    public int solution(int A, int B) {
        // The maximum length of the square side
        int maxLength = 0;

        // The total length of the sticks
        int totalLength = A + B;

        // The minimum length of the sticks that can be used to form a square
        // is determined by the shortest stick
        int minLength = Math.min(A, B);

        // The maximum side length of the square cannot exceed the sum of the lengths
        // divided by 4, because we need 4 sides of equal length
        maxLength = totalLength / 4;

        // If the maxLength is greater than the minLength, it means we cannot use the maxLength
        // because the shorter stick cannot be split into 4 pieces of maxLength
        if (maxLength > minLength) {
            maxLength = minLength;
        }

        return maxLength;
    }
}

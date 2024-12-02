package com.interview.notes.code.year.y2024.jan24.test10;

class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Example test cases
        System.out.println("Example test: (10, 21) | Expected output: 7 | Actual output: " + solution.solution(10, 21));
        System.out.println("Example test: (13, 11) | Expected output: 5 | Actual output: " + solution.solution(13, 11));
        System.out.println("Example test: (2, 1) | Expected output: 0 | Actual output: " + solution.solution(2, 1));
        System.out.println("Example test: (1, 8) | Expected output: 2 | Actual output: " + solution.solution(1, 8));
    }

    public int solution(int A, int B) {
        // The maximum side length is initialized to the smallest possible square side,
        // which is the integer division of the shorter stick by 2.
        int maxSideLength = Math.min(A, B) / 2;

        // We iterate to find the largest possible square side.
        // We start from the maximum side length and decrease until we find a suitable length.
        while (maxSideLength > 0) {
            // The number of square sides that can be obtained from sticks A and B
            int countFromA = A / maxSideLength;
            int countFromB = B / maxSideLength;

            // If we can obtain at least 4 sides in total, we have found our square.
            if (countFromA + countFromB >= 4) {
                return maxSideLength;
            }

            // Decrease the side length to check the next smaller possible square
            maxSideLength--;
        }

        // If no square can be formed, return 0
        return 0;
    }
}

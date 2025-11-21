package com.interview.notes.code.year.y2024.jan24.test11;

public class Solution2 {

    public static int solution(int A, int B) {
        // The maximum length of the square's side is the total length of the sticks divided by 4,
        // because we need 4 sides of equal length.
        int totalLength = A + B;
        int maxSquareSide = totalLength / 4;

        // If either stick is less than the maximum square side length, then we can't form a square
        // with sides of length maxSquareSide because we would have to cut one of the sticks into more
        // than one piece, which is not allowed.
        while (maxSquareSide > 0) {
            if (A >= maxSquareSide && B >= maxSquareSide) {
                return maxSquareSide;
            }
            maxSquareSide--;
        }

        // If no square can be formed, return 0.
        return 0;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        // Example test cases
        System.out.println("Example test: (10, 21) | Expected output: 7 | Actual output: " + solution(10, 21));
        System.out.println("Example test: (13, 11) | Expected output: 5 | Actual output: " + solution(13, 11));
        System.out.println("Example test: (2, 1) | Expected output: 0 | Actual output: " + solution(2, 1));
        System.out.println("Example test: (1, 8) | Expected output: 2 | Actual output: " + solution(1, 8));
    }
}

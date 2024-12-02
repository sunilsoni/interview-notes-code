package com.interview.notes.code.year.y2024.jan24.test11;

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
        // The greatest common divisor will help us find the largest square side
        int gcd = greatestCommonDivisor(A, B);

        // Calculate the maximum number of sides that can be obtained from both sticks
        int totalSides = (A / gcd) + (B / gcd);

        // If we cannot obtain at least 4 sides in total, return 0 as it's not possible
        if (totalSides < 4) {
            return 0;
        }

        // Otherwise, return the greatest common divisor, which is the largest square side
        return gcd;
    }

    private int greatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        }
        return greatestCommonDivisor(b, a % b);
    }
}

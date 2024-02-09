package com.interview.notes.code.months.jan24.test10;

class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        // Example test cases
        System.out.println("Example test: (10, 21) | Expected output: 7 | Actual output: " + solution.solution(10, 21));
        System.out.println("Example test: (13, 11) | Expected output: 5 | Actual output: " + solution.solution(13, 11));
        System.out.println("Example test: (2, 1) | Expected output: 0 | Actual output: " + solution.solution(2, 1));
        System.out.println("Example test: (1, 8) | Expected output: 2 | Actual output: " + solution.solution(1, 8));
    }

    public int solution(int A, int B) {
        // Find the greatest common divisor of A and B
        int gcd = gcd(A, B);

        // If the gcd is 1, it means there is no common divisor
        // and thus no square can be formed.
        if (gcd == 1) {
            return 0;
        }

        // Return the gcd as the side length of the largest square
        return gcd;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

package com.interview.notes.code.months.jan24.test11;

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
        int maxSideLength = 0;

        // Start from the smallest stick and try to find the largest square side possible
        for (int side = Math.min(A, B); side >= 1; side--) {
            // Calculate the total number of sides we can get from both sticks
            int totalSides = (A / side) + (B / side);

            // If we can get at least 4 sides, this is the largest square side we can make
            if (totalSides >= 4) {
                maxSideLength = side;
                break;
            }
        }

        return maxSideLength;
    }
}

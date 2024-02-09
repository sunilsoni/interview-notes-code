package com.interview.notes.code.months.jan24.test11;

class Solution4 {
    public int solution(int A, int B) {
        // The solution starts by assuming the largest square side possible
        // is the length of the shorter stick.
        int maxSideLength = Math.min(A, B);
        
        // Decrement the side length until we can make four sides of the square
        while (maxSideLength > 0) {
            if ((A / maxSideLength) + (B / maxSideLength) >= 4) {
                // If we can make at least four sides of this length, we have our answer
                return maxSideLength;
            }
            maxSideLength--;
        }
        
        // If it's not possible to make a square, return 0
        return 0;
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();

        // Test the solution with example test cases
        int result;

        result = solution.solution(10, 21);
        System.out.println("Example test: (10, 21) | Expected output: 7 | Actual output: " + result);

        result = solution.solution(13, 11);
        System.out.println("Example test: (13, 11) | Expected output: 5 | Actual output: " + result);

        result = solution.solution(2, 1);
        System.out.println("Example test: (2, 1) | Expected output: 0 | Actual output: " + result);

        result = solution.solution(1, 8);
        System.out.println("Example test: (1, 8) | Expected output: 2 | Actual output: " + result);
    }
}

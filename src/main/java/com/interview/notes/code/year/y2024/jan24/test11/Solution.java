package com.interview.notes.code.year.y2024.jan24.test11;


/**
 * Working Solution:
 * There are two wooden sticks of lengths A and B respectively. Each of them can be cut into shorter sticks of integer lengths. Our goal is to construct the largest possible square. In order to do this, we want to cut the sticks in such a way as to achieve four sticks of the same length (note that there can be some leftover pieces). What is the longest side of square that we can achieve?
 * Write a function:
 * class Solution i public int solution(int A, int B); }
 * that, given two integers A, B, returns the side length of the largest square that we can obtain. If it is not possible to create any square, the function should return 0.
 * Examples:
 * 1. Given A = 10, B = 21, the function should return 7. We can split the second stick into three sticks of length
 * 7 and shorten the first stick by 3.
 * 2. Given A = 13, B = 11, the function should return 5. We can cut two sticks of length 5 from each of the
 * given sticks.
 * 3. Given A = 2, B = 1, the function should return 0. It is not possible to make any square from the given
 * sticks.
 * 4. Given A = 1, B = 8, the function should return 2. We can cut stick B into four parts.
 * Write an efficient algorithm for the following assumptions:
 * • A and B are integers within the range [1.1,000,000,000].
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test the solution with example test cases
        System.out.println("Example test: (10, 21) | Expected output: 7 | Actual output: " + solution.solution(10, 21));
        System.out.println("Example test: (13, 11) | Expected output: 5 | Actual output: " + solution.solution(13, 11));
        System.out.println("Example test: (2, 1) | Expected output: 0 | Actual output: " + solution.solution(2, 1));
        System.out.println("Example test: (1, 8) | Expected output: 2 | Actual output: " + solution.solution(1, 8));
    }

    public int solution(int A, int B) {
        // The possible side length of the square can't be larger than the sum of A and B divided by 4.
        int maxSideLength = (A + B) / 4;

        // We decrease the side length until we find the one that can make four sides
        for (int side = maxSideLength; side > 0; side--) {
            int neededSticks = 4;
            int sticksFromA = A / side;
            int sticksFromB = B / side;

            // Subtract the number of sticks we can get from A and B from the needed sticks
            neededSticks -= sticksFromA;
            neededSticks -= sticksFromB;

            // If the needed sticks is less than or equal to zero, we can make the square
            if (neededSticks <= 0) {
                return side;
            }
        }

        // If we can't make a square, return 0
        return 0;
    }
}

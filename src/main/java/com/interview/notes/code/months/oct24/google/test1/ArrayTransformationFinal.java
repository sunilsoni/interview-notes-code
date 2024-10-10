package com.interview.notes.code.months.oct24.google.test1;

import java.util.Random;

/*

WORKING

You begin with an array filled with N zeros and you want to obtain an array A.

In one move, you can choose an arbitrary interval and increase all the elements within that interval by 1. For example, you can transform [0, 0, 0, 0, 0] into [0, 1, 1, 1, 0] in a single move. However, you need three moves to obtain [1, 0, 1, 2, 2]. One possible sequence of moves is: [0, 0, 0, 0, 0] → [0, 0, 1, 1, 1] → [0, 0, 1, 2, 2] → [1, 0, 1, 2, 2], where → denotes a single move.

What is the minimum number of moves needed to obtain A, starting from a zero-filled array?

Write a function:
int solution(int[] A);

that, given an array A of length N, returns as an integer the minimum number of moves needed to transform a zero-filled array into A.

Examples:
1. Given A = [2, 1, 3], the function should return 4.
   For example, the following sequence of moves leads to the solution: [0, 0, 0] → [1, 1, 1] → [2, 1, 1] → [2, 1, 2] → [2, 1, 3].

2. Given A = [2, 2, 0, 0, 1], the function should return 3. The following sequence of moves leads to the solution: [0, 0, 0, 0, 0] → [1, 1, 0, 0, 0] → [2, 2, 0, 0, 0] → [2, 2, 0, 0, 1].

3. Given A = [5, 4, 2, 4, 1], the function should return 7. One possible optimal sequence of moves is: [0, 0, 0, 0, 0] → [1, 1, 1, 1, 1] → [2, 2, 2, 2, 1] → [3, 3, 2, 2, 1] → [4, 4, 2, 2, 1] → [5, 4, 2, 2, 1] → [5, 4, 2, 3, 1] → [5, 4, 2, 4, 1].

Write an efficient algorithm for the following assumptions:
- N is an integer within the range [1..100,000];
- each element of array A is an integer within the range [0..1,000,000,000];
- we guarantee that the answer will not exceed 1,000,000,000.


 */
public class ArrayTransformationFinal {
    public static void main(String[] args) {
        ArrayTransformationFinal sol = new ArrayTransformationFinal();

        // Test case 1
        int[] A1 = {2, 1, 3};
        sol.runTest(1, A1, 4);

        // Test case 2
        int[] A2 = {2, 2, 0, 0, 1};
        sol.runTest(2, A2, 3);

        // Test case 3
        int[] A3 = {5, 4, 2, 4, 1};
        sol.runTest(3, A3, 7);

        // Additional test cases
        // Test case 4: Single element
        int[] A4 = {0};
        sol.runTest(4, A4, 0);

        // Test case 5: Increasing sequence
        int[] A5 = {1, 2, 3, 4, 5};
        sol.runTest(5, A5, 5);

        // Test case 6: Large input
        int[] A6 = sol.generateLargeTestCase();
        sol.runTest(6, A6, -1); // Expected value is not checked for large test

        // Test case 7: All zeros
        int[] A7 = new int[100000];
        sol.runTest(7, A7, 0);
    }

    /**
     * Calculates the minimum number of moves needed to transform a zero-filled
     * array into the target array A, where in each move you can increment all
     * elements within an arbitrary interval by 1.
     *
     * @param A The target array.
     * @return The minimum number of moves required.
     */
    public int solution(int[] A) {
        long moves = A[0];
        for (int i = 1; i < A.length; i++) {
            int diff = A[i] - A[i - 1];
            if (diff > 0) {
                moves += diff;
            }
        }
        return (int) moves;
    }

    /**
     * Runs a test case and prints whether it passed or failed.
     *
     * @param testCaseNumber The test case number.
     * @param A              The input array.
     * @param expected       The expected result.
     */
    public void runTest(int testCaseNumber, int[] A, int expected) {
        int result = solution(A);
        if (result == expected) {
            System.out.println("Test case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test case " + testCaseNumber + ": FAIL");
            System.out.println("Expected " + expected + ", but got " + result);
        }
    }

    /**
     * Generates a large test case to check performance on large inputs.
     *
     * @return A large array for testing.
     */
    public int[] generateLargeTestCase() {
        int N = 100000;
        int[] A = new int[N];
        Random rand = new Random();
        A[0] = rand.nextInt(1000000000);
        for (int i = 1; i < N; i++) {
            A[i] = A[i - 1] + rand.nextInt(100);
        }
        return A;
    }
}

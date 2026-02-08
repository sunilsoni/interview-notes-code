package com.interview.notes.code.year.y2026.feb.Codility.test1;

public class TokenStackReducer {

    public static void main(String[] args) {
        TokenStackReducer solver = new TokenStackReducer();

        test(1, solver.solution(new int[]{2, 3}), 1);
        test(2, solver.solution(new int[]{1, 0, 4, 1}), 3);
        test(3, solver.solution(new int[]{5}), 2);
        test(4, solver.solution(new int[]{4, 0, 3, 0}), 1);

        int[] largeData = new int[100_000];
        java.util.Arrays.fill(largeData, 1);
        System.out.println("Large Data Test: " + (solver.solution(largeData) >= 0 ? "PASS" : "FAIL"));
    }

    private static void test(int id, int actual, int expected) {
        System.out.println("Case " + id + ": " + (actual == expected ? "PASS" : "FAIL (Expected " + expected + ", Got " + actual + ")"));
    }

    public int solution(int[] A) {
        int total = 0;
        int lastIndex = A.length - 1;

        for (int i = 0; i <= lastIndex; i++) {
            int currentTokens = A[i];

            // Move excess tokens forward to minimize remainder
            if (currentTokens >= 2) {
                int moveForward = (currentTokens - (i < lastIndex ? 0 : currentTokens % 2)) / 2;

                if (i + 1 < A.length) {
                    A[i + 1] += moveForward;
                    A[i] -= moveForward * 2;
                } else if (moveForward > 0) {
                    // Extend array logic for infinite stacks
                    int[] newA = java.util.Arrays.copyOf(A, A.length + 1);
                    newA[i + 1] = moveForward;
                    newA[i] -= moveForward * 2;
                    A = newA;
                    lastIndex++;
                }
            }
        }

        return java.util.Arrays.stream(A).sum();
    }
}
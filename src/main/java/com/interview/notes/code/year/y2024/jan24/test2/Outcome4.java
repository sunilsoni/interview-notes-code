package com.interview.notes.code.year.y2024.jan24.test2;

class Outcome4 {

    public static String solve(int N, int P, int M, int X) {
        // If P is already at X, the game is a draw because no move can be made.
        if (P == X) {
            return "Draw";
        }

        // Check if Steve can win in the first move.
        if (Math.abs(P - X) <= M) {
            return "Steve";
        }

        // Calculate the total moves required to get from P to X.
        int totalMovesRequired = (Math.abs(P - X) - 1) / (M - 1);

        // If the total moves required is even, it means Harvey will make the last move, and Steve wins.
        // If the total moves required is odd, it means Steve will make the last move, and Harvey wins.
        if (totalMovesRequired % 2 == 0) {
            return "Steve";
        } else {
            return "Harvey";
        }
    }

    public static void main(String[] args) {

        System.out.println(solve(4, 1, 2, 4));//draw
        System.out.println(solve(3, 1, 2, 2));//steve
    }
}

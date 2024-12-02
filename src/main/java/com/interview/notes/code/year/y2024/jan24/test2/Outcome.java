package com.interview.notes.code.year.y2024.jan24.test2;

import java.util.Scanner;

class Outcome {

    public static String solve(int N, int P, int M, int X) {
        // If the gold key is already at the winning position, it's a draw because no moves are possible.
        if (P == X) {
            return "Draw";
        }

        // If the gold key is one move away from the winning position, Steve wins because he goes first.
        if (Math.abs(X - P) < M) {
            return "Steve";
        }

        // If M is 2, players will keep swapping the first two keys, so it's a draw.
        if (M == 2) {
            return "Draw";
        }

        // If M is greater than 2, check if the gold key can be moved to the winning position.
        // Since Steve goes first, check if the distance to the winning position is a multiple of M-1.
        // If it's not a multiple of M-1, then the key can't be placed in the winning position in one turn.
        int distance = Math.abs(P - X);
        if (distance % (M - 1) == 1) {
            return "Steve";
        }

        // Check the positions from which the gold key can be placed in the winning position in one turn.
        int moves = (distance - 1) / (M - 1);
        if (moves % 2 == 1) {
            // If the number of moves is odd, Harvey will have the last move to place the gold key.
            return "Harvey";
        } else {
            // If the number of moves is even, it will be Steve's turn and he can force the key to the winning position.
            return "Steve";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Number of keys
        int P = scanner.nextInt(); // Initial position of the gold-plated key
        int M = scanner.nextInt(); // Number of consecutive keys that can be reversed
        int X = scanner.nextInt(); // Winning position

        System.out.println(solve(N, P, M, X));
    }
}

package com.interview.notes.code.months.jan24.test2;

class Outcome5 {

    public static String solve1(int N, int P, int M, int X) {
        // Calculate the distance from the current position of the gold key to the winning position.
        int distanceToWinningPosition = Math.abs(X - P);

        // If the gold key is already at the winning position or cannot reach in one move,
        // and the distance to the winning position is not a multiple of the number of moves possible,
        // then the game will result in a draw.
        if ((distanceToWinningPosition % (M - 1) != 0 && distanceToWinningPosition > M) || distanceToWinningPosition == 0) {
            return "Draw";
        }

        // If Steve can win in the first move or place the gold key such that Harvey cannot win in the next move,
        // then Steve wins.
        if (P != X && (distanceToWinningPosition <= M || (distanceToWinningPosition - 1) % (M - 1) != 0)) {
            return "Steve";
        }

        // In all other cases, Harvey will have a strategy to win.
        return "Harvey";
    }

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
        System.out.println(solve(4, 1, 2, 4));//draw
        System.out.println(solve(3, 1, 2, 2));//steve
    }
}

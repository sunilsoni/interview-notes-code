package com.interview.notes.code.months.jan24.test2;

class Outcome2 {

    public static String solve(int N, int P, int M, int X) {
        // Since players play optimally, we only need to determine if the gold key
        // can be moved to the winning position in one move by Steve.
        // If it can, Steve wins, since he plays first.
        // If it can't, and since each player can only move the gold key to a new
        // position that is at most M-1 places away, the game will be a draw if
        // the initial distance to the winning position is not divisible by M-1
        // or the distance is 0 and M is 2. If M is 2, players will keep swapping
        // the first two keys indefinitely, leading to a draw.

        int distance = Math.abs(P - X);

        // If the distance is 0 and M is 2, it's a draw because Steve can't move.
        if (distance == 0 && M == 2) {
            return "Draw";
        }

        // If the gold key can be placed directly to the winning position by Steve
        if (distance <= M - 1 && distance != 0) {
            return "Steve";
        }

        // If the gold key can't be placed directly to the winning position by Steve,
        // check if the distance is divisible by M-1.
        // If it is not, then Steve can force a position such that Harvey will be the
        // one to place the key on the winning position or vice versa.
        if ((distance % (M - 1)) != 0) {
            return "Steve";
        } else {
            return "Harvey";
        }
    }

    public static void main(String[] args) {

        System.out.println(solve(4,1,2,4));//draw
        System.out.println(solve(3,1,2,2));//steve
    }
}

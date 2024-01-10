package com.interview.notes.code.months.jan24.test2;

class Outcome1 {

    public static String solve(int N, int P, int M, int X) {
        // Determine the initial distance of the gold key from the winning position
        int initialDistance = Math.abs(P - X);

        // Check for immediate win or draw scenarios
        if (initialDistance == 0) {
            return "Draw"; // Gold key is already at the winning position
        }
        if (initialDistance % M == 0) {
            return "Steve"; // Steve can win in one move
        }

        // Simulate the game to determine the winner
        int currentPosition = P;
        boolean steveTurn = true;
        while (true) {
            // Calculate the new position of the gold key after the current move
            int newPosition = currentPosition + (steveTurn ? M : -M);
            while (newPosition < 1) {
                newPosition += N;  // Wrap around to the end if necessary
            }
            newPosition %= N;  // Ensure the position stays within bounds

            // Check for a win
            if (newPosition == X) {
                return steveTurn ? "Steve" : "Harvey";
            }

            // Update the position and turn
            currentPosition = newPosition;
            steveTurn = !steveTurn;
        }
    }

    public static void main(String[] args) {

        System.out.println(solve(4,1,2,4));
    }
}

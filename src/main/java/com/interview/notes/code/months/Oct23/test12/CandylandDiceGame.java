package com.interview.notes.code.months.Oct23.test12;

import java.util.Random;

public class CandylandDiceGame {
    private int[] board = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6};
    private int[] playerPositions = {0, 0};
    private int currentPlayer = 0;
    private Random random = new Random();

    public static void main(String[] args) {
        CandylandDiceGame game = new CandylandDiceGame();

        String result;
        do {
            result = game.playTurn();
            System.out.println(result);
        } while (!result.contains("wins"));
    }

    // Rolls a dice and returns a number between 1 and 6
    private int rollDice() {
        return 1 + random.nextInt(6); // [1,6]
    }

    // Determines the next position of the player based on dice rolls
    private int getNextPosition(int currentPos, int dice1, int dice2) {
        int targetNumber = (dice1 == dice2) ? dice1 : Math.max(dice1, dice2);
        int occurrences = (dice1 == dice2) ? 2 : 1;

        for (int i = currentPos + 1; i < board.length; i++) {
            if (board[i] == targetNumber) {
                occurrences--;
                if (occurrences == 0) return i;
            }
        }
        return board.length; // If not found, move to end
    }

    // Checks if a player has won the game
    private boolean hasWon(int position) {
        return position >= board.length;
    }

    // Simulates a turn for the current player
    public String playTurn() {
        int dice1 = rollDice();
        int dice2 = rollDice();

        playerPositions[currentPlayer] = getNextPosition(playerPositions[currentPlayer], dice1, dice2);

        if (hasWon(playerPositions[currentPlayer])) {
            return "Player " + (currentPlayer + 1) + " wins!";
        }

        currentPlayer = 1 - currentPlayer; // Switch player
        return "Player " + (currentPlayer + 1) + "'s turn next!";
    }
}

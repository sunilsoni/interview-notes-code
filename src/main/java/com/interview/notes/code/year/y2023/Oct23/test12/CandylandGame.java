package com.interview.notes.code.year.y2023.Oct23.test12;

// CandylandGame class
public class CandylandGame {

    private static final int BOARD_SIZE = 24;

    private final Player[] players;
    private int currentPlayerIndex;

    public CandylandGame() {
        players = new Player[2];
        currentPlayerIndex = 0;
    }

    public void startGame() {
        // Create two players
        players[0] = new Player();
        players[1] = new Player();

        // Start the game loop
        while (!isGameWon()) {
            // Get the current player
            Player currentPlayer = players[currentPlayerIndex];

            // Roll the dice
            int diceRoll = rollDice();

            // Move the current player
            move(currentPlayer, diceRoll);

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }

        // Display the winner
        Player winner = players[currentPlayerIndex];
        System.out.println("The winner is: " + winner.getName());
    }

    private int rollDice() {
        // Simulate rolling two dice and return the result
        int die1 = (int) (Math.random() * 6) + 1;
        int die2 = (int) (Math.random() * 6) + 1;
        return die1 + die2;
    }

    private void move(Player player, int diceRoll) {
        // Calculate the player's new position
        int newPosition = player.getCurrentPosition() + diceRoll;

        // Handle the case where the player rolls past the "End" square
        if (newPosition >= BOARD_SIZE) {
            newPosition = BOARD_SIZE - 1;
        }

        // Set the player's new position
        player.setCurrentPosition(newPosition);
    }

    private boolean isGameWon() {
        // Check if the current player has reached the "End" square
        return players[currentPlayerIndex].getCurrentPosition() == BOARD_SIZE - 1;
    }
}

// Player class


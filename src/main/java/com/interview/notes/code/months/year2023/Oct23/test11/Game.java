package com.interview.notes.code.months.year2023.Oct23.test11;

/**
 * Candyland Dice Game
 * This game begins with two players, each with 2 dice. Players take turns rolling both
 * dice, and moving along this board:
 * Start | 1 | 2 | 3 | 4 | 5 | 6 | 1 | 2 | 3 | 4 | 5 | 6 | 1 | 2 | 3 | 4 | 5 | 6 | 1 | 2 | 3 | 4 | 5 | 6 | End
 * Player 1 goes first. There are two movement possibilities with each role:
 * ● If both dice are different: the player uses the higher number of the two
 * dice rolled. The player moves to the next occurrence of that number along the
 * board.
 * ● If both dice are the same: The player moves to the second occurrence of that
 * number along the board. (i.e. skip the first occurrence of that number)
 * The ‘End’ square is a wildcard - any roll that would pass the last square on the board
 * reaches the ‘End’. The first player to reach the ‘End’ square wins!
 */
class Game {
    private Player[] players = new Player[2];
    private Dice dice = new Dice();
    private Board board = new Board();
    private int currentPlayerIndex = 0;

    public Game() {
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
    }

    public static void main(String[] args) {
        Game game = new Game();

        String result;
        do {
            result = game.playTurn();
            System.out.println(result);
        } while (!result.contains("wins"));
    }

    public String playTurn() {
        int dice1 = dice.roll();
        int dice2 = dice.roll();

        Player currentPlayer = players[currentPlayerIndex];
        int newPosition = board.getNextPosition(currentPlayer.getPosition(), dice1, dice2);
        currentPlayer.setPosition(newPosition);

        if (board.hasReachedEnd(newPosition)) {
            return currentPlayer.getName() + " wins!";
        }

        currentPlayerIndex = 1 - currentPlayerIndex; // Switch player
        return players[currentPlayerIndex].getName() + "'s turn next!";
    }
}
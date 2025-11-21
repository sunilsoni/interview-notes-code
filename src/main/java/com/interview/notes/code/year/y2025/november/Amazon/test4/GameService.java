package com.interview.notes.code.year.y2025.november.Amazon.test4;// GameService.java

import java.util.*;

public class GameService {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final Queue<Player> playerTurns;
    private boolean gameActive;

    private GameService(Builder builder) {
        this.board = builder.board;
        this.players = builder.players;
        this.dice = builder.dice;
        this.playerTurns = new LinkedList<>(players);
        this.gameActive = false;
    }

    public void startGame() {
        gameActive = true;
        System.out.println("\n=== GAME STARTED ===");
        System.out.println("Board Size: " + board.getSize());
        System.out.println("Number of Players: " + players.size());
        System.out.println("Starting positions: All players at 0\n");

        while (gameActive) {
            Player currentPlayer = playerTurns.poll();
            makeMove(currentPlayer);

            if (board.hasWon(currentPlayer.getCurrentPosition())) {
                System.out.println("\n🎉 " + currentPlayer.getName() + " WINS THE GAME! 🎉");
                gameActive = false;
                break;
            }

            playerTurns.offer(currentPlayer);
        }
    }

    private void makeMove(Player player) {
        int diceValue = dice.roll();
        int currentPosition = player.getCurrentPosition();

        System.out.println(player.getName() + "'s turn:");
        System.out.println("  Current Position: " + currentPosition);
        System.out.println("  Dice Roll: " + diceValue);

        int nextPosition = board.getNextPosition(currentPosition, diceValue);

        if (nextPosition == currentPosition && (currentPosition + diceValue) > board.getSize()) {
            System.out.println("  Cannot move - would exceed board limit. Turn skipped.");
        } else {
            player.setCurrentPosition(nextPosition);
            System.out.println("  New Position: " + nextPosition);
        }

        System.out.println();
    }

    // Builder Pattern for Game Configuration
    public static class Builder {
        private final List<Player> players = new ArrayList<>();
        private Board board;
        private Dice dice;

        public Builder withBoard(int size) {
            this.board = new Board(size);
            return this;
        }

        public Builder addSnake(int start, int end) {
            if (board == null) {
                throw new IllegalStateException("Board must be initialized first");
            }
            board.addSnake(start, end);
            return this;
        }

        public Builder addLadder(int start, int end) {
            if (board == null) {
                throw new IllegalStateException("Board must be initialized first");
            }
            board.addLadder(start, end);
            return this;
        }

        public Builder addPlayer(String name) {
            String id = UUID.randomUUID().toString();
            players.add(new Player(id, name));
            return this;
        }

        public Builder withDice(int numberOfDice) {
            this.dice = new Dice(numberOfDice);
            return this;
        }

        public GameService build() {
            if (board == null) {
                throw new IllegalStateException("Board is required");
            }
            if (players.isEmpty()) {
                throw new IllegalStateException("At least one player is required");
            }
            if (dice == null) {
                dice = new Dice(1); // Default single dice
            }
            return new GameService(this);
        }
    }
}

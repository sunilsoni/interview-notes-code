package com.interview.notes.code.year.y2025.november.Amazon.test4;

// Main.java - Demo Application
public class SnakeAndLadderApplication {
    public static void main(String[] args) {
        // Create and configure the game
        GameService game = new GameService.Builder()
            .withBoard(100)  // 10x10 board
            .addSnake(99, 54)
            .addSnake(87, 36)
            .addSnake(62, 18)
            .addSnake(56, 8)
            .addSnake(48, 26)
            .addSnake(36, 6)
            .addSnake(32, 10)
            .addLadder(2, 37)
            .addLadder(4, 14)
            .addLadder(9, 31)
            .addLadder(21, 42)
            .addLadder(28, 84)
            .addLadder(51, 67)
            .addLadder(72, 91)
            .addLadder(80, 98)
            .addPlayer("Alice")
            .addPlayer("Bob")
            .addPlayer("Charlie")
            .withDice(1)  // Single dice
            .build();
        
        // Start the game
        game.startGame();
    }
}
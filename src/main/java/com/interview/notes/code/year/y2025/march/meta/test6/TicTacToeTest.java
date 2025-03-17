package com.interview.notes.code.year.y2025.march.meta.test6;

public class TicTacToeTest {
    public static void main(String[] args) {
        runAllTests();
    }

    public static void runAllTests() {
        testBasicWin();

    }

    static void testBasicWin() {
        TicTacToe game = new TicTacToe(3);
        assert !game.move(0, 0, 1);  // Player 1 moves
        assert !game.move(1, 1, 2);  // Player 2 moves
        assert !game.move(0, 1, 1);  // Player 1 moves
        assert !game.move(2, 2, 2);  // Player 2 moves
        assert game.move(0, 2, 1);   // Player 1 wins
        System.out.println("Basic win test: PASS");
    }

    // Add more test methods...
}

package com.interview.notes.code.year.y2025.jan24.test13;

/*
validating a knight's move on a chessboard
Write a function that takes four integers x1,x2,y1,y2 as input
x1,y1 represents the current position of the knight
x2,y2 represents the knight's next position

Function should return if the move is valid and false otherwise

 */
public class KnightMoveValidator {
    
    // Validates if a knight's move from (x1,y1) to (x2,y2) is legal
    public static boolean isValidKnightMove(int x1, int y1, int x2, int y2) {
        // Check if positions are within chessboard (1-8)
        if (!isValidPosition(x1, y1) || !isValidPosition(x2, y2)) {
            return false;
        }
        
        // Calculate absolute differences
        int xDiff = Math.abs(x2 - x1);
        int yDiff = Math.abs(y2 - y1);
        
        // Knight moves in L-shape: 2 squares in one direction and 1 in perpendicular
        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }
    
    // Helper method to check if position is within chessboard
    private static boolean isValidPosition(int x, int y) {
        return x >= 1 && x <= 8 && y >= 1 && y <= 8;
    }
    
    public static void main(String[] args) {
        // Test cases
        testKnightMove(2, 1, 4, 2, true);  // Valid L-shape move
        testKnightMove(2, 1, 3, 3, true);  // Valid L-shape move
        testKnightMove(2, 1, 2, 3, false); // Invalid move
        testKnightMove(0, 1, 2, 2, false); // Invalid position
        testKnightMove(8, 8, 6, 7, true);  // Valid move from corner
        testKnightMove(1, 1, 2, 2, false); // Invalid diagonal move
    }
    
    // Test helper method
    private static void testKnightMove(int x1, int y1, int x2, int y2, boolean expected) {
        boolean result = isValidKnightMove(x1, y1, x2, y2);
        System.out.printf("Move from (%d,%d) to (%d,%d): %s%n", 
            x1, y1, x2, y2, 
            result == expected ? "PASS" : "FAIL");
    }
}

package com.interview.notes.code.months.jan24.test3;

public class Chessboard {

    // Main method for example execution
    public static void main(String[] args) {
        // Example function calls
        System.out.println(getSquareColor(3, 'd')); // d3
        System.out.println(getSquareColor(1, 'a')); // a1
    }

    // Function to determine the color of the square on a chessboard
    public static String getSquareColor(int row, char column) {
        // Convert the column letter to a number (1-8)
        int file = column - 'a' + 1;

        // Calculate color: if sum of file (column) and rank (row) is even, square is white; if odd, square is black
        boolean isWhite = (file + row) % 2 == 0;

        // Construct the position string
        String position = "" + column + row;

        // Return the result as a string
        return position + " is " + (isWhite ? "white" : "black");
    }
}

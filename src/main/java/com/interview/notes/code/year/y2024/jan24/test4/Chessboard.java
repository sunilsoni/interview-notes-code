package com.interview.notes.code.year.y2024.jan24.test4;

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

        // Calculate color: if sum of file (column) and rank (row) is odd, square is white; if even, square is black
        // This is because the "a1" square is black and we toggle the color from there
        boolean isWhite = (file + row) % 2 != 0;

        // Construct the position string
        String position = "" + column + row;

        // Return the result as a string
        return position + " is " + (isWhite ? "white" : "black");
    }
}

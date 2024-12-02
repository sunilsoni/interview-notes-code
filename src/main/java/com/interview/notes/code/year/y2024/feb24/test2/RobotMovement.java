package com.interview.notes.code.year.y2024.feb24.test2;

public class RobotMovement {
    // Method to determine if the robot returns to the starting position
    public static boolean doesRobotReturn(String moves) {
        // Initialize starting position
        int x = 0, y = 0;

        // Iterate through the moves string
        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'N':
                    y++;
                    break; // Move north
                case 'E':
                    x++;
                    break; // Move east
                case 'S':
                    y--;
                    break; // Move south
                case 'W':
                    x--;
                    break; // Move west
            }
        }

        // Check if the robot returns to the start
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        // Example cases
        System.out.println(doesRobotReturn("NES")); // Should return false
        System.out.println(doesRobotReturn("NESW")); // Should return true
    }
}

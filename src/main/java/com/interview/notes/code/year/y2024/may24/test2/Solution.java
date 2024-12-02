package com.interview.notes.code.year.y2024.may24.test2;

/**
 * F = forward one step in current direction
 * L => turn left in the current position
 * R = turn right in the current position
 * Eg: FRLLF
 */
public class Solution {
    public static boolean isRoundTrip(String command) {
        Robot robot = new Robot();
        int initialX = robot.x, initialY = robot.y;
        char initialDirection = robot.direction;

        // Execute the commands
        for (char c : command.toCharArray()) {
            switch (c) {
                case 'F':
                    robot.moveForward();
                    break;
                case 'L':
                    robot.turnLeft();
                    break;
                case 'R':
                    robot.turnRight();
                    break;
            }
        }

        // Check if the robot revisits the initial state after executing the commands
        return (robot.x == initialX && robot.y == initialY);
    }

    public static boolean isRoundTrip1(String command) {
        Robot robot = new Robot();
        int initialX = robot.x, initialY = robot.y;
        char initialDirection = robot.direction;

        for (char c : command.toCharArray()) {
            switch (c) {
                case 'F':
                    robot.moveForward();
                    break;
                case 'L':
                    robot.turnLeft();
                    break;
                case 'R':
                    robot.turnRight();
                    break;
            }
        }

        return robot.x == initialX && robot.y == initialY && robot.direction == initialDirection;
    }

    public static void main(String[] args) {
        // String command = "FRLLF";
        String command = "LRLRL";//FLLF
        command = "FLLF";//FLLF
        System.out.println(isRoundTrip(command));
    }

    static class Robot {
        int x, y; // Position
        char direction; // Direction: N, E, S, W

        public Robot() {
            this.x = 0;
            this.y = 0;
            this.direction = 'N'; // Initial direction: North
        }

        public void moveForward() {
            switch (direction) {
                case 'N':
                    y++;
                    break;
                case 'E':
                    x++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'W':
                    x--;
                    break;
            }
        }

        public void turnLeft() {
            switch (direction) {
                case 'N':
                    direction = 'W';
                    break;
                case 'E':
                    direction = 'N';
                    break;
                case 'S':
                    direction = 'E';
                    break;
                case 'W':
                    direction = 'S';
                    break;
            }
        }

        public void turnRight() {
            switch (direction) {
                case 'N':
                    direction = 'E';
                    break;
                case 'E':
                    direction = 'S';
                    break;
                case 'S':
                    direction = 'W';
                    break;
                case 'W':
                    direction = 'N';
                    break;
            }
        }
    }
}

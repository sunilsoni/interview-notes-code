package com.interview.notes.code.year.y2024.sept24.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battleship {
    private static final int GRID_SIZE = 20;
    private static final char EMPTY = '.';
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private char[][] grid;
    private List<Ship> ships;

    public Battleship() {
        grid = new char[GRID_SIZE][GRID_SIZE];
        for (char[] row : grid) {
            Arrays.fill(row, EMPTY);
        }
        ships = new ArrayList<>();
        placeShips();
    }

    public static void main1(String[] args) {
        Battleship game = new Battleship();

        // Test cases
        System.out.println("Hit at (0, 0): " + game.check_hit(0, 0));
        System.out.println("Hit at (0, 1): " + game.check_hit(0, 1));
        System.out.println("Hit at (5, 5): " + game.check_hit(5, 5));
        System.out.println("Hit at (19, 19): " + game.check_hit(19, 19));


    }

    public static void main(String[] args) {
        Battleship1 game = new Battleship1();

        // Test cases
        testHit(game, 0, 0, true, false, false);
        testHit(game, 0, 1, true, true, false);  // Sinks the 2-size ship
        testHit(game, 5, 5, true, false, false);
        testHit(game, 19, 19, false, false, false);

        // Add more test cases as needed
    }

    private static void testHit(Battleship1 game, int row, int col, boolean expectedHit, boolean expectedSunk, boolean expectedGameOver) {
        Battleship1.HitResult result = game.check_hit(row, col);
        System.out.printf("Hit at (%d, %d): %s\n", row, col,
                result.isHit == expectedHit && result.isSunk == expectedSunk && result.isGameOver == expectedGameOver ? "PASS" : "FAIL");
    }

    private void placeShips() {
        ships.add(new Ship(5));
        ships.add(new Ship(4));
        ships.add(new Ship(3));
        ships.add(new Ship(3));
        ships.add(new Ship(2));

        placeShipOnGrid(ships.get(0), 0, 0, true);
        placeShipOnGrid(ships.get(1), 2, 2, false);
        placeShipOnGrid(ships.get(2), 5, 5, true);
        placeShipOnGrid(ships.get(3), 7, 7, false);
        placeShipOnGrid(ships.get(4), 9, 9, true);
    }

    private void placeShipOnGrid(Ship ship, int row, int col, boolean isHorizontal) {
        for (int i = 0; i < ship.size; i++) {
            if (isHorizontal) {
                grid[row][col + i] = SHIP;
                ship.addCoordinate(row, col + i);
            } else {
                grid[row + i][col] = SHIP;
                ship.addCoordinate(row + i, col);
            }
        }
    }

    public HitResult check_hit(int row, int col) {
        if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE) {
            throw new IllegalArgumentException("Invalid coordinates");
        }

        if (grid[row][col] == SHIP) {
            grid[row][col] = HIT;
            for (Ship ship : ships) {
                if (ship.isHit(row, col)) {
                    ship.hit();
                    boolean isSunk = ship.isSunk();
                    boolean isGameOver = checkGameOver();
                    return new HitResult(true, isSunk, isGameOver);
                }
            }
        } else if (grid[row][col] == EMPTY) {
            grid[row][col] = MISS;
        }

        return new HitResult(false, false, false);
    }

    private boolean checkGameOver() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    // For testing purposes
    public char[][] getGrid() {
        return grid;
    }

    private static class Ship {
        int size;
        int hits;
        List<Point> coordinates;

        Ship(int size) {
            this.size = size;
            this.hits = 0;
            this.coordinates = new ArrayList<>();
        }

        void addCoordinate(int row, int col) {
            coordinates.add(new Point(row, col));
        }

        boolean isHit(int row, int col) {
            return coordinates.stream().anyMatch(p -> p.row == row && p.col == col);
        }

        void hit() {
            hits++;
        }

        boolean isSunk() {
            return hits >= size;
        }
    }

    private static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class HitResult {
        public final boolean isHit;
        public final boolean isSunk;
        public final boolean isGameOver;

        public HitResult(boolean isHit, boolean isSunk, boolean isGameOver) {
            this.isHit = isHit;
            this.isSunk = isSunk;
            this.isGameOver = isGameOver;
        }
    }
}

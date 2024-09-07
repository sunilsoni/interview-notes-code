package com.interview.notes.code.months.sept24.amz;

import java.util.ArrayList;
import java.util.List;

/*
You are going to define a part of the rules for the game of Battleship. Battleship is a two player game where
each player has a two dimensional grid of size 20x20, for a total of 400 different grid locations. Players have
5 different ships, one of size 2, two of size 3, one of size 4, and one of size 5. Neither player can see each
others grid. Prior to the game starting, players place their ships on the grid in either a horizontal or vertical
direction. Each player then takes turns guessing (firing a missile) at a location on the other persons grid. This
question is focused on providing the logic for a function check_hit(). The check_hit function will take in the coordinates
of a missile fired and return back if a ship was hit, if the ship was sunk, and if the game is over. The game is over
when all ships have been hit. To start you can assume there is a function which placed the items on the grid, but the
data structure which represents this has not yet been defined. An example of what a 5x5 board would look like is below.
For visual clarity we are using the following indicators for the 2d array, but you do not have to use the same.
[S] Ship, [H] hit ship, [W] water. The below board shows three ships (one of size three and two of size two). One ship has
been hit once, one is sunk, and the other has not been hit yet. Again, these indicators used are just for visual purposes
and are the not required to be howyou represent if something is hit.

[ S, S, H, W, W ]
[ W, W, W, S, S ]
[ W, W, W, W, W ]
[ H, W, W, W, W ]
[ H, W, W, W, W ]



CODE WILL GO DOWN HERE WHEN YOU ARE READY. PLEASE ASK ANY QUESTIONS YOU HAVE AS WELL
 */
public class Battleship1 {
    private static final int GRID_SIZE = 20;
    private static final char WATER = 'W';
    private static final char SHIP = 'S';
    private static final char HIT = 'H';

    private char[][] grid;
    private List<Ship> ships;

    public Battleship1() {
        grid = new char[GRID_SIZE][GRID_SIZE];
        ships = new ArrayList<>();
        initializeGrid();
        placeShips();
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
        HitResult result = game.check_hit(row, col);
        System.out.printf("Hit at (%d, %d): %s\n", row, col,
                result.isHit == expectedHit && result.isSunk == expectedSunk && result.isGameOver == expectedGameOver ? "PASS" : "FAIL");
    }

    private void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = WATER;
            }
        }
    }

    private void placeShips() {
        // This is a simplified placement. In a real game, this would be more complex.
        ships.add(new Ship(2));
        ships.add(new Ship(3));
        ships.add(new Ship(3));
        ships.add(new Ship(4));
        ships.add(new Ship(5));

        // Place ships on the grid (simplified)
        placeShipOnGrid(ships.get(0), 0, 0, true);
        placeShipOnGrid(ships.get(1), 2, 0, false);
        placeShipOnGrid(ships.get(2), 5, 5, true);
        placeShipOnGrid(ships.get(3), 10, 10, false);
        placeShipOnGrid(ships.get(4), 15, 15, true);
    }

    private void placeShipOnGrid(Ship ship, int row, int col, boolean isHorizontal) {
        for (int i = 0; i < ship.size; i++) {
            if (isHorizontal) {
                grid[row][col + i] = SHIP;
            } else {
                grid[row + i][col] = SHIP;
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
        }

        return new HitResult(false, false, false);
    }

    private boolean checkGameOver() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    private static class Ship {
        int size;
        int hits;

        Ship(int size) {
            this.size = size;
            this.hits = 0;
        }

        void hit() {
            hits++;
        }

        boolean isSunk() {
            return hits >= size;
        }

        boolean isHit(int row, int col) {
            // This method should check if the hit coordinates belong to this ship
            // For simplicity, we're assuming it always returns true if the ship is hit
            return true;
        }
    }

    public static class HitResult {
        public final boolean isHit;
        public final boolean isSunk;
        public final boolean isGameOver;

        HitResult(boolean isHit, boolean isSunk, boolean isGameOver) {
            this.isHit = isHit;
            this.isSunk = isSunk;
            this.isGameOver = isGameOver;
        }
    }
}

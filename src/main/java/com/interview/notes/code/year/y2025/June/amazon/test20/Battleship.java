package com.interview.notes.code.year.y2025.June.amazon.test20;

import java.util.*;

// represent the result of firing at a square
enum ShotResult {MISS, HIT, SUNK;}  // three possible outcomes

// simple (row,col) holder with proper equals/hashCode
class Coordinate {
    int row;                          // row index
    int col;                          // column index

    Coordinate(int row, int col) {   // constructor
        this.row = row;               // set row
        this.col = col;               // set col
    }

    @Override
    public boolean equals(Object o) { // equality by row+col
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate c = (Coordinate) o;
        return row == c.row && col == c.col;
    }

    @Override
    public int hashCode() {           // allow in hash-based sets
        return Objects.hash(row, col);
    }
}

// tracks one ship’s positions and which have been hit
class Ship {
    List<Coordinate> positions;      // all squares occupied
    Set<Coordinate> hits;            // subset that have been hit

    Ship(List<Coordinate> positions) {
        this.positions = positions;
        this.hits = new HashSet<>(); // no hits at start
    }

    // register a hit; return true if this ship was hit here
    boolean registerHit(Coordinate c) {
        if (positions.contains(c)) {
            hits.add(c);             // record the hit
            return true;             // it was a hit
        }
        return false;                // not part of this ship
    }

    // sunk when every position is in hits
    boolean isSunk() {
        return hits.containsAll(positions);
    }
}

// represents one player’s board & fleet
class Player {
    private final int size = 10;      // grid is 10×10
    int[][] grid;                     // 0=empty,1=ship,2=hit,3=miss
    List<Ship> ships;                 // all ships placed

    Player() {
        grid = new int[size][size];   // initialize all zeros
        ships = new ArrayList<>();    // start with no ships
    }

    // place a ship if coords valid and not overlapping
    boolean placeShip(List<Coordinate> coords) {
        // validate each coord
        for (Coordinate c : coords) {
            if (c.row < 0 || c.row >= size || c.col < 0 || c.col >= size)
                return false;          // out of bounds
            if (grid[c.row][c.col] != 0)
                return false;          // overlap
        }
        // mark ship on grid
        for (Coordinate c : coords)
            grid[c.row][c.col] = 1;     // occupied
        ships.add(new Ship(coords));   // add to fleet
        return true;                   // success
    }

    // fire at a target, update grid & ships, return outcome
    ShotResult hitSquare(Coordinate target) {
        int r = target.row, c = target.col;
        // bounds check
        if (r < 0 || r >= size || c < 0 || c >= size)
            return ShotResult.MISS;    // treat invalid as miss
        // repeat shot?
        if (grid[r][c] == 2 || grid[r][c] == 3)
            return ShotResult.MISS;    // ignore repeats
        // ship present?
        if (grid[r][c] == 1) {
            grid[r][c] = 2;            // mark hit
            // inform each ship
            ships.forEach(ship -> ship.registerHit(target));
            // check if this hit sank a ship
            boolean sank = ships.stream()
                    .anyMatch(ship ->
                            ship.positions.contains(target) && ship.isSunk());
            return sank ? ShotResult.SUNK : ShotResult.HIT;
        } else {
            grid[r][c] = 3;            // mark miss
            return ShotResult.MISS;
        }
    }

    // all ships sunk → lost
    boolean hasLost() {
        return ships.stream().allMatch(Ship::isSunk);
    }
}

// driver with simple main() for tests (no JUnit)
public class Battleship {
    public static void main(String[] args) {
        Player p1 = new Player();    // first player
        Player p2 = new Player();    // second player

        // Test 1: valid placement of length-2 ship
        List<Coordinate> s1 = Arrays.asList(
                new Coordinate(0, 0), new Coordinate(0, 1)
        );
        boolean ok1 = p1.placeShip(s1);
        System.out.println("Place valid ship: " + (ok1 ? "PASS" : "FAIL"));

        // Test 2: overlapping placement should fail
        List<Coordinate> s2 = Arrays.asList(
                new Coordinate(0, 1), new Coordinate(0, 2)
        );
        boolean ok2 = p1.placeShip(s2);
        System.out.println("Overlap detection: " + (!ok2 ? "PASS" : "FAIL"));

        // Test 3: hit then sink
        ShotResult r1 = p1.hitSquare(new Coordinate(0, 0));
        System.out.println("Hit detection: " + (r1 == ShotResult.HIT ? "PASS" : "FAIL"));
        ShotResult r2 = p1.hitSquare(new Coordinate(0, 1));
        System.out.println("Sink detection: " + (r2 == ShotResult.SUNK ? "PASS" : "FAIL"));

        // Test 4: has_lost after sinking all
        System.out.println("Loss detection: " + (p1.hasLost() ? "PASS" : "FAIL"));

        // Test 5: out-of-bounds shot treated as MISS
        ShotResult r3 = p1.hitSquare(new Coordinate(-1, 5));
        System.out.println("Bounds check: " + (r3 == ShotResult.MISS ? "PASS" : "FAIL"));

        // Performance test: many random shots on p2 (no crash)
        Random rnd = new Random();
        for (int i = 0; i < 100_000; i++) {
            p2.hitSquare(new Coordinate(rnd.nextInt(10), rnd.nextInt(10)));
        }
        System.out.println("Performance test (100k shots): PASS");
    }
}
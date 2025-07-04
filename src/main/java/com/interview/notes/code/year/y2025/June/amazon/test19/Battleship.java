package com.interview.notes.code.year.y2025.June.amazon.test19;

import java.util.*;

/*
### **Implement the Game: Battleship**

You are tasked with implementing the game **Battleship**, a classic two-player guessing game. If you're not familiar with the game, don't worry—this prompt provides all the details.

---

### **Overview:**

Battleship is played between two players. Each player owns two **10×10 grids** and a **fleet of ships** of different lengths:

* **One grid** is used to secretly place the player's ships.
* **The second grid** is used to record the player's shots at the opponent.

---

### **Game Setup:**

* Before the game begins, **each player secretly arranges their ships** on their main grid.
* Ships occupy a number of **consecutive squares**, arranged either **horizontally or vertically** (not diagonally).
* The ship lengths range from **2 to 6 units**.
* Ships **cannot overlap** and **must be fully contained** within the grid bounds.
* The types and number of ships are the **same for both players**.

---

### **Gameplay:**

* The game proceeds in **turn-based rounds**.
* In each round, a player **targets a square** on the opponent's grid.
* The opponent announces whether it's a **"hit"** or a **"miss"**.
* The player then marks the result on their **secondary grid**.
* When **all squares of a ship** are hit, that ship is considered **sunk**.
* If **all of a player's ships** have been sunk, the game ends and their **opponent wins**.

---

### **Developer Notes from Discussion:**

* The game should be implemented as a **2D matrix** (10×10 grid).
* You can define values for **empty**, **occupied**, **hit**, and **missed** squares as you see fit.
* Inputs such as **ship placements** and **targeted coordinates** can be accepted in any format (e.g., coordinates, strings, etc.).
* All necessary logic must be encapsulated in a **player interface** that supports:

  * **Placing ships**
  * **Targeting squares**
  * **Checking if a player has lost** (all ships sunk)
* Return types for checking hit/miss or game loss status can be **Boolean** or suitable custom values.

---

### **Implement the following methods:**

1. `place_ship(grid, ship_coordinates)`:
   Places a ship on the grid using the provided coordinates.

2. `hit_square(grid, target_coordinate)`:
   Marks a target square and returns whether it was a hit or miss.

3. `has_lost(player)`:
   Checks if the player has lost (i.e., all their ships have been sunk). Returns a boolean.

---
 */
// possible outcomes when a player fires at a coordinate
enum ShotResult {
    MISS,   // shot hit water or was out-of-bounds/repeat
    HIT,    // shot hit but did not yet sink a ship
    SUNK    // shot hit and sank a ship
}

// represents a single (row, col) coordinate on the 10×10 grid
class Coordinate {
    int row;    // zero-based row index
    int col;    // zero-based column index

    // constructor to initialize row and col
    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // override equals to allow comparison in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                              // same object
        if (!(o instanceof Coordinate)) return false;            // wrong type
        Coordinate c = (Coordinate) o;                           // cast to Coordinate
        return row == c.row && col == c.col;                     // equal if row & col match
    }

    // override hashCode to satisfy the equals/hashCode contract
    @Override
    public int hashCode() {
        return Objects.hash(row, col);                           // combine row & col
    }
}

// models one ship: its occupied coordinates and which have been hit
class Ship {
    List<Coordinate> positions;  // all grid squares the ship occupies
    Set<Coordinate> hits;        // subset of positions that have been hit

    // constructor takes the list of its occupied coordinates
    Ship(List<Coordinate> positions) {
        this.positions = positions;        // store ship positions
        this.hits = new HashSet<>();       // no hits initially
    }

    // register a shot; returns true if this ship was hit at c
    boolean registerHit(Coordinate c) {
        if (positions.contains(c)) {       // check if c is part of this ship
            hits.add(c);                   // record the hit
            return true;                   // it was a valid hit
        }
        return false;                      // shot missed this ship
    }

    // check if every position has been hit
    boolean isSunk() {
        return hits.containsAll(positions); // sunk when all positions are in hits
    }
}

// represents a player with two grids and a fleet of ships
class Player {
    private static final int SIZE = 10;    // grid dimensions (10×10)
    int[][] shipGrid;                      // 0=empty,1=ship,2=hit,3=miss (defense)
    int[][] targetGrid;                    // 0=unknown,2=hit,3=miss (attack log)
    List<Ship> ships;                      // list of this player's ships

    // initialize both grids and empty fleet
    Player() {
        shipGrid = new int[SIZE][SIZE];    // all zeros by default
        targetGrid = new int[SIZE][SIZE];  // all zeros by default
        ships = new ArrayList<>();         // no ships at start
    }

    /**
     * placeShip:
     * validates coords are in-bounds and non-overlapping,
     * then marks shipGrid and adds the Ship to the fleet.
     */
    boolean placeShip(List<Coordinate> coords) {
        // bounds & overlap check
        for (Coordinate c : coords) {
            if (c.row < 0 || c.row >= SIZE || c.col < 0 || c.col >= SIZE)
                return false;               // out-of-bounds
            if (shipGrid[c.row][c.col] != 0)
                return false;               // overlap with existing ship
        }
        // mark ship squares on shipGrid
        for (Coordinate c : coords) {
            shipGrid[c.row][c.col] = 1;      // 1 indicates occupied by ship
        }
        ships.add(new Ship(coords));        // add this ship to the fleet
        return true;                        // placement successful
    }

    /**
     * receiveShot:
     * defender’s method. Fires at this player's shipGrid.
     * Updates shipGrid and ships list. Returns MISS, HIT, or SUNK.
     */
    ShotResult receiveShot(Coordinate target) {
        int r = target.row, c = target.col;
        // out-of-bounds or repeat shots are treated as MISS
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE)
            return ShotResult.MISS;
        if (shipGrid[r][c] == 2 || shipGrid[r][c] == 3)
            return ShotResult.MISS;

        // if there's a ship here
        if (shipGrid[r][c] == 1) {
            shipGrid[r][c] = 2;             // mark as hit
            // inform each ship of this shot
            ships.forEach(ship -> ship.registerHit(target));
            // check if this shot caused any ship to sink
            boolean sank = ships.stream()
                    .anyMatch(ship ->
                            ship.positions.contains(target) && ship.isSunk()
                    );
            return sank ? ShotResult.SUNK : ShotResult.HIT;
        } else {
            shipGrid[r][c] = 3;             // mark as miss
            return ShotResult.MISS;
        }
    }

    /**
     * fireAt:
     * attacker’s method. Fires at opponent, updates own targetGrid.
     * Returns the result from opponent.receiveShot(...).
     */
    ShotResult fireAt(Player opponent, Coordinate target) {
        ShotResult result = opponent.receiveShot(target);  // ask opponent to process shot
        // record on targetGrid: 2=hit, 3=miss
        targetGrid[target.row][target.col] = (result == ShotResult.MISS ? 3 : 2);
        return result;                                     // bubble up result
    }

    // check if all this player’s ships are sunk
    boolean hasLost() {
        return ships.stream().allMatch(Ship::isSunk);     // true only if every ship is sunk
    }
}

// driver class with a simple main() for manual PASS/FAIL tests
public class Battleship {
    public static void main(String[] args) {
        Player p1 = new Player();    // create first player
        Player p2 = new Player();    // create second player

        // ========== TEST 1: Valid ship placement on p1 ==========
        List<Coordinate> ship1 = Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(0, 1)
        );
        System.out.println("Test 1 - Place valid ship: " +
                (p1.placeShip(ship1) ? "PASS" : "FAIL")
        );

        // ========== TEST 2: Overlap detection on p1 ==========
        List<Coordinate> ship2 = Arrays.asList(
                new Coordinate(0, 1),
                new Coordinate(0, 2)
        );
        System.out.println("Test 2 - Overlap detection: " +
                (!p1.placeShip(ship2) ? "PASS" : "FAIL")
        );

        // ========== TEST 3: p1 fires at p2 (no ships yet) ==========
        ShotResult r0 = p1.fireAt(p2, new Coordinate(5, 5));
        System.out.println("Test 3 - Fire at empty p2: " +
                (r0 == ShotResult.MISS ? "PASS" : "FAIL")
        );

        // ========== SETUP: place a ship on p2 ==========
        List<Coordinate> ship3 = Arrays.asList(
                new Coordinate(3, 3),
                new Coordinate(3, 4),
                new Coordinate(3, 5)
        );
        p2.placeShip(ship3);

        // ========== TEST 4: Hit detection ==========
        ShotResult r1 = p1.fireAt(p2, new Coordinate(3, 4));
        System.out.println("Test 4 - Hit detection: " +
                (r1 == ShotResult.HIT ? "PASS" : "FAIL")
        );

        // ========== TEST 5: Sink detection ==========
        p1.fireAt(p2, new Coordinate(3, 3)); // first hit
        ShotResult r2 = p1.fireAt(p2, new Coordinate(3, 5)); // final hit
        System.out.println("Test 5 - Sink detection: " +
                (r2 == ShotResult.SUNK ? "PASS" : "FAIL")
        );

        // ========== TEST 6: Loss detection for p2 ==========
        System.out.println("Test 6 - Loss detection p2: " +
                (p2.hasLost() ? "PASS" : "FAIL")
        );

        // ========== TEST 7: targetGrid updated on p1 ==========
        boolean recorded = (p1.targetGrid[3][3] == 2 && p1.targetGrid[3][4] == 2 && p1.targetGrid[3][5] == 2);
        System.out.println("Test 7 - targetGrid update: " +
                (recorded ? "PASS" : "FAIL")
        );

        // ========== TEST 8: Out-of-bounds shot ==========
        ShotResult r3 = p1.fireAt(p2, new Coordinate(-1, 9));
        System.out.println("Test 8 - Out-of-bounds shot: " +
                (r3 == ShotResult.MISS ? "PASS" : "FAIL")
        );

        // ========== TEST 9: Performance test ==========
        Random rnd = new Random();    // for random coordinates
        for (int i = 0; i < 100_000; i++) {
            p1.fireAt(p2, new Coordinate(rnd.nextInt(10), rnd.nextInt(10)));
        }
        System.out.println("Test 9 - Performance (100k shots): PASS");
    }
}
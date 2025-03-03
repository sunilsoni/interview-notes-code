package com.interview.notes.code.year.y2025.feb.Amazon.test4;

import java.util.*;
import java.util.stream.Collectors;

/*

### Fulfillment Center System

In a fulfillment center (FC), a fleet of robots (R) is responsible for retrieving shelves (S) that contain the items requested by customers and transporting them to the designated pick station (P).

#### Warehouse Layout:
```
+----+----+----+----+----+
|  P |    |    |    |    |
+----+----+----+----+----+
|    |   |  S |    |    |
+----+----+----+----+----+
|   |  R  |    |   |    S |
+----+----+----+----+----+
|   |  SR |   |  S  |
+----+----+----+----+----+
|    |    |    |    |R
+----+----+----+----+----+
```

When a customer places an order, the system needs to:
- Identify the shelf (S) that should be retrieved to fulfill the order.
- Assign a robot (R) to perform the retrieval.

The requested item may be present on multiple shelves, and multiple quantities of a single item may be distributed arbitrarily across shelves in the FC.

#### To avoid congestion, the system should:
1. Randomly select among shelves that contain the requested item.
2. If possible, select the robot assignment that minimizes the time it takes for the shelf to be retrieved. Otherwise, just select any available robot.

---

### API Design Requirements

Design and implement an API that:
- **Input:** A requested item assigned to a pick station (P).
- **Output:** The robot (R) and the shelf (S) that should be retrieved to fulfill the order.

#### Additional Considerations:
1. If needed, model core entities (e.g., Item, Shelf, Robot).
2. Choose a suitable data structure to represent the fulfillment center.
3. Implement the API per the above requirements.

 */
public class FulfillmentCenterSystem {

    // Lists representing the FC state
    static List<Shelf> shelves = new ArrayList<>();
    static List<Robot> robots = new ArrayList<>();

    // Helper to calculate Manhattan distance
    private static int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * API method:
     * Given a requested item name and a pick station, this method returns an Assignment
     * (which shelf and robot should be used) or null if not possible.
     */
    public static Assignment assignShelfAndRobot(String itemName, PickStation pickStation) {
        // Step 1: Filter shelves containing the requested item.
        List<Shelf> matchingShelves = shelves.stream()
                .filter(shelf -> shelf.containsItem(itemName))
                .collect(Collectors.toList());

        if (matchingShelves.isEmpty()) {
            System.out.println("No shelf contains the requested item: " + itemName);
            return null;
        }

        // Step 2: Randomly select one shelf from the matching shelves.
        Shelf selectedShelf = matchingShelves.get(new Random().nextInt(matchingShelves.size()));

        // Step 3: Choose the robot that minimizes retrieval time.
        // For this example, we calculate the Manhattan distance from robot to the shelf.
        Optional<Robot> selectedRobotOpt = robots.stream()
                .min(Comparator.comparingInt(r -> calculateDistance(r.x, r.y, selectedShelf.x, selectedShelf.y)));

        if (!selectedRobotOpt.isPresent()) {
            System.out.println("No available robot.");
            return null;
        }

        return new Assignment(selectedShelf, selectedRobotOpt.get());
    }

    // Testing method using a simple main method (no JUnit)
    public static void main(String[] args) {
        // Initialize sample data for testing
        shelves.clear();
        robots.clear();

        // Create shelves with items
        shelves.add(new Shelf("S1", 1, 2, Arrays.asList(new Item("Book"), new Item("Pen"))));
        shelves.add(new Shelf("S2", 3, 4, Arrays.asList(new Item("Laptop"), new Item("Book"))));
        shelves.add(new Shelf("S3", 5, 6, Arrays.asList(new Item("Tablet"))));

        // Create robots with positions
        robots.add(new Robot("R1", 0, 0));
        robots.add(new Robot("R2", 2, 3));
        robots.add(new Robot("R3", 4, 5));

        // Define a pick station
        PickStation pickStation = new PickStation("P1", 0, 0);

        // Test Case 1: Normal scenario - item exists in multiple shelves
        System.out.println("Test Case 1: Requesting 'Book'");
        Assignment assignment1 = assignShelfAndRobot("Book", pickStation);
        if (assignment1 != null) {
            System.out.println("PASS: " + assignment1);
        } else {
            System.out.println("FAIL: No assignment created for 'Book'");
        }

        // Test Case 2: Edge scenario - item not found
        System.out.println("\nTest Case 2: Requesting 'Camera'");
        Assignment assignment2 = assignShelfAndRobot("Camera", pickStation);
        if (assignment2 == null) {
            System.out.println("PASS: Correctly handled missing item 'Camera'");
        } else {
            System.out.println("FAIL: Unexpected assignment for 'Camera'");
        }

        // Test Case 3: Edge scenario - no robots available
        System.out.println("\nTest Case 3: No robots available");
        List<Robot> backupRobots = new ArrayList<>(robots);
        robots.clear();  // simulate no robots available
        Assignment assignment3 = assignShelfAndRobot("Book", pickStation);
        if (assignment3 == null) {
            System.out.println("PASS: Correctly handled no available robots");
        } else {
            System.out.println("FAIL: Unexpected assignment when no robots are available");
        }
        // Restore robots for further tests
        robots.addAll(backupRobots);

        // Test Case 4: Stress test with large data input
        System.out.println("\nTest Case 4: Stress test with large data input");
        // Add a large number of shelves and robots
        for (int i = 0; i < 10000; i++) {
            shelves.add(new Shelf("S_large_" + i, i % 100, i % 100, Arrays.asList(new Item("Item" + (i % 10)))));
            robots.add(new Robot("R_large_" + i, i % 50, i % 50));
        }
        long startTime = System.currentTimeMillis();
        Assignment assignment4 = assignShelfAndRobot("Item5", pickStation);
        long endTime = System.currentTimeMillis();
        if (assignment4 != null) {
            System.out.println("PASS: Stress test assignment created in " + (endTime - startTime) + " ms: " + assignment4);
        } else {
            System.out.println("FAIL: Stress test assignment failed");
        }
    }

    // Basic entity classes
    static class Item {
        String name;

        public Item(String name) {
            this.name = name;
        }
    }

    static class Shelf {
        String id;
        List<Item> items;
        int x, y;  // coordinate position

        public Shelf(String id, int x, int y, List<Item> items) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.items = items;
        }

        public boolean containsItem(String itemName) {
            return items.stream().anyMatch(item -> item.name.equalsIgnoreCase(itemName));
        }
    }

    static class Robot {
        String id;
        int x, y;  // coordinate position

        public Robot(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class PickStation {
        String id;
        int x, y;  // coordinate position

        public PickStation(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    // Assignment result object
    static class Assignment {
        Shelf shelf;
        Robot robot;

        public Assignment(Shelf shelf, Robot robot) {
            this.shelf = shelf;
            this.robot = robot;
        }

        @Override
        public String toString() {
            return "Assignment: Shelf[" + shelf.id + "] at (" + shelf.x + ", " + shelf.y + "), " +
                    "Robot[" + robot.id + "] at (" + robot.x + ", " + robot.y + ")";
        }
    }
}
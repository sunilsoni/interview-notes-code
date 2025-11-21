package com.interview.notes.code.year.y2025.feb.Amazon.test3;

import java.util.*;

class FulfillmentCenter {
    private final List<Robot> robots;
    private final List<Shelf> shelves;
    private final Position pickStation;

    public FulfillmentCenter() {
        robots = new ArrayList<>();
        shelves = new ArrayList<>();
        pickStation = new Position(0, 0); // P is at (0,0)
        initializeWarehouse();
    }

    // Main method for testing
    public static void main(String[] args) {
        FulfillmentCenter fc = new FulfillmentCenter();

        // Test case 1: Valid order
        Map<String, String> result1 = fc.processOrder("item1");
        System.out.println("Test 1 - Order for item1: " +
                (result1.isEmpty() ? "FAIL" : "PASS - Robot: " + result1.get("robotId") +
                        ", Shelf: " + result1.get("shelfId")));

        // Test case 2: Non-existent item
        Map<String, String> result2 = fc.processOrder("item4");
        System.out.println("Test 2 - Order for non-existent item: " +
                (result2.isEmpty() ? "PASS" : "FAIL"));

        // Test case 3: Large number of orders
        System.out.println("Test 3 - Processing multiple orders:");
        for (int i = 0; i < 100; i++) {
            Map<String, String> result = fc.processOrder("item1");
            if (result.isEmpty()) {
                System.out.println("FAIL at iteration " + i);
                break;
            }
            if (i == 99) System.out.println("PASS - Successfully processed 100 orders");
        }
    }

    private void initializeWarehouse() {
        // Initialize robots
        robots.add(new Robot("R1", new Position(1, 2)));
        robots.add(new Robot("R2", new Position(4, 4)));

        // Initialize shelves with items
        Shelf s1 = new Shelf("S1", new Position(2, 1));
        s1.items.put("item1", 5);
        s1.items.put("item2", 3);

        Shelf s2 = new Shelf("S2", new Position(4, 1));
        s2.items.put("item1", 2);
        s2.items.put("item3", 4);

        shelves.addAll(Arrays.asList(s1, s2));
    }

    public Map<String, String> processOrder(String itemId) {
        // Find available shelves containing the item
        List<Shelf> availableShelves = null;// shelves.stream()

        if (availableShelves.isEmpty()) {
            return Collections.emptyMap();
        }

        // Randomly select a shelf
        Shelf selectedShelf = availableShelves.get(new Random().nextInt(availableShelves.size()));

        // Find nearest available robot
        Optional<Robot> nearestRobot = robots.stream()
                .filter(r -> r.available)
                .min((r1, r2) -> {
                    int dist1 = calculateDistance(r1.position, selectedShelf.position);
                    int dist2 = calculateDistance(r2.position, selectedShelf.position);
                    return Integer.compare(dist1, dist2);
                });

        if (!nearestRobot.isPresent()) {
            return Collections.emptyMap();
        }

        Map<String, String> result = new HashMap<>();
        result.put("robotId", nearestRobot.get().id);
        result.put("shelfId", selectedShelf.id);
        return result;
    }

    private int calculateDistance(Position p1, Position p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    // Core entities
    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Robot {
        String id;
        Position position;
        boolean available;

        Robot(String id, Position position) {
            this.id = id;
            this.position = position;
            this.available = true;
        }
    }

    static class Shelf {
        String id;
        Position position;
        Map<String, Integer> items; // item -> quantity

        Shelf(String id, Position position) {
            this.id = id;
            this.position = position;
            this.items = new HashMap<>();
        }
    }
}

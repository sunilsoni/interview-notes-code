package com.interview.notes.code.year.y2025.June.wallmart.test1;

import java.util.*;

public class GameLevelNavigator {

    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 10;
    private static final int[] OBSTACLES = {4, 6};

    public static boolean navigateLevel(List<Integer> obstacles, String instructions) {
        int position = 0;
        char lastDirection = 'R';

        System.out.println("\nInstructions: " + instructions);
        System.out.println("Starting position: " + position);

        for (char move : instructions.toCharArray()) {
            int prevPosition = position;

            switch (move) {
                case 'R':
                    if (position < 10) {
                        position++;
                        lastDirection = 'R';
                    }
                    break;

                case 'L':
                    if (position > 0) {
                        position--;
                        lastDirection = 'L';
                    }
                    break;

                case 'J':
                    // Modified jump logic
                    if (lastDirection == 'R') {
                        // Ensure jump doesn't exceed position 10
                        int newPos = position + 2;
                        position = (newPos <= 10) ? newPos : position;
                    } else {
                        // Ensure jump doesn't go below position 0
                        int newPos = position - 2;
                        position = (newPos >= 0) ? newPos : position;
                    }
                    break;
            }

            System.out.printf("Move: %c, Position: %d -> %d (Direction: %c)%n",
                    move, prevPosition, position, lastDirection);

            if (obstacles.contains(position)) {
                System.out.println("Hit obstacle at position: " + position);
                return false;
            }
        }

        System.out.println("Final position: " + position);
        return position == 10;
    }

    public static void main1(String[] args) {
        List<Integer> obstacles1 = Arrays.asList(4, 6);
        List<Integer> obstacles2 = Arrays.asList(9, 4, 2);
        List<Integer> obstacles3 = Collections.emptyList();

        // Focus on failing tests
        System.out.println("Testing Test 3: RRRJJRRL");
        boolean test3 = navigateLevel(obstacles1, "RRRJJRRL");
        System.out.println("Test 3 result: " + test3);

        System.out.println("\nTesting Test 9: RJJJR");
        boolean test9 = navigateLevel(obstacles1, "RJJJR");
        System.out.println("Test 9 result: " + test9);

        // Run all tests
        Map<String, TestCase> tests = new LinkedHashMap<>();
        tests.put("Test 1", new TestCase(obstacles1, "RRRJJRRR", true));
        tests.put("Test 2", new TestCase(obstacles1, "RRRLJ", false));
        tests.put("Test 3", new TestCase(obstacles1, "RRRJJRRL", true));
        tests.put("Test 4", new TestCase(obstacles1, "RRRLRJJRRR", true));
        tests.put("Test 5", new TestCase(obstacles1, "RRRRRRRRRR", false));
        tests.put("Test 6", new TestCase(obstacles2, "RJJRRRRR", false));
        tests.put("Test 7", new TestCase(obstacles2, "RJJRRRJ", true));
        tests.put("Test 8", new TestCase(obstacles3, "R", false));
        tests.put("Test 9", new TestCase(obstacles1, "RJJJR", true));
        tests.put("Test 10", new TestCase(obstacles1, "R".repeat(1000), false));

        int passed = 0;
        for (Map.Entry<String, TestCase> entry : tests.entrySet()) {
            TestCase test = entry.getValue();
            boolean result = navigateLevel(test.obstacles, test.instructions);
            boolean isPassed = result == test.expectedResult;

            System.out.printf("%s: %s (Expected: %b, Got: %b)%n",
                    entry.getKey(),
                    isPassed ? "PASS" : "FAIL",
                    test.expectedResult,
                    result
            );

            if (isPassed) passed++;
        }

        System.out.printf("%nTests Summary: %d/%d passed (%.1f%%)%n",
                passed, tests.size(), (passed * 100.0 / tests.size()));
    }

    public static boolean canReachExit(String instructions) {
        int position = 0;
        char previousMove = 'R'; // Default direction is right

        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'R':
                    if (position < MAX_POSITION) {
                        position++;
                    }
                    previousMove = 'R';
                    break;

                case 'L':
                    if (position > MIN_POSITION) {
                        position--;
                    }
                    previousMove = 'L';
                    break;

                case 'J':
                    // Jump 2 positions based on previous direction
                    if (previousMove == 'R') {
                        position = Math.min(position + 2, MAX_POSITION);
                    } else { // previousMove == 'L'
                        position = Math.max(position - 2, MIN_POSITION);
                    }
                    break;
            }

            // Check for obstacles after each move
            if (position == 4 || position == 6) {
                return false;
            }

            // Success condition
            if (position == MAX_POSITION) {
                return true;
            }
        }

        // Check final position
        return position == MAX_POSITION;
    }

    static class TestCase {
        List<Integer> obstacles;
        String instructions;
        boolean expectedResult;

        TestCase(List<Integer> obstacles, String instructions, boolean expectedResult) {
            this.obstacles = obstacles;
            this.instructions = instructions;
            this.expectedResult = expectedResult;
        }
    }

}

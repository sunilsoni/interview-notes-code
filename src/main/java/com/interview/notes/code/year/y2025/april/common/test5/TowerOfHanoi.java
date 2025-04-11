package com.interview.notes.code.year.y2025.april.common.test5;

public class TowerOfHanoi {
    private static int moveCount = 0;

    public static void solveTowerOfHanoi(int n, char source, char auxiliary, char target) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + target);
            moveCount++;
            return;
        }
        
        solveTowerOfHanoi(n - 1, source, target, auxiliary);
        System.out.println("Move disk " + n + " from " + source + " to " + target);
        moveCount++;
        solveTowerOfHanoi(n - 1, auxiliary, source, target);
    }

    public static void main(String[] args) {
        // Test cases
        testHanoi(1, 1);      // Simplest case
        testHanoi(2, 3);      // Basic case
        testHanoi(3, 7);      // Standard case
        testHanoi(4, 15);     // Medium case
        testHanoi(5, 31);     // Larger case
        testHanoi(9, 511);     // Larger case

    }

    private static void testHanoi(int disks, int expectedMoves) {
        System.out.println("\nTesting with " + disks + " disks:");
        moveCount = 0;
        solveTowerOfHanoi(disks, 'A', 'B', 'C');
        
        boolean passed = (moveCount == expectedMoves);
        System.out.println("Expected moves: " + expectedMoves);
        System.out.println("Actual moves: " + moveCount);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }
}

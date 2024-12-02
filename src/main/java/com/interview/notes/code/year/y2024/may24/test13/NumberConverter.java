package com.interview.notes.code.year.y2024.may24.test13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 7. Number Converter
 * Given two integers A and B, convert A to B using the minimum number of operations. The operations allowed are:
 * 1. Subtract 1 from the current value of A.
 * 2. Multiply the current value of A by 2.
 * For each of these samples, determine the minimum number of operations required. Select the matching list from the answers below.
 * Sample 01: A = 1, B = 2
 * Sample 02: A = 9, B = 1
 * Sample 03: A = 2, B = 10
 * Sample 04: A = 100, B = 500
 * Pick ONE option
 * 1, 9, 8, 400
 * 1, 8, 6, 400
 * 1, 8, 6, 40
 * 1, 8, 5, 41
 */
class NumberConverter {
    // Method to find minimum operations to convert A to B
    public static int minOperations(int A, int B) {
        if (A == B) return 0;

        // Use a queue to implement BFS
        Queue<State> queue = new LinkedList<>();
        // Use a set to avoid revisiting the same state
        Set<Integer> visited = new HashSet<>();

        // Initialize the queue with the starting point
        queue.add(new State(A, 0));
        visited.add(A);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Check the two possible operations
            // Operation 1: Subtract 1
            int nextValue = current.value - 1;
            if (nextValue == B) return current.steps + 1;
            if (nextValue > 0 && visited.add(nextValue)) {
                queue.add(new State(nextValue, current.steps + 1));
            }

            // Operation 2: Multiply by 2
            nextValue = current.value * 2;
            if (nextValue == B) return current.steps + 1;
            if (nextValue > 0 && visited.add(nextValue)) {
                queue.add(new State(nextValue, current.steps + 1));
            }
        }

        return -1; // Return -1 if no solution is found (should not happen in these cases)
    }

    public static void main(String[] args) {
        int[][] samples = {
                {1, 2},
                {9, 1},
                {2, 10},
                {100, 500}
        };

        for (int[] sample : samples) {
            int operations = minOperations(sample[0], sample[1]);
            System.out.println("Minimum operations to convert " + sample[0] + " to " + sample[1] + ": " + operations);
        }
    }

    // A helper class to hold the current value and the count of operations
    static class State {
        int value;
        int steps;

        State(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }
}

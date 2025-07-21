package com.interview.notes.code.year.y2025.july.amazon.test5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {
    // Main method to find minimum dice throws
    static int getMinDiceThrows(int[] moves, int N) {
        // Create a visited array to mark cells we've already processed
        boolean[] visited = new boolean[N];

        // Create a queue for BFS
        Queue<QueueEntry> queue = new LinkedList<>();

        // Initialize first position (0) with 0 dice throws
        visited[0] = true;
        queue.add(new QueueEntry(0, 0));

        // Create entry for tracking current position
        QueueEntry qe = null;

        // BFS implementation
        while (!queue.isEmpty()) {
            // Remove front entry from queue
            qe = queue.remove();
            int vertex = qe.vertex;

            // If we reached final position, return dice throws
            if (vertex == N - 1)
                break;

            // Check all possible dice values (1 to 6)
            for (int j = vertex + 1; j <= (vertex + 6) && j < N; j++) {
                // If not visited
                if (!visited[j]) {
                    // Mark as visited
                    visited[j] = true;

                    // Calculate next position after snake/ladder
                    int nextPosition = moves[j] != -1 ? moves[j] : j;

                    // Add to queue with increased dice throw count
                    queue.add(new QueueEntry(nextPosition, qe.distance + 1));
                }
            }
        }

        // Return final count of dice throws
        return qe.distance;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Simple board
        int N = 30;
        int[] moves = new int[N];
        Arrays.fill(moves, -1);

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;

        int result = getMinDiceThrows(moves, N);
        System.out.println("Test Case 1: " + (result == 3 ? "PASS" : "FAIL") +
                " (Expected: 3, Got: " + result + ")");

        // Test Case 2: Large board
        N = 100;
        moves = new int[N];
        Arrays.fill(moves, -1);

        // Add more ladders and snakes for large board
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        result = getMinDiceThrows(moves, N);
        System.out.println("Test Case 2: " + (result == 7 ? "PASS" : "FAIL") +
                " (Expected: 7, Got: " + result + ")");
    }

    // Class to represent a cell in the queue with position and dice throws
    static class QueueEntry {
        int vertex;     // Current position on board
        int distance;   // Number of dice throws to reach this position

        public QueueEntry(int v, int d) {
            vertex = v;
            distance = d;
        }
    }
}

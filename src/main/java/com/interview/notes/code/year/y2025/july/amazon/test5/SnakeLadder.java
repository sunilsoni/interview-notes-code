package com.interview.notes.code.year.y2025.july.amazon.test5;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Class to solve the minimum dice throws problem on a snakes & ladders board
public class SnakeLadder {

    /**
     * Computes minimum dice throws to reach the last square (N-1).
     *
     * @param move array of length N, where move[i] = -1 if no jump,
     *             or move[i] = destination index of snake/ladder.
     * @return minimum number of throws, or -1 if unreachable.
     */
    public static int minDiceThrows(int[] move) {
        int N = move.length;                   // board size
        boolean[] visited = new boolean[N];    // track visited squares
        Deque<Node> queue = new ArrayDeque<>();// BFS queue

        // start at square 0 with 0 throws
        visited[0] = true;                     // mark start visited
        queue.addLast(new Node(0, 0));         // enqueue start

        while (!queue.isEmpty()) {             // standard BFS loop
            Node node = queue.removeFirst();   // dequeue next
            int s = node.pos;                  // current square

            // try all possible dice rolls from 1 to 6
            for (int d = 1; d <= 6; d++) {
                int t = s + d;                 // raw target square
                if (t >= N) break;             // cannot go beyond board

                // if there's a snake or ladder, take it
                if (move[t] != -1) {
                    t = move[t];               // jump to destination
                }

                // if we've reached the last square, return result
                if (t == N - 1) {
                    return node.throwsSoFar + 1; // one more throw
                }

                // enqueue if not yet visited
                if (!visited[t]) {
                    visited[t] = true;         // prevent re-visiting
                    queue.addLast(new Node(t, node.throwsSoFar + 1));
                }
            }
        }

        // if BFS finishes without reaching end
        return -1;                              // unreachable
    }

    // Entry point with multiple tests, prints PASS or FAIL
    public static void main(String[] args) {
        // build test cases
        List<TestCase> tests = Arrays.asList(
                new TestCase(
                        "Small board with snakes & ladders",
                        new int[]{ // size = 30 example
                                -1, 14, -1, -1, -1, 17, -1, -1,
                                4, -1, -1, 26, -1, -1, -1, -1,
                                -1, -1, -1, -1, -1, -1, -1, -1,
                                -1, -1, -1, -1, -1, -1
                        },
                        3 // sample known answer
                ),
                new TestCase(
                        "No snakes/ladders (straight run)",
                        createNoJumpBoard(25),      // filled with -1
                        (25 - 1 + 5) / 6            // ceil((N-1)/6)
                ),
                new TestCase(
                        "Immediate ladder from start to finish",
                        new int[]{1, -1},          // size=2, ladder from 0 → 1
                        1                           // one throw (roll a 1)
                ),
                new TestCase(
                        "Large board without jumps",
                        createNoJumpBoard(10000),   // large board
                        (10000 - 1 + 5) / 6         // expected throws
                )
        );

        // run tests using Java 8 Stream API
        tests.stream().forEach(tc -> {
            int result = minDiceThrows(tc.board);         // compute result
            String status = (result == tc.expected)
                    ? "PASS"
                    : "FAIL (got " + result + ")";
            System.out.printf("%s: %s%n", tc.name, status); // print outcome
        });
    }

    // Helper to create a board of given size with no snakes/ladders
    private static int[] createNoJumpBoard(int size) {
        int[] board = new int[size];   // allocate array
        Arrays.fill(board, -1);        // fill with -1 (no jumps)
        return board;                  // return it
    }

    // Inner class to represent a board position and the number of throws taken to reach it
    private static class Node {
        int pos;         // current square index
        int throwsSoFar; // dice throws taken to reach this square

        Node(int pos, int throwsSoFar) {
            this.pos = pos;               // assign square index
            this.throwsSoFar = throwsSoFar; // assign throws count
        }
    }

    // Simple TestCase holder
    private static class TestCase {
        String name;   // descriptive name
        int[] board;   // move[] array
        int expected;  // expected throws

        TestCase(String name, int[] board, int expected) {
            this.name = name;
            this.board = board;
            this.expected = expected;
        }
    }
}
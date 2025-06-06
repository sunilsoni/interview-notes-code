package com.interview.notes.code.year.y2025.may.common.test13;

import java.util.*;

public class GoldKeyGame {

    /**
     * Determines the outcome of the Gold-Plated Key Game under optimal play.
     *
     * @param N Total number of keys (positions 1..N).
     * @param P Starting position of the gold key.
     * @param M Number of consecutive keys to reverse in each move.
     * @param X Winning position for the gold key.
     * @return "Steve", "Harvey", or "Draw" depending on the result.
     */
    public static String gameResult(int N, int P, int M, int X) {
        // If the gold key already starts at the winning position, Steve wins immediately.
        if (P == X) {
            return "Steve";
        }

        // If M > N, no valid reversal segment exists → no moves possible → Draw.
        if (M > N) {
            return "Draw";
        }

        // Build two TreeSets containing all unvisited positions of each parity:
        // evenSet holds even indices, oddSet holds odd indices.
        TreeSet<Integer> evenSet = new TreeSet<>();
        TreeSet<Integer> oddSet = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            // Add each index to the appropriate parity set.
            if ((i & 1) == 0) {
                evenSet.add(i);
            } else {
                oddSet.add(i);
            }
        }

        // We will run a BFS from X to find the shortest distance (in moves)
        // from X to every other position. Then we check dist[P].
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1); // -1 means "unvisited"

        Deque<Integer> queue = new ArrayDeque<>();
        dist[X] = 0;            // Distance from X to itself is zero
        queue.offerLast(X);     // Start BFS from X

        // Remove X from its parity set so we never revisit it.
        if ((X & 1) == 0) {
            evenSet.remove(X);
        } else {
            oddSet.remove(X);
        }

        // BFS loop: compute distances from X to all positions by traversing edges backward.
        while (!queue.isEmpty()) {
            int curr = queue.pollFirst();
            int currDist = dist[curr];

            // Determine the parity of neighbor positions that can reverse to 'curr':
            // A neighbor j must satisfy j % 2 == (curr + M - 1) % 2.
            int neededParity = ((curr + M - 1) & 1);

            // Compute the range [low..high] of indices j that could reach 'curr' by reversing M keys.
            int low = curr - (M - 1);
            int high = curr + (M - 1);
            if (low < 1) {
                low = 1;
            }
            if (high > N) {
                high = N;
            }

            // Select the TreeSet that holds all indices of neededParity.
            TreeSet<Integer> paritySet = (neededParity == 0) ? evenSet : oddSet;

            // Repeatedly pull any unvisited position j from paritySet in [low..high].
            // Each such position j is one reversal away from curr.
            Integer candidate = paritySet.ceiling(low);
            while (candidate != null && candidate <= high) {
                dist[candidate] = currDist + 1;
                queue.offerLast(candidate);

                // Remove from paritySet so we don't revisit
                paritySet.remove(candidate);

                // Move to the next candidate in range
                candidate = paritySet.ceiling(low);
            }
        }

        // After BFS, dist[i] is the minimum number of moves to go from i to X.
        // If dist[P] == -1, P is not connected to X → Draw.
        if (dist[P] == -1) {
            return "Draw";
        }

        // Otherwise, if dist[P] is odd, Steve wins (he moves on move 1,3,5,...).
        // If dist[P] is even, Harvey wins (he moves on move 2,4,6,...).
        return ((dist[P] & 1) == 1) ? "Steve" : "Harvey";
    }

    /**
     * Helper to run a single test: compares actual vs expected, prints PASS or FAIL.
     */
    private static void runTest(int N, int P, int M, int X, String expected) {
        String actual = gameResult(N, P, M, X);
        if (actual.equals(expected)) {
            System.out.println("PASS | N=" + N + " P=" + P + " M=" + M + " X=" + X +
                    " | Expected=" + expected + " | Actual=" + actual);
        } else {
            System.out.println("FAIL | N=" + N + " P=" + P + " M=" + M + " X=" + X +
                    " | Expected=" + expected + " | Actual=" + actual);
        }
    }

    /**
     * Main method: runs provided examples, edge cases, and a large random test for performance.
     */
    public static void main(String[] args) {
        System.out.println("=== Provided Example Tests ===");
        // Example 1: N=3, P=1, M=2, X=2 -> Steve can reverse [1,2] and win immediately.
        runTest(3, 1, 2, 2, "Steve");

        // Example 2: N=4, P=1, M=2, X=4 -> Steve can force a win.
        runTest(4, 1, 2, 4, "Steve");

        System.out.println("\n=== Additional Edge Case Tests ===");
        // 1. P == X at start -> Steve wins without any moves.
        runTest(5, 3, 3, 3, "Steve");

        // 2. M = 1: Reversing a single key does nothing. If P != X -> Draw.
        runTest(10, 4, 1, 7, "Draw");

        // 3. N < M: No valid reversal segment exists -> Draw if P != X.
        runTest(5, 2, 6, 4, "Draw");

        // 4. Small chain where Harvey wins because dist[P] is even.
        //    N=5, M=3, P=2, X=5 -> shortest path length from 2 to 5 is 2 moves (Harvey).
        runTest(5, 2, 3, 5, "Harvey");

        // 5. Another scenario: Steve wins if dist[P] is odd.
        //    N=7, M=4, P=2, X=7 -> shortest path length from 2 to 7 is 3 moves (Steve).
        runTest(7, 2, 4, 7, "Steve");

        // 6. Parity‐mismatch case when M is even and P, X both even (they become unreachable) -> Draw.
        runTest(10, 2, 2, 4, "Harvey"); // Actually with M=2 you can move 2→3→4 in 2 moves -> Harvey.

        System.out.println("\n=== Large Random/Performance Test ===");
        // Build one large random scenario: N=1_000_000, random P, M, X.
        // We won't know expected, just ensure it runs fast.
        Random rand = new Random(1);
        int N_large = 1_000_000;
        int M_large = rand.nextInt(N_large) + 1;   // M in [1..N]
        int P_large = rand.nextInt(N_large) + 1;   // P in [1..N]
        int X_large = rand.nextInt(N_large) + 1;   // X in [1..N]
        long start = System.currentTimeMillis();
        String resultLarge = gameResult(N_large, P_large, M_large, X_large);
        long end = System.currentTimeMillis();
        System.out.println("Large test completed in " + (end - start) + " ms.  Result: " + resultLarge);
    }
}
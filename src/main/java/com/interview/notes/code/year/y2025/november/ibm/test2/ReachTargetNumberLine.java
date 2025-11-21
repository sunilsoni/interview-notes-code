package com.interview.notes.code.year.y2025.november.ibm.test2;

import java.util.stream.IntStream;

/**
 * Class that contains the solution method reachTarget
 * and a main method to run manual tests (no JUnit).
 */
public class ReachTargetNumberLine {

    /**
     * Function: reachTarget
     * ---------------------
     * Finds the minimum number of moves to reach exactly the given target.
     *
     * @param target the desired final position on the number line
     * @return minimum number of moves required
     */
    public static int reachTarget(int target) {
        // Take absolute value of target because going to -X or +X
        // needs the same number of steps due to symmetry.
        int t = Math.abs(target);

        // If target is 0, we are already there; no moves needed.
        if (t == 0) {
            return 0;
        }

        // Use long for sum to avoid overflow when summing many integers.
        long sum = 0L;   // sum of 1 + 2 + ... + steps
        int steps = 0;   // how many moves we have taken so far

        // Loop until both conditions are true:
        // 1) sum >= t  (we have reached or passed the target)
        // 2) (sum - t) is even (we can flip directions of some moves)
        while (sum < t || ((sum - t) & 1L) != 0L) {
            // Each iteration represents taking the next move.
            steps++;          // increase move count (next move length)
            sum += steps;     // add that move length to the running sum
            // We do not need to decide left/right here; we only care
            // about the total distance we *could* reach by adjusting signs later.
        }

        // When the loop ends, 'steps' is the minimum number of moves
        // that meets both requirements.
        return steps;
    }

    /**
     * Helper method to run a single test and print result.
     *
     * @param target   input target position
     * @param expected expected minimum moves
     */
    private static void runSingleTest(int target, int expected) {
        // Call the solution method with the given target.
        int actual = reachTarget(target);

        // Check if the actual result matches the expected result.
        boolean pass = (actual == expected);

        // Build a simple message to show test details.
        String msg = "Target = " + target
                + ", Expected = " + expected
                + ", Actual = " + actual
                + " --> " + (pass ? "PASS" : "FAIL");

        // Print the message so we can see the outcome for this test.
        System.out.println(msg);
    }

    /**
     * Main method: runs multiple test cases and prints PASS/FAIL.
     * This replaces any need for JUnit or other unit-test frameworks.
     */
    public static void main(String[] args) {
        // Define an array of targets to test.
        // These include positive, zero, negative, small, and one large input.
        int[] targets = {
                7,              // example from the problem statement
                3,              // example from the problem statement
                0,              // already at the target
                1,              // smallest positive target
                2,              // check small number where overshoot is needed
                4,              // another small target
                5,              // small odd target
                6,              // small even target
                11,             // slightly larger number
                -7,             // negative target (symmetry check)
                1_000_000_000   // large target to test performance and overflow safety
        };

        // Pre-computed expected answers for each target above.
        // These values come from mathematical reasoning and manual checks.
        int[] expected = {
                5,      // target 7 -> 5 moves (given in problem)
                2,      // target 3 -> 2 moves (given in problem)
                0,      // target 0 -> 0 moves
                1,      // target 1 -> 1 move: +1
                3,      // target 2 -> moves: +1, -2, +3
                3,      // target 4 -> moves: +1, +2, +3 then flip one move
                5,      // target 5 -> minimum moves is 5
                3,      // target 6 -> +1 +2 +3 = 6
                5,      // target 11 -> known answer from reasoning
                5,      // target -7 -> same as +7 due to symmetry
                44_723  // target 1_000_000_000 -> checked with our formula/logic
        };

        // Sanity check: arrays should be same length to pair correctly.
        if (targets.length != expected.length) {
            // If this ever happens, something is wrong with test setup.
            System.out.println("Test arrays length mismatch. Please fix test data.");
            return;  // Exit early to avoid index errors.
        }

        // Use Java 8 IntStream to iterate over indices of the test arrays.
        // This satisfies the "use Java 8 Stream API" requirement.
        IntStream.range(0, targets.length)
                .forEach(i -> runSingleTest(targets[i], expected[i]));

        // Optional extra: a quick performance demonstration for a very large range.
        // Here we only count how many times reachTarget can be called fast.
        // We DO NOT print every result to keep output small and handle "large data" smoothly.
        int maxSample = 100_000;  // up to 100k targets for a quick stress test
        long startTime = System.currentTimeMillis();  // capture current time before loop

        // Use IntStream again to call reachTarget for many values.
        long count = IntStream.rangeClosed(1, maxSample)
                .map(ReachTargetNumberLine::reachTarget) // call method for each target
                .count();  // force the stream to execute; result itself is not important

        long endTime = System.currentTimeMillis();  // capture time after loop

        // Print simple performance information.
        System.out.println("Stress test calls = " + count
                + ", range 1.." + maxSample
                + ", time taken = " + (endTime - startTime) + " ms");
    }
}

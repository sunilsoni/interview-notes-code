package com.interview.notes.code.months.sept24.test8;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate1 {

    public static void main(String[] args) {
        Dota2Senate1 solution = new Dota2Senate1();
        boolean allTestsPassed = true;

        // Test cases
        String[] testCases = {
                "RD",
                "RDD",
                "RDRD",
                "RRDDD",
                "DDRRR",
                "R",
                "D",
                "RRRR",
                "DDDD",
                "RDRDRDRD"
        };
        String[] expectedResults = {
                "Radiant",
                "Dire",
                "Radiant",
                "Dire",
                "Radiant",
                "Radiant",
                "Dire",
                "Radiant",
                "Dire",
                "Radiant"
        };

        // Process each test case
        for (int i = 0; i < testCases.length; i++) {
            String result = solution.predictPartyVictory(testCases[i]);
            boolean passed = result.equals(expectedResults[i]);
            allTestsPassed &= passed;
            System.out.println("Test case " + (i + 1) + ": Input: \"" + testCases[i] + "\" Expected: \"" + expectedResults[i]
                    + "\" Output: \"" + result + "\" Result: " + (passed ? "Pass" : "Fail"));
        }

        // Summary of test results
        if (allTestsPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    /**
     * Predicts which party will win in the Dota2 senate.
     *
     * @param senate A string representing the sequence of senators.
     * @return The name of the winning party: "Radiant" or "Dire".
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiantQueue = new LinkedList<>();
        Queue<Integer> direQueue = new LinkedList<>();

        // Initialize queues with the indices of the senators
        for (int i = 0; i < n; i++) {
            char c = senate.charAt(i);
            if (c == 'R') {
                radiantQueue.offer(i);
            } else if (c == 'D') {
                direQueue.offer(i);
            }
        }

        // Simulate the rounds
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            int radiantIndex = radiantQueue.poll();
            int direIndex = direQueue.poll();

            // The senator with the lower index bans the other
            if (radiantIndex < direIndex) {
                // Radiant senator bans Dire senator
                radiantQueue.offer(radiantIndex + n);
            } else {
                // Dire senator bans Radiant senator
                direQueue.offer(direIndex + n);
            }
        }

        // Determine the winning party
        return radiantQueue.isEmpty() ? "Dire" : "Radiant";
    }
}

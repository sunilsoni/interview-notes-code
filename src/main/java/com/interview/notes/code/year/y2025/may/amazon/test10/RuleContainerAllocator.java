package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.util.*;

public class RuleContainerAllocator {
    // Global best count of rules placed
    private int bestCount = 0;
    // Number of containers and their capacity
    private final int containerCount, capacity;
    // Sorted list of rules
    private final List<String> rules;

    public RuleContainerAllocator(List<String> rules, int capacity, int containerCount) {
        // Sort rules ascending by length for better pruning
        this.rules = new ArrayList<>(rules);
        this.rules.sort(Comparator.comparingInt(String::length));
        this.capacity = capacity;
        this.containerCount = containerCount;
    }

    /**
     * Entry point: returns the maximum number of rules that can be placed.
     */
    public int maximize() {
        // usedChars[k] = characters used so far in container k
        int[] usedChars = new int[containerCount];
        // Start recursive search at rule index 0 with 0 placed
        backtrack(0, 0, usedChars);
        return bestCount;
    }

    /**
     * Recursive backtracking:
     * @param idx    current rule index
     * @param placed number of rules placed so far
     * @param used   usedChars per container
     */
    private void backtrack(int idx, int placed, int[] used) {
        // If we've considered all rules, update bestCount
        if (idx == rules.size()) {
            bestCount = Math.max(bestCount, placed);
            return;
        }
        // Prune: even if we place all remaining rules, cannot beat bestCount
        int remaining = rules.size() - idx;
        if (placed + remaining <= bestCount) {
            return;
        }
        String rule = rules.get(idx);
        int len = rule.length();
        // Try placing this rule in each container
        for (int c = 0; c < containerCount; c++) {
            int extra = (used[c] == 0 ? len : len + 1);
            if (used[c] + extra <= capacity) {
                // Place rule in container c
                used[c] += extra;
                backtrack(idx + 1, placed + 1, used);
                // Backtrack: remove rule
                used[c] -= extra;
            }
        }
        // Also try *not* placing this rule
        backtrack(idx + 1, placed, used);
    }

    /** Simple main method for testing PASS/FAIL of test cases. */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> tests = Arrays.asList(
            new TestCase(
                Arrays.asList("EnableGuardDuty","UseIAMRole","LogS3Events","AccessBilling","DenyEC2Start","EnableMFA"),
                32, 5, 6
            ),
            new TestCase(
                Arrays.asList("aaa","bbbb","cc","ddddd"),
                10, 2, 4
            ),
            new TestCase(
                Arrays.asList("ruleOneLongerThanCap","small"),
                5, 1, 0
            )
        );

        // Run each test
        for (TestCase tc : tests) {
            RuleContainerAllocator alloc =
                new RuleContainerAllocator(tc.rules, tc.capacity, tc.containers);
            int result = alloc.maximize();
            String status = (result == tc.expected) ? "PASS" : "FAIL";
            System.out.printf("%s: got %d, expected %d%n", status, result, tc.expected);
        }
    }

    /** Helper class for tests */
    static class TestCase {
        List<String> rules;
        int capacity, containers, expected;
        TestCase(List<String> rules, int capacity, int containers, int expected) {
            this.rules = rules;
            this.capacity = capacity;
            this.containers = containers;
            this.expected = expected;
        }
    }
}
package com.interview.notes.code.year.y2026.feb.Linkedin.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// The interface exactly as provided in the problem description
interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

public class ReverseDepthSum {

    // Main solution method
    public static int reverseDepthSum(List<NestedInteger> input) {
        int unweightedSum = 0; // Tracks the running sum of all raw integers found so far
        int weightedSum = 0;   // Tracks the final accumulated result
        
        while (!input.isEmpty()) { // Keep looping level by level until no elements are left
            
            // 1. Find all plain integers at the current level, sum them, and add to our running total
            unweightedSum += input.stream().filter(NestedInteger::isInteger).mapToInt(NestedInteger::getInteger).sum();
            
            // 2. Add the running total to the final sum (this naturally applies the reverse depth multiplier as we go deeper)
            weightedSum += unweightedSum;
            
            // 3. Find all nested lists at the current level, extract their contents, and make them the new input for the next loop iteration
            input = input.stream().filter(ni -> !ni.isInteger()).flatMap(ni -> ni.getList().stream()).toList();
        }
        
        return weightedSum; // Return the final calculated weighted sum
    }

    // ============================================================================
    // TESTING SECTION (No JUnit, just a simple main method)
    // ============================================================================

    public static void main(String[] args) {
        System.out.println("Running test cases...\n");

        // Test Case 1: {{1,1},2,{1,1}} -> Expected: 8
        List<NestedInteger> test1 = Arrays.asList(
            new SimpleNI(Arrays.asList(new SimpleNI(1), new SimpleNI(1))),
            new SimpleNI(2),
            new SimpleNI(Arrays.asList(new SimpleNI(1), new SimpleNI(1)))
        );
        runTest("Test Case 1", test1, 8);

        // Test Case 2: {1,{4,{6,2}}} -> Expected: 19
        List<NestedInteger> test2 = Arrays.asList(
            new SimpleNI(1),
            new SimpleNI(Arrays.asList(
                new SimpleNI(4),
                new SimpleNI(Arrays.asList(new SimpleNI(6), new SimpleNI(2)))
            ))
        );
        runTest("Test Case 2", test2, 19);

        // Test Case 3: Empty list {} -> Expected: 0
        List<NestedInteger> test3 = new ArrayList<>();
        runTest("Test Case 3 (Empty List)", test3, 0);

        // Test Case 4: Large Data / Deep Nesting {{{...{1}...}}} (100 levels deep)
        NestedInteger deepNested = new SimpleNI(1);
        for (int i = 0; i < 99; i++) {
            deepNested = new SimpleNI(List.of(deepNested));
        }
        List<NestedInteger> test4 = List.of(deepNested);
        // It's a single '1' at depth 100. So its reverse depth is 1. Result should be 1.
        runTest("Test Case 4 (100 levels deep)", test4, 1);
    }

    // Helper method to format test output cleanly
    private static void runTest(String testName, List<NestedInteger> input, int expected) {
        int result = reverseDepthSum(input); // Execute the algorithm
        boolean passed = (result == expected); // Check if output matches expected
        System.out.printf("%s: %s (Expected: %d, Got: %d)%n", 
                testName, passed ? "PASS" : "FAIL", expected, result); // Print formatted result
    }
}

// ============================================================================
// UTILITY CLASS: Simple implementation of NestedInteger just to build test data
// ============================================================================
class SimpleNI implements NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    // Constructor for a single integer
    public SimpleNI(Integer value) {
        this.value = value;
    }

    // Constructor for a nested list
    public SimpleNI(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return value != null; // If value is not null, it's a single integer
    }

    @Override
    public Integer getInteger() {
        return value; // Returns the integer value (or null if it's a list)
    }

    @Override
    public List<NestedInteger> getList() {
        return list; // Returns the nested list (or null if it's an integer)
    }
}
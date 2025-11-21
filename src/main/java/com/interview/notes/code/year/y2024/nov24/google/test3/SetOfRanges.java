package com.interview.notes.code.year.y2024.nov24.google.test3;

import java.util.ArrayList;
import java.util.List;

/*
Problem Analysis:

We need to implement a SetOfRanges class that supports adding ranges and querying numbers efficiently. The key requirements are:

addRange(start, end): Add a range [start, end] to the set, merging overlapping or adjacent ranges.
query(number): Check if a number exists within any of the stored ranges.
Handle large datasets and ensure efficient performance.
 */
public class SetOfRanges {
    private final List<Range> ranges;

    public SetOfRanges() {
        ranges = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Test case runner
        testBasicFunctionality();
        testEdgeCases();
        testLargeDataSet();
        testOverlappingRanges();
    }

    private static void testBasicFunctionality() {
        System.out.println("Running basic functionality tests...");
        SetOfRanges set = new SetOfRanges();

        set.addRange(1, 4);

        // Test cases
        boolean test1 = set.query(3);
        boolean test2 = !set.query(5);
        boolean test3 = set.query(1);
        boolean test4 = set.query(4);

        System.out.println("Test 1 (query 3): " + (test1 ? "PASS" : "FAIL"));
        System.out.println("Test 2 (query 5): " + (test2 ? "PASS" : "FAIL"));
        System.out.println("Test 3 (query 1): " + (test3 ? "PASS" : "FAIL"));
        System.out.println("Test 4 (query 4): " + (test4 ? "PASS" : "FAIL"));
    }

    private static void testEdgeCases() {
        System.out.println("\nRunning edge case tests...");
        SetOfRanges set = new SetOfRanges();

        set.addRange(Integer.MIN_VALUE, 0);
        set.addRange(0, Integer.MAX_VALUE);

        boolean test1 = set.query(Integer.MIN_VALUE);
        boolean test2 = set.query(Integer.MAX_VALUE);
        boolean test3 = set.query(0);

        System.out.println("Edge Test 1 (MIN_VALUE): " + (test1 ? "PASS" : "FAIL"));
        System.out.println("Edge Test 2 (MAX_VALUE): " + (test2 ? "PASS" : "FAIL"));
        System.out.println("Edge Test 3 (0): " + (test3 ? "PASS" : "FAIL"));
    }

    private static void testLargeDataSet() {
        System.out.println("\nRunning large dataset tests...");
        SetOfRanges set = new SetOfRanges();

        // Add 10000 ranges
        for (int i = 0; i < 10000; i++) {
            set.addRange(i * 2, i * 2 + 1);
        }

        long startTime = System.currentTimeMillis();
        boolean test1 = !set.query(15000);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Dataset Test: " + (test1 ? "PASS" : "FAIL"));
        System.out.println("Query execution time: " + (endTime - startTime) + "ms");
    }

    private static void testOverlappingRanges() {
        System.out.println("\nRunning overlapping ranges tests...");
        SetOfRanges set = new SetOfRanges();

        set.addRange(1, 5);
        set.addRange(3, 7);
        set.addRange(6, 10);

        boolean test1 = set.query(4);
        boolean test2 = set.query(6);
        boolean test3 = !set.query(11);

        System.out.println("Overlap Test 1 (query 4): " + (test1 ? "PASS" : "FAIL"));
        System.out.println("Overlap Test 2 (query 6): " + (test2 ? "PASS" : "FAIL"));
        System.out.println("Overlap Test 3 (query 11): " + (test3 ? "PASS" : "FAIL"));
    }

    public void addRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot be greater than end");
        }
        ranges.add(new Range(start, end));
    }

    public boolean query(int number) {
        for (Range range : ranges) {
            if (number >= range.start && number <= range.end) {
                return true;
            }
        }
        return false;
    }

    private static class Range {
        int start;
        int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
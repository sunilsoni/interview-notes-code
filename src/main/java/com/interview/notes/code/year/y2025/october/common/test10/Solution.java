package com.interview.notes.code.year.y2025.october.common.test10;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Solution class contains the method and test harness
public class Solution {

    // main method runs test cases and prints PASS/FAIL, plus large-data tests and timings
    public static void main(String[] args) {         // program entry point
        // Example tests from the problem statement (expected results provided)
        Integer[] ex1 = new Integer[]{3, 4, 5};     // test 1 input array
        Integer[] ex2 = new Integer[]{5, 1, 3, 2, 4}; // test 2 input array
        Integer[] ex3 = new Integer[]{3, 4, 6};     // test 3 input array
        Integer[] ex4 = new Integer[]{5, 3, 7, 5, 6, 11}; // test 4 input array
        Integer[] ex5 = new Integer[]{1, 6, 10, 9, 7, 8}; // test 5 input array

        // run each test and print PASS/FAIL
        runTest(Arrays.asList(ex1), true, "Example 1 [3,4,5]");   // expected true
        runTest(Arrays.asList(ex2), true, "Example 2 [5,1,3,2,4]"); // expected true
        runTest(Arrays.asList(ex3), false, "Example 3 [3,4,6]");  // expected false
        runTest(Arrays.asList(ex4), false, "Example 4 [5,3,7,5,6,11]"); // expected false
        runTest(Arrays.asList(ex5), true, "Example 5 [1,6,10,9,7,8]"); // expected true

        // additional edge case tests
        runTest(Collections.emptyList(), false, "Edge: empty");   // empty -> false
        runTest(List.of(3), false, "Edge: single element"); // single -> false
        runTest(Arrays.asList(3, 4), false, "Edge: two elements"); // two -> false
        runTest(Arrays.asList(0, 3, 4, 5), true, "Edge: contains zero (ignored) & triple present"); // zero ignored, triple present
        runTest(Arrays.asList(null, 3, 4, 5), true, "Edge: null in collection (ignored)");

        // test with duplicate values where dedup should not break correctness
        runTest(Arrays.asList(3, 3, 4, 4, 5, 5), true, "Duplicates: [3,3,4,4,5,5]");

        // Large-data test 1: create 2000 random numbers WITHOUT a guaranteed triple (we'll try to avoid common triples)
        Random rnd = new Random(42);                 // deterministic random for reproducible test
        List<Integer> bigNoTriple = IntStream.range(0, 2000)
                .mapToObj(i -> 1001 + rnd.nextInt(900)) // produce numbers roughly 1001..1900 to reduce chance of small triples
                .collect(Collectors.toList());    // collect to list
        long t0 = System.currentTimeMillis();       // start timing
        boolean resultBigNo = containsPythagoras(bigNoTriple); // run method
        long t1 = System.currentTimeMillis();       // end timing
        System.out.printf("Large test (no forced triple) -> result=%b | time=%d ms\n", resultBigNo, (t1 - t0));

        // Large-data test 2: create 2000 numbers and inject a known triple (3,4,5)
        List<Integer> bigWithTriple = new ArrayList<>(bigNoTriple); // reuse list
        bigWithTriple.set(0, 3);                      // ensure 3 is present
        bigWithTriple.set(1, 4);                      // ensure 4 is present
        bigWithTriple.set(2, 5);                      // ensure 5 is present
        t0 = System.currentTimeMillis();              // start timing
        boolean resultBigYes = containsPythagoras(bigWithTriple); // run method
        t1 = System.currentTimeMillis();              // end timing
        System.out.printf("Large test (forced 3,4,5) -> result=%b | time=%d ms\n", resultBigYes, (t1 - t0));
    }

    // helper to run a single test and print PASS/FAIL compared to expected boolean
    private static void runTest(Collection<Integer> input, boolean expected, String testName) {
        boolean actual = containsPythagoras(input);  // compute actual result
        String status = (actual == expected) ? "PASS" : "FAIL"; // compare with expected
        System.out.printf("%s: expected=%b actual=%b -> %s\n", testName, expected, actual, status); // print result
    }

    /**
     * Return true if the collection contains at least one Pythagorean triple.
     * Uses Java 8 Streams for preprocessing and a HashSet for fast lookups.
     */
    static boolean containsPythagoras(final Collection<Integer> numbers) { // method signature
        if (numbers == null) {                       // handle null input defensively
            return false;                            // null collection -> nothing to check -> false
        }

        // Convert collection to a deduplicated int[] of positive values using streams
        int[] values = numbers.stream()               // obtain a stream of Integer elements
                .filter(Objects::nonNull)             // remove nulls
                .mapToInt(Integer::intValue)          // convert Integer to int
                .filter(i -> i > 0)                   // keep only positive values (>0)
                .distinct()                           // remove duplicates (distinct values enough for existence checks)
                .toArray();                           // collect into an int array

        if (values.length < 3) {                      // if fewer than 3 distinct positive numbers
            return false;                             // cannot form a triple -> false
        }

        // compute squares of each value and collect them both in an array and in a set for O(1) lookup
        int[] squares = Arrays.stream(values)         // stream over int[] values
                .map(x -> x * x)                      // map value to its square
                .toArray();                           // collect squares into an int array

        Set<Integer> squareSet = Arrays.stream(squares) // stream squares
                .boxed()                              // box int to Integer to collect into a Set
                .collect(Collectors.toSet());         // collect into HashSet for O(1) membership tests

        int n = squares.length;                       // number of distinct squared values

        // Check every pair (i < j) to see if square[i] + square[j] exists in the set
        for (int i = 0; i < n - 1; i++) {             // outer loop over first element index
            for (int j = i + 1; j < n; j++) {         // inner loop over second element index (j > i)
                int sum = squares[i] + squares[j];    // compute sum of two squares
                if (squareSet.contains(sum)) {        // check if any squared value equals that sum
                    return true;                      // found a^2 + b^2 = c^2 -> return true
                }
            }
        }

        return false;                                 // no pair matched -> return false
    }
}

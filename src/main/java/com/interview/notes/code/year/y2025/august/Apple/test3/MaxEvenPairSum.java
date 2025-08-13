package com.interview.notes.code.year.y2025.august.Apple.test3;

import java.util.*; // Import utilities used for testing, arrays, lists, streams (in alt method)

/**
 * Problem: Find the maximum even sum made from two different elements of the array.
 * Approach: Track the two largest evens and two largest odds in one pass; return the max valid even sum.
 * Parity: Determined with modulo (x % 2 == 0 for even), as requested (no bit operations).
 */
public class MaxEvenPairSum {

    /**
     * Core method: Single-pass O(n) and O(1) extra space, uses modulo to check parity.
     */
    public static int maxEvenPairSum(int[] nums) {
        // Early exit: if array is null or has fewer than two elements, we cannot form any pair
        if (nums == null || nums.length < 2) return -1;

        // Initialize "top two" trackers for evens: e1 is largest even, e2 is second largest even
        int e1 = Integer.MIN_VALUE; // start with smallest possible int so any real even can replace it
        int e2 = Integer.MIN_VALUE; // same for second largest even

        // Initialize "top two" trackers for odds: o1 is largest odd, o2 is second largest odd
        int o1 = Integer.MIN_VALUE; // largest odd placeholder
        int o2 = Integer.MIN_VALUE; // second largest odd placeholder

        // Counters to know if we actually saw at least two elements of each parity (different indices)
        int evenCount = 0; // how many even elements encountered (not distinct values; counts elements)
        int oddCount  = 0; // how many odd  elements encountered

        // Single scan over the array to maintain top-two evens and top-two odds
        for (int x : nums) {                     // iterate element by element
            if (x % 2 == 0) {                    // check evenness with modulo (no bit operations)
                evenCount++;                     // we found an even element, increment even counter

                // Maintain top two evens in descending order: after this block e1 >= e2
                if (x >= e1) {                   // if current even is at least the largest seen so far
                    e2 = e1;                     // demote previous largest to second largest
                    e1 = x;                      // set current as the new largest even
                } else if (x > e2) {             // otherwise, maybe it's better than the second largest
                    e2 = x;                      // update second largest even
                }

            } else {                             // if not even, it’s odd
                oddCount++;                      // increment odd counter

                // Maintain top two odds in descending order: after this block o1 >= o2
                if (x >= o1) {                   // if current odd is at least the largest odd so far
                    o2 = o1;                     // demote previous largest to second largest
                    o1 = x;                      // set current as new largest odd
                } else if (x > o2) {             // otherwise, maybe it’s better than the second largest odd
                    o2 = x;                      // update second largest odd
                }
            }
        }

        // Prepare candidates; use long for the sum to avoid overflow when adding two large ints
        Long bestEvenSum = null;                 // candidate from even+even if we have >= 2 evens
        Long bestOddSum  = null;                 // candidate from odd+odd  if we have >= 2 odds

        if (evenCount >= 2) {                    // valid even pair exists among evens
            bestEvenSum = (long) e1 + (long) e2; // compute sum in long to be safe
        }
        if (oddCount >= 2) {                     // valid even pair exists among odds
            bestOddSum = (long) o1 + (long) o2;  // compute sum in long to be safe
        }

        // Pick the maximum available candidate, carefully handling null (absence)
        Long ans = null;                         // will hold the chosen best sum (if any)
        if (bestEvenSum != null) ans = bestEvenSum;             // seed with even candidate if present
        if (bestOddSum  != null) {                              // compare odd candidate if present
            if (ans == null || bestOddSum > ans) ans = bestOddSum; // choose the larger of the two
        }

        // If no candidate existed, return -1 as per problem statement
        if (ans == null) return -1;

        // Clamp to int bounds in the unlikely event of overflow beyond int range
        if (ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;   // clamp downwards if too large
        if (ans < Integer.MIN_VALUE) return Integer.MIN_VALUE;   // clamp upwards if too small
        return ans.intValue();                                   // return final int answer
    }

    /**
     * Alternate Java 8 Streams solution (clear but O(n log n) due to sorting).
     * Still uses modulo for parity, no bit operations.
     */
    public static int maxEvenPairSumStreamSlower(int[] nums) {
        // If fewer than two elements, no pair can be formed
        if (nums == null || nums.length < 2) return -1;

        // Compute the sum of the top two evens via a descending sort and limit(2)
        OptionalInt evenSumOpt =
            Arrays.stream(nums)                                   // stream the array
                  .filter(x -> x % 2 == 0)                        // keep evens with modulo parity
                  .boxed()                                        // box to Integer to allow Comparator
                  .sorted(Comparator.reverseOrder())              // sort descending
                  .mapToInt(Integer::intValue)                    // back to primitive
                  .limit(2)                                       // take top two
                  .reduce((a, b) -> a + b);                       // sum if both exist

        // Compute the sum of the top two odds similarly
        OptionalInt oddSumOpt =
            Arrays.stream(nums)
                  .filter(x -> x % 2 != 0)                        // keep odds (no bit ops)
                  .boxed()
                  .sorted(Comparator.reverseOrder())
                  .mapToInt(Integer::intValue)
                  .limit(2)
                  .reduce((a, b) -> a + b);

        // Decide based on presence of candidates
        boolean hasEven = evenSumOpt.isPresent();                 // true if we had >=2 evens
        boolean hasOdd  = oddSumOpt.isPresent();                  // true if we had >=2 odds

        if (!hasEven && !hasOdd) return -1;                       // no even sum possible
        if (hasEven && !hasOdd)  return evenSumOpt.getAsInt();    // only even+even available
        if (!hasEven && hasOdd)  return oddSumOpt.getAsInt();     // only odd+odd available
        return Math.max(evenSumOpt.getAsInt(), oddSumOpt.getAsInt()); // both exist: choose max
    }

    /**
     * Simple holder for test case input and expected output.
     */
    private static class TestCase {
        final int[] input;     // the test array
        final int expected;    // expected answer for this test
        TestCase(int[] input, int expected) {
            this.input = input;       // set the input field
            this.expected = expected; // set the expected field
        }
    }

    /**
     * Main method: runs functional tests and a large-data performance sanity test (no JUnit).
     */
    public static void main(String[] args) {
        // Build a list of functional tests, covering prompt samples and edge cases
        List<TestCase> tests = Arrays.asList(
            new TestCase(new int[]{5, 1, 3, 4, 2}, 8),               // sample: best is 5+3=8 (odd+odd)
            new TestCase(new int[]{0, 1}, -1),                       // sample: cannot form even+even or odd+odd
            new TestCase(new int[]{2, 2}, 4),                        // duplicates allowed (two indices)
            new TestCase(new int[]{1, 1, 2, 2, 1000001}, 1000002),   // best odd+odd (1000001+1=1000002)
            new TestCase(new int[]{2}, -1),                          // single element: impossible
            new TestCase(new int[]{1}, -1),                          // single odd element: impossible
            new TestCase(new int[]{}, -1),                           // empty array
            new TestCase(new int[]{-2, -4, -3, -5}, -6),             // best even+even (-2 + -4)
            new TestCase(new int[]{0, 0}, 0),                        // zeros form 0 (even+even)
            new TestCase(new int[]{7, 9, 11}, 20),                   // best odd+odd (11 + 9)
            new TestCase(new int[]{8, 6, 4}, 14)                     // best even+even (8 + 6)
        );

        // Run the optimal O(n) method across all tests and report PASS/FAIL
        System.out.println("== Functional Tests (Optimal O(n) method; modulo for parity) ==");
        int pass = 0;                                        // count passed tests
        int fail = 0;                                        // count failed tests
        for (TestCase t : tests) {                           // iterate each test case
            int got = maxEvenPairSum(t.input);               // compute result using optimal method
            boolean ok = (got == t.expected);                // compare to expected
            System.out.printf("Input=%s | Expected=%d | Got=%d | %s%n",
                    Arrays.toString(t.input), t.expected, got, ok ? "PASS" : "FAIL"); // print result
            if (ok) pass++; else fail++;                     // update pass/fail counters
        }
        System.out.printf("Summary: PASS=%d, FAIL=%d%n", pass, fail); // print summary

        // Optional cross-check with the stream method on a few tests (sanity check)
        System.out.println("\n== Cross-check (Stream method; modulo for parity) ==");
        for (int i = 0; i < Math.min(5, tests.size()); i++) {        // check first few tests
            TestCase t = tests.get(i);                                // pick test case
            int a = maxEvenPairSum(t.input);                          // result from optimal method
            int b = maxEvenPairSumStreamSlower(t.input);              // result from stream method
            System.out.printf("Input=%s | Optimal=%d | Stream=%d | %s%n",
                    Arrays.toString(t.input), a, b, (a == b) ? "MATCH" : "MISMATCH"); // print comparison
        }

        // Large-data performance sanity test: generate a big random array and time the optimal method
        System.out.println("\n== Large Data Performance Test (may adjust N for your heap) ==");
        final int N = 1_000_000;                         // size of the large array (tune based on memory)
        int[] big = new int[N];                          // allocate array of size N
        Random rnd = new Random(42);                     // fixed seed for reproducibility
        for (int i = 0; i < N; i++) {                    // fill the array
            big[i] = rnd.nextInt(2_000_001) - 1_000_000; // values in [-1_000_000, 1_000_000]
        }
        long t1 = System.nanoTime();                     // start timing
        int bigAns = maxEvenPairSum(big);                // run optimal method on large data
        long t2 = System.nanoTime();                     // end timing
        System.out.printf("Large test: N=%d | Result=%d | Time=%.2f ms%n",
                N, bigAns, (t2 - t1) / 1e6);            // report size, result, and time taken
    }
}
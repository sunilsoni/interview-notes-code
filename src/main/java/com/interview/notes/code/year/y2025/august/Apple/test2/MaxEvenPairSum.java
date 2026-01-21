package com.interview.notes.code.year.y2025.august.Apple.test2;

import java.util.*;

public class MaxEvenPairSum {

    /**
     * Returns the maximum even sum achievable from two different elements.
     * Single-pass O(n), O(1) space.
     */
    public static int maxEvenPairSum(int[] nums) {
        // Handle null or too-small inputs early; need at least two elements for a pair
        if (nums == null || nums.length < 2) return -1;

        // Track top two evens: e1 >= e2 at all times
        int e1 = Integer.MIN_VALUE; // largest even seen so far
        int e2 = Integer.MIN_VALUE; // second largest even seen so far

        // Track top two odds: o1 >= o2 at all times
        int o1 = Integer.MIN_VALUE; // largest odd seen so far
        int o2 = Integer.MIN_VALUE; // second largest odd seen so far

        // Counts for parity to know if at least two values exist in each group
        int evenCount = 0; // how many evens encountered (distinct indices)
        int oddCount = 0; // how many odds encountered (distinct indices)

        // Single pass over the array to update our top-two trackers
        for (int x : nums) {
            // Check parity using bit operation: (x & 1) == 0 => even; faster than x % 2
            if ((x & 1) == 0) { // x is even
                evenCount++; // increase count of evens seen

                // Maintain top two evens in descending order
                if (x >= e1) {      // new value becomes the largest even
                    e2 = e1;        // demote previous largest to second largest
                    e1 = x;         // current value is now the largest even
                } else if (x > e2) { // otherwise, maybe it fits as second largest
                    e2 = x;         // update second largest even
                }

            } else { // x is odd
                oddCount++; // increase count of odds seen

                // Maintain top two odds in descending order
                if (x >= o1) {      // new value becomes the largest odd
                    o2 = o1;        // demote previous largest to second largest
                    o1 = x;         // current value is now the largest odd
                } else if (x > o2) { // otherwise, maybe it fits as second largest
                    o2 = x;         // update second largest odd
                }
            }
        }

        // Build candidate sums using long to avoid rare int overflow during addition
        Long bestEvenSum = null; // null indicates "no valid even pair"
        Long bestOddSum = null; // null indicates "no valid odd pair"

        // If at least two evens exist, we can form an even sum from e1 + e2
        if (evenCount >= 2) {
            bestEvenSum = (long) e1 + (long) e2; // compute safely in long
        }

        // If at least two odds exist, we can form an even sum from o1 + o2
        if (oddCount >= 2) {
            bestOddSum = (long) o1 + (long) o2; // compute safely in long
        }

        // Decide the final result by taking the maximum available candidate
        Long ansLong = null; // will hold the best even sum (as Long) if any

        // Compare candidates carefully, considering nulls
        if (bestEvenSum != null) ansLong = bestEvenSum;
        if (bestOddSum != null) {
            if (ansLong == null || bestOddSum > ansLong) ansLong = bestOddSum;
        }

        // If no candidate was built, return -1 as required
        if (ansLong == null) return -1;

        // If result fits into int range, return it; else, problem domain typically assumes safe int sums
        // We clamp behavior to int bounds to be explicit and safe.
        if (ansLong > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (ansLong < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return ansLong.intValue();
    }

    /**
     * Alternate Stream-based solution (simpler to read, but O(n log n) due to sorting).
     * Not used for performance-critical paths; provided for reference.
     */
    public static int maxEvenPairSumStreamSlower(int[] nums) {
        // Early exit: need at least two numbers to form any pair
        if (nums == null || nums.length < 2) return -1;

        // Using Streams: get top-2 evens in descending order via sort, then sum if present
        OptionalInt evenSumOpt =
                Arrays.stream(nums)                            // stream over the array
                        .filter(x -> (x & 1) == 0)               // keep evens
                        .boxed()                                 // box to Integer to allow reverse sorting
                        .sorted(Comparator.reverseOrder())       // sort descending
                        .mapToInt(Integer::intValue)             // back to primitive int
                        .limit(2)                                // keep only top two
                        .reduce((a, b) -> a + b);                // sum top two if both exist

        // Similarly for odds
        OptionalInt oddSumOpt =
                Arrays.stream(nums)
                        .filter(x -> (x & 1) != 0)               // keep odds
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .mapToInt(Integer::intValue)
                        .limit(2)
                        .reduce((a, b) -> a + b);

        // Pick the maximum among the available optional sums
        boolean hasEven = evenSumOpt.isPresent();
        boolean hasOdd = oddSumOpt.isPresent();

        if (!hasEven && !hasOdd) return -1;                // no valid even-sum pair at all
        if (hasEven && !hasOdd) return evenSumOpt.getAsInt();
        if (!hasEven && hasOdd) return oddSumOpt.getAsInt();
        return Math.max(evenSumOpt.getAsInt(), oddSumOpt.getAsInt());
    }

    // ---------- Testing Harness (no JUnit; simple main) ----------

    public static void main(String[] args) {
        // Define test cases: array with expected answer
        List<TestCase> tests = Arrays.asList(
                new TestCase(new int[]{5, 1, 3, 4, 2}, 8),      // from prompt: odd+odd (5+3)=8
                new TestCase(new int[]{0, 1}, -1),              // from prompt: no even+even or odd+odd
                new TestCase(new int[]{2, 2}, 4),               // even+even duplicate allowed
                new TestCase(new int[]{1, 1, 2, 2, 1000001}, 1000002), // best is odd+odd
                new TestCase(new int[]{2}, -1),                 // only one element
                new TestCase(new int[]{1}, -1),                 // only one odd
                new TestCase(new int[]{}, -1),                  // empty
                new TestCase(new int[]{-2, -4, -3, -5}, -6),    // best is even+even (-2 + -4)
                new TestCase(new int[]{0, 0}, 0),               // zeros: even+even
                new TestCase(new int[]{7, 9, 11}, 20),          // odd+odd (11+9)
                new TestCase(new int[]{8, 6, 4}, 14)            // even+even (8+6)
        );

        // Run functional tests on the optimal method
        System.out.println("== Functional Tests (Optimal O(n) method) ==");
        int pass = 0, fail = 0;
        for (TestCase t : tests) {
            int got = maxEvenPairSum(t.input);
            boolean ok = (got == t.expected);
            System.out.printf("Input=%s | Expected=%d | Got=%d | %s%n",
                    Arrays.toString(t.input), t.expected, got, ok ? "PASS" : "FAIL");
            if (ok) pass++;
            else fail++;
        }
        System.out.printf("Summary: PASS=%d, FAIL=%d%n", pass, fail);

        // Cross-check a few with the Stream-based method to validate consistency
        System.out.println("\n== Cross-check Selected Cases with Stream-based method ==");
        for (int i = 0; i < Math.min(5, tests.size()); i++) {
            TestCase t = tests.get(i);
            int gotOptimal = maxEvenPairSum(t.input);
            int gotStream = maxEvenPairSumStreamSlower(t.input);
            System.out.printf("Input=%s | Optimal=%d | Stream=%d | %s%n",
                    Arrays.toString(t.input), gotOptimal, gotStream,
                    (gotOptimal == gotStream) ? "MATCH" : "MISMATCH");
        }

        // Large data performance test: generate 5 million ints (mix of even/odd)
        // Adjust N smaller if memory/heap is tight in your environment.
        System.out.println("\n== Large Data Performance Test ==");
        final int N = 1_000_000; // 1 million; increase to 5_000_000 for heavier test
        int[] big = new int[N];
        Random rnd = new Random(42);
        for (int i = 0; i < N; i++) {
            // Random ints with moderate range; includes negatives, evens, odds
            big[i] = rnd.nextInt(2_000_001) - 1_000_000;
        }
        long t1 = System.nanoTime();
        int bigAns = maxEvenPairSum(big);
        long t2 = System.nanoTime();
        System.out.printf("Large test: N=%d | Result=%d | Time=%.2f ms%n",
                N, bigAns, (t2 - t1) / 1e6);
    }

    // Simple holder for test cases
    private record TestCase(int[] input, int expected) {
    }
}
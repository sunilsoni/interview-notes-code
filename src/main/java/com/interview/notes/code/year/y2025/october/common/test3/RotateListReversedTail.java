package com.interview.notes.code.year.y2025.october.common.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RotateListReversedTail {

    /**
     * Rotate a list by taking the last k elements, reversing them, and placing them
     * at the front, then append the remaining (first part) unchanged.
     *
     * Example: ["a","b","c","d","e","abc"], k=3 -> ["abc","e","d","a","b","c"]
     *
     * @param input original list (may be null)
     * @param k number of elements from tail to bring to front (can be > size or negative)
     * @return new List with transformation applied (null if input is null)
     */
    public static List<String> rotateByReversedTail(List<String> input, int k) {
        // If input is null preserve null (avoid NPE)
        if (input == null) return null;

        // Get size once for efficiency
        final int size = input.size();

        // If size is 0 or 1 or k normalized to zero, return a new copy (immutability of original)
        if (size == 0) return Collections.emptyList();

        // Normalize k to [0, size-1] so large or negative k values are handled
        k = ((k % size) + size) % size;

        // If effective rotation is 0 then return a shallow copy so caller cannot modify original list
        if (k == 0) return new ArrayList<>(input);

        // Extract the tail sublist (last k elements). Use a new ArrayList to isolate from original.
        List<String> tail = new ArrayList<>(input.subList(size - k, size));

        // Reverse the tail in-place to get the order required by the problem (reverse of last-k)
        Collections.reverse(tail);

        // Extract the head sublist (first size-k elements)
        List<String> head = new ArrayList<>(input.subList(0, size - k));

        // Build the result by concatenating tail then head. Use streams to follow Java8 style.
        List<String> result = Stream.concat(tail.stream(), head.stream())
                                    .collect(Collectors.toList());

        // Return the new transformed list
        return result;
    }

    // Main test harness (no JUnit). Runs small tests and a large-data sample test.
    public static void main(String[] args) {
        // Build test cases list
        List<TC> tests = new ArrayList<>();                         // container for tests

        // Provided example (this must PASS)
        tests.add(new TC(
                Arrays.asList("a","b","c","d","e","abc"),           // input
                3,                                                  // k
                Arrays.asList("abc","e","d","a","b","c")            // expected (as you gave)
        ));

        // k = 0 (no change) test
        tests.add(new TC(
                Arrays.asList("x","y","z"),
                0,
                Arrays.asList("x","y","z")
        ));

        // k larger than size (should normalize using modulo)
        tests.add(new TC(
                Arrays.asList("1","2","3","4"),
                6, // 6 % 4 = 2 -> last 2 reversed moved to front: ["4","3","1","2"]
                Arrays.asList("4","3","1","2")
        ));

        // Single element and negative k (negative handled by normalization)
        tests.add(new TC(
                List.of("solo"),
                -5, // equivalent to k = ((-5 % 1)+1)%1 = 0 -> same single element
                List.of("solo")
        ));

        // Empty list
        tests.add(new TC(
                Collections.emptyList(),
                3,
                Collections.emptyList()
        ));

        // Null list should preserve null (expected null)
        tests.add(new TC(
                null,
                2,
                null
        ));

        // Run small tests and print results
        int pass = 0, fail = 0;
        for (int i = 0; i < tests.size(); i++) {
            TC tc = tests.get(i);                               // fetch test case
            List<String> actual = rotateByReversedTail(tc.input, tc.k); // execute transformation
            if (Objects.equals(actual, tc.expected)) {          // compare expected vs actual (handles null)
                System.out.println("Test " + (i + 1) + " : PASS"); // success output
                pass++;
            } else {
                System.out.println("Test " + (i + 1) + " : FAIL"); // failure output
                System.out.println("  input   : " + tc.input);
                System.out.println("  k       : " + tc.k);
                System.out.println("  expected: " + tc.expected);
                System.out.println("  actual  : " + actual);
                fail++;
            }
        }

        // Large-input performance & correctness sample
        final int LARGE_N = 300_000;                  // count of strings to generate (adjust for machine)
        final int STRING_LEN = 8;                     // small strings for memory safety in test
        final int largeK = 123;                       // rotate amount for large test (will be normalized)

        // Generate deterministic data for large test to make correctness checks reproducible
        List<String> bigList = new ArrayList<>(LARGE_N);            // pre-size list for performance
        for (int i = 0; i < LARGE_N; i++) {                        // populate
            bigList.add("s" + String.format("%05d", i).substring(0, Math.min(STRING_LEN - 1, 5)) + i%10);
        }

        long start = System.currentTimeMillis();                    // start timing
        List<String> rotated = rotateByReversedTail(bigList, largeK);// run transformation
        long end = System.currentTimeMillis();                      // end timing

        // Quick sample checks (spot-check a few indices) rather than full scan to save time
        boolean largeOk = true;
        int n = bigList.size();
        int effectiveK = ((largeK % n) + n) % n;                    // how many tail elements were moved
        // Compute expected sample positions and compare
        for (int sample = 0; sample < 5; sample++) {
            // index in result to inspect
            int idx = sample;
            String actualVal = rotated.get(idx);                   // actual value from rotated result

            // determine expected value at position idx:
            // positions [0..effectiveK-1] come from reversed tail: original indices size-1 down to size-k
            String expectedVal;
            if (idx < effectiveK) {
                int origIndex = n - 1 - idx;                       // reversed tail mapping
                expectedVal = bigList.get(origIndex);
            } else {
                int origIndex = idx - effectiveK;                  // mapping into original head
                expectedVal = bigList.get(origIndex);
            }

            if (!Objects.equals(actualVal, expectedVal)) {
                largeOk = false;
                System.out.println("Large-sample mismatch at result index " + idx);
                System.out.println("  expected: " + expectedVal);
                System.out.println("  actual  : " + actualVal);
                break;
            }
        }

        // Summary prints
        System.out.println("Summary: Passed = " + pass + ", Failed = " + fail);
        System.out.println("Large input sample checks passed: " + largeOk + " (time ms = " + (end - start) + ")");
    }

    // Simple test-case holder
        private record TC(List<String> input, int k, List<String> expected) {
    }
}

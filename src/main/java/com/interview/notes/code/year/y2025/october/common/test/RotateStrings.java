package com.interview.notes.code.year.y2025.october.common.test;

import java.util.*;
import java.util.stream.Collectors;

public class RotateStrings {                      // main class container

    // Rotate a single string to the right by k positions (handles null/empty and negative k)
    public static String rotateRight(String s, int k) { // helper method signature
        if (s == null) return null;               // if input is null, preserve null (avoid NPE)
        int len = s.length();                     // compute length once for efficiency
        if (len == 0) return s;                   // empty string rotated is empty string
        // Normalize k to [0, len-1] even if k negative or large
        k = ((k % len) + len) % len;              // modular normalization to handle negative/large k
        if (k == 0) return s;                     // no rotation needed if effective k is 0
        // build rotated string by taking suffix(len-k) + prefix(len-k)
        return s.substring(len - k) + s.substring(0, len - k); // final rotated result
    }

    // Rotate every string in the input list by k positions using Java 8 Streams
    public static List<String> rotateList(List<String> input, int k) { // signature returns new List
        if (input == null) return null;           // if input list is null, preserve null
        return input.stream()                     // create stream from the list
                .map(s -> rotateRight(s, k))      // map each string to its rotated version
                .collect(Collectors.toList());   // collect results into a new List and return
    }

    // Simple test harness (no JUnit) that prints PASS/FAIL for each case and a large-data check
    public static void main(String[] args) {
        // --- Test cases setup ---
        List<TestCase> tests = new ArrayList<>(); // container for test cases

        // Basic examples
        tests.add(new TestCase(Arrays.asList("abcde", "hello", "xyz"), 3,
                Arrays.asList("cdeab", "llohe", "xyz"))); // expected right-rotations by 3

        // Edge cases: empty, single-char, n = 0
        tests.add(new TestCase(Arrays.asList("", "a", "ab"), 0,
                Arrays.asList("", "a", "ab"))); // rotation by 0

        // n > length test and mixed null
        tests.add(new TestCase(Arrays.asList("rotate", null, "hi"), 10,
                Arrays.asList("terota", null, "hi"))); // 10 mod len handled, null preserved

        // negative rotation (left rotation equivalent)
        tests.add(new TestCase(List.of("abcdef"), -2,
                List.of("cdefab"))); // rotate right by -2 => left by 2 => "cdefab"

        // empty list
        tests.add(new TestCase(Collections.emptyList(), 5, Collections.emptyList()));

        // null list
        tests.add(new TestCase(null, 3, null));

        // --- Run unit-like tests ---
        int pass = 0;                               // count passed tests
        int fail = 0;                               // count failed tests
        for (int i = 0; i < tests.size(); i++) {    // iterate test cases
            TestCase tc = tests.get(i);             // fetch test case
            List<String> actual = rotateList(tc.input, tc.k); // run code under test
            if (Objects.equals(actual, tc.expected)) { // compare expected vs actual (handles null)
                System.out.println("Test " + (i + 1) + ": PASS"); // print pass
                pass++;                             // increment pass count
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL"); // print fail
                System.out.println("  Input   : " + tc.input);
                System.out.println("  k       : " + tc.k);
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual  : " + actual);
                fail++;                             // increment fail count
            }
        }

        // --- Large input performance & correctness check ---
        final int LARGE_N = 200_000;                // number of strings to generate for large test
        final int STRING_LEN = 12;                  // length of each generated string
        int rotateBy = 3;                           // rotate by 3 for this large test

        List<String> largeList = new ArrayList<>(LARGE_N); // pre-size for performance
        for (int i = 0; i < LARGE_N; i++) {          // generate deterministic strings like s000000, s000001...
            largeList.add(generateFixedString(i, STRING_LEN)); // add string
        }

        // Measure time and run rotation
        long t0 = System.currentTimeMillis();        // start time
        List<String> rotatedLarge = rotateList(largeList, rotateBy); // perform rotation
        long t1 = System.currentTimeMillis();        // end time

        // Quick correctness checks (sampling) and ensure all rotated strings are correct
        boolean largeOk = true;                      // assume success until proven otherwise
        for (int i = 0; i < 10; i++) {               // sample first 10 elements for correctness
            String original = largeList.get(i);      // original
            String expected = rotateRight(original, rotateBy); // expected via helper
            if (!Objects.equals(expected, rotatedLarge.get(i))) { // compare
                largeOk = false;                     // mark failure
                System.out.println("Large test sample mismatch at index " + i);
                System.out.println("  original: " + original);
                System.out.println("  expected: " + expected);
                System.out.println("  actual  : " + rotatedLarge.get(i));
                break;
            }
        }

        // Print summary
        System.out.println("Summary: Passed = " + pass + ", Failed = " + fail);
        System.out.println("Large input test passed: " + largeOk + " (time ms = " + (t1 - t0) + ")");
    }

    // Small deterministic string generator used in large-data test to avoid randomness
    private static String generateFixedString(int idx, int len) {
        String base = Integer.toString(idx);        // convert index to digits
        // create a string of desired length by repeating the base and trimming/padding
        StringBuilder sb = new StringBuilder(len);  // builder of target length
        while (sb.length() < len) {                 // append until target length
            sb.append(base);
        }
        return sb.substring(0, len);                // trim to exact length
    }

    /**
     * @param input    input list (may be null)
     * @param k        rotation amount
     * @param expected expected output (may be null)
     */ // TestCase holder to keep input, k and expected output together
    private record TestCase(List<String> input, int k, List<String> expected) {
        // constructor
        // assign input
        // assign k
        // assign expected
    }
}

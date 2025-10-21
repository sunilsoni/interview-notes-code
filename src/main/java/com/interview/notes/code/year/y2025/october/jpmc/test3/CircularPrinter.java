package com.interview.notes.code.year.y2025.october.jpmc.test3;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CircularPrinter {

    // getTime computes the minimal total movement time using a simple fast loop
    public static long getTime(String s) {
        String u = s == null ? "" : s.toUpperCase(Locale.ROOT); // normalize to uppercase; null-safe
        long total = 0L;                                        // accumulator for total time
        char prev = 'A';                                        // pointer starts at 'A'
        for (int i = 0; i < u.length(); i++) {                  // visit each target letter once
            char cur = u.charAt(i);                             // current letter to print
            int diff = Math.abs(cur - prev);                    // straight (clockwise) distance
            total += Math.min(diff, 26 - diff);                 // add the shorter circular distance
            prev = cur;                                         // move pointer to current letter
        }
        return total;                                           // final minimal time
    }

    // Same logic using Java 8 Streams (for completeness and the requirement)
    public static long getTimeStream(String s) {
        String u = s == null ? "" : s.toUpperCase(Locale.ROOT); // normalize to uppercase; null-safe
        return IntStream.range(0, u.length())                   // indices 0..len-1
                .map(i -> {                                     // map each step to its minimal distance
                    char from = (i == 0) ? 'A' : u.charAt(i - 1); // previous character (or 'A' initially)
                    char to = u.charAt(i);                      // current target character
                    int diff = Math.abs(to - from);             // straight distance
                    return Math.min(diff, 26 - diff);           // minimal circular distance
                })
                .asLongStream()                                 // sum may exceed int for very long inputs
                .sum();                                         // total minimal time
    }

    // Helper to run and print a single test with expected answer
    private static void runTest(String name, String input, long expected) {
        long got = getTime(input);                               // compute with loop version (primary)
        boolean ok = (got == expected);                          // check against expected value
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL")
                + " | Input=\"" + input + "\" | Output=" + got + " | Expected=" + expected);
        // Cross-check the stream version for correctness parity
        long streamGot = getTimeStream(input);                   // same computation via streams
        if (streamGot != got) {                                  // sanity check: both methods must match
            System.out.println("  Mismatch between loop and stream: loop=" + got + ", stream=" + streamGot);
        }
    }

    // Large/random test generator and runner to check performance and correctness quickly
    private static void runLargeTests() {
        // deterministic large case: all 'A' -> cost 0
        char[] zeros = new char[100_000];                        // 100k characters
        Arrays.fill(zeros, 'A');                                 // all same letter
        String allA = new String(zeros);                         // build string
        long t0 = System.nanoTime();                             // start timing
        long resZero = getTime(allA);                            // should be 0
        long t1 = System.nanoTime();                             // end timing
        System.out.println("Large Test 1 (100k 'A'): " + (resZero == 0 ? "PASS" : "FAIL")
                + " | Result=" + resZero + " | TimeMs=" + ((t1 - t0) / 1_000_000));

        // random large case: 100k mixed uppercase letters
        char[] rnd = new char[100_000];                          // allocate buffer
        ThreadLocalRandom r = ThreadLocalRandom.current();       // fast RNG
        for (int i = 0; i < rnd.length; i++) {                   // fill with random A..Z
            rnd[i] = (char) ('A' + r.nextInt(26));
        }
        String big = new String(rnd);                            // build string
        long s0 = System.nanoTime();                             // start timing for loop
        long loopAns = getTime(big);                             // compute via loop
        long s1 = System.nanoTime();                             // stop timing for loop
        long s2 = System.nanoTime();                             // start timing for stream
        long streamAns = getTimeStream(big);                     // compute via stream
        long s3 = System.nanoTime();                             // stop timing for stream
        boolean same = loopAns == streamAns;                     // ensure both implementations agree
        System.out.println("Large Test 2 (100k random): " + (same ? "PASS" : "FAIL")
                + " | LoopMs=" + ((s1 - s0) / 1_000_000)
                + " | StreamMs=" + ((s3 - s2) / 1_000_000)
                + " | Result=" + loopAns);
    }

    public static void main(String[] args) {
        // Sample tests from the prompt
        runTest("Sample 0", "AZGB", 13);                         // given example -> 13
        runTest("Sample 1", "ZNMD", 23);                         // given example -> 23
        runTest("Sample 2", "BZA", 4);                           // given example -> 4

        // Additional sanity tests / edge-like cases
        runTest("Single letter", "A", 0);                        // already at 'A'
        runTest("Repeat same", "AAAAA", 0);                      // no movement
        runTest("Wrap both ways", "AZA", 2);                     // A->Z (1) + Z->A (1) = 2
        runTest("Mixed case", "aZgB", 13);                       // case-insensitive; same as AZGB

        // Large-data performance and parity tests
        runLargeTests();                                         // should finish very fast
    }
}

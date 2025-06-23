package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class RisingSequence {

    /**
     * Returns the total number of times you need to add B
     * to elements of ar so that ar becomes strictly increasing.
     */
    public static int solve(int B, List<Integer> ar) {
        // prev holds the last value in the rising sequence so far
        AtomicLong prev = new AtomicLong(ar.get(0));
        // count holds the total additions of B
        AtomicInteger count = new AtomicInteger(0);

        // For each element after the first, bump it just enough times
        ar.stream()
          .skip(1)
          .forEach(currentValue -> {
              long cur = currentValue;
              long needed = prev.get() + 1;  // must exceed prev
              if (cur < needed) {
                  long diff = needed - cur;
                  // how many B’s we need to add: ceil(diff / B)
                  long times = (diff + B - 1) / B;
                  count.addAndGet((int) times);
                  cur += times * B;
              }
              prev.set(cur);
          });

        return count.get();
    }

    /** Simple test harness in main—no JUnit, just prints PASS or FAIL. */
    public static void main(String[] args) {
        class Test {
            final int B;
            final List<Integer> ar;
            final int expected;
            Test(int B, List<Integer> ar, int expected) {
                this.B = B; this.ar = ar; this.expected = expected;
            }
        }

        List<Test> tests = Arrays.asList(
            // Provided examples
            new Test(2, Arrays.asList(1, 3, 3, 2), 3),
            new Test(1, Arrays.asList(1, 1),       1),
            // Already strictly increasing
            new Test(5, Arrays.asList(1, 2, 3, 4), 0),
            // Fully decreasing
            new Test(1, Arrays.asList(5, 4, 3),    6),
            // Large input: all zeros, N=2000, B=1 → sum of 1..1999 = 1_999_000
            new Test(1,
                     Collections.nCopies(2000, 0),
                     1_999_000),
            // Large input with big B: same pattern
            new Test(1_000_000,
                     Collections.nCopies(2000, 0),
                     1_999_000)
        );

        // Run and report
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = solve(t.B, t.ar);
            String status = (result == t.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %2d: expected=%d, got=%d → %s%n",
                              i+1, t.expected, result, status);
        }
    }
}
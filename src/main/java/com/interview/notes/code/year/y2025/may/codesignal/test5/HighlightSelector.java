package com.interview.notes.code.year.y2025.may.codesignal.test5;

import java.util.*;
import java.util.stream.*;

public class HighlightSelector {

    /**
     * ---------- Core logic ----------
     * A height is a highlight if it is higher than BOTH neighbours
     * (missing neighbours are treated as -∞, so edges only need
     *   to beat the single neighbour they have).
     *
     * We keep removing the smallest highlight and store the order.
     * ---------------------------------
     */
    public static int[] solution(int[] heights) {
        // Keep the data in a mutable list for easy removals
        List<Integer> list = Arrays.stream(heights)
                                   .boxed()
                                   .collect(Collectors.toCollection(ArrayList::new));

        List<Integer> order = new ArrayList<>();

        while (!list.isEmpty()) {

            // 1️⃣  Collect all current highlights
            List<Integer> highlights = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                int curr = list.get(i);

                boolean leftOK  = (i == 0)                 || curr > list.get(i - 1);
                boolean rightOK = (i == list.size() - 1)   || curr > list.get(i + 1);

                if (leftOK && rightOK) {
                    highlights.add(curr);
                }
            }

            // 2️⃣  Remove the smallest highlight
            int smallestHighlight = Collections.min(highlights);
            order.add(smallestHighlight);
            list.remove((Integer) smallestHighlight);   // remove by value, not index
        }

        // 3️⃣  Convert the order list back to int[]
        return order.stream().mapToInt(Integer::intValue).toArray();
    }

    /* -----------------------------------------------------------------------
       Simple helpers for testing
       -------------------------------------------------------------------- */
    private static boolean arraysEqual(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    private static String toStr(int[] arr) {
        return Arrays.toString(arr);
    }

    /* -----------------------------------------------------------------------
       Main method – runs a few demo tests and a large random test
       -------------------------------------------------------------------- */
    public static void main(String[] args) {

        class TestCase {
            final String name;
            final int[] input;
            final int[] expected;              // may be null for “large” test

            TestCase(String name, int[] input, int[] expected) {
                this.name = name;
                this.input = input;
                this.expected = expected;
            }
        }

        List<TestCase> tests = Arrays.asList(
                new TestCase("Example",
                        new int[]{2, 7, 8, 5, 1, 6, 3, 9, 4},
                        new int[]{6, 8, 7, 5, 2, 9, 4, 3, 1}),

                new TestCase("Ascending",
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{5, 4, 3, 2, 1}),

                new TestCase("Descending",
                        new int[]{5, 4, 3, 2, 1},
                        new int[]{5, 4, 3, 2, 1}),

                new TestCase("Single element",
                        new int[]{42},
                        new int[]{42}),

                new TestCase("Two elements",
                        new int[]{10, 99},
                        new int[]{99, 10})
        );

        /* ---------------- Run the small deterministic tests ---------------- */
        for (TestCase tc : tests) {
            int[] result = solution(tc.input);
            boolean pass  = arraysEqual(result, tc.expected);
            System.out.println(tc.name + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("   expected = " + toStr(tc.expected));
                System.out.println("   got      = " + toStr(result));
            }
        }

        /* ---------------- Run one larger random test ---------------- */
        // generate 1000 distinct random positives ≤ 1e9
        int size = 1000;
        Random rnd = new Random(2025);
        Set<Integer> set = new HashSet<>();
        while (set.size() < size)
            set.add(rnd.nextInt(1_000_000_000) + 1);

        int[] largeInput = set.stream().mapToInt(Integer::intValue).toArray();

        long t0 = System.nanoTime();
        int[] largeResult = solution(largeInput);
        long t1 = System.nanoTime();

        // basic sanity: same length, same elements (order can differ)
        Arrays.sort(largeInput);
        int[] sortedResult = largeResult.clone();
        Arrays.sort(sortedResult);

        boolean passLarge = arraysEqual(largeInput, sortedResult);

        System.out.printf("Large test (%d elements): %s  [%.2f ms]%n",
                size,
                passLarge ? "PASS" : "FAIL",
                (t1 - t0) / 1e6
        );
    }
}

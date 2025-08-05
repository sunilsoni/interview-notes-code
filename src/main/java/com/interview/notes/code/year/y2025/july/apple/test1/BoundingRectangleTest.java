package com.interview.notes.code.year.y2025.july.apple.test1;

import java.util.*;
import java.util.stream.*;

public class BoundingRectangleTest {

    /**
     * Given a list of points [x, y], returns:
     * [ minX, minY, width, height ]
     * where width = maxX - minX, height = maxY - minY.
     */
    public static List<Integer> boundingRectangle(List<List<Integer>> points) {
        // find min and max of x and y using streams
        int minX = points.stream().mapToInt(p -> p.get(0)).min().orElse(0);
        int maxX = points.stream().mapToInt(p -> p.get(0)).max().orElse(0);
        int minY = points.stream().mapToInt(p -> p.get(1)).min().orElse(0);
        int maxY = points.stream().mapToInt(p -> p.get(1)).max().orElse(0);

        int width  = maxX - minX;
        int height = maxY - minY;

        return Arrays.asList(minX, minY, width, height);
    }

    /** Simple holder for a test case. */
    static class TestCase {
        final String name;
        final List<List<Integer>> input;
        final List<Integer> expected;

        TestCase(String name, List<List<Integer>> input, List<Integer> expected) {
            this.name     = name;
            this.input    = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        // define some fixed test cases
        List<TestCase> tests = Arrays.asList(
            new TestCase("Example 1",
                Arrays.asList(
                    Arrays.asList(2, 39),
                    Arrays.asList(99, 39),
                    Arrays.asList(2, 130),
                    Arrays.asList(99, 130)
                ),
                Arrays.asList(2, 39, 97, 91)
            ),
            new TestCase("Two Points",
                Arrays.asList(
                    Arrays.asList(-5, 10),
                    Arrays.asList(5, -10)
                ),
                Arrays.asList(-5, -10, 10, 20)
            ),
            new TestCase("Vertical Line",
                Arrays.asList(
                    Arrays.asList(0, 0),
                    Arrays.asList(0, 5),
                    Arrays.asList(0, 2)
                ),
                Arrays.asList(0, 0, 0, 5)
            ),
            new TestCase("Horizontal Line",
                Arrays.asList(
                    Arrays.asList(3, 7),
                    Arrays.asList(10, 7),
                    Arrays.asList(6, 7)
                ),
                Arrays.asList(3, 7, 7, 0)
            )
        );

        // run fixed tests
        for (TestCase tc : tests) {
            List<Integer> actual = boundingRectangle(tc.input);
            if (actual.equals(tc.expected)) {
                System.out.println(tc.name + ": PASS");
            } else {
                System.out.println(tc.name + ": FAIL"
                    + " — expected=" + tc.expected
                    + ", actual=" + actual);
            }
        }

        // large random data performance test
        final int N = 1_000_000;
        Random rnd = new Random(42);
        List<List<Integer>> large = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            // generate points in range [0, 1_000_000)
            large.add(Arrays.asList(rnd.nextInt(1_000_000), rnd.nextInt(1_000_000)));
        }

        long start = System.nanoTime();
        List<Integer> result = boundingRectangle(large);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\nLarge‐data test (" + N + " points):");
        System.out.println("Result = " + result + "  processed in " + elapsedMs + " ms");
    }
}
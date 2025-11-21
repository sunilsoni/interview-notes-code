package com.interview.notes.code.year.y2024.nov24.CodeSignal.test2;

import java.util.*;

/*
WORKING



**Problem Description**
There are lamps placed on a coordinate line, where each lamp illuminates a certain area around it within a given radius. You are given the coordinates of these lamps on the line, as well as the effective radius of each lamp’s illumination.

The input consists of a two-dimensional array `lamps`, where:
- `lamps[i][0]` represents the coordinate of the \(i\)-th lamp.
- `lamps[i][1]` represents the effective radius of the \(i\)-th lamp.

This means that each lamp at position `lamps[i][0]` illuminates the range from `lamps[i][0] - lamps[i][1]` to `lamps[i][0] + lamps[i][1]`, inclusive.

Your task is to find the coordinate of the point that is illuminated by the highest number of lamps. If multiple points have the same highest illumination, return the smallest coordinate among them.

---

**Input/Output**
- **Execution time limit**: 3 seconds (Java)
- **Memory limit**: 1 GB

**Input**
- `lamps`: A two-dimensional integer array containing information about the lamps.
  - Each lamp is described by a two-element array with the coordinate and the effective radius.
  - Constraints:
    - \(1 \leq \text{lamps.length} \leq 10^5\)
    - `lamps[i].length = 2`
    - \(-10^9 \leq \text{lamps[i][0]} \leq 10^9\)
    - \(1 \leq \text{lamps[i][1]} \leq 10^5\)

**Output**
- An integer representing the coordinate of the point with the smallest coordinate that is illuminated by the most number of lamps.

---

**Examples**
1. **Input**: `lamps = [[-2, 3], [2, 3], [2, 1]]`
   **Output**: `1`
   - Explanation:
     - The first lamp illuminates the range `[-5, 1]`.
     - The second lamp illuminates the range `[-1, 5]`.
     - The third lamp illuminates the range `[1, 3]`.
     - The point `1` is illuminated by all three lamps, so the answer is `1`.

2. **Input**: `lamps = [[-2, 1], [2, 1]]`
   **Output**: `-3`
   - Explanation:
     - The lamps illuminate the ranges `[-3, -1]` and `[1, 3]`.
     - All points are illuminated by only one lamp, but the smallest coordinate among these points is `-3`.

3. **Input**: `lamps = [[-2, 3], [2, 3], [2, 1]]`
   - Visual representation:
     - The lamps illuminate overlapping regions on a coordinate line, with the highest illuminated point shown as `1`.

---

The goal is to identify the point with the maximum lamp coverage, and in case of ties, return the point with the smallest coordinate.

 */
public class LampIlluminationSolver {
    public static int solution(int[][] lamps) {
        // Create events for each lamp's range start and end
        List<Event> events = new ArrayList<>();
        for (int[] lamp : lamps) {
            long pos = lamp[0];  // Use long to prevent overflow
            long radius = lamp[1];

            // Ensure we don't exceed Integer.MIN_VALUE/MAX_VALUE
            long startPos = Math.max(Integer.MIN_VALUE, pos - radius);
            long endPos = Math.min(Integer.MAX_VALUE, pos + radius);

            events.add(new Event((int) startPos, 1));  // Start of illumination
            events.add(new Event((int) endPos + 1, -1)); // End of illumination
        }

        // Sort events by position and type
        Collections.sort(events);

        int maxCount = 0;
        int currentCount = 0;
        int result = Integer.MAX_VALUE;  // Initialize with maximum value

        // Process events to find maximum overlapping point
        for (Event event : events) {
            currentCount += event.type;

            // Update result when we find a higher count or same count with smaller position
            if (currentCount > maxCount ||
                    (currentCount == maxCount && event.position < result)) {
                maxCount = currentCount;
                result = event.position;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        class TestCase {
            final int[][] lamps;
            final int expected;
            final String description;

            TestCase(int[][] lamps, int expected, String description) {
                this.lamps = lamps;
                this.expected = expected;
                this.description = description;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        // Example test cases
        testCases.add(new TestCase(
                new int[][]{{-2, 3}, {2, 3}, {2, 1}},
                1,
                "Basic test case with three overlapping lamps"
        ));

        testCases.add(new TestCase(
                new int[][]{{-2, 1}, {2, 1}},
                -3,
                "Two non-overlapping lamps"
        ));

        testCases.add(new TestCase(
                new int[][]{{0, 1}},
                -1,
                "Single lamp"
        ));

        // Maximum range test
        testCases.add(new TestCase(
                new int[][]{{-1000000000, 100000}, {1000000000, 100000}},
                -1000000100,
                "Maximum range test"
        ));

        // Additional edge cases
        testCases.add(new TestCase(
                new int[][]{{Integer.MAX_VALUE, 100000}, {Integer.MIN_VALUE, 100000}},
                Integer.MIN_VALUE,
                "Extreme values test"
        ));

        // Generate large test case
        int[][] largeLamps = new int[100000][2];
        Random rand = new Random(42);
        for (int i = 0; i < 100000; i++) {
            largeLamps[i][0] = rand.nextInt(2000000001) - 1000000000;
            largeLamps[i][1] = rand.nextInt(100000) + 1;
        }
        testCases.add(new TestCase(
                largeLamps,
                -1,
                "Large input test (100,000 lamps)"
        ));

        // Run tests
        int passedTests = 0;
        int totalTests = testCases.size();

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases.get(i);
            System.out.printf("\nTest Case %d: %s\n", i + 1, tc.description);

            try {
                long startTime = System.currentTimeMillis();
                int result = solution(tc.lamps);
                long endTime = System.currentTimeMillis();

                // For large input test, only verify execution time
                if (tc.lamps.length > 10000) {
                    System.out.printf("Execution time: %d ms\n", endTime - startTime);
                    System.out.println("Result: " + result);
                    if (endTime - startTime <= 3000) {
                        System.out.println("PASS (Performance test)");
                        passedTests++;
                    } else {
                        System.out.println("FAIL (Performance test - too slow)");
                    }
                    continue;
                }

                boolean passed = result == tc.expected;
                System.out.println("Input (first few lamps): " +
                        Arrays.deepToString(Arrays.copyOf(tc.lamps, Math.min(3, tc.lamps.length))));
                System.out.printf("Expected: %d, Got: %d\n", tc.expected, result);
                System.out.println(passed ? "PASS" : "FAIL");

                if (passed) passedTests++;

            } catch (Exception e) {
                System.out.println("FAIL - Exception occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Print summary
        System.out.println("\nTest Summary:");
        System.out.printf("Passed: %d/%d tests\n", passedTests, totalTests);
        System.out.printf("Success Rate: %.2f%%\n", (passedTests * 100.0) / totalTests);
    }

    static class Event implements Comparable<Event> {
        int position;
        int type; // 1 for start, -1 for end

        Event(int position, int type) {
            this.position = position;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.position != other.position) {
                return Integer.compare(this.position, other.position);
            }
            // For same position, process starts before ends
            return -Integer.compare(this.type, other.type);
        }
    }
}
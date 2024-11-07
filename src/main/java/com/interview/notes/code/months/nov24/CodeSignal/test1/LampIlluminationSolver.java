package com.interview.notes.code.months.nov24.CodeSignal.test1;

import java.util.*;

public class LampIlluminationSolver {
    public static int solution(int[][] lamps) {
        // Create events for each lamp's range start and end
        List<Event> events = new ArrayList<>();
        for (int[] lamp : lamps) {
            int pos = lamp[0];
            int radius = lamp[1];
            events.add(new Event(pos - radius, 1));  // Start of illumination
            events.add(new Event(pos + radius + 1, -1)); // End of illumination
        }

        // Sort events by position and type
        Collections.sort(events);

        int maxCount = 0;
        int currentCount = 0;
        int result = Integer.MIN_VALUE;

        // Process events to find maximum overlapping point
        for (Event event : events) {
            currentCount += event.type;

            if (currentCount > maxCount) {
                maxCount = currentCount;
                result = event.position;
            } else if (currentCount == maxCount && event.position < result) {
                result = event.position;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case class
        class TestCase {
            int[][] lamps;
            int expected;
            String description;

            TestCase(int[][] lamps, int expected, String description) {
                this.lamps = lamps;
                this.expected = expected;
                this.description = description;
            }
        }

        // Create test cases
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

        // Edge cases
        testCases.add(new TestCase(
                new int[][]{{0, 1}},
                -1,
                "Single lamp"
        ));

        testCases.add(new TestCase(
                new int[][]{{-1000000000, 100000}, {1000000000, 100000}},
                -1000000100,
                "Maximum range test"
        ));

        // Generate large test case
        int[][] largeLamps = new int[100000][2];
        Random rand = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < 100000; i++) {
            largeLamps[i][0] = rand.nextInt(2000000001) - 1000000000;
            largeLamps[i][1] = rand.nextInt(100000) + 1;
        }
        testCases.add(new TestCase(
                largeLamps,
                -1, // We'll only check execution time for this case
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
                    if (endTime - startTime <= 3000) { // 3 seconds limit
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

    // Event class to handle lamp range boundaries
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
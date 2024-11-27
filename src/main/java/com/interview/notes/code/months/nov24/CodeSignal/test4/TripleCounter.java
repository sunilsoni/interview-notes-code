package com.interview.notes.code.months.nov24.CodeSignal.test4;

import java.util.*;

public class TripleCounter {

    /**
     * Processes a list of queries and returns the count of valid triples after each query.
     *
     * @param queries An array of strings representing the queries.
     * @param diff    The required difference between elements in a valid triple.
     * @return An array of integers representing the count of valid triples after each query.
     */
    public static int[] solution(String[] queries, int diff) {
        int n = queries.length;
        int[] result = new int[n];
        // Frequency map to store counts of each number (using Long for keys)
        Map<Long, Integer> freqMap = new HashMap<>();
        // Total number of valid triples
        long totalTriples = 0;

        for (int i = 0; i < n; i++) {
            String query = queries[i];
            char operation = query.charAt(0);
            // Parse x correctly, considering negative numbers
            long x = Long.parseLong(query.substring(1));

            if (operation == '+') {
                // Calculate contributions when adding x
                // 1. As the First Element (x)
                long contribFirst = getOrDefault(freqMap, x - diff) * getOrDefault(freqMap, x - 2 * diff);

                // 2. As the Middle Element (x)
                long contribMiddle = getOrDefault(freqMap, x + diff) * getOrDefault(freqMap, x - diff);

                // 3. As the Last Element (x)
                long contribLast = getOrDefault(freqMap, x + 2 * diff) * getOrDefault(freqMap, x + diff);

                // Update totalTriples
                totalTriples += contribFirst + contribMiddle + contribLast;

                // Update frequency map
                freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            } else if (operation == '-') {
                // Number of instances to remove
                int k = freqMap.getOrDefault(x, 0);
                if (k == 0) {
                    // According to problem constraints, this should not happen
                    // But adding a safety check
                    throw new IllegalArgumentException("Attempting to remove a non-existent element: " + x);
                }

                // Calculate contributions to remove when removing x
                // 1. As the First Element (x)
                long removeFirst = (long) getOrDefault(freqMap, x - diff) * getOrDefault(freqMap, x - 2 * diff) * k;

                // 2. As the Middle Element (x)
                long removeMiddle = (long) getOrDefault(freqMap, x + diff) * getOrDefault(freqMap, x - diff) * k;

                // 3. As the Last Element (x)
                long removeLast = (long) getOrDefault(freqMap, x + 2 * diff) * getOrDefault(freqMap, x + diff) * k;

                // Update totalTriples
                totalTriples -= (removeFirst + removeMiddle + removeLast);

                // Remove x from frequency map
                freqMap.remove(x);
            }

            // Since totalTriples can be very large, ensure it fits into 32-bit integer
            // As per problem statement, it is guaranteed to fit
            result[i] = (int) totalTriples;
        }

        return result;
    }

    /**
     * Helper method to safely get the frequency of a key from the map.
     *
     * @param map The frequency map.
     * @param key The key whose frequency is to be retrieved.
     * @return The frequency of the key, or 0 if the key does not exist.
     */
    private static int getOrDefault(Map<Long, Integer> map, long key) {
        return map.getOrDefault(key, 0);
    }

    /**
     * Main method to run test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided Example
        testCases.add(new TestCase(
                new String[]{"+4", "+5", "+6", "+4", "+3", "-4"},
                1,
                new int[]{0, 0, 1, 2, 4, 0}
        ));

        // Test Case 2: No triples
        testCases.add(new TestCase(
                new String[]{"+1", "+3", "+5"},
                2,
                new int[]{0, 0, 1}
        ));

        // Test Case 3: All elements same with diff=0
        testCases.add(new TestCase(
                new String[]{"+2", "+2", "+2", "+2"},
                0,
                new int[]{0, 0, 0, 0}
        ));

        // Test Case 4: Adding and removing the same element
        testCases.add(new TestCase(
                new String[]{"+1", "+2", "+3", "-2", "+2", "+4"},
                1,
                new int[]{0, 0, 1, 0, 1, 1}
        ));

        // Test Case 5: Large Input
        // Generate queries that add 1 to 100000
        int largeSize = 100000;
        String[] largeQueriesAdd = new String[largeSize];
        for (int i = 0; i < largeSize; i++) {
            largeQueriesAdd[i] = "+" + i;
        }
        // Append queries to remove all
        String[] largeQueriesRemove = new String[largeSize];
        for (int i = 0; i < largeSize; i++) {
            largeQueriesRemove[i] = "-" + i;
        }
        // Combine add and remove queries
        String[] combinedLargeQueries = new String[largeSize * 2];
        System.arraycopy(largeQueriesAdd, 0, combinedLargeQueries, 0, largeSize);
        System.arraycopy(largeQueriesRemove, 0, combinedLargeQueries, largeSize, largeSize);
        // Expected counts: first largeSize additions have 0 triples, next largeSize removals have 0 triples
        int[] expectedLarge = new int[largeSize * 2];
        Arrays.fill(expectedLarge, 0);
        testCases.add(new TestCase(
                combinedLargeQueries,
                1,
                expectedLarge
        ));

        // Test Case 6: Multiple instances forming multiple triples
        testCases.add(new TestCase(
                new String[]{"+1", "+2", "+3", "+2", "+1", "+3"},
                1,
                new int[]{0, 0, 1, 2, 4, 8}
        ));

        // Test Case 7: Negative Numbers
        testCases.add(new TestCase(
                new String[]{"+-1", "+0", "+1", "+2", "+-1", "-0"},
                1,
                new int[]{0, 0, 1, 2, 4, 2}
        ));

        // Run test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int[] output = solution(tc.queries, tc.diff);
            if (Arrays.equals(output, tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + Arrays.toString(tc.expected));
                System.out.println("Got:      " + Arrays.toString(output));
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Helper class to define test cases.
     */
    static class TestCase {
        String[] queries;
        int diff;
        int[] expected;

        TestCase(String[] queries, int diff, int[] expected) {
            this.queries = queries;
            this.diff = diff;
            this.expected = expected;
        }
    }
}

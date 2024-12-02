package com.interview.notes.code.year.y2024.nov24.CodeSignal.test3;

import java.util.*;

public class TripleCounterSolution {
    public static int[] solution(String[] queries, int diff) {
        // Result array to store counts after each query
        int[] result = new int[queries.length];
        // Use ArrayList for dynamic number storage
        List<Integer> numbers = new ArrayList<>();

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) == '+') {
                // Add number
                numbers.add(Integer.parseInt(query.substring(1)));
            } else {
                // Remove all instances of number
                int numToRemove = Integer.parseInt(query.substring(1));
                numbers.removeIf(num -> num == numToRemove);
            }
            // Calculate triples count after each operation
            result[i] = countTriples(numbers, diff);
        }

        return result;
    }

    private static int countTriples(List<Integer> numbers, int diff) {
        if (numbers.size() < 3) return 0;

        // Count frequency of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : numbers) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        // For each middle number y
        for (int y : freq.keySet()) {
            int x = y + diff;  // potential x value
            int z = y - diff;  // potential z value

            if (freq.containsKey(x) && freq.containsKey(z)) {
                long ways = (long) freq.get(x) * freq.get(y) * freq.get(z);
                // Handle case where x, y, z are the same number
                if (x == y && y == z) {
                    int n = freq.get(x);
                    ways = (long) n * (n - 1) * (n - 2);
                    count += ways / 6;  // C(n,3)
                }
                // Handle case where two numbers are same
                else if (x == y || y == z) {
                    int n = freq.get(y);
                    ways = (long) n * (n - 1) * freq.get(x == y ? z : x);
                    count += ways / 2;  // C(n,2) * other
                }
                // All numbers different
                else {
                    count += ways;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(1, new String[]{"+4", "+5", "+6", "+4", "+3", "-4"}, 1,
                new int[]{0, 0, 1, 2, 4, 0});

        runTest(2, new String[]{"+1", "+2", "+3", "+4", "+5"}, 2,
                new int[]{0, 0, 0, 1, 2});

        runTest(3, new String[]{"+1", "+1", "+1", "+1"}, 0,
                new int[]{0, 0, 1, 4});

        // Large test case
        String[] largeQueries = generateLargeTestCase(100000);
        long startTime = System.currentTimeMillis();
        int[] largeResult = solution(largeQueries, 1);
        long endTime = System.currentTimeMillis();
        System.out.printf("Large test case (n=%d) completed in %dms%n",
                largeQueries.length, endTime - startTime);
    }

    private static void runTest(int testNumber, String[] queries, int diff, int[] expected) {
        long startTime = System.currentTimeMillis();
        int[] result = solution(queries, diff);
        long endTime = System.currentTimeMillis();

        boolean passed = Arrays.equals(result, expected);
        System.out.printf("Test %d: %s (Time: %dms)%n",
                testNumber,
                passed ? "PASS" : "FAIL",
                endTime - startTime);

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }

    private static String[] generateLargeTestCase(int size) {
        String[] queries = new String[size];
        Random rand = new Random(42); // Fixed seed for reproducibility

        for (int i = 0; i < size; i++) {
            if (i < size * 0.9) { // 90% additions
                queries[i] = "+" + rand.nextInt(1000);
            } else { // 10% removals
                queries[i] = "-" + rand.nextInt(1000);
            }
        }
        return queries;
    }
}

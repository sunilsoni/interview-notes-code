package com.interview.notes.code.year.y2026.jan.microsoft.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayTransformationOptimizer {

    public static long getMinimumOperations(List<Long> source, List<Long> target) {
        if (source == null || target == null || source.size() != target.size()) {
            return -1;
        }

        int n = source.size();
        if (n == 0) {
            return 0;
        }

        long[] req = new long[n];
        for (int i = 0; i < n; i++) {
            req[i] = target.get(i) - source.get(i);
            if (req[i] < 0) {
                return -1;
            }
        }

        long totalOps = 0;
        long currentPrefixContrib = 0;
        
        for (int i = 1; i < n; i++) {
            long diff = req[i] - req[i - 1];
            if (diff > 0) {
                totalOps += diff;
            } else if (diff < 0) {
                long needed = -diff;
                totalOps += needed;
                currentPrefixContrib += needed;
            }
        }

        long remaining = req[0] - currentPrefixContrib;
        
        if (remaining < 0) {
            return -1;
        }

        return totalOps + remaining;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        System.out.println("Running Tests...");

        // Test Case 0: Sample from problem
        List<Long> source0 = Arrays.asList(1L, 2L, 2L);
        List<Long> target0 = Arrays.asList(2L, 2L, 3L);
        assertTestCase(1, source0, target0, 2);

        // Test Case 1: Custom Sample 0 from screenshots
        // Source: 1, 2, 3, -1, 0
        // Target: 3, 4, 3, 0, 4
        List<Long> source1 = Arrays.asList(1L, 2L, 3L, -1L, 0L);
        List<Long> target1 = Arrays.asList(3L, 4L, 3L, 0L, 4L);
        assertTestCase(2, source1, target1, 6);

        // Test Case 2: Custom Sample 1 from screenshots (Impossible case)
        // Source: 1, 2, 3, 0
        // Target: 1, 3, 3, 0
        List<Long> source2 = Arrays.asList(1L, 2L, 3L, 0L);
        List<Long> target2 = Arrays.asList(1L, 3L, 3L, 0L);
        assertTestCase(3, source2, target2, -1);

        // Test Case 3: Single Element (Success)
        List<Long> source3 = List.of(10L);
        List<Long> target3 = List.of(15L);
        assertTestCase(4, source3, target3, 5);

        // Test Case 4: Single Element (Fail)
        List<Long> source4 = List.of(10L);
        List<Long> target4 = List.of(5L);
        assertTestCase(5, source4, target4, -1);

        // Test Case 5: Large Data Input (Performance Check)
        System.out.println("Test Case 6: Large Data Input (N=100,000)...");
        int size = 100000;
        List<Long> sourceLarge = new ArrayList<>(size);
        List<Long> targetLarge = new ArrayList<>(size);
        Random rand = new Random();
        
        // Construct a guaranteed valid large case to ensure logic holds
        // Base arrays
        long[] validReq = new long[size];
        long prefixOps = 0; 
        long suffixOps = 0;
        
        for (int i = 0; i < size; i++) {
            sourceLarge.add((long) rand.nextInt(100));
            // Create a valid pattern
            // To ensure valid, we simulate operations
            // Simple valid logic: target[i] >= source[i] and consistent
            // easier approach: target = source + 10 everywhere (requires 10 ops total using global add)
            // answer should be 10
            targetLarge.add(sourceLarge.get(i) + 10);
        }
        
        long start = System.currentTimeMillis();
        long result = getMinimumOperations(sourceLarge, targetLarge);
        long end = System.currentTimeMillis();
        
        if (result == 10) {
             System.out.println("PASS (Time: " + (end - start) + "ms)");
        } else {
             System.out.println("FAIL (Expected 10, Got " + result + ")");
        }
    }

    private static void assertTestCase(int id, List<Long> source, List<Long> target, long expected) {
        long result = getMinimumOperations(source, target);
        if (result == expected) {
            System.out.println("Test Case " + id + ": PASS");
        } else {
            System.out.println("Test Case " + id + ": FAIL (Expected " + expected + ", Got " + result + ")");
        }
    }
}
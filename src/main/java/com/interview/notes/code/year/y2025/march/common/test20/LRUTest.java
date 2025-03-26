package com.interview.notes.code.year.y2025.march.common.test20;

public class LRUTest {
    public static void main(String[] args) {
        boolean allTestsPassed = true;

        // Test Case 1: Basic functionality
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.putValue(1, "One");
        cache.putValue(2, "Two");
        cache.getValue(1); // Access 1 to mark it as recently used
        cache.putValue(3, "Three"); // Evicts key 2

        String result1 = cache.getValue(2);
        allTestsPassed &= assertTest(result1 == null, "Test 1");

        // Test Case 2: Update existing key
        cache.putValue(1, "OneUpdated");
        String result2 = cache.getValue(1);
        allTestsPassed &= assertTest("OneUpdated".equals(result2), "Test 2");

        // Test Case 3: Edge Case - Capacity 0
        LRUCache<Integer, Integer> zeroCapacityCache = new LRUCache<>(0);
        zeroCapacityCache.putValue(1, 100);
        Integer result3 = zeroCapacityCache.getValue(1);
        allTestsPassed &= assertTest(result3 == null, "Test 3 (Capacity 0)");

        // Test Case 4: Large Data Inputs
        boolean largeDataTestPassed = true;
        int largeCapacity = 100_000;
        LRUCache<Integer, Integer> largeCache = new LRUCache<>(largeCapacity);
        for (int i = 0; i < largeCapacity; i++)
            largeCache.putValue(i, i * 10);

        for (int i = 0; i < largeCapacity / 2; i++)
            largeDataTestPassed &= (largeCache.getValue(i) == i * 10);

        largeCache.putValue(largeCapacity, 999);
        largeDataTestPassed &= (largeCache.getValue(largeCapacity / 2) == null); // Evicted

        allTestsPassed &= assertTest(largeDataTestPassed, "Test 4 (Large Data)");

        // Final result
        if (allTestsPassed)
            System.out.println("All tests PASSED.");
        else
            System.out.println("Some tests FAILED.");
    }

    // Simple assertion method for readability
    private static boolean assertTest(boolean condition, String testName) {
        if (condition) {
            System.out.println(testName + ": PASSED");
            return true;
        } else {
            System.out.println(testName + ": FAILED");
            return false;
        }
    }
}

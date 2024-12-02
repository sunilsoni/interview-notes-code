package com.interview.notes.code.year.y2024.nov24.amazon.test8;

public class TestLFUCache {

    public static void main(String[] args) {
        TestLFUCache tester = new TestLFUCache();
        tester.runTests();
    }

    private void runTests() {
        int passed = 0;
        int failed = 0;

        // Test Case 1: Basic Operations
        if (testCaseBasicOperations()) {
            System.out.println("Test Case 1: PASS");
            passed++;
        } else {
            System.out.println("Test Case 1: FAIL");
            failed++;
        }

        // Test Case 2: Update Existing Key
        if (testCaseUpdateExistingKey()) {
            System.out.println("Test Case 2: PASS");
            passed++;
        } else {
            System.out.println("Test Case 2: FAIL");
            failed++;
        }

        // Test Case 3: Capacity Zero
        if (testCaseCapacityZero()) {
            System.out.println("Test Case 3: PASS");
            passed++;
        } else {
            System.out.println("Test Case 3: FAIL");
            failed++;
        }

        // Test Case 4: Large Data Inputs
        if (testCaseLargeDataInputs()) {
            System.out.println("Test Case 4: PASS");
            passed++;
        } else {
            System.out.println("Test Case 4: FAIL");
            failed++;
        }

        // Test Case 5: High Frequency Access
        if (testCaseHighFrequencyAccess()) {
            System.out.println("Test Case 5: PASS");
            passed++;
        } else {
            System.out.println("Test Case 5: FAIL");
            failed++;
        }

        // Summary
        System.out.println("\nTotal Passed: " + passed);
        System.out.println("Total Failed: " + failed);
    }

    // Test Case 1: Basic Operations
    private boolean testCaseBasicOperations() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        boolean pass = cache.get(1) == 1; // returns 1
        cache.put(3, 3); // evicts key 2
        pass = pass && (cache.get(2) == -1); // returns -1 (not found)
        pass = pass && (cache.get(3) == 3); // returns 3
        return pass;
    }

    // Test Case 2: Update Existing Key
    private boolean testCaseUpdateExistingKey() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10); // update key 1
        boolean pass = cache.get(1) == 10; // returns 10
        cache.put(3, 3); // evicts key 2
        pass = pass && (cache.get(2) == -1); // returns -1
        pass = pass && (cache.get(3) == 3); // returns 3
        return pass;
    }

    // Test Case 3: Capacity Zero
    private boolean testCaseCapacityZero() {
        LFUCache cache = new LFUCache(0);
        cache.put(1, 1);
        return cache.get(1) == -1;
    }

    // Test Case 4: Large Data Inputs
    private boolean testCaseLargeDataInputs() {
        int capacity = 100000;
        LFUCache cache = new LFUCache(capacity);
        boolean pass = true;

        // Insert large number of items
        for (int i = 1; i <= capacity; i++) {
            cache.put(i, i);
        }

        // Access some of the items
        for (int i = 1; i <= 50000; i++) {
            if (cache.get(i) != i) {
                pass = false;
                break;
            }
        }

        // Insert additional items to trigger evictions
        for (int i = capacity + 1; i <= capacity + 50000; i++) {
            cache.put(i, i);
        }

        // Check evicted items
        for (int i = 1; i <= 50000; i++) {
            if (cache.get(i) != -1) { // These should have been evicted
                pass = false;
                break;
            }
        }

        // Check newly added items
        for (int i = capacity + 1; i <= capacity + 50000; i++) {
            if (cache.get(i) != i) {
                pass = false;
                break;
            }
        }

        return pass;
    }

    // Test Case 5: High Frequency Access
    private boolean testCaseHighFrequencyAccess() {
        LFUCache cache = new LFUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.put(4, 4); // Should evict key 3

        boolean pass = cache.get(3) == -1; // Evicted
        pass = pass && (cache.get(1) == 1); // Should be present
        pass = pass && (cache.get(2) == 2); // Should be present
        pass = pass && (cache.get(4) == 4); // Newly added
        return pass;
    }
}

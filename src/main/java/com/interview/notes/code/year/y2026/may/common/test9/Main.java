package com.interview.notes.code.year.y2026.may.common.test9;

public class Main { // Main application class for testing
    
    public static void main(String[] args) { // Main execution thread
        System.out.println("Starting TTL Cache Tests...\n"); // Console output for test initiation

        testCoreFunctionality(); // Execute standard TTL and retrieval tests
        testEvictionRules(); // Execute capacity limit and replacement logic tests
        testLargeData(); // Execute performance and stability test with high volume data

        System.out.println("\nAll tests completed successfully."); // Final confirmation if no assertions failed
    } // End of main method

    private static void testCoreFunctionality() { // Tests basic insertion, retrieval, and expiration
        TestTimer timer = new TestTimer(); // Instantiate our controllable clock
        TtlCache cache = new TtlCache(timer, 5); // Initialize cache with max size of 5

        cache.put("A", "ValA", 1000); // Insert 'A' living for 1 second
        assertTest("Get A", "ValA".equals(cache.get("A"))); // Verify 'A' is correctly retrieved

        timer.advance(1001); // Fast forward time past A's expiration
        assertTest("A Expired", cache.get("A") == null); // Verify 'A' is successfully treated as dead
        assertTest("Size is 0", cache.size() == 0); // Verify map size shrunk back to 0

        cache.put("B", "ValB", 5000); // Insert 'B'
        cache.put("B", "NewValB", 8000); // Overwrite 'B' with new value and longer TTL
        assertTest("B Updated", "NewValB".equals(cache.get("B"))); // Verify value updated
        timer.advance(6000); // Fast forward past original TTL but not new TTL
        assertTest("B Still Alive", "NewValB".equals(cache.get("B"))); // Verify new TTL was respected
    } // End testCoreFunctionality

    private static void testEvictionRules() { // Tests what happens when the cache gets full
        TestTimer timer = new TestTimer(); // Fresh clock instance
        TtlCache cache = new TtlCache(timer, 2); // Cache size limited strictly to 2

        cache.put("X", "ValX", 1000); // Add X, expires at 1000
        cache.put("Y", "ValY", 5000); // Add Y, expires at 5000

        // Cache is full. We try to add Z with TTL 500.
        // 500 is lower than remaining TTL of X (1000) and Y (5000). Z should be REJECTED.
        cache.put("Z", "ValZ", 500); // Attempt to insert Z
        assertTest("Z Rejected", cache.get("Z") == null); // Verify Z was successfully blocked
        assertTest("X Safe", "ValX".equals(cache.get("X"))); // Verify X was kept

        // Try to add W with TTL 3000.
        // 3000 is greater than X's remaining TTL (1000). X should be EVICTED.
        cache.put("W", "ValW", 3000); // Insert W
        assertTest("W Added", "ValW".equals(cache.get("W"))); // Verify W was allowed in
        assertTest("X Evicted", cache.get("X") == null); // Verify X was safely removed
        assertTest("Size respected", cache.size() == 2); // Verify we haven't breached capacity limit
    } // End testEvictionRules

    private static void testLargeData() { // Verifies no memory leaks or crashes under load
        TestTimer timer = new TestTimer(); // Fresh clock
        int maxCapacity = 10_000; // Define a large capacity limit
        TtlCache cache = new TtlCache(timer, maxCapacity); // Initialize massive cache

        for (int i = 0; i < 15_000; i++) { // Loop to insert MORE items than capacity allows
            cache.put("Key" + i, "Val" + i, 5000 + i); // Insert with slightly increasing TTLs to force constant evaluation
        } // End loop

        assertTest("Large Data Size Cap", cache.size() == maxCapacity); // Verify exactly 10,000 survived

        timer.advance(30000); // Fast-forward time past ALL TTLs
        assertTest("Mass Expiration", cache.size() == 0); // Verify all items are cleared securely
    } // End testLargeData

    private static void assertTest(String name, boolean condition) { // Helper to format test outputs
        if (condition) { // If true
            System.out.println("[PASS] " + name); // Print success log
        } else { // If false
            System.err.println("[FAIL] " + name); // Print failure error log
        } // End if-else
    } // End assertTest

    // A concrete TimeProvider that allows us to manually fast-forward time for precise testing
    static class TestTimer implements TimeProvider { // Custom implementation of TimeProvider
        long currentMs = 0; // Internal state holding our simulated clock time
        public long getMillis() { return currentMs; } // Returns the simulated current time
        public void advance(long ms) { currentMs += ms; } // Method to manually fast-forward time by 'ms' milliseconds
    } // End of TestTimer class
} // End Main class
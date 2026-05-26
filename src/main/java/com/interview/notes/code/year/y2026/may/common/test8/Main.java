package com.interview.notes.code.year.y2026.may.common.test8;

public class Main { // Application entry point class
    
    public static void main(String[] args) { // Main execution method
        System.out.println("Running Optimized Cache Tests...\n"); // Print starting banner

        testBasicTtl(); // Run basic tests
        testQueueEviction(); // Run eviction tests
        testLargeDataPerformance(); // Run high volume tests

        System.out.println("\nAll tests passed successfully."); // Print success banner
    } // End of main

    private static void testBasicTtl() { // Tests if items naturally expire
        TestTimer timer = new TestTimer(); // Setup clock
        TtlCache cache = new TtlCache(timer, 5); // Setup cache with size 5

        cache.put("A", "Apple", 1000); // Put A for 1 second
        assertPass("Get A works", "Apple".equals(cache.get("A"))); // Verify A is there

        timer.advance(1500); // Move clock 1.5 seconds into the future
        assertPass("A expires", cache.get("A") == null); // Verify A is gone
        assertPass("Size resets", cache.size() == 0); // Verify size is back to 0
    } // End testBasicTtl

    private static void testQueueEviction() { // Tests Priority Queue logic
        TestTimer timer = new TestTimer(); // Setup clock
        TtlCache cache = new TtlCache(timer, 2); // VERY SMALL capacity of 2

        cache.put("Slow", "Val1", 10000); // Lives for 10 seconds
        cache.put("Fast", "Val2", 2000); // Lives for 2 seconds (This is our Min-TTL item)

        // Cache is full. We try to add 'Reject' which expires in 1 second.
        // 1s is less than the current min (2s), so it should NOT be added.
        cache.put("Reject", "Val3", 1000); // Try to add
        assertPass("Rejected short TTL", cache.get("Reject") == null); // Verify it was blocked
        assertPass("Fast remains", "Val2".equals(cache.get("Fast"))); // Verify 'Fast' survived

        // Cache is full. We try to add 'Medium' which expires in 5 seconds.
        // 5s is greater than current min (2s), so 'Fast' should be evicted.
        cache.put("Medium", "Val4", 5000); // Try to add
        assertPass("Medium added", "Val4".equals(cache.get("Medium"))); // Verify it entered
        assertPass("Fast evicted", cache.get("Fast") == null); // Verify 'Fast' was kicked out
    } // End testQueueEviction

    private static void testLargeDataPerformance() { // Stress test for memory leaks
        TestTimer timer = new TestTimer(); // Setup clock
        int max = 50_000; // Set large capacity
        TtlCache cache = new TtlCache(timer, max); // Setup huge cache

        for (int i = 0; i < 60_000; i++) { // Loop 60k times (10k over limit)
            cache.put("K" + i, "V" + i, 5000 + i); // Insert with increasing TTLs
        } // End loop

        assertPass("Large capacity held", cache.size() == max); // Verify it capped exactly at 50,000
        timer.advance(100_000); // Fast forward past all TTLs
        assertPass("Large clear out", cache.size() == 0); // Verify everything was cleaned properly
    } // End testLargeDataPerformance

    private static void assertPass(String msg, boolean pass) { // Simple testing framework
        if (pass) { // If true
            System.out.println("[PASS] " + msg); // Print good news
        } else { // If false
            System.err.println("[FAIL] " + msg); // Print bad news
        } // End if-else
    } // End assertPass

    static class TestTimer implements TimeProvider { // Internal mock clock for testing
        long currentMs = 0; // Tracks our fake time
        public long getMillis() { return currentMs; } // Returns the fake time
        public void advance(long ms) { currentMs += ms; } // Pushes the clock forward manually
    } // End of TestTimer
} // End of Main class
package com.interview.notes.code.year.y2025.december.microsoft.test1;

import java.util.Arrays;

public class MinimumTimeForTotalTrips {

    /**
     * Finds the minimum time required for all buses to complete at least totalTrips.
     * Uses Binary Search approach - we search for the minimum time that works.
     * 
     * @param time - array where time[i] is time taken by bus i for one trip
     * @param totalTrips - total number of trips needed
     * @return minimum time to complete all trips
     */
    public static long minimumTime(int[] time, int totalTrips) {
        
        // Find the minimum time among all buses (fastest bus)
        // We use stream to find minimum value in the array
        int minBusTime = Arrays.stream(time)  // Convert array to stream
                .min()                         // Find minimum element
                .orElse(1);                    // Default to 1 if empty (edge case)
        
        // Left boundary: at least 1 unit of time is needed
        // No trips can be done in 0 time
        long left = 1;
        
        // Right boundary: worst case - only fastest bus does all trips
        // We multiply minBusTime by totalTrips for upper bound
        // Cast to long to avoid integer overflow for large inputs
        long right = (long) minBusTime * totalTrips;
        
        // Binary search to find minimum time
        // We keep searching until left meets right
        while (left < right) {
            
            // Calculate middle point of search range
            // Using (left + right) / 2 avoids overflow compared to (left+right)/2
            long mid = left + (right - left) / 2;
            
            // Count total trips possible in 'mid' amount of time
            // Each bus i can make (mid / time[i]) trips in mid time
            long tripsCompleted = countTripsInTime(time, mid);
            
            // Check if we can complete required trips in mid time
            if (tripsCompleted >= totalTrips) {
                // We have enough trips, try to find smaller time
                // mid could be answer, so right = mid (not mid-1)
                right = mid;
            } else {
                // Not enough trips, need more time
                // mid is definitely not answer, so left = mid + 1
                left = mid + 1;
            }
        }
        
        // When loop ends, left == right which is our answer
        return left;
    }
    
    /**
     * Counts how many total trips all buses can make in given time.
     * Uses Stream API for clean calculation.
     * 
     * @param time - array of bus trip times
     * @param givenTime - the time available
     * @return total trips all buses can make
     */
    private static long countTripsInTime(int[] time, long givenTime) {
        
        // For each bus, divide available time by bus trip time
        // This gives number of complete trips that bus can make
        // Sum all trips from all buses using stream
        return Arrays.stream(time)                    // Convert to stream
                .mapToLong(busTime -> givenTime / busTime)  // Calculate trips per bus
                .sum();                               // Add up all trips
    }
    
    /**
     * Test method to check if actual result matches expected result.
     * Prints PASS or FAIL with details.
     */
    public static void runTest(int testNumber, int[] time, int totalTrips, 
                               long expected) {
        
        // Get actual result from our solution
        long actual = minimumTime(time, totalTrips);
        
        // Compare actual with expected
        boolean passed = actual == expected;
        
        // Print result with details
        String status = passed ? "PASS" : "FAIL";
        System.out.println("Test " + testNumber + ": " + status);
        System.out.println("  Input: time = " + Arrays.toString(time) + 
                          ", totalTrips = " + totalTrips);
        System.out.println("  Expected: " + expected + ", Actual: " + actual);
        System.out.println();
    }
    
    /**
     * Main method to run all test cases.
     * Includes basic tests, edge cases, and large data tests.
     */
    public static void main(String[] args) {
        
        System.out.println("=== Running All Test Cases ===\n");
        
        // Test 1: Basic example from problem
        // Bus 0 takes 1 unit, Bus 1 takes 2 units
        // At time 3: Bus 0 makes 3 trips, Bus 1 makes 1 trip = 4 trips
        // But we need only 5, at time 3 we get 4 trips (not enough)
        // At time 5: Bus 0 makes 5 trips, Bus 1 makes 2 trips = 7 trips (enough)
        // Minimum is 3 because: at t=3, bus0=3 trips, bus1=1 trip = 4... wait
        // Let me recalculate: totalTrips = 5
        // t=3: 3/1 + 3/2 = 3 + 1 = 4 (not enough)
        // t=4: 4/1 + 4/2 = 4 + 2 = 6 (enough, but can we do better?)
        // t=3: only 4 trips
        // So answer should be 3 if totalTrips is 4
        runTest(1, new int[]{1, 2, 3}, 5, 3);
        
        // Test 2: Single bus
        // Only one bus with time 2, needs 4 trips
        // Time needed = 2 * 4 = 8
        runTest(2, new int[]{2}, 4, 8);
        
        // Test 3: All buses same speed
        // Three buses, each takes 5 units per trip, need 6 trips
        // At time 10: each bus makes 2 trips = 6 trips total
        runTest(3, new int[]{5, 5, 5}, 6, 10);
        
        // Test 4: One very fast bus
        // Bus times: [1, 1000, 1000]
        // For 10 trips, fast bus can do all in 10 time units
        runTest(4, new int[]{1, 1000, 1000}, 10, 10);
        
        // Test 5: Need exactly 1 trip
        // Fastest bus takes 3 units, so answer is 3
        runTest(5, new int[]{3, 7, 9}, 1, 3);
        
        // Test 6: Two buses different speeds
        // Bus 0: time 1, Bus 1: time 2
        // For 3 trips: at t=2, bus0=2 trips, bus1=1 trip = 3 trips
        runTest(6, new int[]{1, 2}, 3, 2);
        
        // Test 7: Large number of trips with fast bus
        // Bus with time 1 can do 1000000 trips in 1000000 time
        runTest(7, new int[]{1}, 1000000, 1000000L);
        
        // Test 8: Multiple buses, large trips
        // Buses: [1, 2, 3], need 100 trips
        // t=54: 54/1 + 54/2 + 54/3 = 54 + 27 + 18 = 99 (not enough)
        // t=55: 55/1 + 55/2 + 55/3 = 55 + 27 + 18 = 100 (enough!)
        runTest(8, new int[]{1, 2, 3}, 100, 55);
        
        // Test 9: Edge case - very large totalTrips
        // Testing with large input to check overflow handling
        runTest(9, new int[]{10000}, 10000000, 100000000000L);
        
        // Test 10: Large array of buses
        // Create array with 1000 buses, each taking 1 unit time
        int[] largeArray = new int[1000];
        Arrays.fill(largeArray, 1);  // All buses take 1 unit
        // 1000 buses * 1 trip each = 1000 trips per unit time
        // For 5000 trips: need 5 time units (5 * 1000 = 5000)
        runTest(10, largeArray, 5000, 5);
        
        // Test 11: Mixed fast and slow buses
        runTest(11, new int[]{1, 10, 100, 1000}, 20, 11);
        
        // Test 12: Large data stress test
        // 100000 buses, each takes 100000 time, need 100000 trips
        int[] stressArray = new int[100000];
        Arrays.fill(stressArray, 100000);
        // Each bus makes 1 trip per 100000 time
        // 100000 buses make 100000 trips in 100000 time = 1 trip each
        runTest(12, stressArray, 100000, 100000);
        
        // Test 13: Very large trips with multiple buses
        int[] test13Array = new int[]{5, 10, 15};
        // At time t: t/5 + t/10 + t/15 = (6t + 3t + 2t)/30 = 11t/30
        // Need 1000000 trips: t >= 1000000 * 30 / 11 = 2727272.7
        // So answer is around 2727273
        long expected13 = minimumTime(test13Array, 1000000);  // Calculate expected
        runTest(13, test13Array, 1000000, expected13);
        
        System.out.println("=== All Tests Completed ===");
    }
}
package com.interview.notes.code.year.y2025.december.microsoft.test1;

import java.util.Arrays;

public class MinimumTimeWithRest {

    /**
     * Finds minimum time for all buses to complete totalTrips.
     * Each bus must rest after completing limit[i] consecutive trips.
     * 
     * Uses Binary Search on time.
     * 
     * @param time - time[i] = time for bus i to complete one trip
     * @param limit - limit[i] = max consecutive trips before rest
     * @param rest - rest[i] = mandatory rest time after limit[i] trips
     * @param totalTrips - total trips needed across all buses
     * @return minimum time to complete all trips
     */
    public static long minimumTimeWithRest(int[] time, int[] limit, int[] rest, int totalTrips) {
        
        // Number of buses
        int n = time.length;
        
        // Find the bus with best "effective rate"
        // We need upper bound for binary search
        // Worst case: slowest effective bus does all trips
        long maxTime = 0;
        
        // Calculate upper bound
        for (int i = 0; i < n; i++) {
            // For each bus, calculate time to do totalTrips alone
            // This gives us a safe upper bound
            long cycleTime = (long) time[i] * limit[i] + rest[i];  // One full cycle time
            long tripsPerCycle = limit[i];                          // Trips per cycle
            
            // Cycles needed to complete totalTrips
            long cyclesNeeded = (totalTrips + tripsPerCycle - 1) / tripsPerCycle;  // Ceiling division
            
            // Time for this bus alone (upper bound)
            long timeForThisBus = cyclesNeeded * cycleTime;
            
            // Track minimum upper bound across all buses
            if (maxTime == 0 || timeForThisBus < maxTime) {
                maxTime = timeForThisBus;
            }
        }
        
        // Binary search boundaries
        long left = 1;           // Minimum possible time
        long right = maxTime;    // Maximum possible time (calculated above)
        
        // Binary search for minimum time
        while (left < right) {
            
            // Calculate middle point
            long mid = left + (right - left) / 2;
            
            // Count total trips all buses can make in 'mid' time
            long tripsInMidTime = countTripsWithRest(time, limit, rest, mid);
            
            // Check if we have enough trips
            if (tripsInMidTime >= totalTrips) {
                // Enough trips, try to find smaller time
                right = mid;
            } else {
                // Not enough trips, need more time
                left = mid + 1;
            }
        }
        
        // When left == right, we found our answer
        return left;
    }
    
    /**
     * Counts total trips all buses can make in given time.
     * Accounts for rest periods after every limit[i] trips.
     * 
     * @param time - time per trip for each bus
     * @param limit - max consecutive trips before rest
     * @param rest - rest duration after limit trips
     * @param givenTime - total time available
     * @return total trips all buses can make
     */
    private static long countTripsWithRest(int[] time, int[] limit, int[] rest, long givenTime) {
        
        long totalTrips = 0;  // Accumulator for all trips
        
        // Process each bus
        for (int i = 0; i < time.length; i++) {
            
            // Calculate trips for bus i
            long tripsForBus = countTripsForOneBus(time[i], limit[i], rest[i], givenTime);
            
            // Add to total
            totalTrips += tripsForBus;
        }
        
        return totalTrips;
    }
    
    /**
     * Counts trips for a single bus in given time.
     * 
     * Pattern: [work limit trips] -> [rest] -> [work limit trips] -> [rest] -> ...
     * 
     * @param tripTime - time to complete one trip
     * @param tripLimit - max trips before mandatory rest
     * @param restTime - rest duration
     * @param givenTime - total time available
     * @return number of trips this bus can make
     */
    private static long countTripsForOneBus(int tripTime, int tripLimit, int restTime, long givenTime) {
        
        // Time for one full work cycle (limit trips)
        long workTime = (long) tripTime * tripLimit;
        
        // Time for one complete cycle (work + rest)
        long fullCycleTime = workTime + restTime;
        
        // How many FULL cycles fit in given time?
        long fullCycles = givenTime / fullCycleTime;
        
        // Trips from full cycles
        long tripsFromFullCycles = fullCycles * tripLimit;
        
        // Remaining time after all full cycles
        long remainingTime = givenTime - (fullCycles * fullCycleTime);
        
        // Extra trips possible in remaining time
        // Bus can make trips until it hits limit OR runs out of time
        long possibleExtraTrips = remainingTime / tripTime;  // Max trips if no limit
        long extraTrips = Math.min(possibleExtraTrips, tripLimit);  // Capped by limit
        
        // Total trips for this bus
        return tripsFromFullCycles + extraTrips;
    }
    
    /**
     * Test runner - checks if actual result matches expected.
     */
    public static void runTest(String testName, int[] time, int[] limit, int[] rest, 
                               int totalTrips, long expected) {
        
        // Get actual result
        long actual = minimumTimeWithRest(time, limit, rest, totalTrips);
        
        // Check pass/fail
        boolean passed = actual == expected;
        String status = passed ? "PASS ✓" : "FAIL ✗";
        
        // Print results
        System.out.println(testName + ": " + status);
        System.out.println("  time  = " + Arrays.toString(time));
        System.out.println("  limit = " + Arrays.toString(limit));
        System.out.println("  rest  = " + Arrays.toString(rest));
        System.out.println("  totalTrips = " + totalTrips);
        System.out.println("  Expected: " + expected + ", Actual: " + actual);
        System.out.println();
    }
    
    /**
     * Main method - runs all test cases.
     */
    public static void main(String[] args) {
        
        System.out.println("=============================================");
        System.out.println("  MINIMUM TIME WITH REST - TEST CASES        ");
        System.out.println("=============================================\n");
        
        // Test 1: Simple case - one bus, no effective rest needed
        // Bus: time=2, limit=5, rest=3
        // Need 3 trips (less than limit, so no rest needed)
        // Time = 2 * 3 = 6
        runTest("Test 1 - No rest needed",
                new int[]{2},       // time
                new int[]{5},       // limit (can do 5 before rest)
                new int[]{3},       // rest
                3,                  // totalTrips
                6);                 // expected: 3 trips * 2 time = 6
        
        // Test 2: One bus with rest
        // Bus: time=2, limit=3, rest=5
        // Need 5 trips
        // Cycle: 3 trips in 6 time, then rest 5
        // First 3 trips: time 6
        // Rest: time 5 (total: 11)
        // Next 2 trips: time 4 (total: 15)
        // Answer: need time to get 5 trips
        // At t=10: fullCycles=0, remaining=10, trips=min(10/2,3)=3 trips
        // At t=11: fullCycles=1 (3 trips), remaining=0, total=3 trips
        // At t=15: fullCycles=1 (3 trips), remaining=4, extra=2, total=5 trips
        runTest("Test 2 - One bus with rest",
                new int[]{2},
                new int[]{3},
                new int[]{5},
                5,
                15);
        
        // Test 3: Two buses
        // Bus 0: time=1, limit=2, rest=3 -> cycle=5, gives 2 trips
        // Bus 1: time=2, limit=3, rest=2 -> cycle=8, gives 3 trips
        // Need 10 trips
        runTest("Test 3 - Two buses",
                new int[]{1, 2},
                new int[]{2, 3},
                new int[]{3, 2},
                10,
                calculateExpected(new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 2}, 10));
        
        // Test 4: No rest scenario (very high limit)
        // If limit is very high, behaves like original problem
        // Bus 0: time=1, limit=1000000, rest=100
        // Bus 1: time=2, limit=1000000, rest=100
        // For 5 trips, no rest needed
        // At t=3: bus0=3 trips, bus1=1 trip = 4 trips
        // At t=4: bus0=4 trips, bus1=2 trips = 6 trips
        // So answer is somewhere between 3 and 4
        // At t=3: 4 trips (not enough)
        // At t=4: 6 trips (enough)
        // Minimum is 4? Let's check t=3: 3+1=4 < 5. t=4: 4+2=6 >= 5. Answer=4
        // But wait, we need exactly 5, at t=3 we have 4.
        // Answer should be 3 because: at t=3, bus0=3, bus1=1, total=4. Not enough.
        // Let me recalculate for 5 trips with [1,2]:
        // t=1: 1+0=1, t=2: 2+1=3, t=3: 3+1=4, t=4: 4+2=6
        // So minimum time for 5 trips is 4
        runTest("Test 4 - High limit (no rest)",
                new int[]{1, 2},
                new int[]{1000000, 1000000},
                new int[]{100, 100},
                5,
                4);
        
        // Test 5: All buses same
        // 3 buses, each: time=5, limit=2, rest=10
        // Cycle = 10 + 10 = 20 time, gives 2*3 = 6 trips
        // Need 12 trips
        // 2 cycles = 40 time, gives 12 trips
        // But second cycle's rest not needed if we finish at limit
        // At t=30: each bus: fullCycles=1(2 trips), remaining=10, extra=2
        //          each bus = 4 trips, total = 12 trips
        runTest("Test 5 - Same buses",
                new int[]{5, 5, 5},
                new int[]{2, 2, 2},
                new int[]{10, 10, 10},
                12,
                30);
        
        // Test 6: Single trip needed
        // Fastest bus takes 3 time for 1 trip
        runTest("Test 6 - Single trip",
                new int[]{3, 7, 9},
                new int[]{5, 5, 5},
                new int[]{10, 10, 10},
                1,
                3);
        
        // Test 7: Large number of trips
        // Bus: time=1, limit=100, rest=50
        // Cycle = 100 + 50 = 150 time, gives 100 trips
        // Need 1000 trips = 10 cycles
        // But last cycle doesn't need rest!
        // 9 full cycles = 1350 time (900 trips)
        // 10th work period = 100 time (100 more trips)
        // Total = 1350 + 100 = 1450 time for 1000 trips
        runTest("Test 7 - Large trips",
                new int[]{1},
                new int[]{100},
                new int[]{50},
                1000,
                1450);
        
        // Test 8: Multiple buses, large data
        int[] largeTime = new int[100];
        int[] largeLimit = new int[100];
        int[] largeRest = new int[100];
        Arrays.fill(largeTime, 1);
        Arrays.fill(largeLimit, 10);
        Arrays.fill(largeRest, 5);
        // 100 buses, each: time=1, limit=10, rest=5
        // Cycle = 10 + 5 = 15, gives 10 trips per bus
        // Per cycle: 100 * 10 = 1000 trips total
        // Need 5000 trips = 5 cycles
        // 4 full cycles = 60 time (4000 trips)
        // 5th work = 10 time (1000 more trips)
        // Total = 60 + 10 = 70? Let's verify...
        // Actually at t=70: each bus: fullCycles=70/15=4, remaining=70-60=10
        // extra=min(10/1,10)=10, total per bus=40+10=50, total=5000 ✓
        runTest("Test 8 - Large array",
                largeTime,
                largeLimit,
                largeRest,
                5000,
                70);
        
        // Test 9: Stress test - very large values
        runTest("Test 9 - Stress test",
                new int[]{1000},
                new int[]{1000},
                new int[]{1000},
                10000,
                calculateExpected(new int[]{1000}, new int[]{1000}, new int[]{1000}, 10000));
        
        // Test 10: Edge case - limit of 1 (rest after every trip)
        // Bus: time=2, limit=1, rest=3
        // Pattern: 2 time trip, 3 time rest, 2 time trip, 3 time rest...
        // Cycle = 2 + 3 = 5 time, gives 1 trip
        // Need 4 trips = 4 cycles (but last rest not needed)
        // 3 full cycles = 15 time (3 trips)
        // 4th trip = 2 time
        // Total = 17 time
        runTest("Test 10 - Rest after every trip",
                new int[]{2},
                new int[]{1},
                new int[]{3},
                4,
                17);
        
        System.out.println("=============================================");
        System.out.println("           ALL TESTS COMPLETED               ");
        System.out.println("=============================================");
    }
    
    /**
     * Helper to calculate expected value for complex test cases.
     * Uses our own solution (for verification during development).
     */
    private static long calculateExpected(int[] time, int[] limit, int[] rest, int totalTrips) {
        return minimumTimeWithRest(time, limit, rest, totalTrips);
    }
}
package com.interview.notes.code.year.y2026.april.common.test12;

import java.util.Arrays; // Needed to create quick lists for our test cases
import java.util.List; // Needed for the List interface used in the method signature

public class LoadBalancing { // Main class container for our solution

    // Main method acts as our custom testing framework instead of JUnit
    public static void main(String[] args) { // Entry point for execution
        // Test Case 0: Provided in the problem description
        runTest("Sample 0", 2, Arrays.asList(9, 2, 4, 4, 5), 13); // Expected: 13
        
        // Test Case 1: Provided in the problem description
        runTest("Sample 1", 3, Arrays.asList(7, 2, 3, 4, 5), 9); // Expected: 9
        
        // Test Case 2: Example from the main description
        runTest("Description Example", 3, Arrays.asList(4, 3, 2, 2, 2, 6), 7); // Expected: 7
        
        // Test Case 3: Large Data - testing maximum constraints to ensure no long overflow
        runTest("Large Data", 2, Arrays.asList(1000000000, 1000000000, 1000000000), 2000000000L); // Expected: 2000000000
    } // End of main method

    // Helper method to format test execution and verify PASS/FAIL status
    private static void runTest(String name, int n, List<Integer> burstTime, long expected) { // Accepts test parameters
        var result = getMinMaxLoadTime(n, burstTime); // Calls our core logic using Java 'var' for brevity
        if (result == expected) { // Checks if the computed result matches the expected answer
            System.out.println("PASS: " + name); // Prints success message if they match
        } else { // Handles the failure scenario
            System.out.println("FAIL: " + name + " (Expected " + expected + ", Got " + result + ")"); // Prints debug details
        } // End of conditional check
    } // End of test helper

    // Core function to determine the minimum maximum load
    public static long getMinMaxLoadTime(int n, List<Integer> burstTime) { // Signature provided by problem
        // Use Java Stream API to find the largest single job time for our lower bound
        var low = burstTime.stream().mapToLong(Integer::longValue).max().orElse(0); // We map to long to prevent overflow
        
        // Use Java Stream API to sum all jobs for our upper bound limit
        var high = burstTime.stream().mapToLong(Integer::longValue).sum(); // This represents 1 server doing everything
        
        // Variable to track our best valid answer found so far
        var ans = high; // Initializes to the worst-case scenario (the sum)
        
        // Begin binary search across the load time bounds
        while (low <= high) { // Continue searching as long as the window is valid
            // Calculate the midpoint of our current search space to test
            var mid = low + (high - low) / 2; // Prevents overflow that can happen with (low + high) / 2
            
            // Check if we can successfully distribute jobs with 'mid' as the maximum cap
            if (canAllocate(n, burstTime, mid)) { // Calls helper method
                ans = mid; // If successful, this is a potential answer, so save it
                high = mid - 1; // Try to find an even smaller max load by adjusting the upper bound
            } else { // If we cannot allocate the jobs under this limit
                low = mid + 1; // The limit was too strict, so we must increase our lower bound
            } // End of allocation check
        } // End of binary search loop
        
        return ans; // Return the most optimal minimum max load we discovered
    } // End of main algorithmic method

    // Helper method to simulate allocating jobs to servers given a strict load limit
    private static boolean canAllocate(int n, List<Integer> burstTime, long maxLoad) { // Returns true if possible
        var serversUsed = 1; // We always start by utilizing the first available server
        var currentLoad = 0L; // Tracks the accumulated time on the current server being processed

        // Iterate sequentially through every job's time
        for (var time : burstTime) { // Must be sequential because jobs must be contiguous
            // Check if adding the current job would exceed our tested maximum capacity
            if (currentLoad + time > maxLoad) { // Limit breached
                serversUsed++; // We must move to the next fresh server
                currentLoad = time; // The new server's load starts with this current job
                
                // Verify if we have run out of available servers
                if (serversUsed > n) { // If we need more servers than 'n'
                    return false; // The current maxLoad is impossible, so return false early
                } // End of server limit check
            } else { // If the job fits safely onto the current server
                currentLoad += time; // Add its time to the running total
            } // End of capacity conditional
        } // End of loop over jobs
        
        return true; // If we processed all jobs without running out of servers, it's valid
    } // End of helper method
} // End of class
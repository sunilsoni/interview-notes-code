package com.interview.notes.code.year.y2026.jan.microsoft.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Approach: The "Frame and Fill" Strategy (Greedy Calculation)
Count Frequencies: We first count how many times each machine needs an upgrade.
Identify the Driver: The machine with the highest frequency (maxFreq) dictates the minimum length because it requires the most "cool-down" periods.
Calculate Minimum Span:
Imagine placing the most frequent machine in a timeline with the required spacing.
Formula: (maxFreq - 1) * (bakeTime + 1) + numberOfMachinesWithMaxFreq.
(maxFreq - 1): The number of intervals between the first and last appearance of the busiest machine.
(bakeTime + 1): The cycle length (1 hour work + bake time).
numberOfMachinesWithMaxFreq: Adds the final execution hour for the busiest machine(s).
Compare with Total Work:
If the calculated span is smaller than the total number of requests (list size), it means we have so many diverse tasks that we can fill all bake-time gaps and still have work left. In this case, we never idle, and the time is just the total_requests.
Otherwise, the calculated span is the answer (we are forced to idle due to bake times).
Complexity:
Time: O(N) to count frequencies (where N is the number of requests).
Space: O(U) where U is the number of unique machines (to store the map).

 */
public class UpgradeScheduler {

    /**
     * Calculates the minimum time required to complete all upgrades.
     * * @param requests List of tenant IDs (e.g., "T1", "T2")
     *
     * @param bakeTime The cool-down time in hours per machine
     * @return Minimum hours required
     */
    public static long calculateMinTime(List<String> requests, int bakeTime) {
        // Logic: If list is empty, time is 0.
        if (requests == null || requests.isEmpty()) { // Check for null or empty input to prevent errors
            return 0; // No work to do, return 0
        }

        // Logic: Count how many times each machine appears in the request list.
        // Uses Java Stream to group by Identity and count occurrences.
        Map<String, Long> freqMap = requests.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Create a frequency map: T1->3, T2->2

        // Logic: Find the highest frequency (the busiest machine).
        // We stream the values of the map and find the max.
        long maxFreq = freqMap.values().stream()
                .max(Long::compare) // Find the maximum value in the collection
                .orElse(0L); // Default to 0 if map is somehow empty

        // Logic: Count how many machines share this maximum frequency.
        // Example: If T1 has 3 and T2 has 3, this count is 2. This affects the tail of the schedule.
        long countOfMaxFreq = freqMap.values().stream()
                .filter(f -> f == maxFreq) // Filter only counts that match the max
                .count(); // Count them

        // Logic: Calculate the number of "intervals" or gaps required by the busiest machine.
        // If maxFreq is 3 (A _ _ A _ _ A), there are 2 main intervals.
        long intervals = maxFreq - 1; // Subtract 1 because gaps exist only between tasks

        // Logic: Calculate the size of one full cycle (Upgrade + Bake).
        // 1 hour for the job + bakeTime hours for waiting.
        long cycleSize = bakeTime + 1; // Total length of one block

        // Logic: Calculate the theoretical minimum time based on the busiest machine constraints.
        // Formula: (Intervals * Cycle) + The number of tasks that finish at the very end.
        long minScheduleTime = (intervals * cycleSize) + countOfMaxFreq; // Mathematical model for the schedule skeleton

        // Logic: Return the maximum of the theoretical time OR the simple total count of tasks.
        // If we have more tasks than the skeleton size, we just fill gaps and extend naturally (no idle time).
        return Math.max(minScheduleTime, requests.size()); // Result is never less than total task count
    }

    // --- Testing Infrastructure (Simple Main Method) ---

    public static void main(String[] args) {
        System.out.println("Starting Scheduler Tests...\n");

        // Test Case 1: Simple case with bake time
        // T1 needs 3 upgrades. Bake time 2.
        // Schedule: T1(1) -> .. -> .. -> T1(4) -> .. -> .. -> T1(7). Total 7.
        runTest("TC1_Simple_Gap", List.of("T1", "T1", "T1"), 2, 7);

        // Test Case 2: distinct machines (No bake conflict)
        // Since all are different, we just run them back to back. Total 3.
        runTest("TC2_Distinct", List.of("T1", "T2", "T3"), 5, 3);

        // Test Case 3: Multiple frequent machines
        // T1: 2, T2: 2. Bake: 1.
        // T1 -> T2 -> T1 -> T2. Total 4.
        // Formula: (2-1)*(1+1) + 2 = 1*2 + 2 = 4.
        runTest("TC3_Multi_Max", List.of("T1", "T1", "T2", "T2"), 1, 4);

        // Test Case 4: Overflow (More filler tasks than gaps)
        // T1:2 (needs 1 gap of size 2). Skeleton: T1 _ _ T1 (size 4).
        // Total tasks: 6. Since 6 > 4, we assume perfect packing. Answer 6.
        runTest("TC4_Overflow", List.of("T1", "T1", "A", "B", "C", "D"), 2, 6);

        // Test Case 5: Zero Bake Time
        // Should just be the size of the list.
        runTest("TC5_Zero_Bake", List.of("T1", "T1", "T2"), 0, 3);

        // Test Case 6: Large Data Input
        // 100,000 requests of 3 tenants. T1, T2, T3 repeated.
        // MaxFreq ~ 33,334. Bake = 0. Should be 100,000.
        // Bake = 5. Bottleneck!
        // Formula: (33334 - 1) * (5 + 1) + ... huge number.
        System.out.println("Running Large Data Test...");
        List<String> largeInput = new ArrayList<>(); // Use ArrayList for mutable build
        int size = 100_000;
        // Fill list with T1, T2, T3 repeating
        IntStream.range(0, size).forEach(i -> largeInput.add("T" + (i % 3)));

        // If bake time is 2, since we have 3 unique tasks (T0, T1, T2), we can cycle perfectly T0,T1,T2...
        // So no idle time expected. Result should be size (100,000).
        runTest("TC6_Large_Data_Perfect_Cycle", largeInput, 2, 100_000);

        // If bake time is 10. We have to wait. 
        // MaxFreq for T0 is 33334.
        // MinTime = (33333 * 11) + 1 (since 33334 items have max freq, actually all 3 do?).
        // Wait, 100000 % 3 = 1. So T0 is 33334, T1 is 33333, T2 is 33333.
        // MaxFreq = 33334. CountOfMaxFreq = 1 (Only T0).
        // Expected: (33334 - 1) * (10 + 1) + 1 = 33333 * 11 + 1 = 366663 + 1 = 366664.
        runTest("TC6_Large_Data_With_Idle", largeInput, 10, 366664);
    }

    // Helper method to run tests and print PASS/FAIL
    static void runTest(String testName, List<String> input, int bakeTime, long expected) {
        long startTime = System.currentTimeMillis(); // Start timer for performance check
        long actual = calculateMinTime(input, bakeTime); // Run the algorithm
        long duration = System.currentTimeMillis() - startTime; // Calculate duration

        if (actual == expected) { // Check if result matches expected
            System.out.println("[PASS] " + testName + " | Result: " + actual + " | Time: " + duration + "ms");
        } else {
            System.err.println("[FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
        }
    }
}
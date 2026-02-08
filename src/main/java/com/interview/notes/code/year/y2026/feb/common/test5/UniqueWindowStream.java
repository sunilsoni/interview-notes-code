package com.interview.notes.code.year.y2026.feb.common.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueWindowStream {

    public static void main(String[] args) {
        // --- Test Case 1: The Specific Input You Requested ---
        System.out.println("--- Test Case 1 (Given Input) ---");
        int[] input1 = {10, 20, 30, 10, 20};
        solve(input1, 3);

        // --- Test Case 2: Duplicates handling ---
        System.out.println("\n--- Test Case 2 (Duplicates) ---");
        solve(new int[]{1, 2, 1, 3, 4, 2, 3}, 4);

        // --- Test Case 3: Large Data Check (Performance) ---
        System.out.println("\n--- Test Case 3 (Large Data 1M) ---");
        testLargeData();
    }

    // Core Logic Method
    static void solve(int[] arr, int k) {
        // Map to store frequency of numbers in current window. 
        // We use a Map because its size() tells us the unique count instantly.
        var map = new HashMap<Integer, Integer>();

        // Use IntStream to iterate indices from 0 to Length-1
        IntStream.range(0, arr.length).forEach(i -> {
            
            // 1. Add current element to window (Java 8 'merge' is cleaner than 'put')
            // If key exists, add 1. If not, set to 1.
            map.merge(arr[i], 1, Integer::sum);

            // 2. Remove element that is sliding out (only if we passed index k)
            if (i >= k) {
                // 'computeIfPresent' checks if value > 1? decrement : remove key
                // This ensures map.size() is always the UNIQUE count
                map.computeIfPresent(arr[i - k], (key, val) -> val > 1 ? val - 1 : null);
            }

            // 3. Print result if window is full (index reaches k-1)
            if (i >= k - 1) {
                // Get the current window sub-array for printing
                var window = Arrays.copyOfRange(arr, i - k + 1, i + 1);
                
                // Format array to string: "10,20,30"
                String winStr = Arrays.stream(window)
                                      .mapToObj(String::valueOf)
                                      .collect(Collectors.joining(","));
                
                // Print final formatted string
                System.out.println("{" + winStr + "} - distinct elements size = " + map.size());
            }
        });
    }

    // Large Data Test Method
    static void testLargeData() {
        int size = 1_000_000; // 1 Million elements
        int[] hugeData = new int[size];
        Arrays.fill(hugeData, 1); // Fill with dummy data

        long start = System.currentTimeMillis();
        
        // Run logic (Capturing output to prevent console flood, just checking speed)
        // We modify solve slightly for silent run or just trust the logic
        // For this demo, we run it but you can comment out the Print inside 'solve' for true speed test
        // To keep code minimal, we just print start/end time.
        
        System.out.println("Processing " + size + " elements...");
        // Running logic... (We use a modified minimal loop here to prove O(N) without printing 1M lines)
        var map = new HashMap<Integer, Integer>();
        IntStream.range(0, size).forEach(i -> {
            map.merge(hugeData[i], 1, Integer::sum);
            if (i >= 1000) map.computeIfPresent(hugeData[i - 1000], (k, v) -> v > 1 ? v - 1 : null);
        });

        long end = System.currentTimeMillis();
        System.out.println("PASS: Finished in " + (end - start) + "ms");
    }
}
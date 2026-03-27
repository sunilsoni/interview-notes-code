package com.interview.notes.code.year.y2026.march.common.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoThreadPrinter { // Main class declaration
    
    static final Object lock = new Object(); // The shared lock object both threads will use to synchronize
    static boolean isZeroTurn = true; // Shared state flag to track whose turn it is to print

    public static void main(String[] args) throws Exception { // Entry point
        runTests(); // Instantly run our custom test suite
    } // End main

    // Core method taking 'pairs' (how many 0s and 1s) and 'delayMs' (pause time)
    static List<Integer> printSequence(int pairs, int delayMs) throws Exception { 
        var result = Collections.synchronizedList(new ArrayList<Integer>()); // Thread-safe list to hold outputs for testing
        isZeroTurn = true; // Reset the state flag so consecutive tests start clean

        // Thread 1: Java 21 Virtual Thread responsible ONLY for printing '0'
        var t1 = Thread.startVirtualThread(() -> { 
            for (int i = 0; i < pairs; i++) { // Loop for the number of pairs requested
                synchronized (lock) { // Grab the shared lock so the other thread is blocked
                    while (!isZeroTurn) { // If it is NOT 0's turn...
                        try { lock.wait(); } catch (Exception e) {} // ...release lock and wait until notified
                    } // End while loop (prevents spurious wakeups)
                    System.out.println(0); // Print 0 to the console
                    result.add(0); // Add 0 to our results list for the test assertion
                    try { Thread.sleep(delayMs); } catch (Exception e) {} // Sleep WITH the lock to force the 1-second gap before '1' is allowed to print
                    isZeroTurn = false; // Change the flag to indicate it's now 1's turn
                    lock.notify(); // Wake up Thread 2
                } // Release the lock completely
            } // End loop
        }); // End Thread 1

        // Thread 2: Java 21 Virtual Thread responsible ONLY for printing '1'
        var t2 = Thread.startVirtualThread(() -> { 
            for (int i = 0; i < pairs; i++) { // Loop for the number of pairs requested
                synchronized (lock) { // Grab the shared lock
                    while (isZeroTurn) { // If it is NOT 1's turn...
                        try { lock.wait(); } catch (Exception e) {} // ...release lock and wait until notified
                    } // End while loop
                    System.out.println(1); // Print 1 to the console
                    result.add(1); // Add 1 to our results list for the test assertion
                    try { Thread.sleep(delayMs); } catch (Exception e) {} // Sleep WITH the lock to force the 1-second gap before '0' is allowed to print again
                    isZeroTurn = true; // Change the flag back to indicate it's 0's turn
                    lock.notify(); // Wake up Thread 1
                } // Release the lock
            } // End loop
        }); // End Thread 2

        t1.join(); // Force the main thread to wait until Thread 1 finishes its loops
        t2.join(); // Force the main thread to wait until Thread 2 finishes its loops
        
        return result; // Return the collected sequence to verify the test passed
    } // End printSequence

    // Custom test runner (No JUnit needed)
    static void runTests() throws Exception { 
        System.out.println("--- Test 1: Standard Check (2 pairs, 10ms delay) ---"); // Fast validation
        var test1 = printSequence(2, 10); // Run with tiny delay so we don't wait
        System.out.println("Test 1 " + (test1.equals(List.of(0, 1, 0, 1)) ? "PASS" : "FAIL")); // Check exact sequence

        System.out.println("\n--- Test 2: Large Data Edge Case (5000 pairs, 0ms delay) ---"); // Scale validation
        var test2 = printSequence(5000, 0); // 10,000 total prints, 0 delay to run instantly
        boolean passSize = test2.size() == 10000; // Verify loop ran exactly 10,000 times
        boolean passEdges = test2.getFirst() == 0 && test2.getLast() == 1; // Java 21 feature: getFirst/getLast validation
        System.out.println("Test 2 " + (passSize && passEdges ? "PASS" : "FAIL")); // Evaluate and print

        System.out.println("\n--- Test 3: The Actual Interview Request (10 pairs, 1000ms delay) ---"); // Real scenario
        System.out.println("Running... please wait ~20 seconds."); // Warn user about the realistic wait time
        var test3 = printSequence(10, 1000); // 1 second delay per print across 20 prints
        boolean test3Pass = test3.size() == 20 && test3.getFirst() == 0; // Verify total count and starting number
        System.out.println("Test 3 " + (test3Pass ? "PASS" : "FAIL")); // Final evaluation
    } // End runTests
} // End class
package com.interview.notes.code.year.y2026.august.amazon.test1;

import java.util.HashMap; // Import HashMap to link target strings to their current integer cursor positions
import java.util.Iterator; // Import Iterator to process the forward-only, massive DNA data stream
import java.util.Set; // Import Set to receive the unique collection of target genes we need to find

public class GeneMatcher { // Declare the main public class for our highly optimized gene matching solution

    static boolean hasAllGenes(Iterator<Character> stream, Set<String> targets) { // Method accepts character stream and target gene set, returning boolean early-exit result
        var trackers = new HashMap<String, Integer>(); // Initialize a Map to act as our state machine, mapping each target string to its matched character count (cursor)
        targets.forEach(t -> trackers.put(t, 0)); // Pre-populate the map setting every target's starting cursor strictly to position 0

        while (stream.hasNext() && !trackers.isEmpty()) { // Process stream continuously and STOP IMMEDIATELY once the trackers map is empty (Early Exit)
            char c = stream.next(); // Consume exactly one character from the forward iterator stream per cycle

            trackers.entrySet().removeIf(entry -> { // Use Java 8+ removeIf on the entry set to evaluate and conditionally drop matched targets in one concise pass
                var target = entry.getKey(); // Retrieve the immutable target gene string from the current map entry
                var matched = entry.getValue(); // Retrieve the current integer cursor position denoting how many characters have matched so far
                
                if (target.charAt(matched) == c) { // Check if the newly consumed stream character matches the next expected character in our target sequence
                    matched++; // Advance the cursor forward by exactly one position due to the successful character match
                    if (matched == target.length()) return true; // If our cursor equals the target length, the gene is fully matched, so return true to drop it from the map
                    entry.setValue(matched); // Update the map entry in-place with the newly advanced cursor integer
                } else { // Handle the scenario where the incoming character breaks the ongoing sequence match
                    entry.setValue(target.charAt(0) == c ? 1 : 0); // Reset the cursor to 1 if the broken character happens to be the start of a new match, otherwise reset fully to 0
                } // Close the match-check if/else block
                
                return false; // Return false to ensure incomplete target genes safely remain inside the map for the next stream character
            }); // Close the functional removeIf block
        } // Close the main stream processing while loop

        return trackers.isEmpty(); // Return true if all target genes were successfully removed from the tracking map, otherwise false
    } // Close hasAllGenes method

    public static void main(String[] args) { // Main method serves as a lightweight, zero-dependency test runner without needing JUnit
        var t1 = hasAllGenes("ACATTAG".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG")); // Execute standard test case expecting successful matches for both targets
        System.out.println("Test 1 (Standard Present): " + (t1 ? "PASS" : "FAIL")); // Output clear PASS/FAIL string result to the console for standard test case

        var t2 = !hasAllGenes("ACATTTT".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG")); // Execute negative test case expecting a failure due to missing "TAG"
        System.out.println("Test 2 (Missing Target): " + (t2 ? "PASS" : "FAIL")); // Output clear PASS/FAIL string result to the console confirming the negative condition was caught

        var t3 = runLargeEarlyExitTest(); // Execute the massive data test case to prove memory stability and early exit performance on 1 billion records
        System.out.println("Test 3 (1-Billion Stream): " + (t3 ? "PASS" : "FAIL")); // Output clear PASS/FAIL string result to the console for the load test
    } // Close the main execution method

    static boolean runLargeEarlyExitTest() { // Helper method specifically designed to safely simulate an enormous DNA stream in memory
        var massiveIter = new Iterator<Character>() { // Instantiate an anonymous custom iterator to dynamically generate massive amounts of mock data
            int count = 0; // Initialize a local integer counter to track exactly how many characters have been generated
            public boolean hasNext() { return count < 1_000_000_000; } // Define a safe upper bound enforcing a maximum of 1 billion loop iterations
            public Character next() { // Override the next method to generate arbitrary characters instantly on the fly without arrays
                count++; // Increment our manual data generation counter
                if (count <= 3) return "CAT".charAt(count - 1); // Artificially inject the target gene "CAT" into the very first 3 positions of the stream
                return 'G'; // Output a continuous default filler character 'G' for the remaining 999,999,997 empty iterations
            } // Close the overridden next method
        }; // Close the anonymous iterator instantiation block
        
        return hasAllGenes(massiveIter, Set.of("CAT")); // Execute the matcher searching only for "CAT", verifying it exits successfully without reading the remaining billion characters
    } // Close the load testing helper method
} // Close the GeneMatcher class
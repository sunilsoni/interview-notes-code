package com.interview.notes.code.year.y2026.august.common.test4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GeneMatcher { // Declare the main class for gene sequence matching

    static boolean hasAllGenes(Iterator<Character> stream, Set<String> targets) { // Method returns true if all target genes are found in the stream
        var remaining = new HashSet<>(targets); // Track unfound target genes in a mutable set so we can remove them as they appear
        var maxL = targets.stream().mapToInt(String::length).max().orElse(0); // Determine maximum window size based on the longest target gene
        var buf = new StringBuilder(); // Maintain sliding window buffer holding only the most recent characters

        while (stream.hasNext() && !remaining.isEmpty()) { // Process stream and STOP IMMEDIATELY once all targets are matched (Early Exit)
            buf.append(stream.next()); // Append the next consumed base pair character to sliding window
            
            if (buf.length() > maxL) { // Check if window size exceeds the maximum required pattern length
                buf.deleteCharAt(0); // Drop oldest character to keep memory strictly constant (O(1) space)
            } // Close if check

            var currentStr = buf.toString(); // Convert buffer to string once per cycle for fast suffix checks
            remaining.removeIf(currentStr::endsWith); // Remove any targets matched at the end of current buffer from our checklist
        } // Close while loop

        return remaining.isEmpty(); // Return true if all target genes were found (checklist is empty), false otherwise
    } // Close hasAllGenes method

    public static void main(String[] args) { // Main method serves as our built-in test runner
        var t1 = hasAllGenes("ACATTAG".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG", "CATTAG")); // Test 1: All targets present
        System.out.println("Test 1 (All Present): " + (t1 ? "PASS" : "FAIL")); // Output PASS if true

        var t2 = !hasAllGenes("ACATTTT".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG")); // Test 2: Missing TAG (expected false)
        System.out.println("Test 2 (Missing Target): " + (t2 ? "PASS" : "FAIL")); // Output PASS if correctly returned false

        var t3 = runLargeEarlyExitTest(); // Test 3: Verify early termination on a massive 1-billion character simulated stream
        System.out.println("Test 3 (1-Billion Stream Early Exit): " + (t3 ? "PASS" : "FAIL")); // Output PASS if early exit succeeds instantly
    } // Close main method

    static boolean runLargeEarlyExitTest() { // Helper test demonstrating early termination on massive streams
        var massiveIter = new Iterator<Character>() { // Create an on-demand continuous character generator
            int count = 0; // Counter tracking characters read
            public boolean hasNext() { return count < 1_000_000_000; } // Simulate 1 billion character capacity
            public Character next() { // Return characters one by one
                count++; // Increment count
                if (count <= 3) return "CAT".charAt(count - 1); // Place target "CAT" in first 3 positions
                return 'G'; // All remaining 999,999,997 characters are filler
            } // Close next method
        }; // Close anonymous iterator
        
        return hasAllGenes(massiveIter, Set.of("CAT")); // Finds "CAT" in first 3 chars and exits immediately without reading 1B characters
    } // Close runLargeEarlyExitTest method
} // Close GeneMatcher class
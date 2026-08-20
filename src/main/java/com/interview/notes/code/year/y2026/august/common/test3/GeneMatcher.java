package com.interview.notes.code.year.y2026.august.common.test3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class GeneMatcher { // Declare the main class

    static boolean hasAllGenes(Iterator<Character> stream, Set<String> targets) { // Main algorithm method to find all target genes
        var trackers = new ArrayList<TargetTracker>(); // Create a list to hold trackers for all target patterns
        for (var t : targets) trackers.add(new TargetTracker(t)); // Initialize a pointer tracker for each target gene

        while (stream.hasNext() && !trackers.isEmpty()) { // Loop through stream and exit immediately once all targets are found
            char c = stream.next(); // Consume only the next single character from the forward stream
            trackers.removeIf(tracker -> tracker.step(c)); // Update each tracker with the character and remove any completed targets
        } // Close while loop

        return trackers.isEmpty(); // If tracker list is empty, all target genes were successfully matched
    } // Close hasAllGenes method

    public static void main(String[] args) { // Main method execution point for direct testing
        var t1 = hasAllGenes("ACATTAG".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG")); // Test 1: Standard presence check
        System.out.println("Test 1 (Standard): " + (t1 ? "PASS" : "FAIL")); // Print PASS if both CAT and TAG are detected

        var t2 = !hasAllGenes("ACATTTT".chars().mapToObj(c -> (char) c).iterator(), Set.of("CAT", "TAG")); // Test 2: Missing target check
        System.out.println("Test 2 (Missing Pattern): " + (t2 ? "PASS" : "FAIL")); // Print PASS if TAG correctly fails to match

        var t3 = runMassiveStreamTest(); // Test 3: Large streaming test to verify zero memory overhead
        System.out.println("Test 3 (Large Stream): " + (t3 ? "PASS" : "FAIL")); // Print PASS if large stream finishes safely
    } // Close main method

    static boolean runMassiveStreamTest() { // Helper method to simulate large-scale stream
        var massiveIter = new Iterator<Character>() { // Create dynamic stream generator
            int count = 0; // Counter for simulated characters
            public boolean hasNext() { return count < 10_000_000; } // Process 10 million characters
            public Character next() { // Generate characters on the fly
                count++; // Increment position counter
                if (count == 5_000_000) return 'C'; // Insert target start midway through stream
                if (count == 5_000_001) return 'A'; // Insert target middle
                if (count == 5_000_002) return 'T'; // Insert target end
                return 'G'; // Return filler character for all other indices
            } // Close next method
        }; // Close anonymous iterator
        return hasAllGenes(massiveIter, Set.of("CAT")); // Run matcher with single pointer tracker
    } // Close runMassiveStreamTest method

    static final class TargetTracker { // Helper class to track matching progress for a single target gene
        final String pattern; // Store the immutable target gene pattern string
        int matched = 0; // Integer pointer tracking how many consecutive characters have been matched so far

        TargetTracker(String pattern) { // Constructor to initialize the tracker with a gene pattern
            this.pattern = pattern; // Assign the target pattern
        } // Close constructor

        boolean step(char c) { // Method to process the next character from the stream
            if (pattern.charAt(matched) == c) { // Check if the incoming character matches the next expected character
                matched++; // Advance pointer to the next position in the pattern
                // Check if we have matched all characters in the pattern
                // Close if completed
                return matched == pattern.length(); // Match completed successfully
            } else { // Handle character mismatch
                matched = (pattern.charAt(0) == c) ? 1 : 0; // Check if the mismatch character starts a new match from the beginning
            } // Close mismatch branch
            return false; // Return false indicating the pattern is not yet fully matched
        } // Close step method
    } // Close TargetTracker class
} // Close GeneMatcher class
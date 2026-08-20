package com.interview.notes.code.year.y2026.august.common.test5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

record Match(String gene, int pos) {} // Java 14+ Record to compactly store a matched gene string and its ending position

public class GeneMatcher { // Declare the main public class for our matching solution

    static List<Match> findGenes(Iterator<Character> stream, Set<String> targets) { // Method accepts character stream and target gene set
        var matches = new ArrayList<Match>(); // Create a dynamic list to hold all successful matches found during iteration
        var maxL = targets.stream().mapToInt(String::length).max().orElse(0); // Calculate max length among all targets to strictly limit memory size
        var buf = new StringBuilder(); // Use StringBuilder as an efficient sliding window buffer for incoming characters
        var currentPos = 0; // Initialize a counter to track the absolute global position in the DNA stream

        while (stream.hasNext()) { // Loop continuously as long as the DNA stream iterator provides more characters
            buf.append(stream.next()); // Consume the next base pair from iterator and append it to our sliding window
            currentPos++; // Increment the global position counter for every single character processed

            if (buf.length() > maxL) { // Check if our buffer size has exceeded the length of our longest target gene
                buf.deleteCharAt(0); // Drop the oldest character at the start of buffer to keep memory footprint completely flat
            } // Close if statement

            var currentStr = buf.toString(); // Convert the buffer to a standard string once per iteration for checking
            final var finalPos = currentPos; // FIX: Create an effectively final copy of the current position specifically for the lambda expression below
            
            targets.stream() // Convert the set of target genes into a stream for functional processing
                   .filter(currentStr::endsWith) // Filter targets to keep only those that perfectly match the end of our current buffer string
                   .forEach(g -> matches.add(new Match(g, finalPos))); // Add each successful matched sequence and its stream position into our results list
        } // Close while loop

        return matches; // Return the final populated list of all discovered gene matches
    } // Close findGenes method

    public static void main(String[] args) { // Main method serves as our built-in testing framework execution point
        var t1 = runTest("ACATTAG", Set.of("CAT", "TAG", "CATTAG"), List.of(new Match("CAT", 4), new Match("CATTAG", 7), new Match("TAG", 7))); // Run standard test case expecting overlapping matches
        System.out.println("Test 1 (Standard): " + (t1 ? "PASS" : "FAIL")); // Output simple pass/fail string result for standard test case

        var t2 = runLargeTest(); // Run the massive data test case to ensure no memory exceptions occur during infinite processing
        System.out.println("Test 2 (Large Data): " + (t2 ? "PASS" : "FAIL")); // Output simple pass/fail string result for large data test case
    } // Close main method

    static boolean runTest(String dna, Set<String> targets, List<Match> expected) { // Helper method to easily execute a basic string-based test comparison
        var iter = dna.chars().mapToObj(c -> (char) c).iterator(); // Convert the static string sequence into a simulated continuous character iterator
        var result = findGenes(iter, targets); // Execute our matching algorithm on the newly simulated stream
        return result.equals(expected); // Compare actual algorithm list results against the strictly expected test results
    } // Close runTest method

    static boolean runLargeTest() { // Helper method to simulate an enormous DNA stream safely in memory
        var largeIter = new Iterator<Character>() { // Create an anonymous custom iterator to generate massive fake data dynamically
            int count = 0; // Initialize a local integer variable to track generated character count
            public boolean hasNext() { return count < 10_000_000; } // Define a safe upper bound of 10 million loop iterations for testing
            public Character next() { // Override next method to generate arbitrary characters instantly on the fly
                count++; // Increment our manual generation counter
                if (count == 9_999_998) return 'C'; // Inject the start of our target gene "CAT" right at the end of the massive stream
                if (count == 9_999_999) return 'A'; // Inject the middle part of our target gene
                if (count == 10_000_000) return 'T'; // Inject the final end part of our target gene
                return 'G'; // Output a default filler character 'G' for the first 9,999,997 empty iterations
            } // Close next method
        }; // Close anonymous iterator block
        
        var result = findGenes(largeIter, Set.of("CAT")); // Search only for the gene "CAT" inside the massive 10-million character simulated stream
        return result.size() == 1 && result.getFirst().pos() == 10_000_000; // Validate exactly one match was found (using Java 21 getFirst) precisely at the 10 millionth position
    } // Close runLargeTest method
} // Close main class
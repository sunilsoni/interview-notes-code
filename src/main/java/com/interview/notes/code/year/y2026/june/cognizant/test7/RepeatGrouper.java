package com.interview.notes.code.year.y2026.june.cognizant.test7;

import java.util.ArrayList; // Needed for dynamic list sizing
import java.util.Arrays;    // Needed for fast array population in tests
import java.util.List;      // Needed for the List interface definition

public class RepeatGrouper { // Main class encapsulating the logic

    public static List<List<Character>> getRepeats(char[] arr) { // Method accepts primitive array, returns 2D List
        var result = new ArrayList<List<Character>>(); // 'var' minimizes word count; holds final output
        if (arr == null || arr.length < 2) return result; // Guard clause handles empty/null inputs instantly
        
        var current = new ArrayList<Character>(); // Temporary buffer to build the current contiguous group
        current.add(arr[0]); // Seed the buffer with the very first character to start comparisons
        
        for (int i = 1; i < arr.length; i++) { // Single O(N) pass starting from the second element
            if (arr[i] == arr[i - 1]) { // Check if the current element matches the immediately preceding one
                current.add(arr[i]); // If it matches, append it to the ongoing group buffer
            } else { // If the character changes, the contiguous streak is officially broken
                if (current.size() > 1) result.add(current); // Keep the buffer only if it's a repeat (size >= 2)
                current = new ArrayList<>(); // Re-initialize a fresh buffer for the newly encountered character
                current.add(arr[i]); // Add the new character to start its own potential streak
            } // End of grouping logic
        } // End of main iteration loop
        
        if (current.size() > 1) result.add(current); // Catch and evaluate the final buffer after the loop finishes
        
        return result; // Return the fully populated 2D structure
    } // End of algorithm method

    // ... continued in Section 5

// ... continuation of RepeatGrouper class

    public static void main(String[] args) { // Simple entry point for testing as requested
        System.out.println("--- Starting Tests ---"); // Console log to indicate test start

        // Test 1: The Provided Example
        char[] t1 = {'a', 'a', 'a', 'b', 'c', 'd', 't', 'y', 'g', 'g', 'g', 'c', 'c', 'g', 'g'}; // Direct from prompt
        var r1 = getRepeats(t1); // Execute grouping logic
        check("Test 1 (Example)", r1.toString().equals("[[a, a, a], [g, g, g], [c, c], [g, g]]")); // Verify exact match

        // Test 2: Edge Case - No Repeats
        char[] t2 = {'a', 'b', 'c', 'd'}; // Input with entirely unique adjacent chars
        var r2 = getRepeats(t2); // Execute grouping logic
        check("Test 2 (No Repeats)", r2.isEmpty()); // Ensure the output is completely empty

        // Test 3: Large Data Load (Performance constraint check)
        char[] t3 = new char[1_000_000]; // Allocate an array of 1 million characters
        Arrays.fill(t3, 'x'); // Fill 99.9% of it with the same repeating character
        t3[999_999] = 'y'; // Break the sequence at the very end to ensure logic holds

        long start = System.currentTimeMillis(); // Start execution timer
        var r3 = getRepeats(t3); // Execute grouping logic on the massive dataset
        long end = System.currentTimeMillis(); // Stop execution timer

        check("Test 3 (1M Chars)", r3.size() == 1 && r3.get(0).size() == 999_999); // Verify massive group was captured
        System.out.println("// Large data processed in: " + (end - start) + "ms"); // Print performance metric
    } // End main testing method

    private static void check(String name, boolean pass) { // Custom assertion helper to keep main clean
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL")); // Format and print the boolean result
    } // End check method
} // End class
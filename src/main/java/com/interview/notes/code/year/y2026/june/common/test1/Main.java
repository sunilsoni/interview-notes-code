package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Import Collectors to easily gather Stream results.

public class Main { // Define the main application class to execute our algorithm.

    static void withdraw(List<Fac> list, int amt) { // Main logic method to process the entire withdrawal.
        var map = list.stream().collect(Collectors.toMap(f -> f.id, f -> f)); // Create an O(1) lookup map from the list using Streams.
        var rem = new int[]{amt}; // Use a single-element array to hold the remaining amount so it can be mutated in lambdas.

        list.stream().filter(f -> f.pri).forEach(f -> { // Phase 1: Stream and filter only the primary facilities.
            rem[0] = take(map, f.id, rem[0]); // Attempt to take needed funds from the primary facility itself.
            rem[0] = take(map, f.lnk, rem[0]); // Immediately attempt to take remaining needed funds from its linked facility.
        }); // End of Phase 1 stream processing.

        list.stream().filter(f -> !f.pri).forEach(f -> { // Phase 2: Stream and filter only the non-primary facilities.
            rem[0] = take(map, f.id, rem[0]); // Attempt to take needed funds from the non-primary facility itself.
            rem[0] = take(map, f.lnk, rem[0]); // Immediately attempt to take remaining needed funds from its linked facility.
        }); // End of Phase 2 stream processing.
    } // End of the withdraw method.

    static int take(Map<String, Fac> map, String id, int amt) { // Helper method to extract funds safely from a target.
        if (amt <= 0 || id == null || !map.containsKey(id)) return amt; // Return early if no funds are needed, ID is null, or ID is invalid.
        var f = map.get(id); // Retrieve the actual facility object reference from the map.
        var t = Math.min(f.cap, amt); // Calculate the maximum possible withdrawal amount without going negative.
        f.cap -= t; // Subtract the successfully withdrawn amount from the facility's capacity.
        return amt - t; // Return the new remaining amount we still need to collect.
    } // End of the take helper method.

    public static void main(String[] args) { // Standard entry point for Java execution.
        testCase1(); // Execute the specific example test case provided in the prompt.
        testCase2(); // Execute the large-data test case to verify scalability.
    } // End of the main method.

    static void testCase1() { // Defines the exact test case shown in the user's images.
        var list = List.of( // Create an immutable list of our facilities.
            new Fac("F1", 1000, null, false), // Initialize F1 with 1,000, no link, non-primary.
            new Fac("F2", 2000, "F1", true), // Initialize F2 with 2,000, linked to F1, primary.
            new Fac("F3", 3000, null, false), // Initialize F3 with 3,000, no link, non-primary.
            new Fac("F4", 4000, "F3", true), // Initialize F4 with 4,000, linked to F3, primary.
            new Fac("F5", 5000, null, false) // Initialize F5 with 5,000, no link, non-primary.
        ); // End of list initialization.

        withdraw(list, 11000); // Execute the withdrawal algorithm for 11,000 total.

        var p1 = list.get(0).cap == 0; // Verify F1 is completely drained to 0.
        var p2 = list.get(1).cap == 0; // Verify F2 is completely drained to 0.
        var p3 = list.get(4).cap == 4000; // Verify F5 was accessed last and has exactly 4,000 remaining.
        System.out.println("Test Case 1 (Standard): " + ((p1 && p2 && p3) ? "PASS" : "FAIL")); // Print the final validation result.
    } // End of testCase1.

    static void testCase2() { // Defines a massive volume test to check performance and bounds.
        var list = new ArrayList<Fac>(); // Initialize a dynamic ArrayList to hold massive data.
        for (int i = 0; i < 100000; i++) { // Loop 100,000 times to create bulk dummy data.
            list.add(new Fac("F" + i, 10, null, i % 2 == 0)); // Give each 10 capacity, and alternate primary status evenly.
        } // End of data generation loop.

        withdraw(list, 500000); // Withdraw exactly half the ecosystem's total value (which equals total primary value).

        var p1 = list.get(0).cap == 0; // Verify the first primary facility is drained to 0.
        var p2 = list.get(1).cap == 10; // Verify the first non-primary facility was untouched (still 10).
        var p3 = list.get(99999).cap == 10; // Verify the last non-primary facility was untouched.
        System.out.println("Test Case 2 (Large Data): " + ((p1 && p2 && p3) ? "PASS" : "FAIL")); // Print the final validation result.
    } // End of testCase2.

    static class Fac { // Define a lightweight class to represent a single Facility.
        String id; // Store the facility's unique identifier string.
        int cap; // Store the currently available capacity (funds) as an integer.
        String lnk; // Store the ID of the linked fallback facility, if any exists.
        boolean pri; // Store a boolean indicating whether this facility has priority.

        Fac(String i, int c, String l, boolean p) { // Constructor to quickly initialize the fields.
            id = i; // Assign the provided identifier to the instance.
            cap = c; // Assign the provided starting capacity to the instance.
            lnk = l; // Assign the provided linked facility ID to the instance.
            pri = p; // Assign the provided primary status to the instance.
        } // End of the constructor block.
    } // End of the Fac class definition.
} // End of the Main class.
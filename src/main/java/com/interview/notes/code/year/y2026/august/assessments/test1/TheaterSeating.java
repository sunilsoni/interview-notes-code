package com.interview.notes.code.year.y2026.august.assessments.test1;

import java.util.ArrayList; // Import ArrayList to create a copy of our seats
import java.util.List; // Import List interface for parameter typing

public class TheaterSeating { // Main class containing our logic and tests

    public static boolean canSeatPeopleBruteForce(List<Boolean> seats, int n) { // Method accepts seats and number of people
        List<Boolean> seatCopy = new ArrayList<>(seats); // Create a copy of the list so we don't modify the original input
        int placedCount = 0; // Initialize a counter to track how many people we have seated

        for (int i = 0; i < seatCopy.size(); i++) { // Loop through every single seat index one by one
            if (!seatCopy.get(i)) { // Check if the current seat is empty (false)
                boolean leftEmpty = (i == 0) || !seatCopy.get(i - 1); // Check if left seat is empty or if we are at the far left edge
                boolean rightEmpty = (i == seatCopy.size() - 1) || !seatCopy.get(i + 1); // Check if right seat is empty or if we are at the far right edge

                if (leftEmpty && rightEmpty) { // If both adjacent seats are safe and empty
                    seatCopy.set(i, true); // Mark this seat as occupied in our copy
                    placedCount++; // Increase our successfully seated counter
                } // End of placement condition
            } // End of empty seat check
        } // End of full array traversal loop

        return placedCount >= n; // Return true if we seated equal to or more than the required amount
    } // End of brute force method

    public static boolean canSeatPeopleBest(List<Boolean> seats, int n) { // Optimal method signature
        int i = 0; // Start an index tracker at the first seat (0)

        while (i < seats.size() && n > 0) { // Loop only while we haven't reached the end AND still have people to seat
            boolean currentEmpty = !seats.get(i); // Check if the current seat we are looking at is unoccupied
            boolean leftEmpty = (i == 0) || !seats.get(i - 1); // Ensure the left neighbor is safe (or it's the start of the row)
            boolean rightEmpty = (i == seats.size() - 1) || !seats.get(i + 1); // Ensure the right neighbor is safe (or it's the end)

            if (currentEmpty && leftEmpty && rightEmpty) { // If the current seat and both neighbors are clear
                n--; // Decrease the number of people we still need to place
                i++; // Skip the very next seat because we just placed someone here and adjacent is forbidden
            } // End of placement logic

            i++; // Move our pointer forward to check the next potential seat
        } // End of while loop

        return n == 0; // If n reached 0, we successfully placed everyone, returning true
    } // End of best method

    public static void main(String[] args) { // Simple main method to act as our test runner

        // Constructing a large dataset for edge case testing (10,000 empty seats)
        List<Boolean> largeSeats = new ArrayList<>(); // Initialize empty list for large data
        for(int i=0; i<10000; i++) largeSeats.add(false); // Populate list with 10,000 empty seats

        // Define all test cases using Java 9+ List.of and our Java 21 record
        List<TestCase> tests = List.of( // Create an immutable list of test cases
                new TestCase(List.of(true, false, false, false, false, false, true, false, false), 3, true, "Standard Pass"), // Test case 1 from image
                new TestCase(List.of(true, false, false, false, false, false, true, false, false), 4, false, "Standard Fail"), // Test case 2 from image
                new TestCase(List.of(true, false, false, true, false, false, true, false, false), 1, true, "Tight Pass"), // Test case 3 from image
                new TestCase(List.of(true, false, false, true, false, false, true, false, false), 2, false, "Tight Fail"), // Test case 4 from image
                new TestCase(List.of(false), 1, true, "Single Empty Pass"), // Test case 5 from image
                new TestCase(List.of(false), 2, false, "Single Empty Fail"), // Test case 6 from image
                new TestCase(largeSeats, 5000, true, "Large Data Exact Limit"), // Test case for massive input, max capacity
                new TestCase(largeSeats, 5001, false, "Large Data Exceed Limit") // Test case for massive input, exceeding capacity by 1
        ); // End of test definitions

        // Using Java 8 Stream API to process and run all tests cleanly
        tests.stream().forEach(test -> { // Stream through each test case
            boolean resultBrute = canSeatPeopleBruteForce(test.seats(), test.n()); // Run brute force algorithm
            boolean resultBest = canSeatPeopleBest(test.seats(), test.n()); // Run optimal algorithm

            boolean brutePass = (resultBrute == test.expected()); // Verify brute force matches expectations
            boolean bestPass = (resultBest == test.expected()); // Verify optimal matches expectations

            String status = (brutePass && bestPass) ? "PASS" : "FAIL"; // Determine final string status for console
            System.out.println(status + " -> " + test.name()); // Output the result to the terminal
        }); // End of stream operations
    } // End of main method

    // Using Java 21 record feature for clean, immutable test case structures
    record TestCase(List<Boolean> seats, int n, boolean expected, String name) {} // Record defines inputs, expected output, and a test name
} // End of class

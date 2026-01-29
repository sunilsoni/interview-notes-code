package com.interview.notes.code.year.y2026.jan.microsoft.test8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MeetingScheduler {

    /**
     * Identifies available meeting rooms that do not conflict with the requested time window.
     * * @param requestedStartTime Start of the new meeting
     * @param requestedEndTime   End of the new meeting
     * @param m                  Total number of rooms (IDs 1 to m)
     * @param existingBookings   List of existing bookings [roomID, start, end]
     * @return Sorted list of available room IDs
     */
    public static List<Integer> scheduleMeetings(int requestedStartTime, int requestedEndTime, int m, List<List<Integer>> existingBookings) {
        // Create a boolean array to track room availability. 
        // We use size 'm + 1' to handle 1-based room IDs directly (indices 1 to m).
        // By default, all values are 'false', meaning we assume rooms are free initially.
        boolean[] isOccupied = new boolean[m + 1]; 

        // Iterate through every existing booking to find conflicts.
        // We use an enhanced for-loop which works efficiently for any List implementation.
        for (var booking : existingBookings) {
            // Extract the room ID (index 0), start time (index 1), and end time (index 2).
            // We use 'get()' because the input is a List<Integer>, not an array.
            int roomID = booking.get(0);
            int start = booking.get(1);
            int end = booking.get(2);

            // Check for time overlap between the existing booking and the requested window.
            // Logic: A conflict exists if the booking starts before the request ends 
            // AND the booking ends after the request starts.
            if (start < requestedEndTime && end > requestedStartTime) {
                // If there is an overlap, mark this room ID as occupied (true).
                isOccupied[roomID] = true;
            }
        }

        // Generate a stream of integers from 1 to m (inclusive) representing all room IDs.
        // This is concise Java Stream API syntax to avoid writing a manual loop.
        return IntStream.rangeClosed(1, m)
                // Filter the stream to keep only room IDs 'i' where isOccupied[i] is false.
                .filter(i -> !isOccupied[i])
                // Convert the primitive 'int' stream to a Stream<Integer> objects.
                .boxed()
                // Collect the results directly into an immutable List (Java 16+ feature).
                .toList();
    }

    // --- Main Method for Testing ---
    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // Test Case 1: Example from Problem Statement
        // Input: m=4, Window=[5, 10]
        // Bookings: Room 1:[1,3,5], Room 1:[1,1,2], Room 2:[2,2,6]
        List<List<Integer>> input1 = new ArrayList<>();
        input1.add(List.of(1, 3, 5));
        input1.add(List.of(1, 1, 2));
        input1.add(List.of(2, 2, 6));
        
        // Expected: [1, 3, 4] (Room 1 is free 5-10, Room 2 overlaps at 5, Rooms 3&4 empty)
        runTest("Example Case", 5, 10, 4, input1, List.of(1, 3, 4));


        // Test Case 2: Boundary/Edge Contact
        // Request: [5, 10]. 
        // Booking A: [0, 5] (Ends exactly at start -> Available)
        // Booking B: [10, 15] (Starts exactly at end -> Available)
        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(List.of(1, 0, 5));   // Room 1: touches start
        input2.add(List.of(2, 10, 15)); // Room 2: touches end
        input2.add(List.of(3, 6, 9));   // Room 3: inside -> Occupied
        
        runTest("Edge Contact", 5, 10, 3, input2, List.of(1, 2));


        // Test Case 3: Large Data Constraints (Performance Check)
        // Simulate 200,000 rooms. We block all even numbered rooms.
        // We expect only odd numbered rooms to be returned.
        int largeM = 10_000; // Using 10k for quick console demo, scalable to 200k
        List<List<Integer>> largeInput = new ArrayList<>();
        List<Integer> expectedLarge = new ArrayList<>();
        
        for (int i = 1; i <= largeM; i++) {
            if (i % 2 == 0) {
                // Block even rooms: [5, 10] overlaps with request [5, 10]
                largeInput.add(List.of(i, 5, 10)); 
            } else {
                expectedLarge.add(i); // Odd rooms remain free
            }
        }

        long t1 = System.currentTimeMillis();
        List<Integer> resultLarge = scheduleMeetings(5, 10, largeM, largeInput);
        long t2 = System.currentTimeMillis();
        
        boolean passLarge = resultLarge.equals(expectedLarge);
        System.out.println("Large Data: " + (passLarge ? "PASS" : "FAIL") + " (Time: " + (t2 - t1) + "ms)");
    }

    // Helper method to run tests and print results
    private static void runTest(String name, int start, int end, int m, List<List<Integer>> bookings, List<Integer> expected) {
        List<Integer> actual = scheduleMeetings(start, end, m, bookings);
        boolean pass = actual.equals(expected);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL [Expected " + expected + " | Got " + actual + "]"));
    }
}
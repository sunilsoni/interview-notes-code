package com.interview.notes.code.year.y2026.march.Hackerank.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms { // Declares the main class that contains our business logic and test cases
    
    static int findMinRooms(List<Meeting> meetings) { // Method takes a list of meetings and returns the integer count of rooms needed
        if (meetings == null || meetings.isEmpty()) return 0; // Guard clause: if the list is empty or null, exactly zero rooms are needed

        meetings.sort(Comparator.comparingInt(Meeting::start)); // Java 8 feature: sorts the meetings chronologically by start time so we process them in order

        var rooms = new PriorityQueue<Integer>(); // Java 21 var: initializes a Min-Heap to track the end times of currently running meetings

        for (var m : meetings) { // Iterates through each chronologically sorted meeting one by one
            if (!rooms.isEmpty() && rooms.peek() <= m.start()) { // Checks if the earliest ending meeting (top of heap) finishes before or exactly when the current meeting starts
                rooms.poll(); // If the room is free, remove that completed meeting's end time from the heap to recycle the room
            } // Closes the if-block condition
            rooms.offer(m.end()); // Reserves a room for the current meeting by recording its end time in the heap
        } // Closes the loop

        return rooms.size(); // The peak number of concurrent rooms used is exactly the size of the heap at the end
    } // Closes the method definition

    public static void main(String[] args) { // Simple main method serving as our custom test execution framework without JUnit

        // Test 1: User provided example [(1,2), (4,6), (2,3), (5,8), (3,7)] - Expected 3 rooms
        runTest("Provided Case", List.of(new Meeting(1, 2), new Meeting(4, 6), new Meeting(2, 3), new Meeting(5, 8), new Meeting(3, 7)), 3); // Executes the standard test case

        // Test 2: Sequential meetings with zero overlap - Expected 1 room
        runTest("No Overlap", List.of(new Meeting(1, 2), new Meeting(2, 3), new Meeting(3, 4)), 1); // Executes edge case where one room is reused constantly

        // Test 3: All meetings happening at the exact same time - Expected 3 rooms
        runTest("All Overlap", List.of(new Meeting(1, 5), new Meeting(1, 5), new Meeting(1, 5)), 3); // Executes edge case checking maximum collision handling

        // Test 4: Large data input generation to ensure no performance timeouts or stack overflows
        var largeList = new ArrayList<Meeting>(); // Java 21 var: creates a dynamic array to hold thousands of meetings
        for (int i = 0; i < 100000; i++) largeList.add(new Meeting(i, i + 2)); // Generates 100,000 meetings where each overlaps only with the immediate next one
        runTest("Large Data (100k items)", largeList, 2); // Tests large dataset; expected 2 rooms since overlap depth never exceeds 2
    } // Closes the main method

    static void runTest(String testName, List<Meeting> input, int expected) { // Helper method to execute logic and print PASS/FAIL status
        var mutableList = new ArrayList<>(input); // Creates a mutable copy of the input list because List.of() is immutable and our logic sorts it
        int result = findMinRooms(mutableList); // Executes our room-finding logic and stores the returned integer
        boolean isPass = result == expected; // Evaluates if the actual result perfectly matches our expected mathematical outcome
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL (Expected: " + expected + ", Got: " + result + ")")); // Prints the final test outcome to the console
    } // Closes the test runner method

    record Meeting(int start, int end) {} // Java 21 feature: creates a highly concise, immutable class to hold start and end times
} // Closes the class
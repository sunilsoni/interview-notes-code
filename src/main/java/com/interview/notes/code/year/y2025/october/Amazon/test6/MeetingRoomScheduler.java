package com.interview.notes.code.year.y2025.october.Amazon.test6;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class MeetingRoomScheduler {
    public static int findMinRooms1(int[][] meetings, int bufferTime) {
        if (meetings == null || meetings.length == 0) return 0;

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // MinHeap to keep track of earliest ending meeting (including buffer)
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        int maxRooms = 0;

        for (int[] meeting : meetings) {
            // Remove meetings that have ended (including buffer time)
            while (!endTimes.isEmpty() && endTimes.peek() <= meeting[0]) {
                endTimes.poll();
            }

            // Add current meeting's end time + buffer
            endTimes.offer(meeting[1] + bufferTime);

            // Current size of heap represents rooms needed at this point
            maxRooms = Math.max(maxRooms, endTimes.size());
        }

        return maxRooms;
    }

    public static int findMinRooms(int[][] meetings, int bufferTime) {
        if (meetings == null || meetings.length == 0) return 0;

        int[] startTimes = new int[meetings.length];
        int[] endTimes = new int[meetings.length];

        // Add buffer time to each end time
        for (int i = 0; i < meetings.length; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1] + bufferTime;  // Add buffer time here
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int currentRooms = 0;
        int maxRooms = 0;
        int startPtr = 0;
        int endPtr = 0;
        int n = meetings.length;

        while (startPtr < n && endPtr < n) {
            if (startTimes[startPtr] < endTimes[endPtr]) {
                currentRooms++;
                startPtr++;
            } else {
                currentRooms--;
                endPtr++;
            }
            maxRooms = Math.max(maxRooms, currentRooms);
        }

        while (startPtr < n) {
            currentRooms++;
            startPtr++;
            maxRooms = Math.max(maxRooms, currentRooms);
        }

        return maxRooms;
    }

    public static int findMinRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;

        int[] startTimes = new int[meetings.length];
        int[] endTimes = new int[meetings.length];

        // Separate start and end times
        for (int i = 0; i < meetings.length; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int currentRooms = 0;
        int maxRooms = 0;
        int startPtr = 0;
        int endPtr = 0;

        while (startPtr < meetings.length) {
            if (startTimes[startPtr] < endTimes[endPtr]) {
                currentRooms++;
                startPtr++;
            } else {
                currentRooms--;
                endPtr++;
            }
            maxRooms = Math.max(maxRooms, currentRooms);
        }

        return maxRooms;
    }

    // Main method to schedule meetings and find minimum rooms required
    public static int findMinRooms1(int[][] meetings) {
        // If no meetings, return 0 rooms needed
        if (meetings == null || meetings.length == 0) return 0;
        
        // Create separate arrays for start and end times to sort independently
        int[] startTimes = new int[meetings.length];
        int[] endTimes = new int[meetings.length];
        
        // Populate start and end time arrays
        for (int i = 0; i < meetings.length; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1];
        }
        
        // Sort start and end times separately for efficient processing
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        
        int rooms = 0;      // Current number of rooms in use
        int maxRooms = 0;   // Maximum rooms needed at any point
        int startPtr = 0;   // Pointer for start times
        int endPtr = 0;     // Pointer for end times
        
        // Process all meetings using two-pointer technique
        while (startPtr < meetings.length) {
            // If next event is a meeting start
            if (startTimes[startPtr] < endTimes[endPtr]) {
                rooms++;        // Need new room
                startPtr++;     // Move to next start time
            }
            // If next event is a meeting end
            else if (startTimes[startPtr] >= endTimes[endPtr]) {
                rooms--;        // Release a room
                endPtr++;       // Move to next end time
            }
            // Keep track of maximum rooms needed
            maxRooms = Math.max(maxRooms, rooms);
        }
        
        return maxRooms;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic overlapping meetings
        int[][] test1 = {{1, 4}, {2, 5}, {3, 6}};
        int expected1 = 3;
        testCase(test1, expected1, "Test 1: Basic overlapping");
        
        // Test Case 2: No overlapping meetings
        int[][] test2 = {{1, 2}, {3, 4}, {5, 6}};
        int expected2 = 1;
        testCase(test2, expected2, "Test 2: No overlapping");
        
        // Test Case 3: Partial overlapping
        int[][] test3 = {{1, 4}, {4, 5}, {2, 3}, {3, 6}};
        int expected3 = 2;
        testCase(test3, expected3, "Test 3: Partial overlapping");
        
        // Test Case 4: Empty input
        int[][] test4 = {};
        int expected4 = 0;
        testCase(test4, expected4, "Test 4: Empty input");
        
        // Test Case 5: Large dataset
        int[][] test5 = generateLargeTestCase(1000);
        testCase(test5, -1, "Test 5: Large dataset (1000 meetings)");
    }
    
    // Helper method to test cases
    private static void testCase(int[][] meetings, int expected, String testName) {
        int result = findMinRooms(meetings);
        if (expected == -1 || result == expected) {
            System.out.println(testName + " - PASS (Result: " + result + ")");
        } else {
            System.out.println(testName + " - FAIL (Expected: " + expected + ", Got: " + result + ")");
        }
    }
    
    // Helper method to generate large test case
    private static int[][] generateLargeTestCase(int size) {
        int[][] meetings = new int[size][2];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int start = rand.nextInt(24);
            meetings[i][0] = start;
            meetings[i][1] = start + rand.nextInt(5) + 1; // Meeting duration 1-5 hours
        }
        return meetings;
    }
}

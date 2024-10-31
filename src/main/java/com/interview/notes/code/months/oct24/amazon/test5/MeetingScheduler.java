package com.interview.notes.code.months.oct24.amazon.test5;

import java.util.Arrays;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), , determine if a person could attend all meetings.
Example 1:
Input: [[0, 30], [5,10], [15, 20]]
Output: false
Example 2:
Input: [[7,10],[2,4]]
Output: true
Example 3:
Input: [[0,30], [45,10], [15,20]]
Output: false
 */
public class MeetingScheduler {

    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler();
        scheduler.runTests();
    }

    // Method to check if a person can attend all meetings
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;  // Overlapping intervals found
            }
        }
        return true;
    }

    // Method to test all cases
    public void runTests() {
        // Test case 1
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Test 1: " + (canAttendMeetings(meetings1) == false ? "PASS" : "FAIL"));

        // Test case 2
        int[][] meetings2 = {{7, 10}, {2, 4}};
        System.out.println("Test 2: " + (canAttendMeetings(meetings2) == true ? "PASS" : "FAIL"));

        // Test case 3 (Invalid interval, [45,10] is reversed)
        int[][] meetings3 = {{0, 30}, {45, 10}, {15, 20}};
        System.out.println("Test 3: " + (canAttendMeetings(meetings3) == false ? "PASS" : "FAIL"));

        // Large test case
        int[][] largeMeetings = new int[100000][2];
        for (int i = 0; i < 100000; i++) {
            largeMeetings[i][0] = i * 10;
            largeMeetings[i][1] = (i * 10) + 9;
        }
        System.out.println("Test 4 (Large Data): " + (canAttendMeetings(largeMeetings) == true ? "PASS" : "FAIL"));

        // Edge test case: No meetings
        int[][] noMeetings = {};
        System.out.println("Test 5 (No meetings): " + (canAttendMeetings(noMeetings) == true ? "PASS" : "FAIL"));
    }
}

package com.interview.notes.code.year.y2026.march.Hackerank.test2;

import java.util.Arrays;

public class MeetingRoomCalculator { // class name based on problem

    static int findMinRooms(int[][] intervals) { // method to calculate rooms
        
        int n = intervals.length; // total number of meetings
        
        int[] start = new int[n]; // array to store start times
        int[] end = new int[n];   // array to store end times
        
        for (int i = 0; i < n; i++) { // loop through all meetings
            start[i] = intervals[i][0]; // extract start time
            end[i] = intervals[i][1];   // extract end time
        }
        
        Arrays.sort(start); // sort start times
        Arrays.sort(end);   // sort end times
        
        int i = 0, j = 0;   // pointers for start and end arrays
        
        int rooms = 0;      // current rooms needed
        int maxRooms = 0;   // max rooms required
        
        while (i < n) { // iterate through all meetings
            
            if (start[i] < end[j]) { // if meeting starts before previous ends
                
                rooms++; // need new room
                maxRooms = Math.max(maxRooms, rooms); // update max rooms
                i++; // move to next start
                
            } else { // meeting ended before next starts
                
                rooms--; // free one room
                j++; // move end pointer
            }
        }
        
        return maxRooms; // return result
    }

    public static void main(String[] args) { // main method for testing
        
        int[][] input = { // test case input
            {1,2}, {4,6}, {2,3}, {5,8}, {3,7}
        };
        
        int expected = 2; // expected output
        
        int result = findMinRooms(input); // call method
        
        if (result == expected) // check result
            System.out.println("PASS"); // print pass
        else
            System.out.println("FAIL: " + result); // print fail
        
        
        // Additional test cases
        
        int[][] test2 = { {1,5}, {2,6}, {3,7} }; // all overlap
        System.out.println(findMinRooms(test2) == 3 ? "PASS" : "FAIL");
        
        int[][] test3 = { {1,2}, {2,3}, {3,4} }; // no overlap
        System.out.println(findMinRooms(test3) == 1 ? "PASS" : "FAIL");
        
        
        // Large data test
        
        int[][] large = new int[10000][2]; // large input
        
        for (int i = 0; i < 10000; i++) { // fill large data
            large[i][0] = i; // start time
            large[i][1] = i + 1; // end time
        }
        
        System.out.println(findMinRooms(large) == 1 ? "PASS" : "FAIL");
    }
}
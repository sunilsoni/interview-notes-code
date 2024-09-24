package com.interview.notes.code.months.sept24.test13;

import java.util.*;

/*
You are given an array schedules representing existing meetings for all employees over the course of an entire day, and an integer length representing the length of a new meeting in minutes.
Each element in schedules is an array, such that schedules[i][j] represents the jth meeting for the ich employee. Each meeting is represented by a pair of integers: [startTime, finishTime] , where each integer represents the number of minutes passed since the beginning of the day. startTime and finishTime do not exceed 24 x 60 -
Your task is to find the earliest possible time when a meeting of length length can be scheduled for all employees. If there is no time block which suits everyone, return -1 .
Note: The new meeting should also fit within the same day, so the finish time for this meeting should not exceed 24 × 60.
Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than O(schedules. lengtl? . max(schedules [i]. length)?) will fit within the execution time limit.
Example
• For
schedules = [
[[60, 150], [180, 240]], [CO, 210], [360, 420]]
]
and length = 120, the output should be solution(schedules, length) = 240.
• Expand to see the example video.
Explanation:
If the new meeting is scheduled to start from minute 240, it will last until minute 360. The interval [240, 360] does not coincide with any other intervals from schedules, so it's possible to schedule this meeting to allow all employees to attend.
A meeting with a duration of 120 minutes that suits the schedule of all employees can't be scheduled earlier, so the answer is 240 .
• For
schedules = [
[[480, 5101],
11375, 40011
]

and length = 180, the output should be solution(schedules, length) = 0
0
240
330
minutes passed since beginning of day
375 400
480
510
180
1
2
Explanation:
• For
If the new meeting is scheduled right at the beginning of the day, then it wouldn't conflict with any other meetings, so the answer is o
schedules = [
LEO, 1439]],
[[О, 1439]],
[[o, 390], [480, 510]]
]


and length = 90, the output should be solution(schedules, length) = -1.
Explanation:
The first two employees are booked for the whole day, so it's not possible to have a meeting with a duration of 90 minutes that all the employees can attend. Thus the answer is -1 .
Input/Output
• [execution time limit] 3 seconds (java)
• [memory limit] 1 GB
• [input] array.array.array.integer schedules
An array of arrays of integer arrays, where each integer array schedules[i][j] contains 2 distinct integers representing the jth meeting booked for the jth employee. Each integer within the array represents the time (in minutes) passed since the beginning of the day.
Guaranteed constraints:
1 ≤ schedules. length ≤ 100,
0 ≤ schedules [i]. length ≤ 100 .
schedules［i］lj］.length=2，
0 以 schedules［i］［j］［0］ s schedules［i］lj］ ［1］ ≤ 1440 .
• [input] integer length
An integer containing the length of the new meeting in minutes.
Guaranteed constraints"
1 ≤ length ≤ 24 * 60
• [output] integer
The earliest possible time when the meeting can be scheduled that would fit the schedules of all employees. The output should be represented as minutes passed since the beginning of the day.

 */
public class MeetingScheduler {

    public static int solution(int[][][] schedules, int length) {
        // Flatten and sort all meetings
        List<int[]> allMeetings = new ArrayList<>();
        for (int[][] employeeSchedule : schedules) {
            allMeetings.addAll(Arrays.asList(employeeSchedule));
        }
        Collections.sort(allMeetings, Comparator.comparingInt(a -> a[0]));

        // Check for available slot from the beginning of the day
        int lastEndTime = 0;
        for (int[] meeting : allMeetings) {
            if (meeting[0] - lastEndTime >= length) {
                return lastEndTime;
            }
            lastEndTime = Math.max(lastEndTime, meeting[1]);
        }

        // Check for slot after the last meeting
        if (1440 - lastEndTime >= length) {
            return lastEndTime;
        }

        return -1; // No suitable slot found
    }

    public static void main(String[] args) {
        // Test Case 1
        int[][][] schedules1 = {
            {{60, 150}, {180, 240}},
            {{0, 210}},
            {{360, 420}}
        };
        int length1 = 120;
        System.out.println("Test Case 1: " + (solution(schedules1, length1) == 240 ? "PASS" : "FAIL"));

        // Test Case 2
        int[][][] schedules2 = {
            {{480, 510}},
            {{375, 400}}
        };
        int length2 = 180;
        System.out.println("Test Case 2: " + (solution(schedules2, length2) == 0 ? "PASS" : "FAIL"));

        // Test Case 3
        int[][][] schedules3 = {
            {{0, 1439}},
            {{0, 1439}},
            {{0, 390}, {480, 510}}
        };
        int length3 = 90;
        System.out.println("Test Case 3: " + (solution(schedules3, length3) == -1 ? "PASS" : "FAIL"));

        // Additional Test Case: Edge case with no meetings
        int[][][] schedules4 = {{}};
        int length4 = 60;
        System.out.println("Test Case 4: " + (solution(schedules4, length4) == 0 ? "PASS" : "FAIL"));

        // Additional Test Case: Meeting exactly fits between two existing meetings
        int[][][] schedules5 = {
            {{0, 100}, {200, 300}}
        };
        int length5 = 100;
        System.out.println("Test Case 5: " + (solution(schedules5, length5) == 100 ? "PASS" : "FAIL"));
    }
}
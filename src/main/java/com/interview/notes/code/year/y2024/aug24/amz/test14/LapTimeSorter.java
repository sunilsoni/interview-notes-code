package com.interview.notes.code.year.y2024.aug24.amz.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

You are a manager of associates at a fulfillment center,
where many hard-working associates work to fulfill orders for Amazon's customers.
Each day, you receive the lists of the timestamps (Integer value)
per Associate for what time they completed each of their tasks (representing individual Amazon.com orders).
One of your responsibilities as a manager is to provide a summary for the day, showing the time each Associate completed their task, sorted in increasing order by time.
Provide a solution that will generate this summary view given the input.
Example
ーーーーーーーー
Input:
Associate_1 -> 1, 5, 7, 9
Associate 2 - > 3, 4, 8
Associate_3 --> 2, 6
Output:
Associate_1:1, Associate_3:2, Associate_2:3, Associate_2:4, Associate_1:5, Associate_3:6, Associate_1:7, Associate_2:8, Associate_1:9

 */
public class LapTimeSorter {

    public static List<Integer> mergeLapTimes(List<List<Integer>> associateTimes) {
        List<Integer> mergedTimes = new ArrayList<>();
        int[] pointers = new int[associateTimes.size()];

        while (true) {
            int minTime = Integer.MAX_VALUE;
            int minIndex = -1;

            // Find the smallest time among all current pointers
            for (int i = 0; i < associateTimes.size(); i++) {
                if (pointers[i] < associateTimes.get(i).size()) {
                    int currentTime = associateTimes.get(i).get(pointers[i]);
                    if (currentTime < minTime) {
                        minTime = currentTime;
                        minIndex = i;
                    }
                }
            }

            // If we didn't find a valid time, we're done
            if (minIndex == -1) {
                break;
            }

            // Add the smallest time to our result and move the pointer
            mergedTimes.add(minTime);
            pointers[minIndex]++;
        }

        return mergedTimes;
    }

    public static void main(String[] args) {
        List<List<Integer>> associateTimes = new ArrayList<>();
        associateTimes.add(Arrays.asList(1, 5, 7, 9));
        associateTimes.add(Arrays.asList(3, 4, 8));
        associateTimes.add(Arrays.asList(2, 6, 10));

        List<Integer> sortedTimes = mergeLapTimes(associateTimes);
        System.out.println("Merged lap times: " + sortedTimes);
    }
}

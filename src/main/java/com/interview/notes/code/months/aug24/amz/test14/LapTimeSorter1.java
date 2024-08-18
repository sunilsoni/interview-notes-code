package com.interview.notes.code.months.aug24.amz.test14;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
public class LapTimeSorter1 {

    public static List<Integer> mergeSortLapTimes(List<List<Integer>> associateTimes) {
        List<Integer> mergedTimes = new ArrayList<>();
        PriorityQueue<LapTime> minHeap = new PriorityQueue<>();

        // Initialize the heap with the first lap time from each associate
        for (int i = 0; i < associateTimes.size(); i++) {
            if (!associateTimes.get(i).isEmpty()) {
                minHeap.offer(new LapTime(associateTimes.get(i).get(0), i, 0));
            }
        }

        // Merge lap times
        while (!minHeap.isEmpty()) {
            LapTime current = minHeap.poll();
            mergedTimes.add(current.time);

            // If there are more lap times for this associate, add the next one to the heap
            if (current.timeIndex + 1 < associateTimes.get(current.associateIndex).size()) {
                minHeap.offer(new LapTime(
                        associateTimes.get(current.associateIndex).get(current.timeIndex + 1),
                        current.associateIndex,
                        current.timeIndex + 1
                ));
            }
        }

        return mergedTimes;
    }

    public static void main(String[] args) {
        List<List<Integer>> associateTimes = new ArrayList<>();
        associateTimes.add(List.of(1, 5, 7, 9));
        associateTimes.add(List.of(3, 4, 8));
        associateTimes.add(List.of(2, 6, 10));

        List<Integer> sortedTimes = mergeSortLapTimes(associateTimes);
        System.out.println("Sorted lap times: " + sortedTimes);
    }

    static class LapTime implements Comparable<LapTime> {
        int time;
        int associateIndex;
        int timeIndex;

        LapTime(int time, int associateIndex, int timeIndex) {
            this.time = time;
            this.associateIndex = associateIndex;
            this.timeIndex = timeIndex;
        }

        @Override
        public int compareTo(LapTime other) {
            return Integer.compare(this.time, other.time);
        }
    }
}

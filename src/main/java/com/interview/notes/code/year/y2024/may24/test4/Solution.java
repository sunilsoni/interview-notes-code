package com.interview.notes.code.year.y2024.may24.test4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] jobs = {{1, 3, 2}, {2, 3, 1}, {5, 3, 1}};
        int maxCpus = 3;
        System.out.println(sol.canExecute(jobs, maxCpus));
    }

    public boolean canExecute(int[][] jobs, int maxCpus) {
        List<int[]> events = new ArrayList<>();
        for (int[] job : jobs) {
            int start = job[0];
            int duration = job[1];
            int cpus = job[2];
            events.add(new int[]{start, cpus});
            events.add(new int[]{start + duration, -cpus});
        }
        Collections.sort(events, (a, b) -> a[0] - b[0]);
        for (int[] event : events) {
            maxCpus -= event[1];
            if (maxCpus < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canExecuteWithHeap(int[][] jobs, int maxCpus) {
        List<int[]> jobList = new ArrayList<>();
        Collections.addAll(jobList, jobs);
        Collections.sort(jobList, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int availableCpus = maxCpus;
        for (int[] job : jobList) {
            int startTime = job[0];
            int duration = job[1];
            int requiredCpus = job[2];
            while (!pq.isEmpty() && pq.peek()[0] <= startTime) {
                int[] finishedJob = pq.poll();
                availableCpus += finishedJob[1];
            }
            if (requiredCpus > availableCpus) {
                return false;
            }
            pq.offer(new int[]{startTime + duration, requiredCpus});
            availableCpus -= requiredCpus;
        }
        return true;
    }
}

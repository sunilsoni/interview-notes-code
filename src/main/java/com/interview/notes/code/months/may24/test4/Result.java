package com.interview.notes.code.months.may24.test4;

import java.util.Arrays;

public class Result {

    public static int getMaximumJobs1(int n, int m, int k) {
        if (n == 1) return m; // If there's only one processor, it gets all the jobs.

        int jobsPerProcessor = m / n; // Base number of jobs per processor
        int extraJobs = m % n; // Remaining jobs after initial distribution

        // Calculate minimum and maximum jobs possible on any processor
        int minJobs = jobsPerProcessor;
        int maxJobs = jobsPerProcessor + (extraJobs > 0 ? 1 : 0);

        // To keep the distribution balanced, difference between any two processors can't be more than 1
        if (k == 1 || k == n) {
            // If k is the first or last processor, it can hold up to maxJobs because it only has one neighboring processor
            return maxJobs;
        } else {
            // If k is not on the boundary, it can also hold up to maxJobs, since both neighbors can have minJobs or one can have maxJobs
            return maxJobs;
        }
    }

    public static int getMaximumJobs3(int n, int m, int k) {
        // Base number of jobs per processor
        int base = m / n;

        // Extra jobs that need to be distributed
        int extra = m % n;

        // Creating array to simulate job distribution
        int[] jobs = new int[n];
        Arrays.fill(jobs, base);
        for (int i = 0; i < extra; i++) {
            jobs[i]++;
        }

        // Function to calculate maximum jobs for k-th processor maintaining balance
        int maxJobs = jobs[k - 1]; // k-1 for 0-based index

        // Checking if increasing jobs at k-th processor keeps the schedule balanced
        for (int j = Math.max(0, k - 2); j <= Math.min(n - 1, k); j++) {
            if (j > 0 && Math.abs(jobs[j] - jobs[j - 1]) > 1) {
                return maxJobs; // Current distribution is max possible while balanced
            }
            if (j < n - 1 && Math.abs(jobs[j] - jobs[j + 1]) > 1) {
                return maxJobs;
            }
        }

        // Try to maximize the job count at k while keeping balance
        while (true) {
            jobs[k - 1]++;
            boolean valid = true;

            for (int j = Math.max(0, k - 2); j <= Math.min(n - 1, k); j++) {
                if (j > 0 && Math.abs(jobs[j] - jobs[j - 1]) > 1) {
                    valid = false;
                    break;
                }
                if (j < n - 1 && Math.abs(jobs[j] - jobs[j + 1]) > 1) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                jobs[k - 1]--; // Revert the last increment
                break;
            }
            maxJobs = jobs[k - 1];
        }

        return maxJobs;
    }


    public static int getMaximumJobs4(int n, int m, int k) {
        int baseJobs = m / n;
        int extraJobs = m % n;

        // Arrays to simulate job counts on each processor
        int[] jobs = new int[n];
        Arrays.fill(jobs, baseJobs);

        // Distribute extra jobs from the 1st processor onwards
        for (int i = 0; i < extraJobs; i++) {
            jobs[i]++;
        }

        // Start by assuming k-th processor can take the maximum possible while still balanced
        int maxPossibleForK = jobs[k - 1]; // Adjust for 0-based index

        // Check if increasing jobs at k-th processor is possible without violating balance
        int leftNeighbor = k > 1 ? jobs[k - 2] : Integer.MAX_VALUE; // Left neighbor of k
        int rightNeighbor = k < n ? jobs[k] : Integer.MAX_VALUE; // Right neighbor of k

        // Determine the maximum jobs that can be assigned to k while maintaining balance
        while (maxPossibleForK < m && Math.abs(maxPossibleForK + 1 - leftNeighbor) <= 1 && Math.abs(maxPossibleForK + 1 - rightNeighbor) <= 1) {
            maxPossibleForK++;
        }

        return maxPossibleForK;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumJobs(5, 11, 5)); // Example Case 0
        System.out.println(getMaximumJobs(5, 16, 2)); // Example Case 1
    }

    public static int getMaximumJobs(int n, int m, int k) {
        // Calculate the average number of jobs per processor (excluding the most efficient one)
        int avgJobs = (m - 1) / (n - 1);

        // Ensure each processor has at least one job
        avgJobs = Math.max(avgJobs, 1);

        // Calculate the remaining jobs to be scheduled on the most efficient processor
        int remainingJobs = m - (avgJobs * (n - 1));

        // Check if the remaining jobs can be scheduled on the kth processor while maintaining balance
        if (remainingJobs <= avgJobs + 1) {
            return remainingJobs;
        } else {
            // If not, return the maximum allowed jobs on the kth processor (avgJobs + 1)
            return avgJobs + 1;
        }
    }
}

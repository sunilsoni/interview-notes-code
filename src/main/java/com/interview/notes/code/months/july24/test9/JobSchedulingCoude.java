package com.interview.notes.code.months.july24.test9;

public class JobSchedulingCoude {
    public static void main(String[] args) {
        System.out.println("Test Case 1 Expected: 1, Got: " + getMaximumJobs(1, 1, 1));
        System.out.println("Test Case 2 Expected: 1, Got: " + getMaximumJobs(5, 5, 3));
        System.out.println("Test Case 3 Expected: 2, Got: " + getMaximumJobs(5, 10, 3));
        System.out.println("Test Case 4 Expected: 5, Got: " + getMaximumJobs(5, 15, 3));
        System.out.println("Test Case 4 Expected: 4, Got: " + getMaximumJobs(5, 16, 2));
        System.out.println("Test Case 5 Expected: 5, Got: " + getMaximumJobs(100, 500, 50));
        System.out.println("Test Case 6 Expected: 4, Got: " + getMaximumJobs(5, 11, 5));
        System.out.println("Test Case 7 Expected: 4, Got: " + getMaximumJobs(5, 16, 1));
    }

    public static int getMaximumJobs(int n, int m, int k) {
        // Base case: if there are fewer jobs than processors, return 1
        if (m < n) return 1;

        // Calculate the minimum number of jobs per processor
        int minJobsPerProcessor = m / n;

        // Calculate the remaining jobs after distributing minJobsPerProcessor to each
        int remainingJobs = m % n;

        // If k is within the range of processors that get an extra job
        if (k <= remainingJobs) {
            return minJobsPerProcessor + 1;
        }

        // Calculate the maximum possible jobs for kth processor
        int maxPossible = minJobsPerProcessor + 1;

        // Check if assigning maxPossible to kth processor keeps the schedule balanced
        int jobsBeforeK = (k - 1) * minJobsPerProcessor + Math.min(k - 1, remainingJobs);
        int jobsAfterK = (n - k) * minJobsPerProcessor + Math.max(0, remainingJobs - (k - 1));

        if (m - jobsBeforeK - jobsAfterK <= maxPossible &&
                maxPossible - minJobsPerProcessor <= 1 &&
                maxPossible - (minJobsPerProcessor + (remainingJobs > 0 ? 1 : 0)) <= 1) {
            return maxPossible;
        }

        // If maxPossible doesn't work, return minJobsPerProcessor
        return minJobsPerProcessor;
    }

}

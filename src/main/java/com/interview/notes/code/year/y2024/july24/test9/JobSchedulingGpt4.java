package com.interview.notes.code.year.y2024.july24.test9;

public class JobSchedulingGpt4 {
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

    /*
     * Complete the 'getMinimumJobs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER n
     * 2. INTEGER m
     * 3. INTEGER k
     */
    public static int getMaximumJobs(int n, int m, int k) {
        // Distribute one job to each processor
        m -= n;

        // The number of jobs that can be added to each processor
        // in a balanced manner is the total remaining jobs divided by n
        int base = m / n;

        // The remaining jobs after the base distribution
        int extra = m % n;

        // Calculate the distance of k from the ends
        int leftDist = k - 1;
        int rightDist = n - k;

        // Calculate the maximum additional jobs that can be added to the kth processor
        int maxAdditionalJobs = base + (extra > leftDist ? 1 : 0) + (extra > rightDist ? 1 : 0);

        // The total number of jobs for the kth processor is the base plus the additional jobs
        // and plus one job since we initially distributed one job to each processor
        return 1 + base + maxAdditionalJobs;
    }

}

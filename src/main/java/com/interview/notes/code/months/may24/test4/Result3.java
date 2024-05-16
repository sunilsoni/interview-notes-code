package com.interview.notes.code.months.may24.test4;

class Result3 {

    public static int getMaximumJobs(int n, int m, int k) {
        // Edge case: If there are fewer jobs than processors, distribute jobs evenly
        if (m <= n) {
            return (k <= m) ? 1 : 0; // If k is within the range of jobs, kth processor gets 1 job, else 0
        }

        // Calculate minimum number of jobs each processor must have
        int minJobsPerProcessor = m / n;
        // Calculate number of processors that will have one extra job
        int processorsWithExtraJob = m % n;

        // Check if the kth processor has an extra job
        boolean kHasExtraJob = k <= processorsWithExtraJob;

        // Calculate maximum number of jobs for the kth processor
        int maxJobsOnKthProcessor = kHasExtraJob ? minJobsPerProcessor + 1 : minJobsPerProcessor;

        return maxJobsOnKthProcessor;
    }

    public static int getMaximumJobs22(int n, int m, int k) {
        // Calculate the maximum number of jobs that can be evenly distributed
        int maxJobs = m / n;

        // Calculate how many processors can have one extra job
        int extraProcessors = m % n;

        // If the kth processor is one of the extra processors, add one job to its maximum
        if (k <= extraProcessors) {
            return maxJobs + 1;
        } else {
            return maxJobs;
        }
    }

    public static int getMaximumJobs11(int n, int m, int k) {
        int avg = m / n;
        int extra = m % n;
        int jobsOnKthProcessor = 0;

        if (k <= extra) {
            jobsOnKthProcessor = avg + 1;
        } else {
            jobsOnKthProcessor = avg;
        }

        return Math.min(jobsOnKthProcessor, avg + 1);
    }

    public static int getMaximumJobs9(int n, int m, int k) {
        int jobsPerProcessor = m / n;
        int remainingJobs = m % n;

        int maxJobs = jobsPerProcessor;
        if (remainingJobs >= k) {
            maxJobs++;
        }

        return Math.min(maxJobs, m - (n - 1));
    }

    public static int getMaximumJobs8(int n, int m, int k) {
        int avg = m / n;
        int extra = m % n;
        int jobsOnKthProcessor = 0;

        if (k <= extra) {
            jobsOnKthProcessor = avg + 1;
        } else {
            jobsOnKthProcessor = avg;
        }

        return Math.min(jobsOnKthProcessor, avg + 1);
    }

    public static int getMaximumJobs7(int n, int m, int k) {
        int minJobs = m / n;
        int remainingJobs = m % n;
        int maxJobs = minJobs + (k <= remainingJobs ? 1 : 0);
        return Math.min(minJobs, maxJobs);
    }


    public static int getMaximumJobs6(int n, int m, int k) {
        if (n == 1) return m; // All jobs go to the single processor

        int base = m / n; // Base number of jobs every processor can have
        int leftover = m % n; // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor with balance check
        int maxJobs = base;  // Start with base number
        if (k <= leftover || k == n && leftover > 0) {
            maxJobs++;  // Add 1 if k-th processor falls within leftover range or is last with leftover
        }

        return maxJobs;
    }

    public static int getMaximumJobs5(int n, int m, int k) {
        int minJobs = m / n;
        int remainingJobs = m % n;
        int maxJobs = minJobs + (k <= remainingJobs ? 1 : 0);
        return maxJobs;
    }

    public static int getMaximumJobs4(int n, int m, int k) {
        if (n == 1) return m; // All jobs go to the single processor

        int base = m / n; // Base number of jobs every processor can have
        int leftover = m % n; // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor with balance check
        int maxJobs = base;  // Start with base number

        if (k <= leftover) {
            maxJobs++;  // Add 1 if k-th processor falls within leftover range (standard case)
        } else if (leftover > 0) {
            maxJobs++;  // Add 1 if there are leftover jobs and k-th processor is NOT last one
        }

        return maxJobs;
    }

    public static int getMaximumJobs3(int n, int m, int k) {
        if (n == 1) return m; // All jobs go to the single processor

        int base = m / n; // Base number of jobs every processor can have
        int leftover = m % n; // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor with priority for last one
        int maxJobs = base;  // Start with base number
        if (k == n) {
            maxJobs = Math.min(base + leftover, m);  // Last processor gets leftover (or all jobs)
        } else if (k <= leftover) {
            maxJobs++;  // Add 1 if k-th processor falls within leftover range (standard case)
        }

        return maxJobs;
    }

    public static int getMaximumJobs2(int n, int m, int k) {
        // Calculate the minimum number of jobs per processor to guarantee a balanced schedule
        int minJobsPerProcessor = m / n;
        // Calculate the remaining jobs after evenly distributing the minimum number of jobs
        int remainingJobs = m % n;

        // The kth processor can have one additional job if the k-th processor is among the first `remainingJobs` processors
        int maxJobsOnKthProcessor = minJobsPerProcessor + (k <= remainingJobs ? 1 : 0);

        return maxJobsOnKthProcessor;
    }


    public static int getMaximumJobs1(int n, int m, int k) {
        // Calculate the minimum number of jobs per processor to guarantee a balanced schedule
        int minJobsPerProcessor = m / n;
        // Calculate the remaining jobs after evenly distributing the minimum number of jobs
        int remainingJobs = m % n;

        // The kth processor can have one additional job if it is within the remainingJobs processors
        int maxJobsOnKthProcessor = minJobsPerProcessor + (remainingJobs >= k ? 1 : 0);

        return maxJobsOnKthProcessor;
    }


    public static void main(String[] args) {
        System.out.println("Maximum jobs on the k-th processor for Example 1 (n=5, m=11, k=5): " + getMaximumJobs(5, 11, 5)); // Should output 3
        System.out.println("Maximum jobs on the k-th processor for Example 2 (n=5, m=16, k=2): " + getMaximumJobs(5, 16, 2)); // Should output 4
    }
}

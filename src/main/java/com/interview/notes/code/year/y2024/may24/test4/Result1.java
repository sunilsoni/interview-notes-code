package com.interview.notes.code.year.y2024.may24.test4;

class Result1 {

    public static int getMaximumJobs11(int n, int m, int k) {
        if (n == 1) return m; // All jobs go to the single processor

        int base = m / n; // Base number of jobs every processor can have
        int leftover = m % n; // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor with balance check
        int maxJobs = base;  // Start with base number
        if (k <= leftover) {
            maxJobs++;  // Add 1 if k-th processor falls within leftover range
        } else if (k == n && leftover > 0) {
            maxJobs++;  // Special case: last processor can take extra job if leftover exists
        }

        return maxJobs;
    }

    public static int getMaximumJobs(int n, int m, int k) {
        if (n == 1) return m; // All jobs go to the single processor

        int base = m / n; // Base number of jobs every processor can have
        int leftover = m % n; // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor with balance check
        int maxJobs = base;  // Start with base number

        if (k <= leftover || k == n) {
            maxJobs++;  // Add 1 if k-th processor falls within leftover range or is the last one
        }

        return maxJobs;
    }

    public static int getMaximumJobs2(int n, int m, int k) {
        if (n == 1) return m;  // All jobs to the single processor

        int base = m / n;  // Base number of jobs every processor can have
        int leftover = m % n;  // Jobs that are left after evenly distributing

        // Calculate max jobs on the most efficient processor
        int maxJobs = base + (k <= leftover ? 1 : 0);

        return maxJobs;
    }

    public static int getMaximumJobs1(int n, int m, int k) {
        int base = m / n;
        int leftover = m % n;
        int maxJobs = base + (k <= leftover ? 1 : 0);
        return maxJobs;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumJobs(5, 11, 5)); // Example Case 0
        System.out.println(getMaximumJobs(5, 16, 2)); // Example Case 1
    }
}

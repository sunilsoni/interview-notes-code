package com.interview.notes.code.year.y2024.may24.test4;

class Result2 {

    public static int getMaximumJobs(int n, int m, int k) {
        int base = m / n;  // Basic number of jobs each processor can have
        int leftover = m % n;  // Extra jobs to distribute

        // Determine basic jobs on k-th processor
        int jobsOnK = base + (k <= leftover ? 1 : 0);

        // Since k-th processor is efficient, check if we can adjust jobs to give it more:
        // Check the difference between jobs on neighboring processors.
        // We can possibly adjust one job from a neighboring processor to k-th if it won't break the balance.
        if (jobsOnK < m) {
            if ((k > 1 && (jobsOnK + 1) - (base + ((k - 1) <= leftover ? 1 : 0)) <= 1) ||
                    (k < n && (jobsOnK + 1) - (base + ((k + 1) <= leftover ? 1 : 0)) <= 1)) {
                jobsOnK++;
            }
        }

        return jobsOnK;
    }

    public static void main(String[] args) {
        System.out.println("Maximum jobs on the k-th processor for Example 1 (n=5, m=11, k=5): " + getMaximumJobs(5, 11, 5)); // Should output 4
        System.out.println("Maximum jobs on the k-th processor for Example 2 (n=5, m=16, k=2): " + getMaximumJobs(5, 16, 2)); // Should output 4
    }
}

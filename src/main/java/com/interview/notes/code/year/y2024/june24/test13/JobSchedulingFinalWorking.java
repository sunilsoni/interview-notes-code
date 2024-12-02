package com.interview.notes.code.year.y2024.june24.test13;

/*
22. Job Scheduling
There are m jobs to schedule on n processors. A schedule is balanced if the difference between the number of jobs scheduled on any two neighboring processors does not exceed 1.
The kth processor is the most efficient, and thus, the maximum number of jobs should be scheduled on that processor. Find the maximum number of jobs that can be scheduled on the kth processor, such that the overall schedule remains balanced.
Note: Each processor must have at least one job scheduled.
Example
Consider n= 5, m = 11, k= 3.
Consider the following job schedules:
Schedule
Is balanced?
Jobs scheduled
on the kth processor
[1, 2, 3, 4, 11
No

[1, 2, 3, 3, 2]
Yes
3
[2, 2,2, 2, 31
Yes
2
[2, 2, 3, 2, 21
Yes
3
[1, 1, 7, 1, 1]
No
-
[4, 3, 2, 1, 11
Yes
2
In each schedule, there are 11 jobs across 5 processors. k assumes 1-based indexing.
The 1st schedule is not balanced because the 5th processor has 1 job scheduled, while the 4th processor has 4 jobs scheduled, their difference is 3, which exceeds 1.
The 5th schedule is not balanced because the difference between the 2nd and 3rd, and between 3rd and 4th processors is more than 1.
Amongst all balanced schedules, the maximum number of jobs that can be scheduled on the 3rd processor is 3. So, the answer is 3.
Function Description
Complete the function getMaximum/obs in the editor below.
getMaximum/obs has the following parameters:
int n: the number of processors int m: the number of jobs
int k: the 1-based index of the most efficient processor
Returns
int: the maximum number of jobs that can be scheduled on the kth processor maintaining a balanced schedule

Returns
int: the maximum number of jobs that can be scheduled on the kth processor maintaining a balanced schedule
Constraints
• 1 ≤ n≤ 105
• nsm≤ 109
• 15k≤n
• Input Format for Custom Testing The first line contains an integer, n.
The second line contains an integer, m.
The third line contains an integer, k.
• Sample Case 0
Sample Input 0
STDIN
FUNCTION
5
11
5
n = 5
m = 11
k = 5
Sample Output 0
4
Explanation
Given n= 5, m = 11, k= 5.
One optimal job schedule is [1, 1, 2, 3, 4].
• Sample Case 1
Sample Input 1
STDIN
FUNCTION
5
16
2
→
→
n = 5
m = 16
k = 2
Sample Output 1
4
Explanation
Given n= 5, m = 16, k= 2.
One optimal job schedule is [4, 4, 3, 3, 2].

 */
//ALL Test Case are Passing
class JobSchedulingFinalWorking {
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

    //ALL Test Case are Passing
    public static int getMaximumJobs(int n, int m, int k) {
        // Subtract one job from each processor
        m -= n;
        // `k` is 1-based indexing
        k -= 1;

        // Initial set of variables
        int left = k;
        int right = n - k - 1;

        int low = 1, high = m + 1;

        // Binary search to find the maximum number of jobs on processor `k`
        while (low < high) {
            int mid = low + (high - low) / 2;
            long total_jobs = mid;

            // Sum of arithmetic progression on the left side of `k`
            total_jobs += (left >= mid) ? (long) (mid - 1) * mid / 2 : (long) left * (2 * mid - left - 1) / 2;
            // Sum of arithmetic progression on the right side of `k`
            total_jobs += (right >= mid) ? (long) (mid - 1) * mid / 2 : (long) right * (2 * mid - right - 1) / 2;

            if (total_jobs <= m) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low - 1 + 1; // add 1 because we initially subtracted 1 job from each processor
    }
}
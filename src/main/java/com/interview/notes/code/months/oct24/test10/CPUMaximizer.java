package com.interview.notes.code.months.oct24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
FINAL WORKING:


A number of tasks need to be distributed among servers. All tasks are assigned to their first server until it reaches capacity. They want to utilize the CPU as much as possible. Determine the maximum units of capacity the first server can process. Any process is only run once.
Example
requirements = [15, 12, 3, 7, 8]
processingCapacity = 18
There are two groups that sum to processingCapacity = 18: [15, 3] and [8, 7, 3]. Either group requires full capacity, which is the perfect
solution. Return 18.
Function Description
Complete the maximizeCPU function in the editor below.
maximizeCPU has two parameters:
int requirements[n]: the processing requirements of each task processingCapacity: the available processing capacity
Returns
int: the maximum processing requirements that can be served
Constraints
• 1 ≤n≤ 42
• 1 ≤ processingCapacity ≤ 10°
• 1 ≤ requirements[i] ≤ 10°
• Input Format For Custom Testing
Locked stub code in the editor reads the following input from stdin and passes it to the function:
The first line contains an integer, n, the number of elements in requirements.
Each line i of the n subsequent lines contains an integer, requirements[i].
The last line contains an integer, processingCapacity.
• Sample Case 0
Sample Input 0
STDINI
Function
21
→ requirements [] size n = 3
→ requirements [] = [2, 9, 71
15
Sample Output 0
11
→ processingCapacity = 15

Sample Output 0
11
Explanation 0
All possible combinations of requirements and their sums are shown.
Items = 0
0: 0
Items = 1
(2): 2, (7): 7, (9): 9
Items = 2
(2, 7): 9, (2, 9): 11, (7, 9): 16
Items = 3
(2, 7, 9): 18
The highest usage that is within the limit of processingCapacity = 15 is 2 + 9 = 11.
• Sample Case 1
Sample Input 1
STDIN
Function
4
7
6
9
11
25
→ requirements[] size n = 4
→ requirements [] = [7, 6, 9, 11]
→ processingCapacity = 25
Sample Output 1
24
Explanation
All possible combinations of requirements and their sums are shown.
Items = 0
0: 0
Items = 1
(6): 6, (7): 7, (9): 9, (11) : 11
Items = 2
(6, 7): 13, (6, 9): 15, (6, 11): 17, (7, 9): 16, (7, 11): 18, (9, 11): 20
Items = 3
(6, 7, 9): 22, (6, 7, 11): 24, (6, 9, 11): 26, (7, 9, 11): 27
Items = 4
(6, 7, 9, 11) : 33
/ *
* Complete the 'maximizeCPU' function below.
*
* The function is expected to return an INTEGER.
* The function accepts following parameters:
* 1. INTEGER_ARRAY requirements
* 2. INTEGER processingCapacity
*/


public class CPUMaximizer {

    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        int n = requirements.size();
        int[] dp = new int[processingCapacity + 1];

        for (int req : requirements) {
            for (int j = processingCapacity; j >= req; j--) {
                dp[j] = Math.max(dp[j], dp[j - req] + req);
            }
        }

        return dp[processingCapacity];
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(Arrays.asList(15, 12, 3, 7, 8), 18, 18);
        runTestCase(Arrays.asList(2, 9, 7), 15, 11);
        runTestCase(Arrays.asList(7, 6, 9, 11), 25, 24);

        // Large input test case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            largeInput.add(i);
        }
        runTestCase(largeInput, 100000, 100000);
    }

    private static void runTestCase(List<Integer> requirements, int processingCapacity, int expected) {
        int result = maximizeCPU(requirements, processingCapacity);
        System.out.println("Input: " + requirements + ", Capacity: " + processingCapacity);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println();
    }
}

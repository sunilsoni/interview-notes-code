package com.interview.notes.code.year.y2024.april24.test6;

import java.util.ArrayList;
import java.util.List;

//WORKING

/**
 * Code Question 1
 * AWS provides scalable systems. A set of n servers are used for horizontally scaling an application. The goal is to have the computational power of the servers in non-decreasing order. To do so, you can increase the computational power of each server in any contiguous segment by x. Choose the values of x such that afte the computational powers are in non-decreasing order, the sum of the x values is minimum.
 * Example
 * There are n = 5 servers and their computational power = [3, 4, 1, 6, 21.
 * 3
 * 2
 * Add 3 units to the subarray (2, 4) and 4 units to the subarray (4, 4). The final arrangement of the servers is: [3, 4, 4, 9, 91. The answer is 3 + 4 = 7.
 * 3
 * 4
 * +3
 * 9
 * +3
 * 5
 * +3
 * 3
 * 9
 * Function Description
 * Complete the function makePowerNonDecreasing in the editor below.
 * makePowerNonDecreasing has the following parameter(s): int powerin]: the computational powers of n different servers
 * Returns
 * int: the minimum possible sum of integers required to make the array non-decreasing
 * Constraints
 * • 1 5ns 105
 * • 1 ≤ power[i] ≤ 109
 * • Input Format For Custom Testing
 * The first line contains an integer, n, denoting the number of elements in power.
 * Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer describing power;.
 * <p>
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 3
 * 3
 * 2
 * 1
 * power [] size n = 3
 * power = [3, 2, 1]
 * Sample Output
 * 2
 * Explanation
 * Add 1 unit to subarray (1,2) and 1 unit to subarray (2, 2). The final arrangement of servers is [3, 3, 3].
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * 4
 * 3
 * 5
 * FUNCTION
 * - -------
 * powerl] size n = 4
 * power = [3, 5, 2, 31
 * 3
 * Sample Output
 * 3
 * Explanation
 * Add 3 units to subarray (2, 3). The final arrangement of servers is [3, 5, 5, 6].
 */
class MakePowerNonDecreasing {


    public static void main(String[] args) {
        // Example test case 1
        List<Integer> power1 = new ArrayList<>();
        power1.add(3);
        power1.add(2);
        power1.add(1);
        System.out.println(makePowerNonDecreasing(power1)); // Output should be 2

        // Example test case 2
        List<Integer> power2 = new ArrayList<>();
        power2.add(3);
        power2.add(5);
        power2.add(2);
        power2.add(3);
        System.out.println(makePowerNonDecreasing(power2)); // Output should be 3


        // Example test case 3
        List<Integer> power3 = new ArrayList<>();
        power3.add(4);
        power3.add(3);
        power3.add(5);
        power3.add(2);
        power3.add(3);
        System.out.println(makePowerNonDecreasing(power3)); // Output should be 3

        // Example test case4
        List<Integer> power4 = new ArrayList<>();
        power4.add(3);
        power4.add(3);
        power4.add(2);
        power4.add(1);
        System.out.println(makePowerNonDecreasing(power4)); // Output should be 2
        // Additional test cases
        // ... Add more test cases as needed
    }

    /*
     * Complete the 'makePowerNonDecreasing' function below.
     *
     * The function is expected to return a long.
     * The function accepts an integer array 'power' as parameter.
     */
    public static long makePowerNonDecreasing(List<Integer> power) {
        long sum = 0;
        int n = power.size();

        // Input validation (optional)
        if (n < 1 || n > 100000 || power.stream().anyMatch(val -> val < 1 || val > 1000000000)) {
            throw new IllegalArgumentException("Invalid input size or power values");
        }

        for (int i = 1; i < n; i++) {
            // Check if current element is less than the previous one
            if (power.get(i) < power.get(i - 1)) {
                // Calculate the difference needed to make it non-decreasing
                int diff = power.get(i - 1) - power.get(i);
                sum += diff;
            }
        }

        return sum;
    }
}

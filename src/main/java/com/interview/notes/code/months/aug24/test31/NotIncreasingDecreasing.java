package com.interview.notes.code.months.aug24.test31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Not increasing, not decreasing
You are given an array consisting of distinct integers. Print the minimum number of elements to be removed such that no three consecutive elements in the array are either increasing or decreasing.
Input
The first line of input contains an integer n, representing the size of the array.
The second line of input contains n space-separated integers, representing the array elements.
Output
Print the minimum number of elements to be removed such that no three consecutive elements in the array are either increasing or decreasing.
Constraints
1 <=n <= 104
Example #1
Input
5
12412
Output
1
Explanation: We need to remove one element (4). {1, 2,4, 1, 2} -> {1, 2, 1, 2}. There are no three consecutive elements in the array are either increasing or decreasing.
 */
class NotIncreasingDecreasing {

    public static int solve(List<Integer> ar) {
        int n = ar.size();
        int removalCount = 0;

        // Convert the list to an ArrayList to allow removal
        List<Integer> modifiableList = new ArrayList<>(ar);

        for (int i = 1; i < n - 1; ) {
            int a = modifiableList.get(i - 1);
            int b = modifiableList.get(i);
            int c = modifiableList.get(i + 1);

            if ((a < b && b < c) || (a > b && b > c)) {
                // We can choose to remove either b or c
                if (i + 2 < n && ((b < c && c < modifiableList.get(i + 2)) || (b > c && c > modifiableList.get(i + 2)))) {
                    // Remove c
                    modifiableList.remove(i + 1);
                } else {
                    // Remove b
                    modifiableList.remove(i);
                }
                n--; // The size of the list has reduced
                removalCount++;
            } else {
                i++;
            }
        }
        return removalCount;
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> testCase1 = Arrays.asList(1, 2, 4, 1, 2);
        System.out.println(solve(testCase1)); // Expected Output: 1

        // Example 2
        List<Integer> testCase2 = Arrays.asList(1, 2, 3, 5);
        System.out.println(solve(testCase2)); // Expected Output: 2

        // You can add more test cases to verify correctness
    }
}

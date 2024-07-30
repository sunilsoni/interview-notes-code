package com.interview.notes.code.months.july24.test12;

import java.util.Arrays;
import java.util.List;

/*
WORKING FINE:

Balanced Array
Description
Given an array of numbers, find the index of the smallest array element (the pivot), for which the sums of all elements to the left and to the right are equal.
The array may not be reordered.
Example arr=[1,2,3,4,6]
• the sum of the first three elements, 1+2+3=6. The value of the last element is 6.
• Using zero based indexing, arr[3]=4 is the pivot between the two subarrays.
• The index of the pivot is 3.
Function Description
Complete the function balancedSum in the editor below.
balancedSum has the following parameters): int arrn]: an array of integers
Returns:
int: an integer representing the index of the pivot


Constraints
• 3 <n ≤ 105
• 1 ≤ arrli] ≤ 2 × 10*, where 0 ≤ i < n
• It is guaranteed that a solution always exists.
• Input Format for Custom Testing
Input from stdin will be processed as follows and passed to the function.
The first line contains an integer n, the size of the array arr.
Each of the next n lines contains an integer, arr[il, where O≤i<n.
• Sample Case 0
Sample Input 0
STDIN
Function Parameters
4
1
2
3
3
arr[] size n = 4
arr = [1, 2, 3, 3]
Sample Output 0
2

Explanation 0
• The sum of the first two elements, 1+2=3. The val of the last element is 3.
• Using zero based indexing, arr[2]=3 is the pivot between the two subarrays.
• The index of the pivot is 2.
• Sample Case 1
Sample Input 1
STDIN

3
arr[] size n = 3
1
arr = [1, 2, 1]
2

1

Function Parameters
Sample Output 1
1
Explanation 1
• The first and last elements are equal to 1.
• Using zero based indexing, arr[1]=2 is the pivot between the two subarrays.
• The index of the pivot is 1.
 */
class Result {

    public static int balancedSum(List<Integer> arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (leftSum == totalSum - leftSum - arr.get(i)) {
                return i; // Return the pivot index
            }
            leftSum += arr.get(i);
        }

        throw new IllegalStateException("Pivot not found, but it is guaranteed to exist.");
    }
}

public class BalancedArray {

    public static void main(String[] args) {
        // Test case 1
        List<Integer> arr1 = Arrays.asList(1, 2, 3, 4, 6);
        System.out.println("The pivot index for test case 1 is: " + Result.balancedSum(arr1));

        // Test case 2
        List<Integer> arr2 = Arrays.asList(1, 2, 3, 3);
        System.out.println("The pivot index for test case 2 is: " + Result.balancedSum(arr2));

        // Test case 3
        List<Integer> arr3 = Arrays.asList(1, 2, 1);
        System.out.println("The pivot index for test case 3 is: " + Result.balancedSum(arr3));

        // Additional Test case 4
        List<Integer> arr4 = Arrays.asList(2, 7, 4, 5, 6);
        System.out.println("The pivot index for test case 4 is: " + Result.balancedSum(arr4));

        // Additional Test case 5
        List<Integer> arr5 = Arrays.asList(10, 5, 1, 7, 6);
        System.out.println("The pivot index for test case 5 is: " + Result.balancedSum(arr5));
    }
}

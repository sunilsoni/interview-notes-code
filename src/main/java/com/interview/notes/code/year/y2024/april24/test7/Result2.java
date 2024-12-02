package com.interview.notes.code.year.y2024.april24.test7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Code Question 2
 * In an Amazon coding marathon, the following challenge was given.
 * The uniqueness of an array of integers is defined as the number of distinct elements present. For example, the uniqueness of [1, 5, 2, 1, 3, 5] is 4, element values 1, 2, 3, and 5. For an array arr of n integers, the uniqueness values of its subarrays is generated and stored in another array, call it subarray_uniqueness for discussion. Find the median of the generated array subarray_uniqueness.
 * Notes
 * 1. The median of a list is defined as the middle value of the list when it is sorted in non-decreasing order. If there are multiple choices for median, the smaller of the two values is taken. For example, the median of [1, 5, 8] is 5, and of [2, 3, 7, 11] is 3.
 * 2. A subarray is a contiguous part of the array. For example, [1, 2, 3] is a subarray of [6, 1, 2, 3, 5] but [6, 2] is not.
 * Example
 * There are n = 3 elements in arr = [1, 2, 1]. The subarrays along with their uniqueness values are:
 * • [1] : uniqueness = 1
 * • [1, 2]: uniqueness = 2
 * • [1, 2, 1]: uniqueness = 2
 * • [2]: uniqueness = 1
 * • [2, 1]: uniqueness = 2
 * • [1]: uniqueness = 1
 * The subarray_uniqueness array is [1, 2, 2, 1, 2, 1]. After sorting, the array is [1, 1, 1, 2, 2, 2]. The choice is between the two bold values. Return the minimum of the two, 1.
 * Function Description
 * Complete the function findMedianOfSubarrayUniqueness in the editor below.
 * findMedianOfSubarrayUniqueness has the following parameter:
 * int arr[n]: the array
 * Returns
 * int: the median of the generated array subarray_uniqueness
 * Constraints
 * ・1≤5ns105
 * • Input Format For Custom Testing
 * The first line contains an integer, n, the number of elements in arr.
 * Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer, arr[i.
 * <p>
 * <p>
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 2
 * 1
 * 1
 * arr[] size n = 2
 * arr = [1, 1]
 * Sample Output
 * 1
 * Explanation
 * The subarrays along with their uniqueness values are:
 * • [1]: uniqueness = 1
 * • [1, 1]: uniqueness = 1
 * • [1]: uniqueness = 1
 * subarray_uniqueness is [1, 1, 1].
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 3
 * 1
 * 2
 * 3
 * arr[] size n = 3
 * arr = [1, 2, 31
 * Sample Output
 * 1
 * Explanation
 * Given n = 3 and arr = [1, 2, 3], the subarrays along with their uniqueness values are:
 * • [1]: uniqueness = 1
 * • [1, 2]: uniqueness = 2
 * • [1, 2, 3] : uniqueness = 3
 * • [2]: uniqueness = 1
 * • [2, 3]: uniqueness = 2
 * • [3]: uniqueness = 1
 * subarray_uniqueness is [1, 2, 3, 1, 2, 1], and after sorting it is [1, 1, 1, 2, 2, 3).
 */
class Result2 {
    public static int findMedianOfSubarrayUniqueness(List<Integer> arr) {
        int[] uniqueFrequency = new int[arr.size() + 1];
        for (int start = 0; start < arr.size(); start++) {
            Set<Integer> seen = new HashSet<>();
            for (int end = start; end < arr.size(); end++) {
                seen.add(arr.get(end));
                uniqueFrequency[seen.size()]++;
            }
        }

        int totalCount = Arrays.stream(uniqueFrequency).sum();
        int medianIndex = (totalCount - 1) / 2;
        int count = 0;
        for (int i = 0; i < uniqueFrequency.length; i++) {
            count += uniqueFrequency[i];
            if (count > medianIndex) {
                return i; // Found the median
            }
        }
        return -1; // this case should not happen
    }

    public static void main(String[] args) {
        List<Integer> arr1 = Arrays.asList(1, 1); // Sample case 1
        List<Integer> arr2 = Arrays.asList(1, 2, 3); // Sample case 2
        // Add more test cases if necessary

        System.out.println(findMedianOfSubarrayUniqueness(arr1)); // Expected output: 1
        System.out.println(findMedianOfSubarrayUniqueness(arr2)); // Expected output: 1
    }
}

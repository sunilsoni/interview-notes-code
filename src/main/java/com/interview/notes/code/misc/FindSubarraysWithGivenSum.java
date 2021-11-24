package com.interview.notes.code.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//https://www.techiedelight.com/find-subarrays-given-sum-array/

/**
 * Given an integer array, find subarrays with a given sum in it.
 */
class FindSubarraysWithGivenSum
{
    // Utility function to insert <key, value> pair into the multimap
    private static<K, V> void insert(Map<K, List<V>> hashMap, K key, V value)
    {
        // if the key is seen for the first time, initialize the list
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }
 
    // Utility function to print subarray `nums[i, j]`
    public static void printSubarray(int[] nums, int i, int j)
    {
        System.out.println(IntStream.range(i, j + 1)
                                    .mapToObj(k -> nums[k])
                                    .collect(Collectors.toList()));
    }
 
    // Function to find subarrays with the given sum in an array
    public static void printAllSubarrays(int[] nums, int target)
    {
        // create a map for storing the end index of all subarrays with
        // the sum of elements so far
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
 
        // To handle the case when the subarray with the given sum starts
        // from the 0th index
        insert(hashMap, 0, -1);
 
        int sum_so_far = 0;
 
        // traverse the given array
        for (int index = 0; index < nums.length; index++)
        {
            // sum of elements so far
            sum_so_far += nums[index];
 
            // check if there exists at least one subarray with the given sum
            if (hashMap.containsKey(sum_so_far - target))
            {
                List<Integer> list = hashMap.get(sum_so_far - target);
                for (Integer value: list) {
                    printSubarray(nums, value + 1, index);
                }
            }
 
            // insert (target so far, current index) pair into the map
            insert(hashMap, sum_so_far, index);
        }
    }
 
    public static void main (String[] args)
    {
        int[] nums = { 3, 4, -7, 1, 3, 3, 1, -4 };
        int target = 7;
 
        printAllSubarrays(nums, target);
    }
}
package com.interview.notes.code.year.y2024.april24.test7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    public static int findMedianOfSubarrayUniqueness(List<Integer> arr) {
        int n = arr.size();

        // Handle edge cases: empty or single-element list
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        // Efficiently count occurrences of elements using a hash table
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Calculate the total number of subarrays (using cumulative sum)
        int totalSubarrays = n * (n + 1) / 2;

        // Track current subarray uniqueness and count (sliding window approach)
        int currentUniqueness = 0;
        int count = 0;
        int median = 0;

        for (int i = 0, j = 0; i < n; i++) {
            // Remove element from the left window if its frequency decreases
            if (freq.get(arr.get(i)) > 1) {
                currentUniqueness--;
                freq.put(arr.get(i), freq.get(arr.get(i)) - 1);
            }

            // Increase current uniqueness and count subarrays with it
            currentUniqueness++;
            count += totalSubarrays - (i + 1) * currentUniqueness;

            // Handle median when even number of subarrays exist
            if (count * 2 >= totalSubarrays) {
                if ((totalSubarrays % 2) == 0) {
                    // Find the second median in case of even number of subarrays
                    while (j < n && count > totalSubarrays / 2) {
                        count -= (i - j + 1) * (freq.get(arr.get(j)) - 1);
                        freq.put(arr.get(j), freq.get(arr.get(j)) - 1);
                        j++;
                    }
                    median = Math.min(currentUniqueness, freq.get(arr.get(j - 1)));
                } else {
                    median = currentUniqueness;
                }
                break;
            }
        }

        return median;
    }

    public static void main(String[] args) {
        // Example test case 1
        List<Integer> arr1 = Arrays.asList(1, 1);
        System.out.println(findMedianOfSubarrayUniqueness(arr1)); // Output should be 1

        // Example test case 2
        List<Integer> arr2 = Arrays.asList(1, 2, 3);
        System.out.println(findMedianOfSubarrayUniqueness(arr2)); // Output should be 1

        // Additional test cases
        // ... Add more test cases as needed
    }
}

package com.interview.notes.code.year.y2025.november.Mphasis.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniquePermutations {

    // Recursive method to generate permutations
    public static void permute(int[] arr, int l, int r, Set<String> result) {
        if (l == r) {
            // Convert array to string and add to set to ensure uniqueness
            result.add(Arrays.toString(arr));
            return;
        }

        for (int i = l; i <= r; i++) {
            swap(arr, l, i); // Swap current index with loop index
            permute(arr, l + 1, r, result); // Recurse for next position
            swap(arr, l, i); // Backtrack to restore original state
        }
    }

    // Swap helper method to exchange two elements in the array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Main method to test with [0, 1, 2]
    public static void main(String[] args) {
        // Input array with 3 distinct elements
        int[] input = {0, 1, 2};

        // Set to store unique permutations as strings
        Set<String> uniquePerms = new HashSet<>();

        // Generate permutations starting from index 0 to length - 1
        permute(input, 0, input.length - 1, uniquePerms);

        // Print all unique permutations
        System.out.println("Unique permutations:");
        for (String s : uniquePerms) {
            System.out.println(s);
        }

        // Print total count of unique permutations
        System.out.println("Total: " + uniquePerms.size());
    }
}

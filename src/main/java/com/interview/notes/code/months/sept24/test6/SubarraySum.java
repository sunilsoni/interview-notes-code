package com.interview.notes.code.months.sept24.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SubarraySum {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 4, 2, 3, 7, 8);
        int targetSum = 5;

        List<List<Integer>> result = findAllSubarraysWithSum(ints, targetSum);

        if (result.isEmpty()) {
            System.out.println("No subarrays found with sum " + targetSum);
        } else {
            System.out.println("Subarrays with sum " + targetSum + ":");
            for (List<Integer> subarray : result) {
                System.out.println(subarray);
            }
        }

        // Additional test cases
        testCase(Arrays.asList(1, 2, 3, 4, 5), 5);
        testCase(Arrays.asList(1, 1, 1, 1, 1), 2);
        testCase(Arrays.asList(1, 2, 3), 6);

        String word = "hello";

        Optional<Character> firstVowel = word.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> "aeiouAEIOU".indexOf(ch) != -1)
                .findFirst();

        firstVowel.ifPresent(vowel -> System.out.println("First vowel: " + vowel));

    }

    /**
     * Finds all subarrays with the given target sum.
     *
     * @param nums      The input list of integers
     * @param targetSum The target sum to find
     * @return A list of lists containing all subarrays with the target sum
     */
    public static List<List<Integer>> findAllSubarraysWithSum(List<Integer> nums, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 0;
        int currentSum = 0;

        for (int end = 0; end < nums.size(); end++) {
            currentSum += nums.get(end);

            while (currentSum > targetSum && start < end) {
                currentSum -= nums.get(start);
                start++;
            }

            if (currentSum == targetSum) {
                result.add(new ArrayList<>(nums.subList(start, end + 1)));
            }
        }

        return result;
    }

    /**
     * Helper method to run and print results for test cases.
     */
    private static void testCase(List<Integer> nums, int targetSum) {
        List<List<Integer>> result = findAllSubarraysWithSum(nums, targetSum);
        System.out.println("Input: " + nums + ", Target: " + targetSum);
        if (result.isEmpty()) {
            System.out.println("No subarrays found");
        } else {
            System.out.println("Subarrays found:");
            for (List<Integer> subarray : result) {
                System.out.println(subarray);
            }
        }
        System.out.println();
    }
}

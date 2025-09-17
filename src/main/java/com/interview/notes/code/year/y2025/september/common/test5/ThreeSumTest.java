package com.interview.notes.code.year.y2025.september.common.test5;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSumTest {

    // Method to find all unique triplets that sum to zero, with final sorting
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort array to enable two-pointer approach and easy duplicate skipping

        List<List<Integer>> result = new ArrayList<>(); // will collect all valid triplets
        int n = nums.length; // store length to avoid recomputing
        
        // iterate through each number as the first element of the triplet
        for (int i = 0; i < n - 2; i++) {
            // skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;    // left pointer
            int right = n - 1;   // right pointer

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; // compute sum of three elements
                if (sum == 0) {
                    // found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // skip duplicates on the left
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // skip duplicates on the right
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    // sum too small, move left pointer to increase sum
                    left++;
                } else {
                    // sum too large, move right pointer to decrease sum
                    right--;
                }
            }
        }

        // sort the list of triplets lexicographically: by first, then second, then third element
        result.sort(
                Comparator
                        .comparing((List<Integer> trip) -> trip.get(0))
                        .thenComparing(trip -> trip.get(1))
                        .thenComparing(trip -> trip.get(2))
        );

        return result; // return sorted triplets
    }

    // Convert triplets to a Set of comma-joined strings for easy comparison in tests
    public static Set<String> convertToSet(List<List<Integer>> triplets) {
        return triplets.stream()
                .map(list -> list.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")))
                .collect(Collectors.toSet());
    }

    // Simple main method to run and verify test cases
    public static void main(String[] args) {
        // prepare test cases
        List<TestCase> tests = Arrays.asList(
                new TestCase(new int[]{-1, 0, 1, 2, -1, -4},
                        Arrays.asList(
                                Arrays.asList(-1, -1, 2),
                                Arrays.asList(-1, 0, 1)
                        )
                ),
                new TestCase(new int[]{},
                        Collections.emptyList()
                ),
                new TestCase(new int[]{0, 0, 0, 0},
                        List.of(
                                Arrays.asList(0, 0, 0)
                        )
                ),
                new TestCase(new int[10000], // large array
                        List.of(
                                Arrays.asList(0, 0, 0)
                        )
                )
        );
        // fill the large test with zeros
        Arrays.fill(tests.get(3).nums, 0);

        // run each test
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            List<List<Integer>> actual = threeSum(tc.nums);           // compute triplets
            Set<String> actualSet = convertToSet(actual);              // for comparison
            Set<String> expectedSet = convertToSet(tc.expected);

            if (actualSet.equals(expectedSet)) {
                System.out.println("Test case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test case " + (i + 1) + ": FAIL");
                System.out.println("  Expected: " + expectedSet);
                System.out.println("  Actual:   " + actualSet);
            }
        }
    }

    // Container for input and expected output of a test
    static class TestCase {
        int[] nums;                         // input array
        List<List<Integer>> expected;      // expected triplets

        TestCase(int[] nums, List<List<Integer>> expected) {
            this.nums = nums;               // save input
            this.expected = expected;       // save expected output
        }
    }
}
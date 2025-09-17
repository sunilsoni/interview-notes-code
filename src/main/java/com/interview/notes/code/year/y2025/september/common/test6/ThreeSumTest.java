package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSumTest { // main class holding solution and tests

    // Method to find all unique triplets in the array that sum to zero
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort array to make two-pointer scanning and duplicate skipping easy
        List<List<Integer>> result = new ArrayList<>(); // will hold the final triplets
        int n = nums.length; // store array length for loops
        for (int i = 0; i < n - 2; i++) { // fix the first element, stop 2 before end
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate first elements
            int left = i + 1; // left pointer just after i
            int right = n - 1; // right pointer at end of array
            while (left < right) { // move pointers until they meet
                int sum = nums[i] + nums[left] + nums[right]; // compute the three-sum
                if (sum == 0) { // found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right])); // add it
                    while (left < right && nums[left] == nums[left + 1]) left++; // skip dups on left
                    while (left < right && nums[right] == nums[right - 1]) right--; // skip dups on right
                    left++; // move inward for new pairs
                    right--; // move inward for new pairs
                } else if (sum < 0) { // sum too small
                    left++; // increase sum by moving left pointer right
                } else { // sum too large
                    right--; // decrease sum by moving right pointer left
                }
            }
        }
        return result; // return all found triplets
    }

    // Helper to turn List<List<Integer>> into a Set<String> for easy comparison
    public static Set<String> convertToSet(List<List<Integer>> triplets) {
        return triplets.stream() // stream over each triplet
                .map(list -> list.stream() // stream over its three elements
                        .map(String::valueOf) // convert each integer to a string
                        .collect(Collectors.joining(",")) // join with commas, e.g. "-1,0,1"
                )
                .collect(Collectors.toSet()); // collect all joined strings into a set
    }

    // Simple main method to run multiple test cases and print PASS/FAIL
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList( // prepare our test cases
                new TestCase(new int[]{-1, 0, 1, 2, -1, -4}, // test #1 input
                        Arrays.asList( // expected triplets
                                Arrays.asList(-1, -1, 2),
                                Arrays.asList(-1, 0, 1)
                        )
                ),
                new TestCase(new int[]{}, // test #2: empty array
                        Collections.emptyList() // expect no triplets
                ),
                new TestCase(new int[]{0, 0, 0, 0}, // test #3: all zeros
                        List.of(
                                Arrays.asList(0, 0, 0) // only one unique triplet
                        )
                ),
                new TestCase(new int[10000], // test #4: large array (all zeros)
                        List.of(
                                Arrays.asList(0, 0, 0) // still only one unique triplet
                        )
                )
        );
        Arrays.fill(tests.get(3).nums, 0); // fill the large test array with zeros

        for (int i = 0; i < tests.size(); i++) { // run through each test
            TestCase tc = tests.get(i); // grab current test case
            List<List<Integer>> actual = threeSum(tc.nums); // compute actual triplets
            Set<String> actualSet = convertToSet(actual); // convert actual to set form
            Set<String> expectedSet = convertToSet(tc.expected); // convert expected to set form

            if (actualSet.equals(expectedSet)) { // compare sets for equality
                System.out.println("Test case " + (i + 1) + ": PASS"); // print PASS
            } else {
                System.out.println("Test case " + (i + 1) + ": FAIL"); // print FAIL
                System.out.println("  Expected: " + expectedSet); // show what we wanted
                System.out.println("  Actual:   " + actualSet); // show what we got
            }
        }
    }

    // Simple container for a single test case
    static class TestCase {
        int[] nums; // input array
        List<List<Integer>> expected; // expected list of triplets

        TestCase(int[] nums, List<List<Integer>> expected) { // constructor
            this.nums = nums; // store input
            this.expected = expected; // store expected output
        }
    }
}
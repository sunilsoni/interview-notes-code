package com.interview.notes.code.months.dec24.test4;/*
Problem Analysis:
- We need to generate all subsets of a given set of numbers (e.g., {1,2,3}).
- For each subset, we also need to generate all possible orderings (permutations).
- Then we print them all (e.g., "1", "2", "3", "12", "21", "13", "31", "23", "32", "123", "132", "213", "231", "312", "321").

Key points considered:
- Directly addresses generating subsets and their permutations.
- Checked feasibility: simple recursion for subsets and permutations is straightforward.
- Simple, direct approach: backtracking to get subsets, then permutations of each subset.
- Should handle larger inputs, though complexity grows factorially. For large data, consider efficient pruning.
- Impact and sustainability: the solution works as intended for small to moderate inputs.
- Improvement: Could skip generating subsets first and directly generate all permutations of all subset sizes. But current approach is simpler to understand.
- Essential requirement: print subsets and their permutations. Additional features like sorting are nice-to-have, not essential.

Minimal Reproducible Example:
- Input: {1,2,3}
- Expected output: a list of permutations of all subsets (except empty): 
  1
  2
  3
  12 21
  13 31
  23 32
  123 132 213 231 312 321

Edge Cases:
- Empty set: should print nothing.
- Single element: just that element.
- Repeated elements (not stated in problem): if allowed, handle duplicates carefully.
- Large data: the approach works but might be slow; consider testing only smaller subsets.

Testing Approach:
- Use a main method to run tests.
- Define expected outputs for a given input.
- Compare generated results with expected and print "PASS" if they match, otherwise "FAIL".
- Test with {1,2,3}, {1}, {}, and potentially larger sets.
- Validate correctness by comparing lengths and exact matches of output sets.
- No JUnit, just simple checks in main.

Code Implementation and Testing:
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSubsetsPermutations {

    // Generate all subsets
    static List<List<Integer>> generateSubsets(int[] arr) {
        List<List<Integer>> subsets = new ArrayList<>();
        backtrackSubsets(arr, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    private static void backtrackSubsets(int[] arr, int start, List<Integer> current, List<List<Integer>> result) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            backtrackSubsets(arr, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // Generate all permutations for a given subset
    static List<List<Integer>> generatePermutations(List<Integer> subset) {
        List<List<Integer>> perms = new ArrayList<>();
        backtrackPermutations(subset, 0, perms);
        return perms;
    }

    private static void backtrackPermutations(List<Integer> subset, int start, List<List<Integer>> result) {
        if (start == subset.size() - 1) {
            result.add(new ArrayList<>(subset));
            return;
        }
        for (int i = start; i < subset.size(); i++) {
            swap(subset, start, i);
            backtrackPermutations(subset, start + 1, result);
            swap(subset, start, i);
        }
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    // Convert list of ints to string
    static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test with input {1,2,3}
        int[] input = {1, 2, 3};
        List<String> expected = Arrays.asList(
                "1", "2", "3",
                "12", "21", "13", "31", "23", "32",
                "123", "132", "213", "231", "312", "321"
        );
        List<String> actual = new ArrayList<>();

        List<List<Integer>> subsets = generateSubsets(input);
        for (List<Integer> subset : subsets) {
            List<List<Integer>> perms = generatePermutations(subset);
            for (List<Integer> p : perms) {
                actual.add(listToString(p));
            }
        }

        // Check if actual matches expected (order not guaranteed, so we sort)
        actual.sort(String::compareTo);
        List<String> sortedExpected = new ArrayList<>(expected);
        sortedExpected.sort(String::compareTo);

        boolean pass = actual.equals(sortedExpected);
        System.out.println("Test {1,2,3}: " + (pass ? "PASS" : "FAIL"));

        // Additional test: empty set
        int[] emptyInput = {};
        List<List<Integer>> emptySubsets = generateSubsets(emptyInput);
        // Expect no output
        System.out.println("Test {}: " + (emptySubsets.isEmpty() ? "PASS" : "FAIL"));

        // Additional test: single element
        int[] singleInput = {1};
        List<List<Integer>> singleSubsets = generateSubsets(singleInput);
        // Expected: subset {1} -> permutation "1"
        List<String> singleActual = new ArrayList<>();
        for (List<Integer> s : singleSubsets) {
            for (List<Integer> p : generatePermutations(s)) {
                singleActual.add(listToString(p));
            }
        }
        System.out.println("Test {1}: " + (singleActual.equals(Arrays.asList("1")) ? "PASS" : "FAIL"));

        // Large data test (simple check):
        int[] largeInput = {1, 2, 3, 4};
        // Just run it and ensure it doesn't fail (performance test)
        // Not comparing to expected due to large output size
        List<List<Integer>> largeSubsets = generateSubsets(largeInput);
        // Just print a success message if it runs without errors
        System.out.println("Test {1,2,3,4}: RUN SUCCESSFUL (not validated for correctness here)");
    }
}

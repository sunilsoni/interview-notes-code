package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Problem: Given an input string (e.g., "abc"), produce all distinct permutations of its characters. Example: "abc" → ["abc","acb","bac","bca","cab","cba"].

 */
public class StringPermutations {       // main public class for permutations and tests

    // Public API: returns list of unique permutations for the given string
    public static List<String> permutations(String s) { // method signature
        // if input is null return empty list to avoid NPE
        if (s == null) return Collections.emptyList(); // handle null edge-case gracefully

        // convert string to char array to allow in-place swaps (more efficient than building new strings)
        char[] arr = s.toCharArray(); // working array for permutations

        // list to collect all generated permutations (may contain duplicates if input has duplicate chars)
        List<String> result = new ArrayList<>(); // accumulator for permutations

        // start the recursive backtracking from index 0
        permute(arr, 0, result); // generate permutations in-place

        // use Java 8 stream to remove duplicates while preserving insertion order (via LinkedHashSet behavior)
        // distinct() is stable and removes duplicate strings if any
        return result.stream().distinct().collect(Collectors.toList()); // return deduped list
    }

    // Recursive helper that generates permutations by swapping characters
    private static void permute(char[] arr, int l, List<String> res) { // arr = working array, l = current index
        // base case: when l reaches last index, add current permutation to result
        if (l == arr.length - 1) { // when only one position left, we have a full permutation
            res.add(new String(arr)); // create a String from current char array and add to results
            return; // end this branch of recursion
        }

        // iterate from current index to end to swap each candidate into position l
        for (int i = l; i < arr.length; i++) { // loop over candidates for position l
            swap(arr, l, i);               // place arr[i] at position l
            permute(arr, l + 1, res);      // recurse to fill next position
            swap(arr, l, i);               // backtrack: restore original order for next iteration
        }
    }

    // Utility to swap two characters in an array; small helper to keep code readable
    private static void swap(char[] a, int i, int j) { // swap a[i] and a[j]
        char tmp = a[i];    // store a[i] temporarily
        a[i] = a[j];        // move a[j] to position i
        a[j] = tmp;         // put originally a[i] into position j
    }

    // Simple main-based test runner (no JUnit) — runs test cases and prints PASS/FAIL
    public static void main(String[] args) {
        // list of test cases: each test holds input string and expected set of permutations
        List<TestCase> tests = new ArrayList<>(); // container for test cases

        // add example testcases from the image (abc, xyz, xy)
        tests.add(new TestCase("abc", new String[]{"abc", "acb", "bac", "bca", "cab", "cba"}));
        tests.add(new TestCase("xyz", new String[]{"xyz", "xzy", "yxz", "yzx", "zxy", "zyx"}));
        tests.add(new TestCase("xy", new String[]{"xy", "yx"}));

        // other important testcases: empty string, single char, duplicates
        tests.add(new TestCase("", new String[]{""}));               // empty string => one permutation: empty
        tests.add(new TestCase("a", new String[]{"a"}));            // single char => single permutation
        tests.add(new TestCase("aa", new String[]{"aa"}));          // duplicates => unique permutations only
        tests.add(new TestCase("aba", new String[]{"aab", "aba", "baa"})); // duplicates example; order not important

        // run each test and print PASS/FAIL
        int passed = 0; // counter for passed tests
        for (int i = 0; i < tests.size(); i++) { // iterate tests by index for readable output
            TestCase tc = tests.get(i); // current test case
            List<String> got = permutations(tc.input); // compute permutations using our method

            // sort both expected and got sets for stable comparison (use sets to ignore order)
            Set<String> expectedSet = Arrays.stream(tc.expected).collect(Collectors.toCollection(LinkedHashSet::new)); // expected set
            Set<String> gotSet = got.stream().collect(Collectors.toCollection(LinkedHashSet::new)); // got set with insertion order

            boolean equal = expectedSet.equals(gotSet); // compare sets (order independent)
            System.out.printf("Test %d | input: '%s' | expected size: %d | got size: %d | %s%n",
                    i + 1, tc.input, expectedSet.size(), gotSet.size(), (equal ? "PASS" : "FAIL")); // print concise result

            if (!equal) { // if test failed, print details for debugging
                System.out.println("  Expected: " + expectedSet);
                System.out.println("  Got     : " + gotSet);
            } else {
                passed++; // increment passed counter
            }
        }

        // print summary of small tests
        System.out.printf("Summary: Passed %d/%d small tests%n", passed, tests.size()); // summary line

        // Large input performance check (length 8 => 40320 permutations) — demonstration of scale
        String large = generateSampleString(8); // e.g., "abcdefgh"
        System.out.println("\nLarge test: generating permutations for '" + large + "' (this produces 8! = 40320 strings)");

        long start = System.currentTimeMillis(); // capture start time
        List<String> largePerms = permutations(large); // generate permutations
        long duration = System.currentTimeMillis() - start; // measure duration

        System.out.println("Large test produced " + largePerms.size() + " permutations in " + duration + " ms"); // performance info

        // Safety note: do not run permutations for n > 10 in production if you intend to store all results
        if (largePerms.size() > 100_000) {
            System.out.println("Warning: very large output (" + largePerms.size() + " items). Memory/time may be high.");
        }
    }

    // Helper to generate a distinct-letter sample string of given length (like "abcdef..." up to 26)
    private static String generateSampleString(int length) {
        // use letters a-z cyclically to build string; IntStream.range used to demonstrate Java8 streams
        return IntStream.range(0, length)
                .mapToObj(i -> String.valueOf((char) ('a' + (i % 26)))) // map index to char
                .collect(Collectors.joining()); // join into single string
    }

    /**
     * @param input    input string for test
     * @param expected expected permutations
     */ // Simple container class for tests to hold input and expected permutations
    private record TestCase(String input, String[] expected) {
        // constructor
        // assign input
        // assign expected array
    }
}
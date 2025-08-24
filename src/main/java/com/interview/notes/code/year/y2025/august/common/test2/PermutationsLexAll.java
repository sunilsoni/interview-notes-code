package com.interview.notes.code.year.y2025.august.common.test2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*

**Group Anagrams Problem**

Given an array of strings `strs`, group the anagrams together.
You can return the answer in any order.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Example 1:**
Input:

```
strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
```

Output:

```
[["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
```

**Example 2:**
Input:

```
strs = [""]
```

Output:

```
[[""]]
```

**Example 3:**
Input:

```
strs = ["a"]
```

Output:

```
[["a"]]
```

**Constraints:**

* 1 ≤ `strs.length` ≤ 10⁴
* 0 ≤ `strs[i].length` ≤ 100
* `strs[i]` consists of lowercase English letters only

**Question:**
Implement the method:

 */
public class PermutationsLexAll {        // Define a public class to hold our solution and test main

    // ------------ CORE API: generate all permutations (including duplicates) in lex order ------------

    public static List<String> permutationsAllWithDuplicatesLex(String s) {  // Public method: generates and returns sorted permutations
        char[] chars = s.toCharArray();                                      // Convert input string to char array so we can index positions
        Arrays.sort(chars);                                                  // Sort characters so iteration happens in a natural order
        boolean[] used = new boolean[chars.length];                          // 'used[i]' marks whether character at index i is already in current permutation
        StringBuilder current = new StringBuilder(chars.length);             // Reusable builder for building one permutation step-by-step
        List<String> all = new ArrayList<>();                                // List to collect all permutations (including duplicates)

        backtrack(chars, used, current, all);                                // Call recursive DFS/backtracking to generate all permutations

        // Ensure final lexicographic order using Streams as requested (Java 8 Stream API)
        return all.stream()                                                  // Create a stream over all generated permutations
                .sorted()                                                  // Sort lexicographically
                .collect(Collectors.toList());                             // Collect back to a List<String>
    }

    // Recursive helper to build permutations
    private static void backtrack(char[] chars, boolean[] used, StringBuilder current, List<String> all) {
        if (current.length() == chars.length) {                              // Base condition: built one full-length permutation
            all.add(current.toString());                                     // Add a copy of the permutation to results
            return;                                                          // Return to explore other branches
        }

        for (int i = 0; i < chars.length; i++) {                             // Iterate over every character position
            if (used[i])
                continue;                                           // Skip if this index is already used in current permutation

            used[i] = true;                                                  // Choose: mark index i as used
            current.append(chars[i]);                                        // Append the chosen character to the partial permutation

            backtrack(chars, used, current, all);                            // Explore deeper with updated state

            current.deleteCharAt(current.length() - 1);                      // Backtrack: remove last character to restore previous state
            used[i] = false;                                                 // Backtrack: unmark index i as available for future choices
        }
    }

    // ------------ Utility: factorial using BigInteger (for correctness on large n) ------------

    private static BigInteger factorial(int n) {                             // Compute n! with BigInteger to avoid overflow
        BigInteger result = BigInteger.ONE;                                  // Start result at 1
        for (int i = 2; i <= n; i++) {                                       // Multiply by all integers from 2 to n
            result = result.multiply(BigInteger.valueOf(i));                 // Update result = result * i
        }
        return result;                                                       // Return n! as BigInteger
    }

    // ------------ TESTING (no JUnit): run PASS/FAIL for multiple cases including large input sanity) ------------

    public static void main(String[] args) {                                 // Single entry point to run tests as requested
        System.out.println("Java 8 / Streams based Permutations (including duplicates) in lexicographic order"); // Informative banner

        // Define test cases: each case is input string and expected outputs or checks
        // NOTE: For AAA (length 3), mathematically there are 6 permutations (3! = 6), all equal to "AAA".
        // The prompt’s '8' is incorrect; we will explicitly show/pass the correct expectation.
        String[] inputs = {
                "ABC",   // Distinct letters: expect 3! = 6 distinct permutations
                "XY",    // Distinct letters: expect 2 permutations
                "AAA",   // All same letters: expect 3! = 6 permutations, all "AAA"
                "",      // Empty string: by convention, 0! = 1 permutation -> [""]
                "AAB"    // Contains duplicates: expect 3! = 6 permutations with duplicates (2 letters A identical)
        };

        // Expected counts (n! regardless of duplicates because we count by positions)
        int[] expectedCounts = {
                6,       // "ABC" -> 3! = 6
                2,       // "XY"  -> 2! = 2
                6,       // "AAA" -> 3! = 6 (all are the same string)
                1,       // ""    -> 0! = 1 (the empty permutation)
                6        // "AAB" -> 3! = 6 (duplicates appear in the list)
        };

        // Expected first and last (lexicographic) items for sanity (optional; helps assert sorting)
        // For duplicates, first and last are still well-defined lexicographically.
        String[] expectedFirst = {
                "ABC",   // first of ABC perms
                "XY",    // first of XY perms
                "AAA",   // first of AAA perms
                "",      // first of empty is empty string
                "AAB"    // first of AAB perms
        };

        String[] expectedLast = {
                "CBA",   // last of ABC perms
                "YX",    // last of XY perms
                "AAA",   // last of AAA perms (same as first)
                "",      // last of empty is empty string
                "BAA"    // last of AAB perms
        };

        // Run tests
        for (int t = 0; t < inputs.length; t++) {                            // Loop all test cases
            String s = inputs[t];                                            // Current test input
            long start = System.nanoTime();                                  // Start timing (performance info)
            List<String> result = permutationsAllWithDuplicatesLex(s);       // Generate sorted permutations
            long end = System.nanoTime();                                    // Stop timing

            boolean countOk = (result.size() == expectedCounts[t]);          // Verify count equals n!
            boolean firstOk = result.get(0).equals(expectedFirst[t]);        // Verify the first element matches expectation
            boolean lastOk = result.get(result.size() - 1).equals(expectedLast[t]); // Verify the last element matches expectation
            boolean sortedOk = isSorted(result);                              // Double-check list is sorted

            boolean pass = countOk && firstOk && lastOk && sortedOk;         // Overall PASS if all checks succeeded

            System.out.println("\nTest #" + (t + 1) + " | s = \"" + s + "\""); // Print test header
            System.out.println("  -> Generated: " + result.size() + " permutations in " + (end - start) / 1_000_000.0 + " ms"); // Show runtime and size
            System.out.println("  -> Expected count: " + expectedCounts[t]); // Show expected count
            System.out.println("  -> First: " + result.get(0) + " | Expected first: " + expectedFirst[t]); // Show first vs expected
            System.out.println("  -> Last : " + result.get(result.size() - 1) + " | Expected last : " + expectedLast[t]); // Show last vs expected
            System.out.println("  -> Sorted check: " + sortedOk);            // Show sorted check
            System.out.println("  -> RESULT: " + (pass ? "PASS ✅" : "FAIL ❌")); // PASS/FAIL

            if (s.equals("AAA")) {                                           // Specifically address the AAA note from the prompt
                System.out.println("  -> Note: For \"AAA\", correct count is 3! = 6 (all \"AAA\")."); // Clarify the discrepancy
            }
        }

        // --------- Large data sanity: DO NOT materialize huge lists; check factorial growth and warn ---------

        String big = "ABCDE";                                                // Choose size 5 for a safe demo (5! = 120)
        System.out.println("\nLarge-data sanity (materialize safely): s = \"" + big + "\"");
        long s1 = System.nanoTime();                                         // Start timing
        List<String> bigRes = permutationsAllWithDuplicatesLex(big);         // Generate all 120 perms (safe to materialize)
        long e1 = System.nanoTime();                                         // Stop timing
        System.out.println("  -> Count = " + bigRes.size() + " (expected " + factorial(big.length()) + "), time = " + (e1 - s1) / 1_000_000.0 + " ms"); // Show count/time

        // Warning + example for truly large input (n >= 10 explodes)
        int nWarn = 10;                                                      // Choose 10 to illustrate factorial explosion (10! = 3,628,800)
        System.out.println("\nWARNING: Generating all permutations is factorial-time and memory heavy.");
        System.out.println("  -> For length " + nWarn + ", total permutations = " + factorial(nWarn) + " (do NOT materialize in real apps)."); // Warn user about explosion
    }

    // Helper to verify lexicographic sort (defensive check)
    private static boolean isSorted(List<String> list) {                     // Utility to check list order
        for (int i = 1; i < list.size(); i++) {                              // Iterate consecutive pairs
            if (list.get(i - 1).compareTo(list.get(i)) > 0) return false;    // If previous > current, not sorted
        }
        return true;                                                         // Otherwise sorted
    }
}
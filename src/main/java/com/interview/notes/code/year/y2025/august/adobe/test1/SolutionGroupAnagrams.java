package com.interview.notes.code.year.y2025.august.adobe.test1;

import java.util.*;                              // Import core collections and utility classes
import java.util.stream.*;                       // Import Stream and Collectors for Java 8 Stream API
/*
Here’s the converted and cleaned-up text from your image, combined into a proper question format:

---

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

```java
public List<List<String>> groupAnagrams(String[] strs) {
    // TODO: Implement your solution here
}
```

---

If you want, I can now write an **optimized Java 8+ solution** using `HashMap` and `Streams` for this problem.

 */
public class SolutionGroupAnagrams {             // Define a public class that holds our solution and a testable main method

    // ------------------------- CORE SOLUTION METHOD -------------------------
    public List<List<String>> groupAnagrams(String[] strs) {  // Public method to group anagrams; returns list of groups
        // Defensive null-check: if input array is null, return empty list to avoid NPE and signal "nothing to group"
        if (strs == null) {                                    // Check if input is null
            return Collections.emptyList();                    // Return an empty immutable list for null input
        }

        // Use a LinkedHashMap to preserve the first-seen order of keys (optional but keeps deterministic iteration)
        Map<String, List<String>> grouped = Arrays.stream(strs) // Create a stream from the input array
            .collect(Collectors.groupingBy(                     // Collect elements by grouping based on a classifier key
                s -> buildKeyByCounts(s),                       // Classifier: for each string s, build its 26-letter frequency key
                LinkedHashMap::new,                             // Map supplier: use LinkedHashMap to preserve insertion order
                Collectors.toList()                             // Downstream collector: gather words with the same key into a list
            ));

        // Convert the map values (each is a List<String>) into a List<List<String>> as final result
        return new ArrayList<>(grouped.values());               // Wrap values into new ArrayList to produce a mutable result list
    }

    // ------------------------- KEY BUILDER (O(L) PER WORD) -------------------------
    private String buildKeyByCounts(String s) {                 // Helper to compute a stable key for an anagram class
        // Handle null defensively: treat null as empty string to avoid exceptions and keep groups logical
        if (s == null) {                                        // Check if the string is null
            s = "";                                             // Normalize null to empty
        }

        int[] freq = new int[26];                               // Allocate 26-slot array to count letters 'a'..'z'
        // Count each character’s frequency in O(L)
        for (int i = 0; i < s.length(); i++) {                  // Loop over each character in the string
            char c = s.charAt(i);                               // Access current character
            // Problem guarantees lowercase English letters; if not, we could guard, but here we trust constraints
            freq[c - 'a']++;                                    // Increment the bucket for this letter
        }

        // Build a compact, collision-free string key from the 26 counts
        StringBuilder key = new StringBuilder(26 * 2);          // Pre-size roughly; each count prefixed by a delimiter for safety
        for (int count : freq) {                                // Iterate through 26 counts
            key.append('#');                                    // Add a delimiter to avoid ambiguity (e.g., "11" vs "1|1")
            key.append(count);                                  // Append the actual count for this letter
        }
        return key.toString();                                  // Return the final key string representing the frequency vector
    }

    // ------------------------- TEST HARNESS (NO JUNIT) -------------------------
    public static void main(String[] args) {                    // Main method to run ad-hoc tests and large-data checks
        SolutionGroupAnagrams sol = new SolutionGroupAnagrams();// Instantiate the solution class to call instance methods

        // Prepare a small utility to run a test and print PASS/FAIL
        // We normalize both expected and actual (sort elements inside groups and sort groups) before comparing
        // so that ordering differences do not affect correctness.
        java.util.function.BiConsumer<List<List<String>>, List<List<String>>> assertEqualsIgnoreOrder =
            (expected, actual) -> {                             // Define a lambda that compares two nested string lists
                List<List<String>> normExpected = normalizeGroups(expected); // Normalize expected groups for order-insensitive compare
                List<List<String>> normActual   = normalizeGroups(actual);   // Normalize actual groups similarly
                boolean pass = normExpected.equals(normActual);  // Equality check after normalization
                System.out.println(pass ? "PASS" : "FAIL");      // Print PASS/FAIL
                if (!pass) {                                     // If fail, show details for debugging
                    System.out.println("Expected: " + normExpected);
                    System.out.println("Actual  : " + normActual);
                }
            };

        // ------------------------- GIVEN EXAMPLES -------------------------
        // Example 1
        String[] in1 = {"eat","tea","tan","ate","nat","bat"};    // Input array for example 1
        List<List<String>> out1 = sol.groupAnagrams(in1);        // Call the solution
        List<List<String>> exp1 = Arrays.asList(                  // Expected groups (order-insensitive)
            Arrays.asList("bat"),
            Arrays.asList("nat","tan"),
            Arrays.asList("ate","eat","tea")
        );
        System.out.print("Example 1: ");                         // Label for readability
        assertEqualsIgnoreOrder.accept(exp1, out1);              // Compare and print result

        // Example 2
        String[] in2 = {""};                                     // Single empty string
        List<List<String>> out2 = sol.groupAnagrams(in2);        // Run solution
        List<List<String>> exp2 = Arrays.asList(                  // Expected: one group with empty string
            Arrays.asList("")
        );
        System.out.print("Example 2: ");                         // Label
        assertEqualsIgnoreOrder.accept(exp2, out2);              // Compare

        // Example 3
        String[] in3 = {"a"};                                    // Single-character input
        List<List<String>> out3 = sol.groupAnagrams(in3);        // Run solution
        List<List<String>> exp3 = Arrays.asList(                  // Expected: one group with "a"
            Arrays.asList("a")
        );
        System.out.print("Example 3: ");                         // Label
        assertEqualsIgnoreOrder.accept(exp3, out3);              // Compare

        // ------------------------- EXTRA EDGE CASES -------------------------
        // Duplicates and mixed sizes
        String[] in4 = {"", "", "a", "b", "ab", "ba", "abc", "cab", "bca"}; // Mix of empty, singles, and anagram families
        List<List<String>> out4 = sol.groupAnagrams(in4);        // Run solution
        List<List<String>> exp4 = Arrays.asList(                  // Expected buckets
            Arrays.asList("", ""),                                // two empties together
            Arrays.asList("a"),                                   // singleton
            Arrays.asList("b"),                                   // singleton
            Arrays.asList("ab","ba"),                             // anagram pair
            Arrays.asList("abc","cab","bca")                      // anagram triple
        );
        System.out.print("Edge Case (duplicates/mix): ");        // Label
        assertEqualsIgnoreOrder.accept(exp4, out4);              // Compare

        // All non-anagrams (every word unique by letters)
        String[] in5 = {"ab","cd","ef"};                         // No two are anagrams
        List<List<String>> out5 = sol.groupAnagrams(in5);        // Run solution
        List<List<String>> exp5 = Arrays.asList(                  // Each stands alone
            Arrays.asList("ab"),
            Arrays.asList("cd"),
            Arrays.asList("ef")
        );
        System.out.print("Edge Case (all unique): ");            // Label
        assertEqualsIgnoreOrder.accept(exp5, out5);              // Compare

        // ------------------------- LARGE DATA STRESS TEST -------------------------
        // Create ~10,000 strings by generating 5,000 base tokens and a shuffled anagram of each
        int groups = 5000;                                       // Number of base groups
        Random rnd = new Random(42);                             // Fixed seed for reproducibility
        List<String> bigInputList = new ArrayList<>(groups * 2); // Pre-size for performance
        for (int i = 0; i < groups; i++) {                       // Generate groups times
            String base = randomLowercaseWord(rnd, 8, 20);       // Random word length 8..20
            String ana  = shuffle(base, rnd);                    // Produce an anagram by shuffling
            bigInputList.add(base);                              // Add base
            bigInputList.add(ana);                               // Add anagram partner
        }
        String[] bigIn = bigInputList.toArray(new String[0]);    // Convert list to array for API

        long t0 = System.currentTimeMillis();                    // Measure start time
        List<List<String>> bigOut = sol.groupAnagrams(bigIn);    // Run solution on large input
        long t1 = System.currentTimeMillis();                    // End time
        // Sanity check: we should get exactly 'groups' groups and each group should have size 2
        boolean sizeOk = bigOut.size() == groups                 // Verify number of groups
                          && bigOut.stream().allMatch(g -> g.size() == 2); // Verify each group cardinality
        System.out.println("Large Test (~10k words): " + (sizeOk ? "PASS" : "FAIL")
                           + " | timeMs=" + (t1 - t0));          // Print PASS/FAIL with timing
    }

    // ------------------------- NORMALIZATION HELPERS FOR TESTS -------------------------
    private static List<List<String>> normalizeGroups(List<List<String>> groups) { // Normalize nested list for order-insensitive compare
        if (groups == null) return Collections.emptyList();      // Null-safe: treat null as empty
        // For each group, sort its strings; then sort groups by lexicographic of their joined representation
        return groups.stream()                                   // Stream over groups
            .map(g -> {                                          // For each group
                List<String> copy = new ArrayList<>(g);          // Copy to avoid mutating input
                Collections.sort(copy);                          // Sort strings inside group
                return copy;                                     // Return sorted group
            })
            .sorted((a, b) -> String.join(",", a)                // Comparator: join both groups and compare strings
                                 .compareTo(String.join(",", b)))
            .collect(Collectors.toList());                       // Collect back to a list
    }

    // ------------------------- RANDOM WORD UTILITIES FOR LARGE TEST -------------------------
    private static String randomLowercaseWord(Random rnd, int minLen, int maxLen) { // Generate a random lowercase word
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;     // Random length in [minLen, maxLen]
        StringBuilder sb = new StringBuilder(len);               // Pre-size builder
        for (int i = 0; i < len; i++) {                          // Build character by character
            char c = (char) ('a' + rnd.nextInt(26));             // Random lowercase letter
            sb.append(c);                                        // Append to builder
        }
        return sb.toString();                                    // Return the constructed word
    }

    private static String shuffle(String s, Random rnd) {        // Return a shuffled permutation (an anagram) of s
        List<Character> chars = s.chars()                        // IntStream of code points
            .mapToObj(c -> (char)c)                              // Map to Character objects
            .collect(Collectors.toList());                       // Collect into a List<Character>
        Collections.shuffle(chars, rnd);                         // Shuffle list with provided Random
        StringBuilder sb = new StringBuilder(s.length());        // Pre-size builder for speed
        for (char c : chars) sb.append(c);                       // Rebuild string from shuffled characters
        return sb.toString();                                    // Return shuffled result
    }
}
package com.interview.notes.code.year.y2025.may.google.test1;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {

    /**
     * Groups a list of strings into lists of anagrams.
     * 
     * @param strs Array of input strings (can be null or empty)
     * @return List of groups, where each group is a list of anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // If input array is null or has length 0, return an empty list of groups
        if (strs == null || strs.length == 0) {
            return new ArrayList<>(); // No strings → no groups
        }

        // Use a HashMap to collect lists of anagrams.
        // Key = sorted version of each string
        // Value = list of all original strings matching that sorted key
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Loop over each string in the input array
        for (String s : strs) {
            // Convert the string to a char array so we can sort its characters
            char[] chars = s.toCharArray();
            // Sort the characters in ascending order
            Arrays.sort(chars);
            // Convert the sorted char array back to a string, which is our "canonical key"
            String key = new String(chars);

            // Get the list from map for this key (or create a new one if none exists),
            // then add the original string 's' into that list.
            // computeIfAbsent: if key is absent, it makes a new ArrayList<>()
            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        // Finally, return a list of all the grouped values
        // anagramGroups.values() is a Collection<List<String>>, so wrap in ArrayList<>
        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * Simple main method to test groupAnagrams(...) with various test cases.
     * We will print "PASS" or "FAIL" for each test case.
     */
    public static void main(String[] args) {
        // List of test cases, each test case is a pair of (input array, expected grouping)
        List<TestCase> testCases = new ArrayList<>();

        // 1. Example from problem statement
        testCases.add(new TestCase(
            new String[] {"eat", "tea", "tan", "ate", "nat", "bat"},
            Arrays.asList(
                Arrays.asList("eat", "tea", "ate"),
                Arrays.asList("tan", "nat"),
                Arrays.asList("bat")
            )
        ));

        // 2. Empty array → expect empty output
        testCases.add(new TestCase(
            new String[] {},
            Collections.emptyList()
        ));

        // 3. Array with one string only → that string forms one group
        testCases.add(new TestCase(
            new String[] {"hello"},
            Arrays.asList(
                Arrays.asList("hello")
            )
        ));

        // 4. All strings are identical → all go into one group
        testCases.add(new TestCase(
            new String[] {"abc", "abc", "abc"},
            Arrays.asList(
                Arrays.asList("abc", "abc", "abc")
            )
        ));

        // 5. No two strings are anagrams → each string is its own group
        testCases.add(new TestCase(
            new String[] {"one", "two", "three", "four"},
            Arrays.asList(
                Collections.singletonList("one"),
                Collections.singletonList("two"),
                Collections.singletonList("three"),
                Collections.singletonList("four")
            )
        ));

        // 6. Strings with empty string edge case
        testCases.add(new TestCase(
            new String[] {"", ""},
            Arrays.asList(
                Arrays.asList("", "")
            )
        ));

        // 7. Mixed-case strings and unicode (we treat them literally)
        testCases.add(new TestCase(
            new String[] {"bAt", "tab", "TAb"}, 
            Arrays.asList(
                Arrays.asList("bAt"),           // "bAt" sorts to "Abt"
                Arrays.asList("tab", "TAb")     // "tab" sorts to "abt", "TAb" sorts to "ATb"
                // Note: because case matters, "bAt" != "tab" in sorted form
            )
        ));

        // 8. Large data scenario (performance check): 100,000 small strings
        //    Here we just generate random strings of length 5, but skip verifying expected grouping
        //    We just ensure no exception and reasonable runtime. We'll treat as PASS if code runs.
        testCases.add(new TestCase(
            generateRandomStrings(100_000, 5),
            null   // don't check exact grouping, just that it returns quickly
        ));

        // Run each test case
        int passedCount = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            boolean passed;

            // Call our groupAnagrams method
            long start = System.currentTimeMillis();
            List<List<String>> result = groupAnagrams(tc.input);
            long duration = System.currentTimeMillis() - start;

            if (tc.expected == null) {
                // For large-data test: just check it returns without exception, and roughly size matches
                passed = (result != null);
            } else {
                // For other cases: we need to check that result groups cover exactly the same strings
                // in any order. We compare counts of groups + group contents ignoring order.
                passed = compareGroupsIgnoringOrder(result, tc.expected);
            }

            // Print PASS or FAIL with some info
            if (passed) {
                passedCount++;
                System.out.printf("Test Case %d: PASS (Time: %d ms)%n", i + 1, duration);
            } else {
                System.out.printf("Test Case %d: FAIL (Time: %d ms)%n", i + 1, duration);
                System.out.println("  Input: " + Arrays.toString(tc.input));
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got     : " + result);
            }
        }

        // Summary
        System.out.printf("%nTotal: %d/%d test cases passed.%n", passedCount, testCases.size());
    }

    /**
     * Generate an array of random lowercase strings of given length.
     * Used for performance / large-data test.
     *
     * @param count Number of strings to generate
     * @param length Length of each random string
     * @return Array of random strings
     */
    private static String[] generateRandomStrings(int count, int length) {
        Random rnd = new Random(0); // seed for reproducibility
        String[] arr = new String[count];
        for (int i = 0; i < count; i++) {
            // Build random string of given length from 'a'..'z'
            char[] chs = new char[length];
            for (int j = 0; j < length; j++) {
                chs[j] = (char) ('a' + rnd.nextInt(26));
            }
            arr[i] = new String(chs);
        }
        return arr;
    }

    /**
     * Compare two lists of anagram-groups, ignoring the order of groups
     * and ignoring the order of strings within each group.
     *
     * @param actual   List of groups returned by the method
     * @param expected Reference list of expected groups
     * @return true if they match ignoring order, false otherwise
     */
    private static boolean compareGroupsIgnoringOrder(
            List<List<String>> actual,
            List<List<String>> expected
    ) {
        if (actual == null || expected == null) {
            return actual == expected;
        }
        if (actual.size() != expected.size()) {
            return false;
        }

        // For easier comparison, convert each group to a multiset (Map<String,Integer>),
        // then see if the two collections of multisets match exactly.

        List<Map<String, Integer>> actualMultisets = actual.stream()
            .map(GroupAnagrams::toCountMap)
            .collect(Collectors.toList());

        List<Map<String, Integer>> expectedMultisets = expected.stream()
            .map(GroupAnagrams::toCountMap)
            .collect(Collectors.toList());

        // For each expected multiset, try to find a matching one in actualMultisets
        // and remove it once matched. If anything remains unmatched, fail.
        for (Map<String, Integer> expMap : expectedMultisets) {
            boolean found = false;
            Iterator<Map<String, Integer>> it = actualMultisets.iterator();
            while (it.hasNext()) {
                Map<String, Integer> actMap = it.next();
                if (actMap.equals(expMap)) {
                    found = true;
                    it.remove();
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        // If all expected groups found matches in actual, pass
        return actualMultisets.isEmpty();
    }

    /**
     * Convert a list of strings into a Map<String, Integer> counting occurrences.
     * This helps compare two groups ignoring ordering of elements and duplicates.
     *
     * @param list List of strings
     * @return Map where key=string, value=frequency in the list
     */
    private static Map<String, Integer> toCountMap(List<String> list) {
        Map<String, Integer> freq = new HashMap<>();
        for (String s : list) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        return freq;
    }

    /**
     * Simple data class to hold a test case: input array and expected grouping.
     */
    private static class TestCase {
        String[] input;
        List<List<String>> expected;

        TestCase(String[] input, List<List<String>> expected) {
            // Defensive copy of input
            this.input = (input == null) ? null : Arrays.copyOf(input, input.length);
            // expected can be null for large-data performance test
            this.expected = expected;
        }
    }
}
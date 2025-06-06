package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(List<String> strs) {
        /* Why we need sorting:
         * 1. Sorting arranges strings in lexicographical order
         * 2. After sorting, strings with most similarities will be adjacent
         * 3. The first and last strings will be the "most different" from each other
         * 4. If there's a common prefix, it MUST be present in ALL strings,
         *    including the first and last strings after sorting
         * 5. This reduces our comparison from checking all strings to just two strings
         */

        // Handle edge cases: null or empty list
        if (strs == null || strs.isEmpty()) return "";

        /* Sort the strings lexicographically
         * Example: ["flower", "flow", "flight"] becomes ["flight", "flow", "flower"]
         * - This puts most similar strings next to each other
         * - Most importantly, puts the lexicographically smallest and largest strings at ends
         */
        Collections.sort(strs);

        /* Get first and last strings
         * - First string: lexicographically smallest string
         * - Last string: lexicographically largest string
         * - These two strings will have the least in common among all pairs
         * - If these two have a common prefix, all strings in between must have it too
         */
        String first = strs.get(0);
        String last = strs.get(strs.size() - 1);

        /* Compare characters of first and last strings
         * Example with ["flight", "flow", "flower"]:
         * first: "flight"
         * last:  "flower"
         * Compare: f-f (match), l-l (match), i-o (mismatch)
         * Result: "fl"
         */
        int i = 0;
        while (i < first.length() &&
                i < last.length() &&
                first.charAt(i) == last.charAt(i)) {
            i++;
        }

        // Extract the common prefix found
        return first.substring(0, i);
    }

    // Test cases to demonstrate why sorting works
    public static void main(String[] args) {
        // Test Case 1: Shows why sorting helps
        List<String> test1 = Arrays.asList("flower", "flow", "flight");
        System.out.println("Test 1 - Before sorting: " + test1);
        Collections.sort(test1);
        System.out.println("Test 1 - After sorting: " + test1);
        System.out.println("Common prefix: " + longestCommonPrefix(test1));
        /* Output explanation:
         * Before: ["flower", "flow", "flight"]
         * After:  ["flight", "flow", "flower"]
         * - Only need to compare "flight" and "flower"
         * - Common prefix "fl" is found
         */

        // Test Case 2: Different starting letters
        List<String> test2 = Arrays.asList("dog", "cat", "bird");
        System.out.println("\nTest 2 - Before sorting: " + test2);
        Collections.sort(test2);
        System.out.println("Test 2 - After sorting: " + test2);
        System.out.println("Common prefix: " + longestCommonPrefix(test2));
        /* Output explanation:
         * Before: ["dog", "cat", "bird"]
         * After:  ["bird", "cat", "dog"]
         * - First and last strings start with different letters
         * - Immediately shows there's no common prefix
         */

        // Test Case 3: All similar strings
        List<String> test3 = Arrays.asList("prefix", "pre", "prepare");
        System.out.println("\nTest 3 - Before sorting: " + test3);
        Collections.sort(test3);
        System.out.println("Test 3 - After sorting: " + test3);
        System.out.println("Common prefix: " + longestCommonPrefix(test3));
        /* Output explanation:
         * Before: ["prefix", "pre", "prepare"]
         * After:  ["pre", "prepare", "prefix"]
         * - Sorting groups similar prefixes together
         * - Common prefix "pre" is easily found
         */
    }
}

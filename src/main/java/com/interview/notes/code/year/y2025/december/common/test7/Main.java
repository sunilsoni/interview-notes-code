package com.interview.notes.code.year.y2025.december.common.test7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {                          // Main class so we can run with `java Main`.

    static String key(String s) {            // Helper to build an anagram signature key for a word.
        var a = s.toCharArray();             // Convert word to char[] so we can sort letters.
        Arrays.sort(a);                      // Sort letters; anagrams become identical after sorting.
        return new String(a);                // Convert sorted chars back to String to use as map key.
    }                                        // End helper.

    static List<List<String>> groupAnagrams(String[] in) {   // Function requested: groups anagrams.
        return Arrays.stream(in)                              // Turn input array into a Stream<String>.
                .collect(Collectors.groupingBy(Main::key))    // Group by sorted-letter key.
                .values()                                     // Take only grouped lists from the map.
                .stream()                                     // Stream the groups.
                .map(List::copyOf)                             // Make each group unmodifiable-safe copy.
                .collect(Collectors.toList());                 // Collect into List<List<String>>.
    }                                                         // End function.

    static String norm(List<List<String>> g) {  // Normalize output for stable PASS/FAIL comparisons.
        return g.stream()                        // Stream each group.
                .map(x -> x.stream().sorted().toList()) // Sort words inside each group.
                .sorted(Comparator.comparing(a -> a.get(0))) // Sort groups by first word.
                .map(Object::toString)           // Convert each group to string form.
                .collect(Collectors.joining("|"));// Join to a single comparable string.
    }                                            // End normalize.

    static void test(String name, String[] in, List<List<String>> expected) { // Single test runner.
        var actual = groupAnagrams(in);          // Run solution on input.
        var ok = norm(actual).equals(norm(expected)); // Compare normalized forms.
        System.out.println((ok ? "PASS: " : "FAIL: ") + name); // Print PASS/FAIL.
        if (!ok) {                               // If failed, print both to debug quickly.
            System.out.println("  expected: " + expected);     // Show expected groups.
            System.out.println("  actual  : " + actual);       // Show actual groups.
        }                                        // End fail block.
    }                                            // End test runner.

    public static void main(String[] args) {      // Main method as you requested (no JUnit).
        test("sample",
                new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},  // Given sample input.
                List.of(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat"))); // Expected.

        test("empty",
                new String[]{},                  // Empty input case.
                List.of());                      // Expect empty output.

        test("single",
                new String[]{"abc"},             // One word only.
                List.of(List.of("abc")));        // One group with that word.

        test("duplicates",
                new String[]{"a", "a", "a"},       // Duplicates.
                List.of(List.of("a", "a", "a")));  // All stay together.

        // Large data test (performance + memory sanity check)
        var n = 200_000;                         // Size big enough to stress test.
        var big = new String[n];                 // Allocate big array.
        for (int i = 0; i < n; i++)              // Fill input.
            big[i] = (i % 2 == 0) ? "eat" : "tea"; // Two anagrams alternating.
        var t1 = System.currentTimeMillis();     // Start time.
        var out = groupAnagrams(big);            // Run grouping.
        var t2 = System.currentTimeMillis();     // End time.
        System.out.println("Large test groups=" + out.size() + " timeMs=" + (t2 - t1)); // Print stats.
    }                                            // End main.
}                                                // End class.

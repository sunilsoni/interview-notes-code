package com.interview.notes.code.year.y2025.may.ibm.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//100% WORKING
public class AnagramProductMatches {

    // Returns, for each query, the list of product names that are anagrams of it.
    public static List<List<String>> getProductMatches(List<String> products, List<String> queries) {
        // 1) Build a map: sortedChars(product) -> list of original products
        Map<String, List<String>> anagramMap = products.stream()
                .collect(Collectors.groupingBy(AnagramProductMatches::sortChars));

        // 2) Sort each list of products alphabetically
        anagramMap.values().forEach(list -> Collections.sort(list));

        // 3) For each query, look up its sorted form in the map
        return queries.stream()
                .map(q -> anagramMap.getOrDefault(sortChars(q), Collections.emptyList()))
                .collect(Collectors.toList());
    }

    // Helper: returns the string’s characters sorted in ascending order
    private static String sortChars(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    // Compare actual vs expected and print PASS/FAIL
    private static void runTest(String testName,
                                List<String> products,
                                List<String> queries,
                                List<List<String>> expected) {
        List<List<String>> actual = getProductMatches(products, queries);
        boolean pass = actual.equals(expected);
        System.out.printf("%s: %s%n", testName, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
    }

    public static void main(String[] args) {
        // Sample Input 1
        runTest("Sample 1",
                Arrays.asList("emits", "items", "baker", "times", "break"),
                Arrays.asList("mites", "brake"),
                Arrays.asList(
                        Arrays.asList("emits", "items", "times"),
                        Arrays.asList("baker", "break")
                )
        );

        // Sample Input 2
        runTest("Sample 2",
                Arrays.asList("allot", "cat", "peach", "dusty", "act", "cheap"),
                Arrays.asList("tac", "study", "peahc"),
                Arrays.asList(
                        Arrays.asList("act", "cat"),
                        Arrays.asList("dusty"),
                        Arrays.asList("cheap", "peach")
                )
        );

        // Sample Input 3
        runTest("Sample 3",
                Arrays.asList("duel", "speed", "dule", "cars"),
                Arrays.asList("spede", "deul"),
                Arrays.asList(
                        Arrays.asList("speed"),
                        Arrays.asList("duel", "dule")
                )
        );

        // Large data test: 5,000 products all "abc", 1,000 queries "cba"
        List<String> bigProducts = Collections.nCopies(5000, "abc");
        List<String> bigQueries = Collections.nCopies(1000, "cba");
        List<List<String>> expectedBig = Collections.nCopies(1000,
                Collections.nCopies(5000, "abc")
        );
        runTest("Large Input",
                bigProducts, bigQueries, expectedBig
        );
    }
}

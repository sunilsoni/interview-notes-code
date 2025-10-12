package com.interview.notes.code.year.y2025.october.assessments.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchSuggestionSystem {

    public static List<List<String>> getProductSuggestions(List<String> products, String search) {
        // Sort lexicographically once at start
        List<String> sortedProducts = products.stream()
                .sorted()
                .collect(Collectors.toList());

        List<List<String>> result = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();

        for (char c : search.toCharArray()) {
            prefix.append(c);
            String currentPrefix = prefix.toString();

            List<String> suggestions = sortedProducts.stream()
                    .filter(p -> p.startsWith(currentPrefix))
                    .limit(3)
                    .collect(Collectors.toList());

            result.add(suggestions);
        }
        return result;
    }

    public static void main(String[] args) {
        // ---------- Test Case 1 ----------
        List<String> products1 = Arrays.asList("carpet", "cart", "car", "camera", "crate");
        String search1 = "camera";
        List<List<String>> expected1 = Arrays.asList(
                Arrays.asList("camera", "car", "carpet"),
                Arrays.asList("camera", "car", "carpet"),
                List.of("camera"),
                List.of("camera"),
                List.of("camera"),
                List.of("camera")
        );
        List<List<String>> output1 = getProductSuggestions(products1, search1);
        System.out.println(output1.equals(expected1) ? "PASS" : "FAIL");

        // ---------- Test Case 2 ----------
        List<String> products2 = Arrays.asList("abcd", "abdc", "abaa", "acbd");
        String search2 = "abad";
        List<List<String>> expected2 = Arrays.asList(
                Arrays.asList("abaa", "abcd", "abdc"),
                Arrays.asList("abaa", "abcd", "abdc"),
                List.of("abaa"),
                List.of("abaa")
        );
        List<List<String>> output2 = getProductSuggestions(products2, search2);
        System.out.println(output2.equals(expected2) ? "PASS" : "FAIL");

        // ---------- Large Data Test ----------
        List<String> largeProducts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) largeProducts.add("product" + i);
        String largeSearch = "product9";
        List<List<String>> outputLarge = getProductSuggestions(largeProducts, largeSearch);
        System.out.println("Large input test passed: " + (outputLarge.size() == largeSearch.length()));
    }
}
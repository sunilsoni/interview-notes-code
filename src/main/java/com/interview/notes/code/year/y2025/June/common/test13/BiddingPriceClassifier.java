package com.interview.notes.code.year.y2025.June.common.test13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BiddingPriceClassifier {
    // build the flip map once for reuse
    private static final Map<Character, Character> FLIP_MAP = Stream.of(new Object[][]{
            {'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}
    }).collect(Collectors.toMap(e -> (Character) e[0], e -> (Character) e[1]));

    /**
     * Classify a bid string as "Ambiguous", "Not Ambiguous", or "Invalid".
     */
    public static String classify(String bid) {
        // reverse the bid string
        String reversed = new StringBuilder(bid).reverse().toString();

        // build the flipped version of the reversed string
        StringBuilder flipped = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            // if no mapping exists, we have an invalid price
            if (!FLIP_MAP.containsKey(c)) {
                return "Invalid";
            }
            // append the flipped character
            flipped.append(FLIP_MAP.get(c));
        }

        String flippedStr = flipped.toString();
        // if flipped == original → not ambiguous
        if (flippedStr.equals(bid)) {
            return "Not Ambiguous";
        }
        // else → ambiguous
        return "Ambiguous";
    }

    /**
     * Simple main to run PASS/FAIL tests without JUnit.
     */
    public static void main(String[] args) {
        // list of test cases: {input, expected}
        List<String[]> tests = Arrays.asList(
                new String[]{"901", "Ambiguous"},
                new String[]{"609", "Not Ambiguous"},
                new String[]{"123", "Invalid"},
                new String[]{"100", "Not Ambiguous"},
                new String[]{"806", "Ambiguous"},
                new String[]{"6", "Ambiguous"},   // single-digit flip
                new String[]{"", "Invalid"}      // empty case
        );

        int passed = 0;
        for (String[] t : tests) {
            String result = classify(t[0]);              // run classifier
            boolean ok = result.equals(t[1]);            // compare to expected
            System.out.printf("Input: %-5s Expected: %-13s Got: %-13s %s%n",
                    t[0], t[1], result, ok ? "PASS" : "FAIL");
            if (ok) passed++;
        }
        System.out.printf("Total: %d/%d passed%n", passed, tests.size());
    }
}
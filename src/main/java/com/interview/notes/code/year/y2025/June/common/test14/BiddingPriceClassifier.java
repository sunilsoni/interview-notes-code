package com.interview.notes.code.year.y2025.June.common.test14;

import java.util.*;                                 // for Map, List, Arrays
import java.util.stream.*;                          // for Stream, Collectors

public class BiddingPriceClassifier {

    // Build a flip map once using Java 8 streams: original → flipped
    private static final Map<Character,Character> FLIP_MAP =
        Stream.of(new Object[][] {
            {'0','0'}, {'1','1'}, {'6','9'},
            {'8','8'}, {'9','6'}
        })
        .collect(Collectors.toMap(
            e -> (Character)e[0],                 // key: original digit
            e -> (Character)e[1]                  // value: flipped digit
        ));


    public static String classify(String bid) {
        if (bid == null || bid.isEmpty()) {        // empty → invalid
            return "Invalid";
        }

        StringBuilder flipped = new StringBuilder();

        // iterate from last char to first, flipping as we go
        for (int i = bid.length() - 1; i >= 0; i--) {
            char c = bid.charAt(i);                    // grab digit at position i
            if (!FLIP_MAP.containsKey(c)) {            // if no flip mapping exists
                return "Invalid";                      // then it's invalid
            }
            flipped.append(FLIP_MAP.get(c));           // append the flipped digit
        }

        String flippedStr = flipped.toString();         // the fully flipped string

        // leading-zero rule: if flipped starts with '0' and is multi-digit, treat as Not Ambiguous
        if (flippedStr.length() > 1 && flippedStr.charAt(0) == '0') {
            return "Not Ambiguous";
        }

        if (flippedStr.equals(bid)) {                   // same as original?
            return "Not Ambiguous";                     // → Not Ambiguous
        }

        return "Ambiguous";                              // otherwise, Ambiguous
    }
    /**
     * Classify the bid string as Ambiguous / Not Ambiguous / Invalid.
     */
    public static String classify1(String bid) {
        if (bid == null || bid.isEmpty()) {        // no digits → invalid
            return "Invalid";
        }

        // reverse the input so we can flip digit-by-digit
        String reversed = new StringBuilder(bid).reverse().toString();

        // build the flipped version of that reversed string
        StringBuilder flipped = new StringBuilder();

        for (int i = 0; i < reversed.length(); i++) {     // for each character
            char c = reversed.charAt(i);                   // grab the char
            if (!FLIP_MAP.containsKey(c)) {                // if no flip mapping
                return "Invalid";                          // it's invalid
            }
            flipped.append(FLIP_MAP.get(c));               // append the flipped digit
        }

        String flippedStr = flipped.toString();             // convert to String

        // if flipped result has a leading zero (and is longer than 1),
        // treat it as Not Ambiguous—bids wouldn’t display leading zeros
        if (flippedStr.length() > 1 && flippedStr.charAt(0) == '0') {
            return "Not Ambiguous";
        }

        // if the flipped string exactly equals the original bid,
        // it’s clearly the same price → Not Ambiguous
        if (flippedStr.equals(bid)) {
            return "Not Ambiguous";
        }

        // otherwise, it’s a different valid price → Ambiguous
        return "Ambiguous";
    }

    /**
     * Simple main() to run PASS/FAIL tests without any testing framework.
     */
    public static void main(String[] args) {
        // list of test cases: each is { inputBid, expectedResult }
        List<String[]> tests = Arrays.asList(
            new String[]{"901", "Ambiguous"},
            new String[]{"609", "Not Ambiguous"},
            new String[]{"123", "Invalid"},
            new String[]{"100", "Not Ambiguous"},      // now passes
            new String[]{"806", "Ambiguous"},
            new String[]{"6",   "Ambiguous"},
            new String[]{"",    "Invalid"}             // empty → invalid
        );

        int passed = 0;                                  // counter for successes

        // iterate through all tests
        for (String[] t : tests) {
            String input    = t[0];                      // the bid string
            String expected = t[1];                      // what we expect
            String result   = classify(input);           // run our classifier
            boolean ok      = result.equals(expected);   // did it match?

            // print formatted PASS/FAIL line
            System.out.printf(
                "Input: %-5s Expected: %-13s Got: %-13s %s%n",
                input, expected, result, ok ? "PASS" : "FAIL"
            );

            if (ok) passed++;                            // increment if correct
        }

        // final summary
        System.out.printf("Total: %d/%d passed%n", passed, tests.size());
    }
}
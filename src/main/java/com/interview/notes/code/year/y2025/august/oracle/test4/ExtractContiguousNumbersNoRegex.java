package com.interview.notes.code.year.y2025.august.oracle.test4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*


Given an array of strings such as:
`["A1B", "C2", "34B", "5F6", "7"]`

Write a function that creates an array of the contiguous numbers in the input array.

Example Output:
`["1", "2", "34", "5", "6", "7"]`

 */
public class ExtractContiguousNumbersNoRegex {

    // Function to extract contiguous digit substrings from an array of strings
    public static List<String> extractNumbers(String[] arr) {
        // Use Arrays.stream to process each input string as a stream element
        return Arrays.stream(arr)
                // For each string, convert it into a stream of number tokens (digit runs)
                .flatMap(ExtractContiguousNumbersNoRegex::digitRunsFromString)
                // Collect all tokens into a single list
                .collect(Collectors.toList());
    }

    // Helper that converts one string into a Stream<String> of its contiguous digit runs
    private static Stream<String> digitRunsFromString(String s) {
        // Prepare a list to hold digit runs found in this string
        List<String> tokens = new ArrayList<>();
        // StringBuilder accumulates current run of digits while scanning
        StringBuilder buf = new StringBuilder();

        // Scan each character in the string once from left to right
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);              // read current character
            if (Character.isDigit(ch)) {        // if it is a digit, we are inside a number run
                buf.append(ch);                 // append digit to the current buffer
            } else {                             // otherwise, boundary: end of a digit run (if any)
                if (buf.length() > 0) {         // if buffer is non-empty, we completed a token
                    tokens.add(buf.toString()); // add the finished token to tokens
                    buf.setLength(0);           // clear buffer to start a new run
                }
                // ignore non-digits; they only act as separators
            }
        }

        // After the loop, there may be a pending run at the end (e.g., "A12")
        if (buf.length() > 0) {                 // if there is a remaining token
            tokens.add(buf.toString());         // add it to tokens
        }

        // Return a stream view of the tokens for flatMap to consume
        return tokens.stream();
    }

    // Simple comparison utility: compares two lists by content equality
    private static boolean equalsList(List<String> a, List<String> b) {
        return Objects.equals(a, b);            // uses List.equals semantics (order + values)
    }

    // Pretty print helper for lists
    private static String asString(List<String> list) {
        return list.toString();                 // human-readable form like ["1", "2"]
    }

    // Main method: runs multiple tests (including large data) and prints PASS/FAIL
    public static void main(String[] args) {
        // Test 1: Given example
        String[] in1 = {"A1B", "C2", "34B", "5F6", "7"};                 // input strings
        List<String> out1 = extractNumbers(in1);                         // actual output
        List<String> exp1 = Arrays.asList("1", "2", "34", "5", "6", "7");// expected output
        System.out.println("Test 1 Output  : " + asString(out1));        // show result
        System.out.println("Test 1 Expected: " + asString(exp1));        // show expected
        System.out.println("Test 1: " + (equalsList(out1, exp1) ? "PASS" : "FAIL"));
        System.out.println();

        // Test 2: No digits at all
        String[] in2 = {"ABC", "xyz", "QQQ"};                            // only letters
        List<String> out2 = extractNumbers(in2);                         // should be empty
        List<String> exp2 = Collections.emptyList();                     // expected empty
        System.out.println("Test 2 Output  : " + asString(out2));
        System.out.println("Test 2 Expected: " + asString(exp2));
        System.out.println("Test 2: " + (equalsList(out2, exp2) ? "PASS" : "FAIL"));
        System.out.println();

        // Test 3: All digits
        String[] in3 = {"123", "0456", "7"};                             // single run in each
        List<String> out3 = extractNumbers(in3);                         // 3 tokens
        List<String> exp3 = Arrays.asList("123", "0456", "7");           // preserves leading zeros
        System.out.println("Test 3 Output  : " + asString(out3));
        System.out.println("Test 3 Expected: " + asString(exp3));
        System.out.println("Test 3: " + (equalsList(out3, exp3) ? "PASS" : "FAIL"));
        System.out.println();

        // Test 4: Mixed and adjacent runs
        String[] in4 = {"12A034B7", "X9Y0Z"};                            // multiple runs in strings
        List<String> out4 = extractNumbers(in4);                         // should split correctly
        List<String> exp4 = Arrays.asList("12", "034", "7", "9", "0");
        System.out.println("Test 4 Output  : " + asString(out4));
        System.out.println("Test 4 Expected: " + asString(exp4));
        System.out.println("Test 4: " + (equalsList(out4, exp4) ? "PASS" : "FAIL"));
        System.out.println();

        // Test 5: Empty input and empty strings
        String[] in5 = {};                                               // no elements
        List<String> out5 = extractNumbers(in5);                         // empty result
        List<String> exp5 = Collections.emptyList();
        System.out.println("Test 5 Output  : " + asString(out5));
        System.out.println("Test 5 Expected: " + asString(exp5));
        System.out.println("Test 5: " + (equalsList(out5, exp5) ? "PASS" : "FAIL"));
        System.out.println();

        String[] in6 = {"", "A", "1", "A2B3C", "0"};                    // includes empty and single-char cases
        List<String> out6 = extractNumbers(in6);
        List<String> exp6 = Arrays.asList("1", "2", "3", "0");
        System.out.println("Test 6 Output  : " + asString(out6));
        System.out.println("Test 6 Expected: " + asString(exp6));
        System.out.println("Test 6: " + (equalsList(out6, exp6) ? "PASS" : "FAIL"));
        System.out.println();

        // Large Data Test: measure speed with many inputs (e.g., 100,000 elements)
        int N = 100_000;                                                 // number of strings
        String[] big = new String[N];                                    // allocate array
        // Fill with a repeating pattern that yields two digit tokens per string
        Arrays.setAll(big, i -> "AA" + i + "BB" + (i + 1) + "ZZ");       // e.g., "AA0BB1ZZ"
        long t1 = System.currentTimeMillis();                            // start timing
        List<String> hugeOut = extractNumbers(big);                      // run extraction
        long t2 = System.currentTimeMillis();                            // end timing
        // Expect exactly 2 tokens per input element
        boolean sizeOk = (hugeOut.size() == 2 * N);                      // check count quickly
        System.out.println("Large Data: tokens=" + hugeOut.size() +
                ", expected=" + (2 * N) + ", time=" + (t2 - t1) + " ms -> " + (sizeOk ? "PASS" : "FAIL"));
    }
}
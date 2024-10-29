package com.interview.notes.code.months.oct24.test19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCodec {

    /**
     * Main method for testing the encode and decode methods.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        StringCodec codec = new StringCodec();
        List<List<String>> testCases = new ArrayList<>();

        // Test case 1: Regular strings
        testCases.add(Arrays.asList("abc", "def", "fgh"));

        testCases.add(Arrays.asList("hello", "world"));

        // Test case 2: Strings with special characters
        testCases.add(Arrays.asList("he#llo", "wo:rld", "test@123"));

        // Test case 3: Empty strings
        testCases.add(Arrays.asList("", "", ""));

        // Test case 4: Large data input
        List<String> largeInput = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add("string" + i);
        }
        testCases.add(largeInput);

        // Process each test case
        int caseNumber = 1;
        for (List<String> testCase : testCases) {
            String encoded = codec.encode(testCase);
            List<String> decoded = codec.decode(encoded);
            boolean passed = testCase.equals(decoded);
            System.out.println("Test Case " + caseNumber + ": " + (passed ? "PASS" : "FAIL"));
            caseNumber++;
        }
    }

    /**
     * Encodes a list of strings to a single string.
     *
     * @param strs List of strings to encode.
     * @return Encoded single string.
     */
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String s : strs) {
            encoded.append(s.length()).append('#').append(s);
        }
        return encoded.toString();
    }

    /**
     * Decodes a single string back into a list of strings.
     *
     * @param s Encoded string.
     * @return Decoded list of original strings.
     */
    public List<String> decode(String s) {
        List<String> decoded = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int separator = s.indexOf('#', i);
            int length = Integer.parseInt(s.substring(i, separator));
            i = separator + 1 + length;
            decoded.add(s.substring(separator + 1, i));
        }
        return decoded;
    }
}

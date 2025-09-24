package com.interview.notes.code.year.y2025.september.common.test;

import java.util.stream.IntStream;

public class StringCompression {

    // Method to compress input string
    public static String compress(String input) {
        // If input is empty return empty
        if (input == null || input.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(); // Store result
        int count = 1; // Count repetitions

        // Traverse string
        for (int i = 1; i <= input.length(); i++) {
            // Check if current char equals previous one
            if (i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                count++; // Increase count
            } else {
                // Append char and count to result
                sb.append(input.charAt(i - 1)).append(count);
                count = 1; // Reset count
            }
        }
        return sb.toString(); // Final compressed string
    }

    // Method to decompress input string
    public static String decompress(String input) {
        if (input == null || input.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(); // Store result
        int i = 0;

        // Traverse compressed string
        while (i < input.length()) {
            char ch = input.charAt(i++); // Get character
            StringBuilder num = new StringBuilder();

            // Collect digits after character
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                num.append(input.charAt(i++));
            }

            // Repeat character 'count' times
            int count = Integer.parseInt(num.toString());
            IntStream.range(0, count).forEach(x -> sb.append(ch));
        }
        return sb.toString(); // Final decompressed string
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        String[][] tests = {
                {"aaabbc", "a3b2c1"},
                {"a", "a1"},
                {"", ""},
                {"aaaaa", "a5"},
                {"abc", "a1b1c1"},
                {"zzzzzzzzzz", "z10"} // test multi-digit
        };

        // Run all test cases
        for (String[] test : tests) {
            String original = test[0];
            String expectedCompressed = test[1];

            String actualCompressed = compress(original);
            String actualDecompressed = decompress(expectedCompressed);

            // Check results and print PASS/FAIL
            boolean pass = expectedCompressed.equals(actualCompressed)
                    && original.equals(actualDecompressed);

            System.out.println("Input: " + original);
            System.out.println("Expected Compressed: " + expectedCompressed + " | Got: " + actualCompressed);
            System.out.println("Decompressed Back: " + actualDecompressed);
            System.out.println(pass ? "PASS\n" : "FAIL\n");
        }

        // Large data test
        String large = new String(new char[100000]).replace('\0', 'a'); // "aaaa...a" (100k times)
        String compressed = compress(large);
        String decompressed = decompress(compressed);
        System.out.println("Large Data Test: " + (large.equals(decompressed) ? "PASS" : "FAIL"));
    }
}
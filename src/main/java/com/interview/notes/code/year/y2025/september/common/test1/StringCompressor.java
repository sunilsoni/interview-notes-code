package com.interview.notes.code.year.y2025.september.common.test1;

import java.util.stream.IntStream;

public class StringCompressor {

    // Compress method with optimization
    static String compress(String input) {
        // Handle null or empty directly
        if (input == null || input.isEmpty()) {
            return input; // return as is
        }

        StringBuilder sb = new StringBuilder(); // Build compressed string
        int count = 1; // Counter for repetitions

        for (int i = 1; i <= input.length(); i++) {
            if (i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                count++; // Same char, increase count
            } else {
                sb.append(input.charAt(i - 1)).append(count); // Add <char><count>
                count = 1; // Reset counter
            }
        }

        String compressed = sb.toString();
        // Optimization: Only return compressed if it's shorter
        return compressed.length() < input.length() ? compressed : input;
    }

    // Decompress method
    static String decompress(String output) {
        if (output == null || output.isEmpty()) {
            return output; // return as is
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < output.length()) {
            char ch = output.charAt(i++); // character
            StringBuilder num = new StringBuilder();

            // collect digits after char
            while (i < output.length() && Character.isDigit(output.charAt(i))) {
                num.append(output.charAt(i++));
            }

            // if no digits found, treat it as "just char"
            int count = num.length() > 0 ? Integer.parseInt(num.toString()) : 1;

            // append char 'count' times
            IntStream.range(0, count).forEach(x -> sb.append(ch));
        }
        return sb.toString();
    }

    // Main test runner
    public static void main(String[] args) {
        String[] inputs = {
                "aaabbc",   // lots of repeats
                "abc",      // no repeats
                "a",        // single char
                "",         // empty
                "aaaaaa",   // long run
                "abababab"  // alternating chars
        };

        for (String input : inputs) {
            String compressed = compress(input);
            String decompressed = decompress(compressed);

            System.out.println("Input:       " + input);
            System.out.println("Compressed:  " + compressed);
            System.out.println("Decompressed:" + decompressed);
            System.out.println(input.equals(decompressed) ? "PASS\n" : "FAIL\n");
        }
    }
}
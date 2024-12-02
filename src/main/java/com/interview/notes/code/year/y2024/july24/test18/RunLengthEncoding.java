package com.interview.notes.code.year.y2024.july24.test18;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement run length encoding of a string. RLE algorithm encodes sequences in which the same data value occurs in many consecutive data elements as a single data value and count.
 * Eg: input: aaabcccddeaa output: a3bc3d2ea2 input: aaabbcccdaa output: a3b2c3d1a2"
 */
public class RunLengthEncoding {

    public static String encode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        int count = 1;

        for (int i = 1; i <= chars.length; i++) {
            if (i < chars.length && chars[i] == chars[i - 1]) {
                count++;
            } else {
                result.append(chars[i - 1]);
                if (count > 0) {
                    result.append(count);
                }
                count = 1;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        List<String> testCases = new ArrayList<>();
        testCases.add("aaabcccddeaa");
        testCases.add("aaabbcccdaa");
        testCases.add("");
        testCases.add("a");
        testCases.add("aaaaaa");
        testCases.add("abcdefg");
        testCases.add("aabbbcccc");

        for (String test : testCases) {
            System.out.println("Input: " + test);
            System.out.println("Output: " + encode(test));
            System.out.println();
        }
    }
}

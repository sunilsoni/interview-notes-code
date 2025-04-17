package com.interview.notes.code.year.y2025.april.common.tst6;

import java.util.*;
import java.util.stream.*;

class Result {

    public static String calculateScore(String text, String prefixString, String suffixString) {
        int maxScore = -1;
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            for (int j = i + 1; j <= text.length(); j++) {
                String sub = text.substring(i, j);
                int prefixScore = IntStream.rangeClosed(1, Math.min(prefixString.length(), sub.length()))
                    .filter(k -> sub.startsWith(prefixString.substring(prefixString.length() - k)))
                    .max().orElse(0);
                int suffixScore = IntStream.rangeClosed(1, Math.min(suffixString.length(), sub.length()))
                    .filter(k -> sub.endsWith(suffixString.substring(0, k)))
                    .max().orElse(0);
                int textScore = prefixScore + suffixScore;

                if (textScore > maxScore || (textScore == maxScore && sub.compareTo(result) < 0)) {
                    maxScore = textScore;
                    result = sub;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String[]> testCases = Arrays.asList(
            new String[]{"ab", "b", "a", "a"},
            new String[]{"nothing", "bruno", "ingenious", "nothing"},
            new String[]{"engine", "raven", "ginkgo", "engin"},
            new String[]{"banana", "bana", "nana", "banana"},
            new String[]{"aaaaaa", "aa", "aaa", "aaaaaa"} // Additional edge test case
        );

        for (int i = 0; i < testCases.size(); i++) {
            String[] testCase = testCases.get(i);
            String output = calculateScore(testCase[0], testCase[1], testCase[2]);
            System.out.println("Test Case " + (i + 1) + ": " + (output.equals(testCase[3]) ? "PASS" : "FAIL") + ", Output: " + output);
        }

        // Large input test case
        String largeText = String.join("", Collections.nCopies(50000, "a"));
        String largePrefix = String.join("", Collections.nCopies(50, "a"));
        String largeSuffix = String.join("", Collections.nCopies(50, "a"));
        String expectedLargeOutput = largeText;

        String largeOutput = calculateScore(largeText, largePrefix, largeSuffix);
        System.out.println("Large Test Case: " + (largeOutput.equals(expectedLargeOutput) ? "PASS" : "FAIL"));
    }
}
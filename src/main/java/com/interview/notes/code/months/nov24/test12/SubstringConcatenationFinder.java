package com.interview.notes.code.months.nov24.test12;

import java.util.*;

public class SubstringConcatenationFinder {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;
        int totalLength = wordLength * totalWords;

        if (s.length() < totalLength) {
            return result;
        }

        // Create frequency map for words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Check each possible starting position
        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j;

            // Check each word in the current window
            for (j = 0; j < totalWords; j++) {
                int wordStart = i + j * wordLength;
                String currentWord = s.substring(wordStart, wordStart + wordLength);

                if (!wordFreq.containsKey(currentWord)) {
                    break;
                }

                seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);

                if (seenWords.get(currentWord) > wordFreq.getOrDefault(currentWord, 0)) {
                    break;
                }
            }

            if (j == totalWords) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        runTest("Test 1", "barfoothefoobarman",
                new String[]{"foo", "bar"},
                Arrays.asList(0, 9));

        runTest("Test 2", "wordgoodgoodgoodbestword",
                new String[]{"word", "good", "best", "word"},
                Arrays.asList());

        runTest("Test 3", "barfoofoobarthefoobarman",
                new String[]{"bar", "foo", "the"},
                Arrays.asList(6, 9, 12));

        // Edge cases
        runTest("Empty String", "",
                new String[]{"foo", "bar"},
                Arrays.asList());

        runTest("Single Character", "a",
                new String[]{"a"},
                Arrays.asList(0));

        // Large input test
        StringBuilder largeString = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeString.append("abcdef");
        }
        runTest("Large Input", largeString.toString(),
                new String[]{"abc", "def"},
                generateExpectedLargeOutput());
    }

    private static void runTest(String testName, String s, String[] words, List<Integer> expected) {
        long startTime = System.currentTimeMillis();
        List<Integer> result = findSubstring(s, words);
        long endTime = System.currentTimeMillis();

        boolean passed = result.size() == expected.size() &&
                new HashSet<>(result).containsAll(expected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
        System.out.println();
    }

    private static List<Integer> generateExpectedLargeOutput() {
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                expected.add(i * 3);
            }
        }
        return expected;
    }
}

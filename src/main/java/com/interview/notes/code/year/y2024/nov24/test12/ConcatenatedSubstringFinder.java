package com.interview.notes.code.year.y2024.nov24.test12;

import java.util.*;

public class ConcatenatedSubstringFinder {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return result;

        // Build frequency map for words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Iterate over wordLen possible starting points
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> tempMap = new HashMap<>();

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                if (wordMap.containsKey(word)) {
                    tempMap.put(word, tempMap.getOrDefault(word, 0) + 1);
                    count++;

                    // If there is more occurrence of "word" than needed, move the left pointer
                    while (tempMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        tempMap.put(leftWord, tempMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If all words matched, add to result
                    if (count == wordCount) {
                        result.add(left);
                        // Move left to look for new possible matches
                        String leftWord = s.substring(left, left + wordLen);
                        tempMap.put(leftWord, tempMap.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                } else {
                    // Reset if word not in wordMap
                    tempMap.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }

    // Method to compare two lists irrespective of order
    private static boolean compareLists(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) return false;
        List<Integer> copy1 = new ArrayList<>(list1);
        List<Integer> copy2 = new ArrayList<>(list2);
        Collections.sort(copy1);
        Collections.sort(copy2);
        return copy1.equals(copy2);
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                "barfoothefoobarman",
                new String[]{"foo", "bar"},
                Arrays.asList(0, 9)
        ));

        // Example 2
        testCases.add(new TestCase(
                "wordgoodgoodgoodbestword",
                new String[]{"word", "good", "best", "word"},
                List.of()
        ));

        // Example 3
        testCases.add(new TestCase(
                "barfoofoobarthefoobarman",
                new String[]{"bar", "foo", "the"},
                Arrays.asList(6, 9, 12)
        ));

        // Additional Test Cases

        // Test Case 4: Empty string s
        testCases.add(new TestCase(
                "",
                new String[]{"foo", "bar"},
                List.of()
        ));

        // Test Case 5: Empty words array
        testCases.add(new TestCase(
                "barfoothefoobarman",
                new String[]{},
                List.of()
        ));

        // Test Case 6: Words with duplicates
        testCases.add(new TestCase(
                "wordgoodgoodgoodbestword",
                new String[]{"word", "good", "best", "good"},
                List.of(8)
        ));

        // Test Case 7: Overlapping substrings
        testCases.add(new TestCase(
                "aaa",
                new String[]{"a", "a"},
                Arrays.asList(0, 1)
        ));

        // Test Case 8: Large input
        StringBuilder largeSBuilder = new StringBuilder();
        String[] largeWords = new String[1000];
        for (int i = 0; i < 1000; i++) {
            largeWords[i] = "word" + i;
            largeSBuilder.append(largeWords[i]);
        }
        testCases.add(new TestCase(
                largeSBuilder.toString(),
                largeWords,
                List.of(0)
        ));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> output = findSubstring(tc.s, tc.words);
            boolean isPass = compareLists(output, tc.expected);
            if (isPass) passed++;
            System.out.println("Test Case " + (i + 1) + ": " + (isPass ? "PASS" : "FAIL"));
            if (!isPass) {
                System.out.println("  Input: s = \"" + tc.s + "\", words = " + Arrays.toString(tc.words));
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Got: " + output);
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    // Helper class to store test cases
    static class TestCase {
        String s;
        String[] words;
        List<Integer> expected;

        TestCase(String s, String[] words, List<Integer> expected) {
            this.s = s;
            this.words = words;
            this.expected = expected;
        }
    }
}

package com.interview.notes.code.year.y2024.nov24.test13;

import java.util.*;

/*

WORKING


You are given a string s and an array of strings words. All the strings of words are of the same length.
A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
The output order does not matter. Returning [9,0] is fine too.
Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
There is no substring of length 16 in s that is equal to the concatenation of any permutation of words.
We return an empty array.
Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.

 */
public class ConcatenatedSubstringFinder {

    /**
     * Main method to test the findSubstring method with various test cases.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ConcatenatedSubstringFinder finder = new ConcatenatedSubstringFinder();

        // Test Case 1
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        List<Integer> expected1 = Arrays.asList(0, 9);
        List<Integer> result1 = finder.findSubstring(s1, words1);
        System.out.println("Test Case 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test Case 2
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        List<Integer> expected2 = Collections.emptyList();
        List<Integer> result2 = finder.findSubstring(s2, words2);
        System.out.println("Test Case 2: " + (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Test Case 3
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        List<Integer> expected3 = Arrays.asList(6, 9, 12);
        List<Integer> result3 = finder.findSubstring(s3, words3);
        System.out.println("Test Case 3: " + (result3.equals(expected3) ? "PASS" : "FAIL"));

        // Additional Edge Case: Empty string s
        String s4 = "";
        String[] words4 = {"foo", "bar"};
        List<Integer> expected4 = Collections.emptyList();
        List<Integer> result4 = finder.findSubstring(s4, words4);
        System.out.println("Test Case 4 (Empty s): " + (result4.equals(expected4) ? "PASS" : "FAIL"));

        // Additional Edge Case: Empty words array
        String s5 = "foobar";
        String[] words5 = {};
        List<Integer> expected5 = Collections.emptyList();
        List<Integer> result5 = finder.findSubstring(s5, words5);
        System.out.println("Test Case 5 (Empty words): " + (result5.equals(expected5) ? "PASS" : "FAIL"));

        // Large Input Test Case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        String s6 = sb.toString();
        String[] words6 = {"a", "a", "a", "a", "a"};
        List<Integer> result6 = finder.findSubstring(s6, words6);
        System.out.println("Test Case 6 (Large Input): " + (result6.size() > 0 ? "PASS" : "FAIL"));
    }

    /**
     * Finds all starting indices of concatenated substrings in s that are formed by any permutation of words.
     *
     * @param s     The input string.
     * @param words The list of words to concatenate.
     * @return A list of starting indices.
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        // Edge case checks
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();       // Length of each word
        int wordCount = words.length;             // Number of words
        int totalLength = wordLength * wordCount; // Total length of concatenated words

        if (s.length() < totalLength) {
            return result;
        }

        // Frequency map for the words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Loop through the string
        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j = 0;

            // Check if the substring starting at i is a concatenation of all words
            while (j < wordCount) {
                int wordStart = i + j * wordLength;
                String currentWord = s.substring(wordStart, wordStart + wordLength);

                if (wordFreq.containsKey(currentWord)) {
                    seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);

                    // If a word occurs more times than it is supposed to
                    if (seenWords.get(currentWord) > wordFreq.get(currentWord)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }

            // If all words matched
            if (j == wordCount) {
                result.add(i);
            }
        }

        return result;
    }
}

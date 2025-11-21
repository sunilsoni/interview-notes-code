package com.interview.notes.code.year.y2024.dec24.oracle.test1;

import java.util.*;

/**
 * *Sentence 2:** For the sentence "in the act", the sentences that can be formed are:
 * - in the act
 * - in the cat
 * <p>
 * *Sentence 3:** For the sentence "act tabs in", the sentences that can be formed are:
 * - act tabs in
 * - cat tabs in
 * - act bats in
 * - cat bats in
 * <p>
 * ---
 * <p>
 * *Sample Input:**
 * ```
 * 6 → wordSet[] size n = 6
 * the → wordSet = ['the', 'bats', 'tabs', 'in', 'cat', 'act']
 * 3 → sentences[] size m = 3
 * cat the bats → sentences = ['cat the bats', 'in the act', 'act tabs in']
 * in the act
 * act tabs in
 * ```
 * <p>
 * ---
 * <p>
 * *Sample Output:**
 * ```
 * 4
 * 2
 * 4
 * ```
 * <p>
 * ---
 * <p>
 * *Explanation:**
 * *Sentence 1:** For the sentence "cat the bats", the sentences that can be formed are:
 * - cat the bats
 * - act the bats
 * - cat the tabs
 * - act the tabs
 * <p>
 * *Sentence 2:** For the sentence "in the act", the sentences that can be formed are:
 * - in the act
 * - in the cat
 * <p>
 * ---
 * <p>
 * *Function Description:**
 * Complete the `countSentences` function in the editor below.
 * <p>
 * `countSentences` has the following parameters:
 * - `string wordSet[n]`: An array of strings
 * - `string sentences[m]`: An array of strings
 * <p>
 * *Returns:**
 * `int[]`: An array of `m` integers that denote the number of sentences that can be formed from each sentence.
 * <p>
 * ---
 * <p>
 * *Constraints:**
 * - \( 0 < n \leq 10^5 \)
 * - \( 1 \leq \text{length of each word} \leq 20 \)
 * - \( 1 \leq m \leq 1000 \)
 * - \( 3 \leq \text{words in a sentence} \leq 20 \)
 * <p>
 * ---
 * <p>
 * *Custom Input Format for Testing:**
 * Sample Case 0
 * <p>
 * ---
 * <p>
 * *Code:**
 * ```java
 * /*
 * Complete the 'countSentences' function below.
 * <p>
 * The function is expected to return a LONG_INTEGER_ARRAY.
 * The function accepts the following parameters:
 * 1. STRING_ARRAY wordSet
 * 2. STRING_ARRAY sentences
 * <p>
 * public static List<Long> countSentences(List<String> wordSet, List<String> sentences) {
 * // Write your code here
 * <p>
 * <p>
 * ---
 * <p>
 * *How Many Sentences?**
 * *Description:**
 * Given an array of words and an array of sentences, calculate how many sentences can be created by replacing any word with one of its
 * <p>
 * anagrams.
 * <p>
 * ---
 * <p>
 * *Note:**
 * - Two words are said to be anagrams of each other if one can be created by rearranging the letters of another word, using all the original letters exactly once.
 * <p>
 * ---
 * <p>
 * *Example:**
 * `wordSet = ['listen', 'silent', 'it', 'is']`
 * `sentence = 'listen it is silent'`
 * <p>
 * Determine that **listen** is an anagram of **silent**. Those two words can be replaced with their anagrams. The four sentences that can be created are:
 * 1. listen it is silent
 * 2. listen it is listen
 * 3. silent it is silent
 * 4. silent it is listen
 * <p>
 * ---
 * <p>
 * *Function Description:**
 * Complete the `countSentences` function in the editor below.
 * <p>
 * `countSentences` has the following parameters:
 * - `string wordSet[n]`: An array of strings
 * - `string sentences[m]`: An array of strings
 * <p>
 * *Returns:**
 * `int[]`: An array of integers denoting how many sentences can be formed from each given sentence by replacing words with their anagrams.
 **/
public class CountAnagramSentences {

    /**
     * Given a list of words (wordSet) and a list of sentences, returns the number of possible
     * sentences that can be formed for each sentence by replacing each word with any of its anagrams found in wordSet.
     */
    public static List<Long> countSentences(List<String> wordSet, List<String> sentences) {
        // Map from sorted word -> frequency count of anagram group
        Map<String, Long> anagramCountMap = new HashMap<>();

        // Preprocessing: build the anagram count map
        for (String w : wordSet) {
            String signature = sortWord(w);
            anagramCountMap.put(signature, anagramCountMap.getOrDefault(signature, 0L) + 1L);
        }

        // Process each sentence
        List<Long> results = new ArrayList<>(sentences.size());
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            long count = 1L;
            for (String word : words) {
                String signature = sortWord(word);
                Long freq = anagramCountMap.get(signature);
                if (freq == null) {
                    // No anagrams found, this sentence variation is 0
                    count = 0L;
                    break;
                } else {
                    // Multiply the count of possible replacements
                    count *= freq;
                }
            }
            results.add(count);
        }

        return results;
    }

    private static String sortWord(String w) {
        char[] arr = w.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    // A simple test runner in main method (no JUnit)
    public static void main(String[] args) {
        // Test with the provided sample input
        List<String> wordSet = Arrays.asList("the", "bats", "tabs", "in", "cat", "act");
        List<String> sentences = Arrays.asList("cat the bats", "in the act", "act tabs in");
        List<Long> output = countSentences(wordSet, sentences);

        // Expected from sample: [4, 2, 4]
        List<Long> expected = Arrays.asList(4L, 2L, 4L);

        if (output.equals(expected)) {
            System.out.println("Sample Test: PASS");
        } else {
            System.out.println("Sample Test: FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + output);
        }

        // Additional test: no anagrams found
        List<String> wordSet2 = List.of("abc");
        List<String> sentences2 = List.of("xyz");
        List<Long> output2 = countSentences(wordSet2, sentences2);
        // Expect 0 because xyz doesn't match any word or anagram
        if (output2.get(0) == 0L) {
            System.out.println("No Anagram Test: PASS");
        } else {
            System.out.println("No Anagram Test: FAIL");
            System.out.println("Expected: 0");
            System.out.println("Got: " + output2.get(0));
        }

        // Additional test: multiple identical words
        List<String> wordSet3 = Arrays.asList("act", "cat");
        List<String> sentences3 = List.of("act act");
        List<Long> output3 = countSentences(wordSet3, sentences3);
        // Each "act" can be "act" or "cat" → 2 * 2 = 4
        if (output3.get(0) == 4L) {
            System.out.println("Identical Words Test: PASS");
        } else {
            System.out.println("Identical Words Test: FAIL");
            System.out.println("Expected: 4");
            System.out.println("Got: " + output3.get(0));
        }

        // Additional edge case: a sentence with a word not in wordSet at all
        List<String> wordSet4 = Arrays.asList("listen", "silent", "it", "is");
        List<String> sentences4 = Arrays.asList("listen it is silent", "hello world");
        // For first: "listen" <-> "silent" anagrams, "listen" signature = "eilnst", "silent" signature = "eilnst"
        // "listen" can be replaced with "listen" or "silent" → 2 options
        // "it" → "it" only → 1
        // "is" → "is" only → 1
        // "silent" → "eilnst" again → 2 options
        // total = 2 * 1 * 1 * 2 = 4
        // For second: "hello" sorted = "ehllo", not in set; no anagrams → 0
        List<Long> output4 = countSentences(wordSet4, sentences4);
        if (output4.get(0) == 4L && output4.get(1) == 0L) {
            System.out.println("Mixed Case Test: PASS");
        } else {
            System.out.println("Mixed Case Test: FAIL");
            System.out.println("Expected: [4, 0]");
            System.out.println("Got: " + output4);
        }

        // Testing large data (pseudo test, no actual big data here)
        // In a real scenario, we would generate a large set and a sentence, but here we trust complexity analysis.
        System.out.println("All done!");
    }
}

package com.interview.notes.code.months.feb24.test1;

import java.util.HashMap;
import java.util.Map;

/**
 *

 In Java : Java find unique no in array without using exsiting  methods use cusotme one


 *. If you- need-more classes, simply define them- inline.
 Find• unique numbers in array without-using hash- map
 • For example, if given input array-is {1,2,4,2,3,5,1, 6,3,8} then output may-be - {4,5,6,8}
 */
public class MostFrequentWord {

    // Method to find the most occurring word in a sentence and its count
    public static String findMostFrequentWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "No words found, 0.";
        }

        // Split the sentence into words based on spaces
        String[] words = sentence.split("\\s+");

        // Map to store word frequencies
        Map<String, Integer> wordCounts = new HashMap<>();

        // Count occurrences of each word
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        // Variables to keep track of the most frequent word and its count
        String mostFrequentWord = "";
        int maxCount = 0;

        // Identify the most frequent word
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentWord + ", " + maxCount + ".";
    }

    // Main method for example execution
    public static void main(String[] args) {
        String input = "The interview went like any other interview";
        String output = findMostFrequentWord(input);
        System.out.println("Output: " + output);
    }
}

package com.interview.notes.code.year.y2024.march24.test16;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"bella", "label", "roller"};
        List<Character> result = solution.commonChars(words);
        System.out.println(result);
    }

    public List<Character> commonChars(String[] words) {
        int[] globalCount = new int[26]; // There are 26 letters in the alphabet
        for (char c : words[0].toCharArray()) { // Initialize with the first word
            globalCount[c - 'a']++;
        }

        // Compare with other words
        for (int i = 1; i < words.length; i++) {
            int[] localCount = new int[26]; // Count for the current word
            for (char c : words[i].toCharArray()) {
                localCount[c - 'a']++;
            }
            // Update global count to reflect the minimum count for each character
            for (int j = 0; j < 26; j++) {
                globalCount[j] = Math.min(globalCount[j], localCount[j]);
            }
        }

        // Collect characters based on global count
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (globalCount[i] > 0) {
                result.add((char) (i + 'a'));
                globalCount[i]--;
            }
        }

        return result;
    }
}

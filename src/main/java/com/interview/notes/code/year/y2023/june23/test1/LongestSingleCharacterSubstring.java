package com.interview.notes.code.year.y2023.june23.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestSingleCharacterSubstring {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "aaaabaaa";

        // Create a map to store the frequency of each character.
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Find the longest substring that occurs at least thrice.
        int maxLen = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= 3) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    maxLen = entry.getKey().charValue();
                }
            }
        }

        System.out.println(maxLen);
    }
}

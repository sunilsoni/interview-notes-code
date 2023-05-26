package com.interview.notes.code.test.test5;

import java.util.HashMap;
import java.util.Map;

public class LongestSingleCharacterSubstring {

    public static void main(String[] args) {
        String str = "Tpere";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int maxLen = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 3) {
                maxLen = Math.max(maxLen, entry.getValue());
            }
        }

        System.out.println(maxLen);
    }
}

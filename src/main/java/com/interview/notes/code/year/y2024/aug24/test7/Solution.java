package com.interview.notes.code.year.y2024.aug24.test7;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * Finds the first character that does not repeat anywhere in the input string
     * If all characters are repeated, return 0
     * Given "apple", the answer is "a"
     * Given "racecars", the answer is "e"
     * Given "ababdc", the answer is "d"
     **/
    public static char findFirst(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> charCount = new LinkedHashMap<>();
        Set<Character> repeatedChars = new HashSet<>();

        // Count occurrences of each character
        for (char c : input.toCharArray()) {
            if (repeatedChars.contains(c)) {
                continue;
            }
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            if (charCount.get(c) > 1) {
                repeatedChars.add(c);
                charCount.remove(c);
            }
        }

        // Return the first character with count 1
        return charCount.isEmpty() ? 0 : charCount.keySet().iterator().next();
    }

    public static boolean doTestsPass() {
        // TODO: implement more tests, please
        // feel free to make testing more elegant
        boolean result = true;
        result = result && findFirst("apple") == 'a';
        result = result && findFirst("racecars") == 'e';
        result = result && findFirst("ababdc") == 'd';
        result = result && findFirst("") == 0;
        result = result && findFirst("aabbcc") == 0;
        result = result && findFirst("aabbcdeeff") == 'c';
        result = result && findFirst("aabcbdeeff") == 'c';
        result = result && findFirst("abcdef") == 'a';
        return result;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }

        // Additional test cases
        System.out.println(findFirst("apple")); // Expected: a
        System.out.println(findFirst("racecars")); // Expected: e
        System.out.println(findFirst("ababdc")); // Expected: d
        System.out.println(findFirst("")); // Expected: 0 (null character)
        System.out.println(findFirst("aabbcc")); // Expected: 0 (null character)
        System.out.println(findFirst("aabbcdeeff")); // Expected: c
        System.out.println(findFirst("abcdef")); // Expected: a
    }
}

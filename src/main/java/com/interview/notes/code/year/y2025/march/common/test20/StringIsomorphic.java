package com.interview.notes.code.year.y2025.march.common.test20;

import java.util.HashMap;
import java.util.Map;

public class StringIsomorphic {
    
    // Main method to test the solution
    public static void main(String[] args) {
        // Test cases
        test("egg", "add");      // Expected: true
        test("foo", "bar");      // Expected: false
        test("paper", "title");  // Expected: true
        test("badc", "baba");    // Expected: false
        test("", "");            // Expected: true
        test("a", "b");          // Expected: true
    }

    // Method to check if two strings are isomorphic
    public static boolean isIsomorphic(String s, String t) {
        // If lengths are different, strings can't be isomorphic
        if (s.length() != t.length()) {
            return false;
        }

        // Create two maps to store character mappings
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        // Iterate through each character
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check mapping from s to t
            if (sToT.containsKey(charS)) {
                if (sToT.get(charS) != charT) {
                    return false;
                }
            } else {
                sToT.put(charS, charT);
            }

            // Check mapping from t to s
            if (tToS.containsKey(charT)) {
                if (tToS.get(charT) != charS) {
                    return false;
                }
            } else {
                tToS.put(charT, charS);
            }
        }

        return true;
    }

    // Test helper method
    private static void test(String s, String t) {
        boolean result = isIsomorphic(s, t);
        System.out.printf("Test: '%s' and '%s' -> %b%n", s, t, result);
    }
}

package com.interview.notes.code.months.nov24.amazon.test23;

import java.util.*;

//Check /Users/sunilsoni/Documents/Work/Interview/interview-notes-code/src/main/java/com/interview/notes/code/months/nov24/amazon/test21/DominantSubstringCounter.java
public class DominantStringCounter {
    public static void main(String[] args) {
        testDominantStringCount();
    }

    public static long getDominantStringCount(String s) {
        long count = 0;
        int n = s.length();
        
        for (int len = 2; len <= n; len += 2) {
            Map<Character, Integer> freqMap = new HashMap<>();
            
            // Initialize frequency map for the first window
            for (int i = 0; i < len; i++) {
                freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
            }
            
            if (isDominantString(freqMap, len)) {
                count++;
            }
            
            // Slide the window
            for (int i = len; i < n; i++) {
                char removed = s.charAt(i - len);
                char added = s.charAt(i);
                
                freqMap.put(removed, freqMap.get(removed) - 1);
                if (freqMap.get(removed) == 0) {
                    freqMap.remove(removed);
                }
                freqMap.put(added, freqMap.getOrDefault(added, 0) + 1);
                
                if (isDominantString(freqMap, len)) {
                    count++;
                }
            }
        }
        
        return count;
    }

    private static boolean isDominantString(Map<Character, Integer> freqMap, int len) {
        for (int freq : freqMap.values()) {
            if (freq == len / 2) return true;
        }
        return false;
    }

    private static void testDominantStringCount() {
        String[] testCases = {
            "aaaaid",
            "abab",
            "idafddfii",
            "aabbccddee",
            "abcdefghij",
            "aaaaaaaaaa",
            "abcabcabcabc",
            "a".repeat(100000)
        };

        long[] expectedResults = {3, 4, 13, 15, 0, 55, 40, 4999950000L};

        for (int i = 0; i < testCases.length; i++) {
            long start = System.currentTimeMillis();
            long result = getDominantStringCount(testCases[i]);
            long end = System.currentTimeMillis();

            System.out.printf("Test Case %d: %s\n", i + 1, result == expectedResults[i] ? "PASS" : "FAIL");
            System.out.printf("Input: %s\n", testCases[i].length() > 20 ? testCases[i].substring(0, 20) + "..." : testCases[i]);
            System.out.printf("Expected: %d, Got: %d\n", expectedResults[i], result);
            System.out.printf("Time taken: %d ms\n\n", end - start);
        }
    }
}

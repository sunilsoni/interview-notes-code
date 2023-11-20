package com.interview.notes.code.months.nov23.test6;

import java.util.HashSet;
import java.util.Set;

public class UniqueCharsSubstring {
    public static int countUniqueChars(String s) {
        int n = s.length();
        int totalUniqueCount = 0;

        // Iterate through all possible substrings
        for (int start = 0; start < n; start++) {
            Set<Character> uniqueChars = new HashSet<>();
            
            for (int end = start; end < n; end++) {
                // Add characters to the set to count unique characters
                char c = s.charAt(end);
                if (!uniqueChars.contains(c)) {
                    uniqueChars.add(c);
                    totalUniqueCount += uniqueChars.size();
                }
            }
        }
        
        return totalUniqueCount;
    }

    public static void main(String[] args) {
        String s1 = "ABC";
        String s2 = "ABA";
        String s3 = "LEETCODE";

        // Test cases
        System.out.println(countUniqueChars(s1)); // Output: 10
        System.out.println(countUniqueChars(s2)); // Output: 8
        System.out.println(countUniqueChars(s3)); // Output: 92
    }
}

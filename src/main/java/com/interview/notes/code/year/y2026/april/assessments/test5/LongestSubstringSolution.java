package com.interview.notes.code.year.y2026.april.assessments.test5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubstringSolution { // main class

    public static int longestUnique(String s) { // method to find longest substring length
        
        Set<Character> set = new HashSet<>(); // set to store unique characters in window
        
        int left = 0; // left pointer of window
        int max = 0; // store max length
        
        for (int right = 0; right < s.length(); right++) { // iterate using right pointer
            
            while (set.contains(s.charAt(right))) { // if duplicate found
                
                set.remove(s.charAt(left)); // remove left character from set
                left++; // move left pointer forward
                
            }
            
            set.add(s.charAt(right)); // add current character to set
            
            max = Math.max(max, right - left + 1); // update max length
            
        }
        
        return max; // return result
    }

    public static void main(String[] args) { // main method for testing
        
        List<String> tests = List.of( // test cases
            "ADCABC", // expected 3
            "ABCABCBB", // expected 3
            "BBBB", // expected 1
            "", // expected 0
            "A", // expected 1
            "ABDEFGABEF" // expected 6
        );

        List<Integer> expected = List.of(3, 3, 1, 0, 1, 6); // expected outputs
        
        for (int i = 0; i < tests.size(); i++) { // loop through test cases
            
            int result = longestUnique(tests.get(i)); // call method
            
            if (result == expected.get(i)) { // check result
                
                System.out.println("PASS | input=" + tests.get(i) + " | output=" + result);
                
            } else { // if fail
                
                System.out.println("FAIL | input=" + tests.get(i) + " | got=" + result + " expected=" + expected.get(i));
                
            }
        }
        
        // Large test case
        String large = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".repeat(10000); // large input
        
        System.out.println("Large Test Output: " + longestUnique(large)); // should be 26
    }
}
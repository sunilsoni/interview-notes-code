package com.interview.notes.code.june23.test2;

import java.util.HashSet;
import java.util.Set;

/*
 Here is a string: “You are the best programmer in the United States”.  Please write a code to return the vowels from the string.

 */
public class FindVowels {

    public static void main(String[] args) {
        String input = "You are the best programmer in the United States";
        String vowels = getVowels(input);
        System.out.println("Vowels: " + vowels);
    }

    static String getVowels(String input) {
        Set<Character> seen = new HashSet<>();
        StringBuilder vowels = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') && !seen.contains(c)) {
                vowels.append(c);
                seen.add(c);
            }
        }
        return vowels.toString();
    }
}

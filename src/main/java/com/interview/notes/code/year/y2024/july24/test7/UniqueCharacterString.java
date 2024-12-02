package com.interview.notes.code.year.y2024.july24.test7;

import java.util.HashSet;
import java.util.Set;

public class UniqueCharacterString {

    public static String removeDuplicates(String input) {
        Set<Character> seen = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("aabbccdd"));       // Output: abcd
        System.out.println(removeDuplicates("cabpqrabc"));      // Output: cabpqr
        System.out.println(removeDuplicates("jjaaqqivvp"));      // Output: jaqivp
        System.out.println(removeDuplicates("cAb123abc321"));    // Output: cAb123
    }
}

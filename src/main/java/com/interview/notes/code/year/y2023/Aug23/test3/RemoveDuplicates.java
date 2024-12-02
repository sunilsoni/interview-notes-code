package com.interview.notes.code.year.y2023.Aug23.test3;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {

    public static void main(String[] args) {
        String input = "Happy Thursday Aashish Rao!";
        System.out.println(removeDuplicates(input));  // Output: Hapy Thursdio!
    }

    public static String removeDuplicates(String input) {
        StringBuilder result = new StringBuilder();
        // Use a HashSet to store characters we've already seen.
        Set<Character> seen = new HashSet<>();

        for (char ch : input.toCharArray()) {
            // Check if we've seen the lowercase version of this character before.
            if (!seen.contains(Character.toLowerCase(ch))) {
                result.append(ch);
                seen.add(Character.toLowerCase(ch));
            }
        }

        return result.toString();
    }
}

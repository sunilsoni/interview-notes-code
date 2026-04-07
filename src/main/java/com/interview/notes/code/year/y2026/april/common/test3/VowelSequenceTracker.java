package com.interview.notes.code.year.y2026.april.common.test3;

public class VowelSequenceTracker {
    public static void main(String[] args) {
        String input = "aeieou";
        printLongestVowelSequence(input);
    }

    public static void printLongestVowelSequence(String s) {
        StringBuilder result = new StringBuilder();
        String vowels = "aeiou";
        int vowelIdx = 0;

        // We iterate through the string to pick characters in the right order
        for (char c : s.toCharArray()) {
            // Rule 1: If it's the current vowel we are looking for, keep it (Duplicate)
            if (vowelIdx < 5 && c == vowels.charAt(vowelIdx)) {
                result.append(c);
            } 
            // Rule 2: If it's the NEXT vowel in the alphabet, move forward and keep it
            else if (vowelIdx < 4 && c == vowels.charAt(vowelIdx + 1)) {
                vowelIdx++;
                result.append(c);
            }
            // Rule 3: If it's 'misplaced' (like the 'e' after 'i'), we skip it
        }

        // Final Validation: Did we reach 'u'? (Index 4)
        if (vowelIdx == 4) {
            System.out.println("Resulting Substring: " + result);
            System.out.println("Length: " + result.length());
        } else {
            System.out.println("Incomplete Sequence. Length: 0");
        }
    }
}
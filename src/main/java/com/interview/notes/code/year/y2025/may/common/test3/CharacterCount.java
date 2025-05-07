package com.interview.notes.code.year.y2025.may.common.test3;

public class CharacterCount {
    public static void main(String[] args) {
        String s = "Code to challenge";
        
        // Convert string to lowercase to count same letters regardless of case
        s = s.toLowerCase();
        
        // Create an array to store character counts (for all ASCII characters)
        int[] charCount = new int[256];
        
        // Count occurrences of each character
        for(char c : s.toCharArray()) {
            charCount[c]++;
        }
        
        // Print the count of each character that appears in the string
        for(int i = 0; i < charCount.length; i++) {
            if(charCount[i] > 0 && Character.isLetter((char)i)) {
                System.out.println((char)i + ": " + charCount[i]);
            }
        }
    }
}

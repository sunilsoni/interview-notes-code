package com.interview.notes.code.months.year2023.Aug23.test5;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        String input = "be implemented the weekend of March 17. DU 10.2 will include an adjustment to the DU credit risk assessment and other updates";

        // Replace punctuation marks with spaces and split the string into words
        String[] words = input.replaceAll("[^a-zA-Z0-9 ]", " ").split("\\s+");

        int minLength = Integer.MAX_VALUE;
        List<String> shortestWords = new ArrayList<>();

        // Loop to find the shortest words
        for (String word : words) {
            if (word.length() < minLength) {
                minLength = word.length();
                shortestWords.clear();  // Clear the list as we've found a shorter word
                shortestWords.add(word);  // Add the new shortest word to the list
            } else if (word.length() == minLength) {
                shortestWords.add(word);  // Add the word if it has the same shortest length
            }
        }

        // Print the shortest words
        System.out.println("The shortest words are: " + shortestWords);
    }
}

package com.interview.notes.code.july23.test12;

import java.util.Arrays;
import java.util.List;

public class BadWordChecker {

  // List of bad words
  static List<String> bad_words = Arrays.asList("jerk", "butt head", "lame");

  public static void main(String[] args) {
    String message = "That guy is a jerk.";
    boolean result = containsBadWord(message);
    System.out.println("Contains a bad word: " + result); // Output: Contains a bad word: true
  }

  // Function to check if a message contains a bad word
  public static boolean containsBadWord(String message) {
    for (String word : bad_words) {
      if (message.contains(word)) {
        return true; // Return true if a bad word is found
      }
    }
    return false; // Return false if no bad words are found
  }
}

package com.interview.notes.code.year.y2025.June.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*


Yeah. Let me open it. Yes, I'm here. All right. There you are. Okay, perfect. So let's imagine. A swipe style keyboard. This is on a phone where instead of typing out each individual. Character. We swipe from one character to another, okay?

So, for example. Instead of saying, Like, hi. Typing out each individual character. I might, like, swipe something like that. There might be extra characters. I'm swiping from all the characters I attend, but. There might be extra characters.

In between. At the beginning, at the end. Okay. So the question is, we want to implement this, so we're given as an input. The user input so all the characters they swiped over. And dictionary. So, like, just the list of possible words in their language. So, given these two.

What are the matching words? Possible words. Matching. Okay. User will swipe through characters. Users will swipe through. Yeah. So they'll swipe your characters, and I guess it'll show like the suggestions Mrs. Butter would be used for, okay.

Let's say what happened if you just swipe. What happens if he swipes? Hello? But actually. Let's say he's touching. Edge or something. Let me type here what I'm. Saying, if he's swiping, if he's swiping.

Let's say hello. User says hello. Okay, but actually, he touched. Some other, let's say. Hg. E. And. Maybe. Something like f and something like. That. So anyone. So in that case. The case is. What will happen. So in this case, do we need to find.

Possible words. In which case, do we have to find all the possible words? Yeah. So I think here, technically, hello, one match. Because. It doesn't have the double. Yeah. So maybe that's, like an advanced phase we want to add later, but for now, let's assume this does not match at all, okay?

 */
public class SwipeKeyboard {

    // Method to find all matching words from dictionary that match swipe pattern
    public static List<String> findMatchingWords(String swipePattern, List<String> dictionary) {
        List<String> matches = new ArrayList<>();

        // Iterate through each word in dictionary
        dictionary.stream()
                .filter(word -> isWordMatch(word, swipePattern))
                .forEach(matches::add);

        return matches;
    }

    // Helper method to check if word matches swipe pattern
    private static boolean isWordMatch(String word, String swipePattern) {
        int wordIndex = 0;    // Track position in word
        int patternIndex = 0; // Track position in swipe pattern

        // Try to find each letter of the word in the swipe pattern
        while (wordIndex < word.length() && patternIndex < swipePattern.length()) {
            if (word.charAt(wordIndex) == swipePattern.charAt(patternIndex)) {
                wordIndex++;   // Move to next letter in word
            }
            patternIndex++;   // Always move forward in pattern
        }

        // Word matches if we found all its letters
        return wordIndex == word.length();
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic matching
        String swipe1 = "hgeflmlko";
        List<String> dict1 = Arrays.asList("hello", "help", "world");
        System.out.println("Test 1: " + findMatchingWords(swipe1, dict1));
        // Expected: [hello]

        // Test Case 2: Multiple matches
        String swipe2 = "abcdefg";
        List<String> dict2 = Arrays.asList("ace", "bad", "ag");
        System.out.println("Test 2: " + findMatchingWords(swipe2, dict2));
        // Expected: [ace, ag]

        // Test Case 3: No matches
        String swipe3 = "xyz";
        List<String> dict3 = Arrays.asList("hello", "world");
        System.out.println("Test 3: " + findMatchingWords(swipe3, dict3));
        // Expected: []

        // Test Case 4: Large input test
        StringBuilder largeSwiping = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeSwiping.append("abcdefghijklmnopqrstuvwxyz");
        }
        List<String> largeDict = Arrays.asList("hello", "world", "test", "zebra");
        System.out.println("Test 4 (Large Input): " +
                findMatchingWords(largeSwiping.toString(), largeDict).size() + " matches found");
    }
}

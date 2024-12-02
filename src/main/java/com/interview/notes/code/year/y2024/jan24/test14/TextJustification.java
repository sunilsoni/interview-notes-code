package com.interview.notes.code.year.y2024.jan24.test14;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * <p>
 * Example 1:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16Output:[
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Example 2:
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16Output:[
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20Output:[
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * Constraints:
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 * <p>
 * has context menu
 */
public class TextJustification {
    // Main method to demonstrate text justification
    public static void main1(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> result = fullJustify(words, maxWidth);
        for (String line : result) {
            System.out.println("\"" + line + "\"");
        }
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedText = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                // If more words can't be added without exceeding maxWidth
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1; // 1 for the space
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1; // Calculate difference to distribute spaces

            // If last line or only one word in a line, left-justify
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i]).append(' ');
                }
                builder.deleteCharAt(builder.length() - 1); // Remove extra space
                while (builder.length() < maxWidth) { // Add spaces to the end if needed
                    builder.append(' ');
                }
            } else {
                // Distribute spaces evenly
                int spaces = (maxWidth - count) / diff;
                int extra = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) { // Add base spaces
                        for (int j = 0; j <= (spaces + ((i - index) < extra ? 1 : 0)); j++) {
                            builder.append(' ');
                        }
                    }
                }
            }
            justifiedText.add(builder.toString());
            index = last;
        }

        return justifiedText;
    }

    public static void main(String[] args) {
        // Example 1
        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth1 = 16;
        List<String> result1 = fullJustify(words1, maxWidth1);
        System.out.println("Example 1:");
        printResult(result1);

        // Example 2
        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth2 = 16;
        List<String> result2 = fullJustify(words2, maxWidth2);
        System.out.println("\nExample 2:");
        printResult(result2);

        // Example 3
        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth3 = 20;
        List<String> result3 = fullJustify(words3, maxWidth3);
        System.out.println("\nExample 3:");
        printResult(result3);
    }

    private static void printResult(List<String> result) {
        for (String line : result) {
            System.out.println("\"" + line + "\"");
        }
    }
}

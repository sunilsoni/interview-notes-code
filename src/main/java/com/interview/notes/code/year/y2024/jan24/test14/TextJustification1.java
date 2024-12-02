package com.interview.notes.code.year.y2024.jan24.test14;

import java.util.ArrayList;
import java.util.List;

public class TextJustification1 {
    public static void main(String[] args) {
        TextJustification1 textJustification = new TextJustification1();

        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth1 = 16;
        List<String> result1 = textJustification.fullJustify(words1, maxWidth1);
        System.out.println("Example 1 Output:");
        for (String line : result1) {
            System.out.println(line);
        }

        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth2 = 16;
        List<String> result2 = textJustification.fullJustify(words2, maxWidth2);
        System.out.println("\nExample 2 Output:");
        for (String line : result2) {
            System.out.println(line);
        }

        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth3 = 20;
        List<String> result3 = textJustification.fullJustify(words3, maxWidth3);
        System.out.println("\nExample 3 Output:");
        for (String line : result3) {
            System.out.println(line);
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left = 0;
        while (left < words.length) {
            int right = findRight(words, left, maxWidth);
            result.add(justify(words, left, right, maxWidth));
            left = right + 1;
        }
        return result;
    }

    private int findRight(String[] words, int left, int maxWidth) {
        int right = left;
        int sum = words[right++].length();
        while (right < words.length && sum + 1 + words[right].length() <= maxWidth) {
            sum += 1 + words[right++].length();
        }
        return right - 1;
    }

    private String justify(String[] words, int left, int right, int maxWidth) {
        if (right - left == 0) {
            return padRight(words[left], maxWidth);
        }
        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpaces = maxWidth - wordsLength(words, left, right);
        String space = isLastLine ? " " : blank(totalSpaces / numSpaces);
        int remainder = isLastLine ? 0 : totalSpaces % numSpaces;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]);
            sb.append(space);
            if (remainder-- > 0) {
                sb.append(" ");
            }
        }
        return padRight(sb.toString().trim(), maxWidth);
    }

    private int wordsLength(String[] words, int left, int right) {
        int length = 0;
        for (int i = left; i <= right; i++) {
            length += words[i].length();
        }
        return length;
    }

    private String padRight(String word, int maxWidth) {
        StringBuilder sb = new StringBuilder(word);
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String blank(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}

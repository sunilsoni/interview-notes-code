package com.interview.notes.code.months.Aug23.test6;

import java.util.ArrayList;
import java.util.List;

public class WordWrapper1 {

    public static List<String> wrapLines(List<String> words, int maxLen) {
        List<String> result = new ArrayList<>();

        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            if (currentLine.length() + word.length() <= maxLen) {
                if (currentLine.length() != 0) {
                    currentLine.append('-'); // append space as '-'
                }
                currentLine.append(word);
            } else {
                result.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            result.add(currentLine.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words1 = List.of("The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant", "flame");
        System.out.println(wrapLines(words1, 13));
        System.out.println(wrapLines(words1, 12));

        List<String> words2 = List.of("Hello");
        System.out.println(wrapLines(words2, 30));

        List<String> words3 = List.of("Hello", "Hello");
        System.out.println(wrapLines(words3, 5));

        List<String> words4 = List.of("Well", "Hello", "world");
        System.out.println(wrapLines(words4, 5));

        List<String> words5 = List.of("Hello", "HelloWorld", "Hello", "Hello");
        System.out.println(wrapLines(words5, 20));

        List<String> words6 = List.of("a", "b", "c", "d");
        System.out.println(wrapLines(words6, 20));
        System.out.println(wrapLines(words6, 4));
        System.out.println(wrapLines(words6, 1));


        System.out.println(wrapLines(words1, 13));
        System.out.println(wrapLines(words1, 12));
        System.out.println(wrapLines(words1, 20));
        System.out.println(wrapLines(words2, 5));
        System.out.println(wrapLines(words2, 30));
        System.out.println(wrapLines(words3, 5));
        System.out.println(wrapLines(words4, 5));
        System.out.println(wrapLines(words5, 20));
        System.out.println(wrapLines(words6, 20));
        System.out.println(wrapLines(words6, 4));
        System.out.println(wrapLines(words6, 1));
    }


}

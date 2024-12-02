package com.interview.notes.code.year.y2024.nov24.test5;

import java.util.ArrayList;
import java.util.List;

public class ReverseWords {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");
        words.add("Java");
        words.add("Programming");

        System.out.println("Original words: " + words);
        List<String> reversedWords = reverseEachWord(words);
        System.out.println("Reversed words: " + reversedWords);
    }

    public static List<String> reverseEachWord(List<String> words) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            String reversedWord = reverseString(word);
            result.add(reversedWord);
        }

        return result;
    }

    public static String reverseString(String str) {
        char[] chars = new char[str.length()];

        for (int i = 0, j = str.length() - 1; i < str.length(); i++, j--) {
            chars[i] = str.charAt(j);
        }

        return new String(chars);
    }
}

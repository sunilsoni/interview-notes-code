package com.interview.notes.code.test.test4;

public class ReverseWords {
    public static void main(String[] args) {
        String input = "Wednesday is today";
        String output = reverseWords(input);
        System.out.println(output);
    }

    public static String reverseWords(String input) {
        String[] words = input.split(" ");
        StringBuilder reversedSentence = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }

        return reversedSentence.toString().trim();
    }
}

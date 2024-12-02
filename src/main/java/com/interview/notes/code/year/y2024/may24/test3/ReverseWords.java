package com.interview.notes.code.year.y2024.may24.test3;

public class ReverseWords {
    public static String reverseWords(String inputString) {
        if (inputString == null || inputString.isEmpty()) return "";

        StringBuilder reversedString = new StringBuilder();
        int wordStart = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == ' ' || i == inputString.length() - 1) {
                String word = i == inputString.length() - 1 ? inputString.substring(wordStart) : inputString.substring(wordStart, i);
                StringBuilder reversedWord = new StringBuilder();
                for (int j = word.length() - 1; j >= 0; j--) {
                    reversedWord.append(word.charAt(j));
                }
                reversedString.append(reversedWord);
                if (i != inputString.length() - 1) {
                    reversedString.append(' ');
                }
                wordStart = i + 1;
            }
        }

        return reversedString.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("hello world")); // Output: "olleh dlrow"
        System.out.println(reverseWords("programming is fun")); // Output: "gnimmargorp si nuf"
        System.out.println(reverseWords("")); // Output: ""
    }
}

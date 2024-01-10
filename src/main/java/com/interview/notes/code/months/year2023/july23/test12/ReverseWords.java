package com.interview.notes.code.months.year2023.july23.test12;

public class ReverseWords {
    public static void main(String[] args) {
        String input = "Dog bites man";
        String reversedWords = reverseWordsInString(input);
        System.out.println(reversedWords); // Outputs: "man bites Dog"
    }

    public static String reverseWordsInString(String input) {
        // Split the input string by spaces to get an array of words
        String[] words = input.split(" "); // O(n)

        // Reverse the array of words
        int start = 0;
        int end = words.length - 1;
        while (start < end) { // O(n/2)
            String temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }

        // Join the reversed words with spaces
        return String.join(" ", words); // O(n)
    }
}

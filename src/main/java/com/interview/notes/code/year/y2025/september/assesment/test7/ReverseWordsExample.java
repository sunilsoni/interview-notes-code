package com.interview.notes.code.year.y2025.september.assesment.test7;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWordsExample {
    public static void main(String[] args) {
        String s = "my name is sukumar";

        // Split by space, reverse each word, then join with space
        String result = Arrays.stream(s.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString()) // reverse each word
                .collect(Collectors.joining(" "));                         // join back with spaces

        System.out.println("Original: " + s);
        System.out.println("Reversed Words: " + result);
    }
}
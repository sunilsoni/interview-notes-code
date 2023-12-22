package com.interview.notes.code.months.dec23.test5;

import java.util.Arrays;


public class VowelCheckJava8 {
    public static void main(String[] args) {
        String input = "Hello, World!";


        // Convert the input string to lowercase and split it into characters
        boolean hasVowel = Arrays.stream(input.toLowerCase().split(""))
                .anyMatch(ch -> "aeiou".contains(ch));


        if (hasVowel) {
            System.out.println("The string contains at least one vowel.");
        } else {
            System.out.println("The string does not contain any vowels.");
        }
    }
}





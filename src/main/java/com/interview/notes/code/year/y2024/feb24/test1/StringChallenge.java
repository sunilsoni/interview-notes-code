package com.interview.notes.code.year.y2024.feb24.test1;

import java.util.Scanner;

class StringChallenge {
    public static String StringChallenge(String sen) {
        String[] words = sen.split("\\W+");
        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        String token = "q2osjbva1c58";
        StringBuilder finalOutput = new StringBuilder();
        for (char c : longestWord.toCharArray()) {
            if (token.toLowerCase().indexOf(Character.toLowerCase(c)) == -1) {
                finalOutput.append(c);
            }
        }
        return finalOutput.length() == 0 ? "EMPTY" : finalOutput.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(StringChallenge(s.nextLine()));
        s.close();
    }
}

package com.interview.notes.code.year.y2025.may.common.test15;

import java.util.StringTokenizer;

public class TokenizerExample {
    public static void main(String[] args) {
        String input = "The,quick,brown,fox";

        // true means return the delimiter "," as tokens too
        StringTokenizer tokenizer = new StringTokenizer(input, ",", true);

        int tokenCount = tokenizer.countTokens();
        System.out.println("Token Count: " + tokenCount);

        // Print each token
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}

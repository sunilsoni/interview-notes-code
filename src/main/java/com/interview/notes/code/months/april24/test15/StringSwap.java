package com.interview.notes.code.months.april24.test15;

import java.util.stream.Collectors;

public class StringSwap {

    public static void main(String[] args) {
        String a = "abc";
        int b = 2;

        String result = swapCharacters(a, b);
        System.out.println("Output: " + result);
    }

    public static String swapCharacters(String str, int shift) {
        return str.chars()
                .mapToObj(ch -> (char) (((ch - 'a' + shift) % 26) + 'a'))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
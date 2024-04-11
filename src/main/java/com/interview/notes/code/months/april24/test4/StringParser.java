package com.interview.notes.code.months.april24.test4;

import java.util.stream.IntStream;

public class StringParser {
    public static void main(String[] args) {
        String input = "Hello, world!";
        IntStream.range(0, input.length())
                 .forEach(i -> System.out.println("Character " + i + ": " + input.charAt(i)));
    }
}

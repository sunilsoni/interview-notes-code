package com.interview.notes.code.june23.test9;

import java.util.stream.IntStream;

public class ReverseHelloWord {
    public static void main(String[] args) {
        String input = "Hello World";
        String reversed = reverse(input);
        System.out.println(reversed);
    }

    private static String reverse(String input) {
        return IntStream.rangeClosed(1, input.length())
                .mapToObj(i -> input.charAt(input.length() - i))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}

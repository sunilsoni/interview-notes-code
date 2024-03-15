package com.interview.notes.code.months.march24.test12;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main1 {
    public static void main(String[] args) {
        String input = "sreedhar"; // Replace with your input string
        int maxLength = 10;

        Predicate<String> validateLength = str -> str.length() == maxLength;

        String result = Stream.of(input)
                .map(str -> {
                    if (str.length() > maxLength) {
                        return str.substring(0, maxLength);
                    }
                    return str;
                })
                .map(str -> validateLength.test(str) ? str : "0")
                .findFirst().orElse("0");

        System.out.println(result);
    }
}

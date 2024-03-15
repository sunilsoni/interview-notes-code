package com.interview.notes.code.months.march24.test12;

import java.util.stream.Stream;

public class Main2 {
    public static void main(String[] args) {
        String input = "sreedhar"; // Replace with your input string
        int maxLength = 10;

        String result = Stream.of(input)
                .map(str -> {
                    if (str.length() > maxLength) {
                        return str.substring(0, maxLength);
                    } else {
                        StringBuilder padded = new StringBuilder(str);
                        while (padded.length() < maxLength) {
                            padded.append("0");
                        }
                        return padded.toString();
                    }
                })
                .findFirst().orElse("0");

        System.out.println(result);
    }
}

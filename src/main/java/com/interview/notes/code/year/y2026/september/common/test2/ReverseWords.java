package com.interview.notes.code.year.y2026.september.common.test2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWords {
    static void main(String[] args) {
        String s = "This is Java Development Interview";

        String result = Arrays.stream(s.trim().split("\\s+"))
                .toList()
                .reversed()
                .stream()
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
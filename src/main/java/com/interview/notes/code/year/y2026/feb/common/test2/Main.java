package com.interview.notes.code.year.y2026.feb.common.test2;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

class Main {
    public static void main(String[] args) {
        // Manual test cases input simulation
        // Format: First line is count, following lines are K
        String manualInput = """
                6
                808
                2133
                -100
                9
                12345
                99999
                """;

        System.out.println(codeHere(new StringBuilder(manualInput)));
    }

    public static String codeHere(StringBuilder inputData) {
        return inputData.toString().lines()
                .skip(1)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .map(k -> LongStream.iterate(k + 1, n -> n + 1)
                        .filter(n -> {
                            String s = String.valueOf(n);
                            return s.contentEquals(new StringBuilder(s).reverse());
                        })
                        .findFirst()
                        .getAsLong())
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }
}
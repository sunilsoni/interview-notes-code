package com.interview.notes.code.year.y2026.feb.common.test5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder inputData = new StringBuilder();
        String thisLine = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((thisLine = br.readLine()) != null) {
            inputData.append(thisLine + "\n");
        }
        System.out.println(codeHere(inputData));
    }

    public static String codeHere(StringBuilder inputData) {
        return Arrays.stream(inputData.toString().trim().split("\\n"))
            .skip(1)
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(Long::parseLong)
            .map(k -> java.util.stream.LongStream.iterate(k + 1, n -> n + 1)
                .filter(n -> {
                    String s = String.valueOf(n);
                    return s.contentEquals(new StringBuilder(s).reverse());
                })
                .findFirst()
                .getAsLong())
            .map(String::valueOf)
            .collect(java.util.stream.Collectors.joining("\n"));
    }
}
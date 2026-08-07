package com.interview.notes.code.year.y2026.august.assessments.test3;

public class Main {
    public static void main(String[] args) {

        String s = "I come through infosys.";

        char result = s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> s.indexOf(c) == s.lastIndexOf(c))
                .findFirst()
                .orElse('\0');

        System.out.println(result);
    }
}
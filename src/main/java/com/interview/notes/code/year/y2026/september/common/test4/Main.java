package com.interview.notes.code.year.y2026.september.common.test4;

public class Main {
    static void main(String[] args) {
        String s = "This is Java Development Interview";

        var result = s.chars()
                .filter(Character::isWhitespace)
                .mapToObj(c -> (char) c)
                .toList()
                .reversed();

        System.out.println(result);
    }
}
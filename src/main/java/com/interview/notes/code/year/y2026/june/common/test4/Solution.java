package com.interview.notes.code.year.y2026.june.common.test4;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {

        String input = "What is the output of this problem";
        Character result = null;

        Set<Character> seen = new HashSet<>();

        result = input.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> !Character.isWhitespace(ch))
                .filter(ch -> !seen.add(ch))
                .findFirst()
                .orElse(null);

        System.out.println("Output : " + result);
    }
}
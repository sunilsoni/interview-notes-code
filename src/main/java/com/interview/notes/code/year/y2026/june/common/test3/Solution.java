package com.interview.notes.code.year.y2026.june.common.test3;

class Solution {
    public static void main(String[] args) {

        String input = "What is the output of this problem";
        Character result = null;

        // Logic here
        result = input.chars()
                .mapToObj(c -> (char) c) // int stream to Character stream
                .filter(ch -> ch != ' ') // ignore spaces
                .filter(ch -> input.indexOf(ch) != input.lastIndexOf(ch)) // repeated later
                .findFirst()
                .orElse(null);

        System.out.println("Output : " + result);
    }
}
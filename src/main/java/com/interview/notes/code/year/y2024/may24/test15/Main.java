package com.interview.notes.code.year.y2024.may24.test15;

public class Main {
    public static void main(String[] args) {
        String input = "abbbabc";
        System.out.println(removeAdjacentDuplicates(input));
    }

    public static String removeAdjacentDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            sb.append(currentChar);
            while (i < s.length() && s.charAt(i) == currentChar) {
                i++;
            }
        }
        String result = sb.toString();
        return result.equals(s) ? result : removeAdjacentDuplicates(result);
    }
}
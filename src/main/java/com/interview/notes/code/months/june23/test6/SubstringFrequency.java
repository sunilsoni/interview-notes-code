package com.interview.notes.code.months.june23.test6;

public class SubstringFrequency {
    public static int getSubstringFrequency(String input, String substring) {
        int count = 0;
        int index = input.indexOf(substring);

        while (index != -1) {
            count++;
            index = input.indexOf(substring, index + substring.length());
        }

        return count;
    }

    public static void main(String[] args) {
        String input = "Hello, Hello, Hello!";
        String substring = "Hello";

        int frequency = getSubstringFrequency(input, substring);
        System.out.println("Frequency of \"" + substring + "\": " + frequency);
    }
}

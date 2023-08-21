package com.interview.notes.code.Aug23.test1;

public class StringCombinations {
    public static void main(String[] args) {
        String input = "ABC";
        printCombinations(input);
    }

    public static void printCombinations(String input) {
        generateCombinations(input, "");
    }

    private static void generateCombinations(String input, String prefix) {
        int n = input.length();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generateCombinations(input.substring(0, i) + input.substring(i + 1, n), prefix + input.charAt(i));
            }
        }
    }
}

package com.interview.notes.code.year.y2026.july.common.test6;

public class Solution {

    public static double shortestDistance(String document, String word1, String word2) {
        String[] words = document.split(" ");

        int index = 0;
        double shortest = document.length();
        double word1Loc = 0;
        double word2Loc = 0;

        for (String word : words) {
            if (word.equals(word1)) {
                word1Loc = index + (word.length() / 2);
            } else if (word.equals(word2)) {
                word2Loc = index + (word.length() / 2);
            }

            if (word1Loc > 0 && word2Loc > 0) {
                double current = word2Loc - word1Loc;
                if (current < shortest) {
                    shortest = current;
                }
            }

            index += word.length();
        }

        if (word1Loc == 0 || word2Loc == 0) {
            return -1;
        }

        return shortest;
    }

    public static boolean doTestsPass() {
        String document = "This is a sample document we just made up";

        return shortestDistance(document, "we", "just") == 4
                && shortestDistance(document, "is", "a") == 2.5
                && shortestDistance(document, "is", "not") == -1;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");

            String document = "This is a sample document we just made up";

            System.out.println("we, just = " + shortestDistance(document, "we", "just"));
            System.out.println("is, a = " + shortestDistance(document, "is", "a"));
            System.out.println("is, not = " + shortestDistance(document, "is", "not"));
        }
    }
}
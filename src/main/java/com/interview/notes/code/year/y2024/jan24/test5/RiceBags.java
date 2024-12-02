package com.interview.notes.code.year.y2024.jan24.test5;

import java.util.Arrays;
import java.util.List;

public class RiceBags {
    static int checkRiceBags(List<Integer> riceBags) {
        int queryWeight = 1000000;
        return checkCombination(riceBags, 0, queryWeight) ? 1 : 0;
    }

    static boolean checkCombination(List<Integer> riceBags, int start, int target) {
        if (target == 0) return true;
        if (target < 0 || start == riceBags.size()) return false;

        if (checkCombination(riceBags, start + 1, target - riceBags.get(start))) return true;

        return checkCombination(riceBags, start + 1, target);
    }

    public static void main(String[] args) {
        List<Integer> riceBags = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);// ...initialize your list...
        System.out.println(checkRiceBags(riceBags));
    }
}

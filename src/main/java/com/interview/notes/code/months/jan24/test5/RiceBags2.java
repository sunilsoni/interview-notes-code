package com.interview.notes.code.months.jan24.test5;

public class RiceBags2 {
    static boolean checkRiceBags(int[] riceBags, int queryWeight) {
        return checkCombination(riceBags, 0, queryWeight);
    }

    static boolean checkCombination(int[] riceBags, int start, int target) {
        if (target == 0) return true;
        if (target < 0 || start == riceBags.length) return false;

        if (checkCombination(riceBags, start + 1, target - riceBags[start])) return true;

        return checkCombination(riceBags, start + 1, target);
    }

    public static void main(String[] args) {
        int[] riceBags = { /* Your array of rice bags */};
        int queryWeight = 1;/* Your query weight */
        ;
        System.out.println(checkRiceBags(riceBags, queryWeight));
    }
}

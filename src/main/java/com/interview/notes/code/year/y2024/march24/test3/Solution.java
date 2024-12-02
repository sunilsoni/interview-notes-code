package com.interview.notes.code.year.y2024.march24.test3;

import java.util.HashSet;
import java.util.List;

public class Solution {

    static boolean hasDistinctDigits(int number) {
        HashSet<Character> digits = new HashSet<>();
        char[] numChars = String.valueOf(number).toCharArray();
        for (char numChar : numChars) {
            if (digits.contains(numChar)) {
                return false;
            }
            digits.add(numChar);
        }
        return true;
    }

    static void countNumbers(List<List<Integer>> arr) {
        for (List<Integer> range : arr) {
            int start = range.get(0);
            int end = range.get(1);
            int count = 0;
            for (int i = start; i <= end; i++) {
                if (hasDistinctDigits(i)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        // You can test your function here
    }
}

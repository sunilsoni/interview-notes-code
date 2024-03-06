package com.interview.notes.code.months.march24.test3;

import java.util.Arrays;

public class WordMover {

    public static void main(String[] args) {
        String[] words = {"a", "z", "chase", "c", "chase", "d"};
        moveWordToEnd(words, "chase");
        System.out.println(Arrays.toString(words));
    }

    public static void moveWordToEnd(String[] words, String target) {
        int insertIndex = 0;
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(target)) {
                words[insertIndex++] = words[i];
            }
        }

        while (insertIndex < words.length) {
            words[insertIndex++] = target;
        }
    }
}

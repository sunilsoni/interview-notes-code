package com.interview.notes.code.anagram;

public class Anagrams {
    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        } else {
            for (int i = 0; i < a.length(); i++) {
                char ch = a.toLowerCase().charAt(i);
                b = b.toLowerCase();
                if (b.indexOf(ch) != -1) {
                    b = b.replaceFirst(ch + "", "");
                } else {
                    return false;
                }
            }
            return b.length() == 0;
        }
    }
}

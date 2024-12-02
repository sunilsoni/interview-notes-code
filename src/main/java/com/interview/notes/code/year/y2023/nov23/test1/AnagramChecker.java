package com.interview.notes.code.year.y2023.nov23.test1;

public class AnagramChecker {

    public static boolean areAnagrams(String string1, String string2) {
        if (string1 == null || string2 == null || string1.length() != string2.length()) {
            return false;
        }

        int[] charCountArray = new int[256];

        for (int i = 0; i < string1.length(); i++) {
            charCountArray[string1.charAt(i)]++;
        }

        for (int i = 0; i < string2.length(); i++) {
            charCountArray[string2.charAt(i)]--;
        }

        // Check if all counts are zero
        for (int count : charCountArray) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("listen,silent " + areAnagrams("listen", "silent"));
        System.out.println("listen,silenl " + areAnagrams("listen", "silenl"));
    }
}

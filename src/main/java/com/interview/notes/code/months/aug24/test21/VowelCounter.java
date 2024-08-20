package com.interview.notes.code.months.aug24.test21;

public class VowelCounter {
    public static int countVowels(String str) {
        int count = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String text = "Hello, how are you doing today?";
        int vowelCount = countVowels(text);
        System.out.println("The number of vowels is: " + vowelCount);
    }
}

package com.interview.notes.code.year.y2026.august.common.test5;

public class StringReverser {
    public static void main(String[] args) {
        String input = "nave";
        char[] chars = input.toCharArray();
        
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        
        String reversed = new String(chars);
        System.out.println(reversed); // evan
    }
}
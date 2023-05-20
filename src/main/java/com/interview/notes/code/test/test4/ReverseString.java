package com.interview.notes.code.test.test4;

public class ReverseString {
    public static void main(String[] args) {
        String original = "Hello, World!";
        String reversed = reverseString(original);
        System.out.println("Original string: " + original);
        System.out.println("Reversed string: " + reversed);
    }
    
    public static String reverseString(String input) {
        char[] characters = input.toCharArray();
        int start = 0;
        int end = input.length() - 1;
        
        while (start < end) {
            char temp = characters[start];
            characters[start] = characters[end];
            characters[end] = temp;
            start++;
            end--;
        }
        
        return new String(characters);
    }
}

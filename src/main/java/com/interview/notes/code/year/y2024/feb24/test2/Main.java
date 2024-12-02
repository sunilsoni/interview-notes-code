package com.interview.notes.code.year.y2024.feb24.test2;

public class Main {
    public static void main(String[] args) {
        String s1 = "abc";
        //String s2 = "abc";
        String s2 = new String("abc");
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
    }
}

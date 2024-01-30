package com.interview.notes.code.months.jan24.test6;

public class IndexOfTesting {
    public static void main(String[] args) {
        String s1 = "helloworld";
        String s2 = "w0rld";

        int index = s1.indexOf(s2);

        if (index != -1) {
            System.out.println("Substring '" + s2 + "' found at index " + index);
        } else {
            System.out.println("Substring '" + s2 + "' not found.");
        }

    }
}

package com.interview.notes.code.Aug23.test4;

public class StringTransformer {

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(transformString(s)); // Expected output: a1b1c1d1
    }

    public static String transformString(String s) {
        StringBuilder transformed = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            transformed.append(s.charAt(i)).append(1);
        }

        return transformed.toString();
    }
}

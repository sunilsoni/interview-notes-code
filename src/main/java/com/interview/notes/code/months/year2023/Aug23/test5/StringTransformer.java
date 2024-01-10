package com.interview.notes.code.months.year2023.Aug23.test5;

public class StringTransformer {

    public static void main(String[] args) {
        String s = "aabb";
        System.out.println(transformString(s)); // Expected output: a2b2
    }

    public static String transformString(String s) {
        StringBuilder transformed = new StringBuilder();

        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                transformed.append(s.charAt(i - 1)).append(count);
                count = 1;
            } else {
                count++;
            }
        }

        return transformed.toString();
    }
}


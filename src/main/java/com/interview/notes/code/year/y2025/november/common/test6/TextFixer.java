package com.interview.notes.code.year.y2025.november.common.test6;

public class TextFixer {
    public static String fix(String s) {
        return java.util.Arrays.stream(s.split("\\s+"))
                .filter(w -> !w.equalsIgnoreCase("error"))
                .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase())
                .collect(java.util.stream.Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(fix("hello world error from java ERROR"));
    }
}

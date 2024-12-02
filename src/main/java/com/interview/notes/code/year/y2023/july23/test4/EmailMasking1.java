package com.interview.notes.code.year.y2023.july23.test4;

public class EmailMasking1 {
    public static void main(String[] args) {
        String s = "My official email is admin@esi.com and my personal email is peter@gmail.com";
        String maskedString = maskEmailAddresses(s);

        System.out.println(maskedString);

        String s1 = "My official email is my@esi.com and my personal email is test@gmail.com";

        String maskedString1 = s1.replaceAll("\\b(\\w+)(@\\w+\\.\\w+)\\b", "#####$2");
        System.out.println(maskedString1);

        String maskedString2 = s1.replaceAll("\\b(\\w{2})(\\w*)(@\\w+\\.\\w+)\\b", "##$2$3");
        System.out.println(maskedString2);
    }

    public static String maskEmailAddresses(String input) {
        return input.replaceAll("\\b(\\w+)(@\\w+\\.\\w+)\\b", "#####$2");
    }
}

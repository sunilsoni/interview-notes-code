package com.interview.notes.code.months.year2023.july23.test4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskEmail {

    public static String maskEmail(String email) {
        Pattern pattern = Pattern.compile("(?<=@)\\w+(?=\\.com)");
        Matcher matcher = pattern.matcher(email);
        String maskedEmail = matcher.replaceAll("#");
        return maskedEmail;
    }

    public static void main(String[] args) {
        String email = "My official email is admin@esi.com and my personal email is peter@gmail.com";
        String maskedEmail = maskEmail(email);
        System.out.println(maskedEmail);
    }
}

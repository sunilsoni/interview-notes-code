package com.interview.notes.code.months.july23.test4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMasking {
    public static void main(String[] args) {
        String s = "My official email is admin@esi.com and my personal email is peter@gmail.com";

        String maskedString = maskEmails(s);

        System.out.println(maskedString);
    }

    private static String maskEmails(String s) {
        Pattern pattern = Pattern.compile("\\b(\\w)(\\w*)@(\\w+\\.\\w+)\\b");
        Matcher matcher = pattern.matcher(s);

        StringBuilder sb = new StringBuilder();
        int previousEnd = 0;

        while (matcher.find()) {
            String username = matcher.group(1);
            String domain = matcher.group(3);

            String maskedUsername = new String(new char[matcher.group().length() - 1]).replace('\0', '#');

            sb.append(s, previousEnd, matcher.start(1))
                    .append(maskedUsername)
                    .append("@")
                    .append(domain);

            previousEnd = matcher.end(3);
        }

        sb.append(s.substring(previousEnd));

        return sb.toString();
    }
}

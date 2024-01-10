package com.interview.notes.code.months.year2023.july23.test4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskEmailAddress {

    public static void main(String[] args) {
        String s = "My official email is admin@esi.com and my personal email is peter@gmail.com";

        Pattern pattern = Pattern.compile("(?<=@)\\w+(?=\\.com)");
        Matcher matcher = pattern.matcher(s);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "#");
        }
        matcher.appendTail(sb);

        System.out.println(sb.toString());
    }
}

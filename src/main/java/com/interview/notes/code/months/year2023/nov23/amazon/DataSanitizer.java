package com.interview.notes.code.months.year2023.nov23.amazon;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DataSanitizer {

    public static String sanitizeData(String input) {
        StringBuilder sanitizedData = new StringBuilder();
        Charset utf8Charset = StandardCharsets.UTF_8;

        for (int i = 0; i < input.length(); ) {
            int codePoint = input.codePointAt(i);
            if (utf8Charset.newEncoder().canEncode((char) codePoint)) {
                sanitizedData.appendCodePoint(codePoint);
            }
            // Move to the next character(s)
            i += Character.charCount(codePoint);
        }

        return sanitizedData.toString();
    }

    public static void main(String[] args) {
        String inputData = "Your input data with non-UTF-8 characters here";
        String sanitizedData = sanitizeData(inputData);
        System.out.println("Sanitized Data: " + sanitizedData);
    }
}

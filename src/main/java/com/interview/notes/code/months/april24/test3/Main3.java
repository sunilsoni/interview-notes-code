package com.interview.notes.code.months.april24.test3;

import java.util.regex.*;

class Main3 {

    public static String StringChallenge(String str) {
        String[] parts = str.split(" ");
        String patternStr = parts[0];
        String text = parts[1];

        // Convert the pattern into a regular expression
        String regex = buildRegex(patternStr);

        // Match the pattern with the text
        return text.matches(regex) ? "true" : "false";
    }

    // Helper function to build the regex from the pattern
    private static String buildRegex(String patternStr) {
        StringBuilder regex = new StringBuilder();
        
        // Loop over the pattern string
        for (int i = 0; i < patternStr.length(); i++) {
            char c = patternStr.charAt(i);
            switch (c) {
                case '+':
                    regex.append("[a-zA-Z]");
                    break;
                case '$':
                    regex.append("[1-9]");
                    break;
                case '*':
                    // Check for the presence of {N}
                    if (i + 1 < patternStr.length() && patternStr.charAt(i + 1) == '{') {
                        // Find the closing brace
                        int closingBraceIndex = patternStr.indexOf('}', i);
                        if (closingBraceIndex != -1) {
                            // Extract the number N and repeat the previous character N times in the regex
                            String num = patternStr.substring(i + 2, closingBraceIndex);
                            regex.append("{" + num + "}");
                            i = closingBraceIndex; // Skip ahead in the pattern string
                        }
                    } else {
                        regex.append(".{3}");
                    }
                    break;
                default:
                    regex.append(Pattern.quote(Character.toString(c)));
                    break;
            }
        }
        
        return regex.toString();
    }

    public static void main (String[] args) {

        System.out.print(StringChallenge("+++++* abcdehhhhhh"));
        System.out.print(StringChallenge("$**+*{2} 9mmmrrrkbb"));
    }
}

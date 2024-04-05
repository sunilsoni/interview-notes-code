package com.interview.notes.code.months.april24.test3;

class Main2 {
    public static String StringChallenge(String str) {
        // Split the input string into the pattern and the second string
        String[] parts = str.split("\\s+");
        String pattern = parts[0];
        String secondString = parts[1];
        
        // Iterate through the pattern and match it with the second string
        int index = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (ch == '+') {
                if (!Character.isLetter(secondString.charAt(index))) {
                    return "false";
                }
                index++;
            } else if (ch == '$') {
                if (!Character.isDigit(secondString.charAt(index)) || secondString.charAt(index) == '0') {
                    return "false";
                }
                index++;
            } else if (ch == '*') {
                char prevChar = pattern.charAt(i - 1);
                int repeatCount = 3;
                if (i < pattern.length() - 1 && pattern.charAt(i + 1) == '{') {
                    int endIndex = pattern.indexOf('}', i);
                    String countStr = pattern.substring(i + 2, endIndex);
                    repeatCount = Integer.parseInt(countStr);
                    i = endIndex; // skip the count
                }
                for (int j = 1; j <= repeatCount; j++) {
                    if (index >= secondString.length() || secondString.charAt(index) != prevChar) {
                        return "false";
                    }
                    index++;
                }
            }
        }
        
        // Check if there are remaining characters in the second string
        return index == secondString.length() ? "true" : "false";
    }

    public static void main(String[] args) {

        System.out.print(StringChallenge("+++++* abcdehhhhhh"));
        System.out.print(StringChallenge("$**+*{2} 9mmmrrrkbb"));
    }
}

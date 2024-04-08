package com.interview.notes.code.months.april24.test3;

class Main4 {
    public static String StringChallenge(String str) {
        String pattern = "";
        String input = "";

        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '+') {
                pattern += "+";
                input += str.charAt(i + 1);
                i += 2;
            } else if (str.charAt(i) == '$') {
                pattern += "$";
                input += str.charAt(i + 1);
                i += 2;
            } else if (str.charAt(i) == '*') {
                pattern += "*";
                input += str.charAt(i + 1);
                i += 2;
            } else if (str.charAt(i) == '{') {
                int j = i + 1;
                while (str.charAt(j) != '}') {
                    j++;
                }
                int count = Integer.parseInt(str.substring(i + 1, j));
                pattern += "{" + count + "}";
                String temp = "";
                for (int k = 0; k < count; k++) {
                    temp += str.charAt(j + 1);
                }
                input += temp;
                i = j + 2;
            }
        }

        return input.equals(pattern) ? "true" : "false";
    }

    public static void main(String[] args) {


        System.out.print(StringChallenge("+++++* abcdehhhhhh"));
        System.out.print(StringChallenge("$**+*{2} 9mmmrrrkbb"));
    }
}
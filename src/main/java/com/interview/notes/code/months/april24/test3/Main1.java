package com.interview.notes.code.months.april24.test3;

class Main1 {
    public static String StringChallenge(String str) {
        // code goes here
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
            } else if (str.charAt(i) == '(') {
                int j = i + 1;
                while (str.charAt(j) != ')') {
                    j++;
                }
                int count = Integer.parseInt(str.substring(i + 1, j));
                pattern += "(" + count + ")";
                input += str.substring(j + 1, j + 1 + count);
                i = j + 1 + count;
            }
        }

        return input.equals(pattern) ? "true" : "false";
    }

    public static void main(String[] args) {

        System.out.print(StringChallenge("+++++*abcdehhhhhh"));
    }
}
package com.interview.notes.code.misc.test4;

public class TestFind {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";

        boolean res = (s1 == s2);
        System.out.println(res);

        String s3 = "SournVam";
        int start = 1;
        char end = 5;
        System.out.println(start + end);
        System.out.println(s3.substring(start, end));

        //  System.out.println(find("FinDTextHere","Text"));
        /**
         * text -->FinDTextHere target:Text
         * text -->inDTextHere target:Text
         * text -->nDTextHere target:Text
         * text -->DTextHere target:Text
         * text -->TextHere target:Text
         * true
         */
    }

    public static boolean find(String text, String target) {
        System.out.println("text -->" + text + " target:" + target);
        if (text == null || target == null) {
            return false;
        }

        if (target.length() > text.length()) {
            return false;
        }

        if (text.length() == target.length()) {
            return text.equals(target);
        }

        return text.startsWith(target) || find(text.substring(1), target);
    }
}

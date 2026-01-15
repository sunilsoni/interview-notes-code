package com.interview.notes.code.year.y2026.jan.common.test1;

public class Snippet3 {

    public static void main(String[] args) {

        String message = "HELLO WORLD";
        String result = modifyString(message);

        if (result == message) {
            System.out.println("1 = " + result);
        }

        if (result.equals(message)) {
            System.out.println("2 = " + result);
        }
    }

    private static String modifyString(String str) {
        return str.toLowerCase();
    }
}

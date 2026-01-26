package com.interview.notes.code.year.y2026.jan.common.test5;

public class Snippet4 {

    public static void main(String[] args) {

        StringBuffer message = new StringBuffer("HELLO WORLD");
        StringBuffer result = modifyStringBuffer(message);

        if (message == result) {
            System.out.println("1 = " + result);
        }

        if (message.equals(result)) {
            System.out.println("2 = " + result);
        }
    }

    private static StringBuffer modifyStringBuffer(StringBuffer strBuffer) {
        return strBuffer.reverse();
    }
}

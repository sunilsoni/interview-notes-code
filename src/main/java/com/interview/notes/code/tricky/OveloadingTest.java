package com.interview.notes.code.tricky;

public class OveloadingTest {
    public static void main(String[] args) {
        todo("test");
        todo("Test");
    }

    public static void todo(String s) {

    }


    public static void todo(Object s) {

    }
}

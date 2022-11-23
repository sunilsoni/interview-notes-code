package com.interview.notes.code.misc;

public class TryCatchFinallyTest {
    public static void main(String[] args) {
        System.out.println(test());//3

        System.out.println(10 + 30 + "aaa");//3
        System.out.println("aa" + 10 + 30);//3

    }

    private static int test() {
        try {
            throw new IllegalArgumentException("1");//
            //return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}

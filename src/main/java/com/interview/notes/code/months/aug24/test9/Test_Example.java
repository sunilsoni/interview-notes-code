package com.interview.notes.code.months.aug24.test9;

public class Test_Example {

    private static int printAnumber() {

        try {

            return 10;

        } catch (Exception e) {

            return 15;

        } finally {

            return 20;

        }

    }

    public static void main(String args[]) {

        System.out.println(printAnumber());

    }
}
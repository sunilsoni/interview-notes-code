package com.interview.notes.code.months.july24.test7;

import java.io.IOException;

public class ExceptionHandlingExample {

    public static void main(String[] args) {
        ExceptionHandlingExample example = new ExceptionHandlingExample();
        try {
            example.methodA();
        } catch (Exception e) {
            System.out.println("Exception in main.");
        }
    }

    private void methodA() throws Exception {
        try {
            this.methodB();
        } catch (Exception e) {
            System.out.println("0");
        }
    }

    private void methodB() throws Exception {
        try {
            this.methodC();
        } catch (RuntimeException e) {
            System.out.println("1");
        } catch (Exception e) {
            System.out.println("2");
        } catch (Throwable e) {
            System.out.println("3");
        }
    }

    private void methodC() throws Exception {
        try {
            if (1 == 1) {
                throw new IOException();
            }
        } catch (RuntimeException e) {
            System.out.println("4");
            throw e;
        }
    }
}

package com.interview.notes.code.months.june23.test8;

class HelloWorld {

    static {
        System.out.println("Hello world");
    }

    public HelloWorld() {
        System.out.println("Hello constructor");
    }

    public static void main(String[] args) {
        HelloWorld m = new HelloWorld();
    }
}
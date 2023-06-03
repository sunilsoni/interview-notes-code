package com.interview.notes.code.test.test8;

class HelloWorld {

    public static void main(String[] args) {
        HelloWorld m = new HelloWorld();
    }
    static {
        System.out.println("Hello world");
    }

    public HelloWorld() {
        System.out.println("Hello constructor");
    }
}
package com.interview.notes.code.test.test10;

@FunctionalInterface
interface Printer {
    void print(String message);
}


public class HelloWorldLambda {
    public static void main(String[] args) {
        Printer printer = message -> System.out.println(message);


        printer.print("Hello, ");
        printer.print("World!");
    }
}



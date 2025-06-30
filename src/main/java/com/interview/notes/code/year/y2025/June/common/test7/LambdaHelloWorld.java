package com.interview.notes.code.year.y2025.June.common.test7;

@FunctionalInterface
interface SayHello {
    void greet();
}

public class LambdaHelloWorld {
    public static void main(String[] args) {
        SayHello hello = () -> System.out.println("Hello, World from Java 8 Lambda!");
        hello.greet();
    }
}

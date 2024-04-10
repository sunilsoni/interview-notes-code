package com.interview.notes.code.months.april24.test4;

public class MyClass {
    public static void main(String[] args) {
        // Implementation using a class
        Greeting helloGreeting = new HelloGreeting();
        System.out.println(helloGreeting.greet("World"));

        // Implementation using a lambda expression (Java 8+)
        Greeting lambdaGreeting = name -> "Hello, " + name + "!";
        System.out.println(lambdaGreeting.greet("Alice"));
    }
}
package com.interview.notes.code.months.year2023.july23.test4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
interface MyCustomConsumer extends Consumer<String> {
    // void consume(String value);

    @Override
    void accept(String s);
}

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using a custom functional interface with lambda expression
        MyCustomConsumer customConsumerLambda = (name) -> System.out.println("Hello, " + name);
        names.forEach(customConsumerLambda);

        // Using a custom functional interface with method reference
        MyCustomConsumer customConsumerMethodRef = CustomFunctionalInterfaceExample::printGreeting;
        names.forEach(customConsumerMethodRef);
    }

    // Method to be used with method reference
    public static void printGreeting(String name) {
        System.out.println("Greetings, " + name);
    }
}

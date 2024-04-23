package com.interview.notes.code.months.april24.test10;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();
}

public class Main {
    public static void main(String[] args) {
        // Using lambda expression to implement MyFunctionalInterface
        MyFunctionalInterface myFunctionalInterface = () -> System.out.println("Executing myMethod");

        // Calling the method using the functional interface
        myFunctionalInterface.myMethod();

        // Example of using the Stream API with a functional interface
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using forEach method with a lambda expression
        numbers.forEach(System.out::println);

        // Using Stream API and lambda expression to filter even numbers
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}

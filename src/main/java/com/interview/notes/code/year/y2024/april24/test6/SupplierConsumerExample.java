package com.interview.notes.code.year.y2024.april24.test6;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SupplierConsumerExample {
    public static void main(String[] args) {
        // Using both Supplier and Consumer in a lambda expression
        Supplier<Double> randomNumberSupplier = () -> Math.random();
        Consumer<Double> printRandomNumber = result -> System.out.println("Generated random number: " + result);

        // Using both in a single lambda expression
        printRandomNumber.accept(randomNumberSupplier.get());
    }
}

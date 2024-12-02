package com.interview.notes.code.year.y2024.april24.test7;

import java.util.function.Consumer;

public class SupplierConsumerExample {
    public static void main(String[] args) {
        // Using both Supplier and Consumer in a lambda expression
        Consumer<Double> printRandomNumber = result -> System.out.println("Generated random number: " + result);
        //  printRandomNumber.accept(() -> Math.random());
    }
}

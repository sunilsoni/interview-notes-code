package com.interview.notes.code.months.march24.test7;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MathOperations {
    static int number1 = 10;
    static int number2 = getValue();

    static int getValue() {
        return number1;
    }

    static int doSum() {
        return number1 + number2;
    }

    static int doMinus() {
        return number1 - number2;
    }

    public static void main(String[] args) {
        System.out.println("Number1 + Number2 = " + doSum());
        System.out.println("Number1 - Number2 = " + doMinus());

        Supplier<String> i = () -> "Car";
        Consumer<String> c = x -> System.out.print(x.toLowerCase());
        Consumer<String> d = x -> System.out.print(x.toUpperCase());
        c.andThen(d).accept(i.get());
        System.out.println();
    }
}

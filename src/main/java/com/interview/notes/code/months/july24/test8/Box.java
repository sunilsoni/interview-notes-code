package com.interview.notes.code.months.july24.test8;

import java.util.Arrays;

public class Box<T> {
    // T stands for "Type"
    private T t;

    public static void main(String[] args) {
        // Create instance of Box for Integers
        Box<Integer> integerBox = new Box<>();
        integerBox.set(123);
        System.out.println("Integer Value: " + integerBox.get());

        // Create instance of Box for Strings
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generics");
        System.out.println("String Value: " + stringBox.get());


        // Usage in a main method or another class
        String[] names = {"Alice", "Bob"};
        Util.swap(names, 0, 1);
        System.out.println("Swapped names: " + Arrays.toString(names));

        Integer[] numbers = {1, 2, 3};
        Util.swap(numbers, 0, 2);
        System.out.println("Swapped numbers: " + Arrays.toString(numbers));

    }

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}



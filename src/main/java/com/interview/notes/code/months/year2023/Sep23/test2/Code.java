package com.interview.notes.code.months.year2023.Sep23.test2;

/**
 * The given code defines a class named `Code` with a `main` method. Inside the `main` method, it calls the `method` by passing a `null` argument.
 * <p>
 * There are two overloaded versions of the `method`:
 * 1. One that accepts an `Object` parameter.
 * 2. Another that accepts a `String` parameter.
 * <p>
 * When you call the method with a `null` argument, Java tries to find the most specific method that can handle that argument. In this case, both the `Object` and `String` versions could technically accept `null`, but Java prefers the `String` version because it's more specific than the `Object` version.
 * <p>
 * Thus, the output will be:
 * ```
 * String method
 * ```
 * <p>
 * Explanation: The JVM chooses the `method(String s)` version when a `null` argument is passed because the `String` type is more specific than the `Object` type.
 */
public class Code {
    public static void main(String[] args) {


        try {
            int i = 10 / 0;
            System.out.println("in try block");
        } catch (Exception ex) {
            System.out.println("in exception block");
        } finally {
            System.out.println("in finally block");
        }

        method(null);
    }

    public static void method(Object o) {
        System.out.println("Object method");
    }

    public static void method(String s) {
        System.out.println("String method");
    }
}
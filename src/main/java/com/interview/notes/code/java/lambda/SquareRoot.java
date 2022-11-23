package com.interview.notes.code.java.lambda;

@FunctionalInterface
public interface SquareRoot {
    static void findSquareRoot1(int n) {

    }

    abstract double findSquareRoot(int n);

    default void findSquareRoot2(int n) {

    }
}
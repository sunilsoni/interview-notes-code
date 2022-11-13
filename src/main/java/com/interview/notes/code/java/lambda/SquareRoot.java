package com.interview.notes.code.java.lambda;

@FunctionalInterface
public interface SquareRoot {
    abstract double findSquareRoot(int n);
    static void findSquareRoot1(int n){

    }

    default void findSquareRoot2(int n){

    }
}
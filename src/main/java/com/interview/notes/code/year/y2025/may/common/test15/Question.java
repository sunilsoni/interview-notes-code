package com.interview.notes.code.year.y2025.may.common.test15;

public class Question<T extends Number> {
    T t;

    public static void main(String[] args) {
        // Line 1
        Question<Integer> q = new Question<>();

        // Line 2 - causes compilation error
       // q.t = new Float(1); // Error: incompatible types

        System.out.println(q.t);
    }
}

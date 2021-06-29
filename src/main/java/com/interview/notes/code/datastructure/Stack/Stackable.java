package com.interview.notes.code.datastructure.Stack;

interface Stackable<T> {
    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();

    int size();

    int search(Object o);
}
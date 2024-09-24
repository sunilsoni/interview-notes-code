package com.interview.notes.code.months.sept24.test9;

public interface CustomImmutableList<T> {
    T get(int index);
    int size();
    CustomImmutableList<T> add(T element);
}

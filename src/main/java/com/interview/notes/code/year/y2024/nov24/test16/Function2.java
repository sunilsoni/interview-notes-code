package com.interview.notes.code.year.y2024.nov24.test16;

@FunctionalInterface
public interface Function2<T, U, V> {
    V apply(T t, U u);

    default void count() {
        // increment counter
    }
}

package com.interview.notes.code.months.nov24.test16;

@FunctionalInterface
public interface Function2<T, U, V> {
    public V apply(T t, U u);

    default void count() {
        // increment counter
    }
}

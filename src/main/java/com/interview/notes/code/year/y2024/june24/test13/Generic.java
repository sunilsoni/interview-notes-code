package com.interview.notes.code.year.y2024.june24.test13;

public class Generic<T> {
    private final T value;

    public Generic(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
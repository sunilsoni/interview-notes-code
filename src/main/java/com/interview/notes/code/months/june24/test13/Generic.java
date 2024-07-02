package com.interview.notes.code.months.june24.test13;

public class Generic<T> {
    private T value;

    public Generic(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
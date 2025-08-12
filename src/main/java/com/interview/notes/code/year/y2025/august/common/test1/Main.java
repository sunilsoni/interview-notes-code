package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.ArrayList;

class Generic<T> {
    private T value;

    public Generic(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Generic> g = new ArrayList<>();

        Generic<?> g1 = new Generic<>(10);
        Generic<?> g2 = new Generic<>("Hello");

        g.add(g1);
        g.add(g2);

        // These lines will cause compile-time error
        int i = (int) g.get(0).getValue();   // Error: incompatible types
        String s = g.get(1).getValue().toString(); // Error: incompatible types

        System.out.println(s);
        System.out.println(i);
    }
}

package com.interview.notes.code.year.y2024.aug24.test11;

import java.util.stream.Stream;

class C {
    public void m1() {
        throw new IllegalArgumentException();
    }
}

public class Test {
    public static void main(String[] args) {
        Stream<C> s = Stream.of(new C(), new C());
        s.forEach(x -> {
            try {
                x.m1();
            } catch (Exception e) {
                System.out.println("Caught it");
            } finally {
                System.out.println("In finally clause");
            }
        });
    }
}

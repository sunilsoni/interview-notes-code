package com.interview.notes.code.year.y2025.feb.Walmart.test3;

import java.util.ArrayList;
import java.util.stream.IntStream;

@FunctionalInterface
interface FuncInterface1 {
    Iterable abstractFun(Iterable<String> ar);
}

@FunctionalInterface
interface FuncInterface2 extends FuncInterface1 {
    Iterable<String> abstractFun(Iterable ar);
}

class Test {
    public static void main(String[] args) {
        FuncInterface2 f2 = ar -> ar;
        ArrayList<String> s = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> s.add(String.valueOf(i++)));
        f2.abstractFun(s).forEach(System.out::print);
    }
}

package com.interview.notes.code.months.march24.test7;// Option Text:
// A. Compilation fails due to an error on line 2.
// B. Compilation fails due to an error on line 3.
// C. Compilation fails due to an error on line 7.
// D. Compilation fails due to an error on line 12.
// E. Compilation fails due to an error on line 13.
// F. Compilation fails due to an error on line 16.
// G. Compilation fails due to an error on line 20.
// H. Compilation fails due to an error on line 24.
// I. Compilation fails due to an error on line 28.
// J. Compilation fails due to an error on line 32.
// K. Compilation fails due to an error on line 36.
// L. Compilation fails due to an error on line 40.
// M. Compilation fails due to an error on line 44.
// N. Compilation fails due to an error on line 48.
// O. Compilation fails due to an error on line 52.
// P. Compilation fails due to an error on line 56.
// Q. Compilation fails due to an error on line 60.
// R. Compilation succeeds, but the output is false, false, false, false.
// S. Compilation succeeds, but the output is false, false, true, false.
// T. Compilation succeeds, but the output is false, false, false, true.
// U. Compilation succeeds, but the output is false, false, true, true.
// V. Compilation succeeds, but the output is true, true, false, false.
// W. Compilation succeeds, but the output is true, true, true, false.
// X. Compilation succeeds, but the output is true, true, false, true.
// Y. Compilation succeeds, but the output is true, true, true, true.

import java.util.function.Predicate;

class MyPredicate<T> implements Predicate<T> {
    Predicate<T> local;

    MyPredicate(Predicate<T> t) {
        local = t;
    }

    @Override
    public boolean test(T t) {
        return local.test(t);
    }
}

public class Predicator {
    static boolean testit(Object s, Predicate<Object> p) {
        return p.test(s);
    }

    public static void main(String[] args) {
        final String s1 = "HI";
        final Integer i1 = 3;
        final int x = 1;
        MyPredicate<Object>[] p = new MyPredicate[4];
        p[0] = new MyPredicate<>(e -> e.equals(s1));
        p[1] = new MyPredicate<>(e -> {
            if (x == 1) return e.equals(s1);
            return e.equals(s1);
        });
        p[2] = new MyPredicate<>(e -> {
            if (x != 1) return e.equals(11);
            return !e.equals(s1);
        });
        p[3] = new MyPredicate<>((Object e) -> {
            if (x != 1) return e.equals(i1);
            return e.equals(s1);
        });
        for (MyPredicate<Object> pp : p) {
            System.out.println(testit(s1, pp));
        }
    }
}

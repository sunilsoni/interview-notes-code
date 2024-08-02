package com.interview.notes.code.months.july24.test18;

import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        Function<char[], String> obj = String::new; // Line 5
        String s = obj.apply(new char[]{'j', 'a', 'v', 'a'}); // Line 6
        System.out.println(s);
    }
}

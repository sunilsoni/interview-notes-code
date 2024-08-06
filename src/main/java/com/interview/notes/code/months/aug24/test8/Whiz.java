package com.interview.notes.code.months.aug24.test8;

import java.util.stream.Stream;

public class Whiz {
    public static void main(String[] args) {
        Stream stream = Stream.of(10, 20, "30");
        boolean out = stream.allMatch(in -> in instanceof Number);
        System.out.println(out);
    }
}

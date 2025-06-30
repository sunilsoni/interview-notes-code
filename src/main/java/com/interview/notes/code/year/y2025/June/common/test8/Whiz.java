package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.stream.Stream;

public class Whiz {
    public static void main(String[] args) {
        Stream<Object> stream = Stream.of(10, 20, "30");
        boolean out = stream.allMatch(in -> in instanceof Number);
        System.out.println("Output: " + out); // Expected: false
    }
}

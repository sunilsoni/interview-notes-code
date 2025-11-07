package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.stream.IntStream;

public class LambdaLoop {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10)
                 .forEach(i -> System.out.println(i));
    }
}

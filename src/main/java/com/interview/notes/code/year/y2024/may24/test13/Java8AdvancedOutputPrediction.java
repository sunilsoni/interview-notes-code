package com.interview.notes.code.year.y2024.may24.test13;

import java.util.stream.IntStream;

public class Java8AdvancedOutputPrediction {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 2)
                .limit(5)
                .mapToObj(String::valueOf)
                .forEach(System.out::println);
    }
}

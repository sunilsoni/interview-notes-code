package com.interview.notes.code.months.nov24.test16;

import java.util.Arrays;

public class TestStream {
    public static void main(String[] args) {
        System.out.println("Stream without terminal operation");

        Arrays.stream(new int[]{1, 2, 3}).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        });

        System.out.println("Stream with terminal operation");
        Arrays.stream(new int[]{1, 2, 3}).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        }).sum();
    }

}

package com.interview.notes.code.misc.test2;

import java.util.Arrays;


public interface Test {

    public static void main(String[] args) {
        //Set
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

    default int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }

        // return 4;
    }

}

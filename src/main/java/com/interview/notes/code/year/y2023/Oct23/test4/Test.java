package com.interview.notes.code.year.y2023.Oct23.test4;

public class Test {
    public static void main(String[] args) {
        int[] table = {
                1,
                2,
                3,
                4,
                5
        };
        table[1] = (table[2 * 1] == 2 - args.length) ? table[3] : 99;
        System.out.println(table[1]);
    }
}


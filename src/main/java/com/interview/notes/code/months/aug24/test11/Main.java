package com.interview.notes.code.months.aug24.test11;

public class Main {
    public static void main(String[] args) {
        int value = 25;
        Integer before = value;
        Long after = ++before == 26 ? 5L : new Long(10);
        System.out.println(after.intValue() - before.intValue());
    }
}

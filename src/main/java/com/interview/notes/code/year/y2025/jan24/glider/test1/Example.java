package com.interview.notes.code.year.y2025.jan24.glider.test1;

public class Example {
    int i[] = {0};

    public static void main(String args[]) {
        int i[] = {1};
        change_i(i);
        System.out.println(i[0]);
    }

    public static void change_i(int i[]) {
        i[0] = 2;
        i[0] *= 2;
    }
}
